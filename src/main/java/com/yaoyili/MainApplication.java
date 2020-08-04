package com.yaoyili;

import com.yaoyili.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//等价于@EnableAutoConfiguration + @ComponentScan +  @Configuration
//spring-boot Main Class
@SpringBootApplication
@EnableTransactionManagement
public class MainApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =  SpringApplication.run(MainApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}

