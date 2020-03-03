## Spring 学习

### 概念 
- IOC/DI(依赖注入)：控制反转或者 依赖注入，具体来讲，就是通过代码自动生成对象和初始化，而不需要通过使用 new 的方式，所需要的类（bean）都在applicationContext中进行注册。
  - IOC的作用：
    - 1.自动创建对象
    - 2.给对象进行赋值
  - applicationContext.xml中配置 bean ，这就是IOC容器，对对象进行统一管理，这样方便进行维护。
  - IOC工厂是超级工厂，支持构造任何类型

### IOC
- bean 有三种属性注入的方式，set注入，构造器注入，还有p命名空间
    - set 注入
        ```
        <bean id = "类名" class = "指明是哪个类">
            <!-- 为变量赋初值 -->
            <!-- 当变量是简单类型（基本类型+String）的时候进行赋值 -->
            <property name = "变量名" value= "变量值"></property>
            <!-- 当变量是引用类型的时候 -->
            <property name = "变量名" ref= "应用对象的类名"></property>
        </bean>
        ```
    - 构造器注入configurations
      ```
        <!--<constructor-arg name = "courseName" value = "java"></constructor-arg>-->
        <!--<constructor-arg name = "teacher" ref = "teacher"></constructor-arg>-->
      ```
    - p命名空间注入
      ```
        <!--命名空间上添加一行-->
        xmlns:p="http://www.springframework.org/schema/p"
        <!--使用的时候-->
        <bean id="course" class = "service.Cource" p:courseName="java/C++" p:teacher-ref="teacher" ></bean>
      ```
- bean 构造集合 如 List<> 、String[]、Set<>、Map<,>、Properties
  -  ```
        <!-- 标签分别使用 -->
        <list>
          <value></value>
        </list>

        <set>
          <value></value>
        </set>

        <array>
          <value></value>
        </array>

        <map>
            <entry>
                <key><value></value></key>
            </entry>
        </map>

        <props>
            <prop key="" >值</prop>
        <props>
        <!-- 这种方式可以进行特殊字符的赋值 -->
        <value><![CDATA[特殊字符]]></value>
        <!-- 或者这种 ,例子中表示< -->
        <value>&lt;</value>
     ``` 
- 基于注解的bean注入，使用`@Component` /`@Repository`/`@Controller`/`@Service`
  使用了上面的注解以后自动生成一个bean装入IOC容器；使用`@Value`可以赋初值。
- 自动装配，Autowire,自动匹配相对应的引用对象进行依赖注入。 

### AOP面向切面
- 切入点：，是个方法即在哪个方法的前或后执行 切面：即切入点和通知的组合 通知：定义的用于插入的操作
- 通知有五种：1.前置通知 2.后置通知 3.异常通知 4.最终通知（无论异常与否） 5.环绕通知 
- 将一个普通类变为通知，1.继承类（MethodBeforeAdvice） 2.实现通知 3.注解 4.配置


### 事务配置
- 事务是对数据库的一组操作，该组操作要么全部失败，要么全部成功。
- ```
    //配置事务管理器，配置事务源，开启事务功能
    <!--配置事务管理器-->
    <bean id = "txManager" class = "org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置数据库相关-->
    <bean id = "dataSource" class = "org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassLoader" value = "oracle.jdbc.OracleDriver"></property>
        <property name="url" value = "127.0.0.1:3306:本地测试"></property>
        <property name="username" value = "Alexander.D"></property>
        <property name="password" value = "123456"></property>
        <property name="maxActive" value = "10"></property>
        <property name="maxIdle" value = "6"></property>
    </bean>

    <!--增加对事务的支持-->
    <tx:annotation-driven transaction-manager="txManager"/>
  ```
### 注解
- @Configuration 相当于是xml配置文件的<beans>标签
- @Bean 相当于是xml配置文件中的<bean>标签



### 项目搭建

