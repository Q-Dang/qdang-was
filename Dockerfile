FROM openjdk:11
ARG TZ=Asia/Seoul
EXPOSE 8080
ARG JAR_FILE=./adapter-web/build/libs/app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=${TZ}","-Dspring.profiles.active=${PROFILE}","app.jar"]