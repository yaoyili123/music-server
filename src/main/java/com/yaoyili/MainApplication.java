package com.yaoyili;

import com.yaoyili.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//等价于@EnableAutoConfiguration + @ComponentScan +  @Configuration
//spring-boot Main Class
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =  SpringApplication.run(MainApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}