[web.xm配置标签解析](https://www.cnblogs.com/z--z/p/9267656.html)

1.先通过IDEA构建一个MAVEN项目。
2.添加Spring框架
3.修改web.xml，添加初始化内容和监听器，调度器，过滤器


## Web项目中使用Spring

因为Web项目和普通的程序不同没有main作为主程序接口，因为不可以通过main方法进行初始化bean。 如 ApplicationContext ct =new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
- 通过监听器监听服务器启动 如Tomcat，当启动时自动进行初始化application Context，将bean自动加载进来

- Web.xml在web项目启动时会自动执行，所以可以将spring初始化通过web.xml进行加载


## 单例模式、多例模式
- 单例模式：singleton 容器在开始构建时生成唯一实例。可以使用 `@Lazy` 注解进行延迟加载（懒加载），即在调用时对调用到的实例进行实例构建，而不会在开始时将所有的bean都都构建一遍。
- 多例模式：prototype，容器在每个http请求中分别构建一个唯一的bean实例。只有在调用该实例构建时才构造bean。

## Bean的生命周期
- 生命周期
  - 初始化
  - 创建并使用
  - 销毁

- Bean的生命周期
  - 单例模式：先进行创建，然后再进行初始化操作，最后进行销毁

### 三种实现初始化和销毁的方法（xml，注解，接口）
- xml方式：
```
//init-method 指定初始化方法 ，destroy 指定销毁方法
<bean id = "collectionType" class = "service.AllCollections" scope = "singleton" init-method = "init" destroy-method="destroy">

</bean>
```
- 注解方式
```
@Bean(value = "beanName", initMethod = "initFucntionName" ,destroyMethod = "destroyFuctionName" )

@PostConstruct (相当于是initMethod)
@PreDestroy (相当于是destroyMethod)
```
- 接口方式
```
//实现InitialzingBean和DisposableBean

//或者实现一个接口 BeanPostProcessor
但是这个接口会拦截所有的Bean，给所有的bean加上初始化和销毁方法，也可以对拦截的bean进行其他操作
```

- `@Qualifier`可以根据Bean名字来寻找进行自动装配。`@Autowire`只能在没有相同类型的Bean的时候单独使用，如果有相同类型的不同Bean则和`@Qualifier`结合使用
```
@Qualifier("BeanName")
@Autowire

@Primary//设置默认 
```
- `@Resource`也可以进行自动装配（是JSR250提供的注解，@Autowired是Spring提供的注解）
- `@Inject`也可以进行自动装配（是JSR330提供的注解）

## 对Spring底层组件开发
- 对 Aware 的子接口进行开发
```
@Component
public class newComponent implements ApplicationContextAware {
  //这个实现方法中的 applicationContext 就是容器，当启动容器的时候，会先执行这个实现类中的方法，例如这里就是进行了打印
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Context Component :"+ applicationContext);
    }
}
```
- `@Profile`指定当前运行环境。在VM options中输入-Dspring.profiles.active=环境名字来指定当前虚拟机的运行环境
```
//或者通过硬编码方式实现
AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext();

            ConfigurableEnvironment e = (ConfigurableEnvironment) ct.getEnvironment();
            e.setActiveProfiles("DEV");

            //重新注册环境，并且进行手动刷新
            ct.register(MyBeanFactory.class);
            ct.refresh();
```
- `BeanPostProcessor`接口，是一个拦截器，拦截所有的 Bean

- Spring 监听器
  - 方法一：接口实现。必须实现ApplicationListener接口的方法，且被监听的对象只能是ApplicationEven的子类或者接口
  - 方法二：@EventListener(classes = {xxxx.class})
- 事件发布

- Bean生成的流程：
声明Bean`->`BeanDefinitionRegistryPostProcessor(解析加载Bean之前的拦截器)`->`解析Bean`->`BeanFactoryPostProcessor(Bean工厂的拦截器)`->`实例化Bean`->`BeanPostProcessor(所有Bean的拦截器)`->`DI注入属性值


## SpringBoot学习
- `@SpringBootConfiguration`注解，表示这个类为配置类。
- `@EnableAutoConfiguration`开启自动配置（如webConfiig.xml/applicationContext.xml等等）

- SpringBoot中的`application.properties`是全局配置文件，可以更改默认的约定配置
- `@PropertySource(value = {"classpath:config.properties"})`该注解可以指定相对应的配置文件，而不使用默认的配置文件路径。
- `@ImportResource`可以导入自己编写的配置文件，而不使用SpringBoot默认的配置

- `@Configuration` 表示为配置类 `@Bean`用于注册bean

- 随机占位符表达式：
```
//用在配置文件中，properties或yml
${random.uuid} 随机uuid
${random.value} 随机字符串
${random.int} 随机整数
${random.long} 随机长整型数
${random.int(10)} 10以内的整数
${random.int[a,b]} 指定随机数范围
```

- `--spring.profile.active` 配置激活环境
- `--spring.config.location=D:/application.properties` 指定配置路径，包外配置的使用优先级比包内配置文件高

## 日志
- 日志等级：`trace < debug < info < warn < error < fatal < off`
- logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n
  - %d：日期时间，{yyyy-MM-dd} 表示时间格式
  - %thread：线程名
  - %-5level：显示日志级别 -5 表示从左显示5个字符宽度
  - %logger{50}：设置日志长度
  - %msg：日志消息
  - %n：换行

## 静态资源 html/js/css 加载 
- SpringBoot通过jar包形式进行静态资源加载
