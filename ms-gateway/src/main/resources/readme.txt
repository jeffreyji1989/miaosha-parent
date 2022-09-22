# https://blog.csdn.net/qq_38380025/article/details/102968559 从这里学习
server:
  port: 6696

logging:
  level:
    org.springframework.cloud.gateway: debug


spring:
  application:
    name: nuwa-gateway-service
  cloud:
    gateway:
      routes:
        # After Route Predicate Factory使用的是时间作为匹配规则，只要当前时间大于设定时间，路由才会匹配请求
        - id: after_route
          uri: https://www.baidu.com
          predicates:
           - After=2020-10-19T16:58:00.789+08:00

        # Before Route Predicate Factory也是使用时间作为匹配规则，只要当前时间小于设定时间，路由才会匹配请求。
        - id: before_route
          uri: https://www.google.com
          predicates:
             - Before=2020-10-15T15:57:00.789+08:00
        # Between Route Predicate Factory也是使用两个时间作为匹配规则，只要当前时间大于第一个设定时间，并小于第二个设定时间，路由才会匹配请求
        - id: between_route
          uri: https://www.cnblogs.com/liuchuanfeng/p/8717454.html
          predicates:
             - Between=2020-10-16T15:57:00.789+08:00, 2020-10-16T16:58:00.789+08:00
        # Cookie Route Predicate Factory使用的是cookie名字和正则表达式的value作为两个输入参数，请求的cookie需要匹配cookie名和符合其中value的正则,路由匹配请求存在cookie名为cookiename，cookie内容匹配cookievalue的，将请求转发到google
        - id: cookie_route
          uri: https://test-eimp.sinoiov.com:8443/login
          predicates:
            - Cookie=mycookie, mycookie1
        # Header Route Predicate Factory，与Cookie Route Predicate Factory类似，也是两个参数，一个header的name，一个是正则匹配的value
        - id: header_route
#          uri: http://localhost:6696/gateway/index.html?param=header_router
          uri: https://www.163.com/?referFrom=
          predicates:
            - Header=Host1,localhost:\d+
        # Method Route Predicate Factory是通过HTTP的method来匹配路由。
        - id: method_route
          uri: http://192.168.188.78:8081/user/index
          predicates:
            - Method=GET
        # Method Route Predicate Factory是通过HTTP的method来匹配路由。
        # PathMatchInfo variables = exchange.getAttribute(URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        #  Map<String, String> uriVariables = variables.getUriVariables();
        #  String segment = uriVariables.get("segment");
        - id: path_route
          uri: https://www.qq.com/
          predicates:
            - Path=/login/1/
#            - Path=/foo/{segment},/bar/{segment}
        #  String segment = uriVariables.get("segment"); 需要和Path配合使用
        - id: query_route
          uri: http://192.168.188.78:8081/user/index
          filters:
            - name: Hystrix
              args:
                name: default
                fallbackUri: forward:/defaultfallback
          predicates:
            - Path=/gateway/**
#            - Query=baz1


   gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: user-router
          uri: lb://user-service
          predicates:
            - Path=/usr/**
      filters:
        - StripPrefix=1 # 表示在转发时去掉usr