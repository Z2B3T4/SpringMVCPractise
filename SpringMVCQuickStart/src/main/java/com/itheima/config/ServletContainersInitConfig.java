package com.itheima.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;
import java.nio.charset.CharacterCodingException;

/**  第四步：定义一个servlet容器启动的配置类，在里面加载Spring配置
 *            在定义时，只需要继承 AbstractDispatcherServletInitializer 接口
 *            */
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 这个是简化开发，下面的是根
     * */

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

   /**
    * 处理请求过来的数据中文乱码问题，重写过滤器方法
    * 这个是处理post请求的乱码，get的乱码还未解决
    * */

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }


    //@Override
    //protected WebApplicationContext createServletApplicationContext() {
    //    /**
    //     * 这个重写的方法意思就是告诉spring我使用了SpringMVC来开发，要加载我的配置文件
    //     * */
    //    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    //    ctx.register(SpringMvcConfig.class);
    //    return ctx;
    //}
    //
    //@Override
    //protected String[] getServletMappings() {
    //    /**
    //     * 这个方法意思是，告诉spring所有请求都要被SpringMVC拦截并走MVC的运行路线
    //     *      不要再走原来的servlet路线啦
    //     * */
    //    return new String[]{"/"};
    //}
    //
    //@Override
    //protected WebApplicationContext createRootApplicationContext() {
    //    /**
    //     * 这个是当设置Spring运行的方法
    //     * */
    //    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    //    ctx.register(SpringConfig.class);
    //    return ctx;
    //
    //}
}
