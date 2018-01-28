FROM tomcat:7-jre8

WORKDIR /usr/local/tomcat/

COPY ./target/simulateur-1.0-SNAPSHOT.war ./webapps/

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

RUN sh -c "touch /usr/local/tomcat/webapps/simulateur-1.0-SNAPSHOT.war"

RUN mkdir -p /usr/local/tomcat/conf

COPY tomcat-users.xml /usr/local/tomcat/conf/

EXPOSE 8080

CMD ["catalina.sh", "run"]
