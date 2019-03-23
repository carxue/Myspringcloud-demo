# Myspringcloud-demo
我的springcloud学习demo

一.Springcloud Sleuth
1.1.整合zipkin实现http方式日志收集
启动项目包括：eureka-server-peer1、sleuth-trace-1、sleuth-trace-2、zipkin-server
sleuth整合zipkin实现最简单的http方式收集日志，其中zipkin-server添加的zipkin ui依赖jar包必须是runtime不然会导致有些依赖jar包没有依赖进来而导致启动时找不到对应的类文件：
<dependency>
    <groupId>io.zipkin.java</groupId>
			<artifactId>zipkin-autoconfigure-ui</artifactId>
			<scope>runtime</scope><!-- 一定使用runtime不然项目无法启动很多依赖类没有加载进来 -->
</dependency>
