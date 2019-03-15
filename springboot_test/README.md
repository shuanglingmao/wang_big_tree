# 基于Spring Aop  注解开发

## 1.介绍
com.msl.aop.TestAcpect 有具体用法

## 2.api
   AspectJ使用org.aspectj.lang.JoinPoint接口表示目标类连接点对象，
   如果是环绕增强时，使用org.aspectj.lang.ProceedingJoinPoint表示连接点对象，该类是JoinPoint的子接口。
   任何一个增强方法都可以通过将第一个入参声明为JoinPoint访问到连接点上下文的信息。我们先来了解一下这两个接口的主要方法： 
   1)JoinPoint 
    java.lang.Object[] **getArgs**()：获取连接点方法运行时的入参列表； 
    Signature **getSignature**() ：获取连接点的方法签名对象； 
    java.lang.Object **getTarget**() ：获取连接点所在的目标对象； 
    java.lang.Object **getThis**() ：获取代理对象本身； 
   2)ProceedingJoinPoint 
   ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法： 
    java.lang.Object **proceed**() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法； 
    java.lang.Object **proceed**(java.lang.Object[] args) throws java.lang.Throwable：通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。

### 切入点表达式
  待完善
    @within(com.cxh.study.aop.controller.UserAccessAnnotation) 
    表示拦截含有com.cxh.study.aop.controller.UserAccessAnnotation这个注解的类中所有方法
    @annotation(com.cxh.study.aop.controller.UserAccessAnnotation) 
    表示拦截含有这个注解的方法
  

## 3.目录结构
```
.
├── README.md
├── pom.xml
├── src
│   ├── main
│   │   ├── filters
│   │   │   ├── development.properties                      开发环境profile,maven默认激活此profile
│   │   │   ├── preProduct.properties                       预生产环境profile
│   │   │   ├── product.properties                          生产环境profile
│   │   │   ├── test.properties                             测试1环境profile
│   │   │   └── test2.properties                            测试2环境profile
│   │   ├── java                                            存放业务java代码，结构为com.ycc.模块.[子模块].[controller..]
│   │   │   └── com
│   │   │       └── ycc
│   │   │           └── template
│   │   ├── resources                                       资源文件夹
│   │   │   ├── applicationContext.xml                      spring配置文件
│   │   │   ├── applicationContextMvc.xml                   springMVC配置文件
│   │   │   ├── com                                         ibatis配置文件
│   │   │   │   └── ycc
│   │   │   │       └── template
│   │   │   │           └── sql
│   │   │   ├── conf_center.properties                      配置中心配置文件ycccfcenter[test].10101111.com
│   │   │   ├── dataAccessContext-ibatis-ycctemplate.xml    DAO设置，自动加载sddl数据源，自动扫描*_sql.xml文件
│   │   │   ├── ibatisConfigPath.properties                 自动扫描*_sql.xml文件的路径配置文件
│   │   │   ├── log4j.properties                            日志配置文件
│   │   │   ├── nettyConfig.properties                      TCP方式远程调用配置文件
│   │   │   ├── redis.properties                            缓存配置文件
│   │   │   ├── registerCenter.properties                   注册中心配置文件，指定注册中心地
│   │   │   ├── startInit.properties                        项目启动类配置，StartInitListener装载启动
│   │   │   ├── switch.properties                           DAO开关配置文件
│   │   │   ├── threadPoolConfig.properties                 线程池配置文件
│   │   │   └── zkConfig.properties                         zk地址配置文件
│   │   └── webapp
│   │       ├── WEB-INF
│   │       │   └── web.xml                                 项目启动文件
│   │       ├── healthCheck.jsp                             健康检查文件
│   │       └── sys
│   │           └── errorpage.jsp                           为了兼容架构报错，将JSON输出在此页面上
│   └── test                                                测试包

```

## 4.开发指南  
    

## 5.技术规范
请参照技术规范文档完成研发工作  
- [基础技术规范](http://wiki.10101111.com/pages/viewpage.action?pageId=169921395&moved=true)  
- [内网服务调用技术规范](http://wiki.10101111.com/pages/viewpage.action?pageId=169919804)  
- [数据库DDL规范](http://wiki.10101111.com/pages/viewpage.action?pageId=169919822)  
- [fe-os数据交互规范](http://wiki.10101111.com/pages/viewpage.action?pageId=169919820)  