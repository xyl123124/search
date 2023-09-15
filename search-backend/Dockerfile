FROM openjdk:8
COPY ./butterfly-backend.jar /tmp/butterfly-backend.jar
EXPOSE 8202
ENTRYPOINT java -jar -Duser.timezone=GMT+08  /tmp/butterfly-backend.jar