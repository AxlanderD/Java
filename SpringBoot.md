## 重定向
- ```
  //主要配置了TomcatServletWebServerFactory，然后添加一个tomcat的监听器监听8080端口，并且将请求重定向到8081端口

  @Configuration
  public class TomcatCOnfig{
      @Bean
      public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
          TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
              @Override 
              protect void postProcessContext(Context context){
                  SecurityConstaint constaint = new SecurityConstaint();
                  constaint.setUserConstraint("CONFIDENTIAL");
                  SecurityCollection collection = new SecurityCollection();
                  collection.addPattern("/*");
                  constaint.addCollection(collection);
                  context.addConstraint(constraint);
              }
          };

          factory.addAdditionalTomcatConnectors(createTomcatConnnector());
      }

      private Connector createTomcatConnector(){
          Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
          connector.setScheme("http");
          connector.setPort(8080);
          connector.setSecure(false);
          connector.setRedirectPort(8081);
          return connector;
      }
  }

  ```

  ### 自动化配置
  - 重点在 WebMvcAutoConfiguration类
  
