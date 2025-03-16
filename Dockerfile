FROM tomcat:10.1-jdk21-temurin
WORKDIR /usr/local/tomcat
RUN rm -rf webapps/*
ENV SERVER_PORT=9020
COPY build/libs/monitor-sensors.war webapps/ROOT.war
RUN sed -i 's/port="8080"/port="9020"/g' conf/server.xml
EXPOSE 9020
CMD ["catalina.sh", "run"]