package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 第一步：导包
 * 第二步： 2.1. 在使用MVC时，必须使用@Controller定义bean
 *
 * */
@Controller
/**
 * 这个写在上面的@RequestMapping("/user")是给本类中所有访问路径加一个前缀，避免自己写的路径与别人的重复
 * */
@RequestMapping("/user")
public class UserController {
    /**  2.2 设置请求路径    */
    @RequestMapping("/save")
    /**  2.3 设置返回值类型，下面这句话的意思就是将我return的东西全部整体返回到浏览器中*/
    @ResponseBody
    public String save(String name,int age){
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("user save");
        return "{'module':'springmvc'}";
    }

    //普通参数：请求参数名与形参名不同时，使用@RequestParam注解关联请求参数名称与形参名称之间的关系
    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String userName , int age){
        System.out.println("普通参数传递 userName ==> "+userName);
        System.out.println("普通参数传递 age ==> "+age);
        return "{'module':'common param different name'}";
    }
    //POJO参数：请求参数与形参对象中的属性对应即可完成参数传递
    /**
     * 这个传进来的参数只要和User中的私有成员名称一样，就可以自动赋值
     * */
    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println("pojo参数传递 user ==> "+user);
        return "{'module':'pojo param'}";
    }

    //嵌套POJO参数：嵌套属性按照层次结构设定名称即可完成参数传递
    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user){
        System.out.println("pojo嵌套pojo参数传递 user ==> "+user);
        return "{'module':'pojo contain pojo param'}";
    }

    //数组参数：同名请求参数可以直接映射到对应名称的形参数组对象中
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println("数组参数传递 likes ==> "+ Arrays.toString(likes));
        return "{'module':'array param'}";
    }

    //集合参数：同名请求参数可以使用@RequestParam注解映射到对应名称的集合对象中作为数据
    @RequestMapping("/listParam")
    @ResponseBody
    /**
     * @RequestParam加上它的目的是：
     *      因为spring会默认吧这个集合和前面的user对象等当做一类处理，都会先出找他的构造方法
     *      但是明显集合不是类对象，所以我们加上@RequestParam，就表明把他当做类似于数组一样处理
     * */
    public String listParam(@RequestParam List<String> likes){
        System.out.println("集合参数传递 likes ==> "+ likes);
        return "{'module':'list param'}";
    }


    //集合参数：json格式
    /** 1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
        2.使用@RequestBody注解将外部传递的json数组数据映射到形参的集合对象中作为数据
        因为传过来的参数位置是在body中没因为是post请求*/
    /**
     * 完成接受json数据有四步：
     *      1.导入依赖坐标
     *      2.在MVC配置类中加入@EnableWebMvc
     *      3.请求的数据格式改为json，postman中body--raw--json
     *      4.在参数位置加上@RequestBody
     * */
    @RequestMapping("/listParamForJson")
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> likes){
        System.out.println("list common(json)参数传递 list ==> "+likes);
        return "{'module':'list common for json param'}";
    }

    //POJO参数：json格式
    //1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
    //2.使用@RequestBody注解将外部传递的json数据映射到形参的实体类对象中，要求属性名称一一对应
    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println("pojo(json)参数传递 user ==> "+user);
        return "{'module':'pojo for json param'}";
    }

    //集合参数：json格式
    //1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
    //2.使用@RequestBody注解将外部传递的json数组数据映射到形参的保存实体类对象的集合对象中，要求属性名称一一对应
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list){
        System.out.println("list pojo(json)参数传递 list ==> "+list);
        return "{'module':'list pojo for json param'}";
    }

    //日期参数
    //使用@DateTimeFormat注解设置日期类型数据格式，默认格式yyyy/MM/dd
    /**
     * @DateTimeFormat使用它指定时间格式
     * */
    @RequestMapping("/dataParam")
    @ResponseBody
    public String dataParam(Date date,
                            @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                            @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date date2){
        System.out.println("参数传递 date ==> "+date);
        System.out.println("参数传递 date1(yyyy-MM-dd) ==> "+date1);
        System.out.println("参数传递 date2(yyyy/MM/dd HH:mm:ss) ==> "+date2);
        return "{'module':'data param'}";
    }
}
