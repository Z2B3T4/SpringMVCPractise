package com.itheima.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**  第三步：配置SpringMVC的配置文件，或者叫Spring的配置文件也可以  */
@Configuration
@ComponentScan("com.itheima.controller")
/**
 * 下面这个标签就是可以开启将请求的JSON数据转成想要的数据
 * 当然还有其他功能
 * */
@EnableWebMvc
public class SpringMvcConfig {

}
