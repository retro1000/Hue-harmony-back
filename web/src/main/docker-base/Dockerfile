FROM openjdk:11-jre-slim

ENV JAVA_OPTS " -Xms512m -Xms512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application

#COPY

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "name_of_jar_from_copy"]