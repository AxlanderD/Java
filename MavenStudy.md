
 * `@Author: alexander.DD4 `
 * `@Date: 2020-01-07 16:28:03 ` 
 * `@Last Modified by:   alexander.DD4 `
 * `@Last Modified time: 2020-01-07 16:28:03 `
 <br>
 ----

# Maven

### 例行简介
- Maven 的作用 : 用于管理第三方的jar包( jar包就是别人已经编写好的class文件，里面有各种类文件[class]，接口文件[interface]等等，毕竟Java说到底还是面向对象的语言 )，Maven就是针对这一点，对第三方类库进行管理的工具。
- 功能: 告知 Maven 我们需要的jar包，Maven会自动下载相关的所有jar 

- 使用Maven搭建项目的时候对于项目目录结构有所要求：
```
project|-src|-main|-java文件(源代码)
       |    |     |-resource文件(资源文件)
       |    |
       |    |-test(测试)|-java(单元测试)
       |                |
       |                |-resource(测试资源文件)
       |
       |
       |-pom(Maven配置文件)

```

- 常用命令 : 
  - mvn compile : 编译源代码，编译完成后的class放在target文件夹下
  - mvn test-compile 编译测试用例
  - mvn test : 执行测试用例
  - mvn package : 打包成jar包
  - mvn clean : 清除target文件
  - mvn install : 安装，将当前项目放到Maven的本地仓库中

- Maven 寻找一个jar包需要三个条件
  - groupId : jar包的项目名(例：com.dwj.web)
  - artifactId : jar包的模块名
  - version : jar包的版本号

- 聚合，指的是同时运行多个项目
```
<modules>
    <module>web-connection-pool</module>
    <module>web-java-crawler</module>
</modules>

```
- 继承另一个项目的配置:
```
父pom ：
<dependencyManagement>
    <dependencies>
          <dependency>
            <groupId>cn.missbe.web.search</groupId>
            <artifactId>resource-search</artifactId>
            <packaging>pom</packaging>
            <version>1.0-SNAPSHOT</version>
          </dependency> 
    </dependencies>
</dependencyManagement>

```
```
子pom ：
<parent>
        <groupId>父pom所在项目的groupId</groupId>
        <artifactId>父pom所在项目的artifactId</artifactId>
        <version>父pom所在项目的版本号</version>
</parent>
 <parent>
        <artifactId>resource-search</artifactId>
        <groupId>cn.missbe.web.search</groupId>
        <version>1.0-SNAPSHOT</version>
</parent>

```

- 标签解释：
```  
<!-- 一个项目的依赖 -->
  <dependency>
  <!-- 项目名 -->
      <groupId> </groupId>
      <!-- 项目中的具体的模块的名 -->
      <artifactId> </artifactId>
      <!-- 版本号 -->
      <version> </version>
      <!-- 依赖范围，可以选择compile(默认值)/provided/runtime/test/system -->
      <scope> </scopee>
  </dependency> 
```