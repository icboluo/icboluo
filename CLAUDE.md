# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a multi-module Spring Boot 3.5.4 (Spring Cloud 2025.0.0) monorepo for Java 21 learning and experimentation.

- **Structure**: Maven multi-module project with Spring Cloud microservices architecture
- **Java version**: 21
- **Spring Boot**: 3.5.4
- **Key technologies**: MySQL/SQLite3/Redis, MyBatis Plus, Spring Cloud Alibaba (Nacos), Spring Cloud OpenFeign
- **Modules**:
  - `icboluo-algorithm`: LeetCode solutions (completed up to problem 2600)
  - `icboluo-common`: Shared utilities (`icboluo-all`, `icboluo-base`, `icboluo-supper`, `icboluo-mapper`)
  - `icboluo-framework`: Framework learning (Spring, Thread, Redis, MyBatis notes)
  - `icboluo-fund`: Fund tracking service
  - `icboluo-game`: Games including 2D plane wars
  - `icboluo-gateway`: Gateway service with embedded Redis
  - `icboluo-nacos-config`: Nacos configuration center
  - `icboluo-note`: Notebook service with Excel-Mysql conversion
  - `icboluo-se`: Java SE concepts (annotations, design patterns, IO, HashMap source)
  - `icboluo-sql`: Database utilities (JDBC, JdbcTemplate, Druid, Redis, MQ)
  - `icboluo-user`: User service for inter-service calls
  - `icboluo-web`: Spring debugging and testing
  - `icboluo-mvc`: Pure MVC without Spring Boot (JSP, Servlet)

## Build and Run

### Build the entire project
```bash
mvn clean install
```

### Run individual services
Each service has a main Application class:
- `NoteApplication` (icboluo-note)
- `FundApplication` (icboluo-fund)
- `GameApplication` (icboluo-game)
- `GatewayApplication` (icboluo-gateway)
- `SqlApplication` (icboluo-sql)
- `UserApplication` (icboluo-user)
- `WebApplication` (icboluo-web)
- `NacosConfigApplication` (icboluo-nacos-config)

Use Spring Boot Maven plugin:
```bash
cd icboluo-note
mvn spring-boot:run
```

Or run the main class directly from IDE.

### Environment configuration
Use `application-simple.yml` for standalone mode (no Nacos required):
```bash
spring.profiles.active=simple
```

SQLite database location: `document/sql/document.db`

## Key Architectural Patterns

### Common Utilities (icboluo-common)

- **icboluo-base**: Minimal dependencies (slf4j, fastjson2, spring-context). Contains:
  - `R` class: Unified response wrapper with `correct()` and `error()` static methods
  - `ReEnum`: Response code/message enums (implements `CodeMessage`)
  - `Response`: Standard response structure
  - Utilities: `DateUtil`, `MathUtil`, `RandomUtil`, `ArrayHelper`, `LambdaUtil`

- **icboluo-supper**: Extended utilities with web dependencies. Contains:
  - `ResponseResultHandler`: Global `@ControllerAdvice` wrapping responses
  - `GlobalControllerExceptionHandler`: Exception handling with internationalization
  - `WebConfiguration`: Interceptor registration, message converters, CORS
  - Interceptors: `AuthInterceptor`, `WebInterceptor`
  - Excel utilities with EasyExcel integration

- **icboluo-mapper**: Database utilities. Contains:
  - `MyBaseMapper`: Base MyBatis mapper interface
  - Redis utilities: `RedisString`, `RedisHash`, `RedisList`, `RedisSet`, `RedisLock`
  - Base entities and services

### Response Handling

All controller responses are automatically wrapped by `ResponseResultHandler`. Controllers return domain objects directly; the advice wraps them into `Response` structure with code/message/data fields.

Error responses use `ReEnum` constants or custom `CodeMessage` implementations with i18n support via `messages.properties`.

### Database Access

- MyBatis Plus with PageHelper for pagination
- SQLite for local development, MySQL for production
- Druid connection pool
- Each service has its own `application.yml` with datasource configuration

### Inter-Service Communication

- **Feign**: Preferred method. Define client interface in caller, annotate with `@EnableFeignClients` in main class
- **RestTemplate**: Alternative method via Spring injection

### Internationalization

- Message sources configured in `WebConfiguration`
- Module-specific i18n files in `src/main/resources/i18n/messages*.properties`
- `@I18n` annotation support for field-level i18n
- `LocaleChangeInterceptor` available for runtime language switching

## Testing

Unit tests use JUnit 5. Example:
```bash
mvn test -pl icboluo-note
```

Test classes follow naming convention: `*Test.java` in `src/test/java`

## Excel Integration

EasyExcel is used throughout. Key features:
- `ExcelUtil`: Helper methods for validation and export
- `ExcelResolve`: Generic class for dynamic column selection
- `ValidHeadBodyListener`: Listener for importing with validation
- Template exports with `relativeHeadRowIndex()`
- Custom column width strategy: `LongestMatchColumnWidthStyleStrategy`

Example controller methods in `icboluo-note/src/main/java/com/icboluo/controller/ExcelController.java`

## Nacos Configuration

Bootstrap configuration in `bootstrap.yml` (only in modules requiring Nacos):
```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        enabled: false  # Set to true when Nacos is running
        register-enabled: false
```

## MVC (Non-Boot) Module

`icboluo-mvc` is a traditional Servlet/JSP application without Spring Boot:
- Uses `web.xml` for configuration
- Traditional MVC pattern with Servlets and JSPs
- Manual dependency management (no Spring Boot starters)
- Includes Jedis, Druid, JUnit examples

## Notable Implementations

- `AuthInterceptor`: Authentication interceptor (implementation in `icboluo-common/icboluo-supper`)
- `HttpFilter`: Servlet filter for request logging (in `icboluo-web`)
- `AsyncController`: `@Async` annotation usage example
- Jackson serialization customization in `JacksonConfig` (LocalDateTime handling)
- `BeanUtil`: Property copying with null-safe operations
