# 基础环境
FROM jdk-17.0.2:2022
# 作者信息
MAINTAINER jeffrey jeffreyji@aliyun.com
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ADD /target/user-service-0.0.1-SNAPSHOT.jar app.jar

ENV LANG=C.UTF-8
#RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8

EXPOSE 8080
# ENTRYPOINT用于容器启动以后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
