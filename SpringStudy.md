## Spring 学习

### 概念 
- IOC/DI(依赖注入)：控制反转或者 依赖注入，具体来讲，就是通过代码自动生成对象和初始化，而不需要通过使用 new 的方式，所需要的类（bean）都在applicationContext中进行注册。
  - IOC的作用：
    - 1.自动创建对象
    - 2.给对象进行赋值
  - applicationContext.xml中配置 bean ，这就是IOC容器，对对象进行统一管理，这样方便进行维护。
  - IOC工厂是超级工厂，支持构造任何类型

### bean的语法
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
    - 构造器注入
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

        
     ``` 


### 项目搭建

[web.xm配置标签解析](https://www.cnblogs.com/z--z/p/9267656.html)

1.先通过IDEA构建一个MAVEN项目。
2.添加Spring框架
3.修改web.xml，添加初始化内容和监听器，调度器，过滤器


