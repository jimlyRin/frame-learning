FROM java:8

RUN mkdir -p /data/app && mkdir -p /data/logs
ADD docker-file-service.jar /data/app/app.jar

EXPOSE 3008
ENTRYPOINT ["java", "-jar", "/data/app/app.jar"]