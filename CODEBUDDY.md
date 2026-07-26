# CODEBUDDY.md This file provides guidance to CodeBuddy when working with code in this repository.

## Build & Common Commands

- **Build the whole reactor**
  `mvn clean install` — builds all 14 modules. Individual module failures do NOT block unrelated services (each service only links its own deps), so a failing module won't stop other services from starting. Run from repo root.

- **Build a single module (with deps)**
  `mvn -pl <module> -am clean install` — e.g. `mvn -pl icboluo-note -am install`. `-am` also builds the upstream internal modules the target depends on (common-* etc.).

- **Run all tests**
  `mvn test` (root) or `mvn -pl <module> -am test`. Tests use JUnit 5 (Jupiter) via `spring-boot-starter-test` — do NOT assume JUnit 4 despite the `junit.version` property in the root pom.

- **Run a single test class/method**
  `mvn -pl <module> -am test -Dtest=ClassName` or `-Dtest=ClassName#methodName`. Surefire reruns only the matched test.

- **Run a Spring Boot service**
  `mvn -pl <module> spring-boot:run` (no `-am` needed if deps already installed), or `java -jar <module>/target/<module>-0.0.1-SNAPSHOT.jar`. Each `*Application` main class is independently runnable.

- **Run a service with no 3rd-party services**
  Use the `application-simple.yml` profile: `mvn -pl <module> spring-boot:run -Dspring-boot.run.profiles=simple`. This starts the service without Nacos/Mysql/Redis (functionality is reduced). Profiles also exist as `application-test.yml` and the default `application.yml`.

## Architecture

This is a **multi-module Maven reactor** (`packaging=pom`) of independent Spring Boot micro-services plus shared libraries and learning-note modules. The root `pom.xml` only does version management (`<dependencyManagement>`); child modules must explicitly declare the dependencies they use. Spring Boot `4.1.0`, Spring Cloud `2025.1.x`, Alibaba Cloud `2025.1.0.0`, **JDK 21**, Lombok.

**Module categories**

- **`icboluo-common/*`** — shared libraries, the foundation everything else depends on. Split deliberately by dependency weight:
  - `icboluo-base`: public exceptions + unified return-value types + low-dependency utility classes (e.g. `HttpUtil`, `HttpConstant`). Safe to import anywhere.
  - `icboluo-mapper`: SQL/Redis/Mybatis-Plus utility classes.
  - `icboluo-supper`: more complex utilities that pull in external packages (interceptors, AOP aspects, validation, Excel, serializers).
  - `icboluo-all`: aggregator that bundles the above for convenience.
- **Business/learning services** (each a runnable Boot app): `icboluo-fund`, `icboluo-game`, `icboluo-note`, `icboluo-sql`, `icboluo-stock`, `icboluo-user`, `icboluo-web`, `icboluo-gateway`, `icboluo-nacos-config`, `icboluo-se`, `icboluo-framework`, `icboluo-algorithm`, `icboluo-mvc`.

**Cross-cutting web concerns** live in `common-*` and `icboluo-web`: `HttpFilter` (filter), `WebInterceptor` (interceptor), `HttpAspect` (logging AOP), `ResponseResultHandler` (unified return wrapping), and `GlobalControllerExceptionHandler` (global exception → unified response). New controllers automatically get the unified `R`/`Response` envelope.

**Nacos config bootstrap (non-obvious):** Most Boot entry classes call `HttpUtil.nacosYml()` in `main()` *before* `SpringApplication.run(...)`. That method hits a custom Nacos endpoint (`HttpConstant.NACOS_SERVICE + "/config/yml"`); if it returns `"simple"`/`"test"` (or the call fails) it sets `spring.profiles.active=test` as a system property. So the active profile is resolved at runtime from Nacos, and local `application.yml` is the fallback. When Nacos is down, expect the `test` profile to be selected.

**Service discovery / inter-service calls:** Nacos is the registry + config center. Feign clients (`@EnableFeignClients`, see `icboluo-note/.../feign/UserFeign.java`) and `RestTemplate` are the two documented call patterns; Feign interfaces must use MVC annotations. `icboluo-user` exists purely as a callable API provider for other services.

**`icboluo-mvc` is special:** it is NOT a Spring Boot app — it's raw Tomcat Servlet MVC (servlets, filters, cookies, request/response, jedis). Do not apply Boot conventions or `spring-boot:run` to it.

**`icboluo-gateway`** embeds Redis (`embedded-redis`) so Redis starts automatically with the service.

**`icboluo-algorithm`** is a learning notebook (data structures, classic algorithms, LeetCode solutions up to #2600), largely standalone Java with `.ts`/`.py` extras — not part of the micro-service graph.

**Naming conventions:** `spring.application.name` == `artifactId`; the Boot starter class drops the `icboluo` prefix and adds `Application` (e.g. `icboluo-note` → `NoteApplication`); each module's base package keeps a module prefix (`com.icboluo.base`, `com.icboluo.note`, …) for clarity in the multi-module layout. File/folder prefixes like `a_`/`z_` are intentional sort-order hints.

**Lombok:** configured via root `lombok.config` — `@EqualsAndHashCode`/`@ToString` use `callSuper=CALL` (include parent fields). Lombok is source-level (generates code at compile time, discarded at `.class`). Note `@Builder` removes the no-arg constructor.

**Tests:** JUnit 5 (Jupiter). `icboluo-common/icboluo-supper/.../BaseTest` is an abstract `@SpringBootTest` base that builds a `MockMvc` (no Tomcat) — extend it for controller tests; helpers like `executorGetResponseBody` parse the unified response envelope.

**MyBatis-Plus 配置要点（易踩坑）:** `icboluo-common/icboluo-mapper` 中的 `MybatisPlusCompatConfig` 自定义了 `sqlSessionFactory` Bean，会触发 `MybatisPlusAutoConfiguration` 的 `@ConditionalOnMissingBean` 回退，导致 `MybatisPlusProperties` Bean 不被自动注册——表现为启动报 `required a bean of type 'MybatisPlusProperties' that could not be found`。因此该类必须标注 `@EnableConfigurationProperties(MybatisPlusProperties.class)`。各服务的 YAML 配置前缀必须用 `mybatis-plus:`（**不是** `mybatis:`），否则配置项不会被读取；`mapper-locations` 应写为 `classpath*:mapper/**/*.xml`（注意末尾不要有多余的 `.`）。YAML 不允许出现重复的顶级 key：若文件中已存在 `mybatis-plus` 块，需把 `mybatis` 块的配置**合并**进去，而不是再新增一个块（否则报 `Key 'mybatis-plus' is duplicated`）。

**本地 SQLite 数据库:** `icboluo-stock` 等模块在 `simple` 模式下使用 SQLite，库文件位于 `document/sql/document.db`。表名默认由 MyBatis-Plus 将实体类名驼峰转下划线（如 `StockDaily` → `stock_daily`）。需要建表时 DDL 应使用 SQLite 语法（自增用 `INTEGER PRIMARY KEY AUTOINCREMENT`，布尔/日期通常用 `INTEGER`/`TEXT`）。跨库（MySQL ↔ SQLite）数据迁移可用纯 JDBC 程序完成，注意需显式 `Class.forName` 加载 `com.mysql.cj.jdbc.Driver` 与 `org.sqlite.JDBC` 两个驱动，且 sqlite-jdbc 运行时还依赖 `slf4j-api`。所有写入操作建议采用"先清空目标再插入"以保证可重复执行。
