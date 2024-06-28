package com.icboluo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author icboluo
 * @since 2024-06-28 上午1:23
 */
@SpringBootTest(classes = SupperApplication.class)
public abstract class BaseTest {
    /**
     * <p>
     * 伪造一个MVC环境
     * <p>
     * 伪造的MVC环境不会启动tomcat，所以测试用了启动的很快
     */
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * <p>
     * 测试代码运行前运行的代码
     * <p>
     * 在这里，我们测试前注册了 mockMvc
     * <p>
     * 我们使用的是 org.junit.jupiter.api 包底下的测试功能，所以使用的是 @BeforeEach 注解
     *
     * @see org.junit.Before 这个是旧版本的
     * @see org.junit.jupiter.api.BeforeEach
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        childBefore();
    }

    protected void childBefore() {

    }
}
