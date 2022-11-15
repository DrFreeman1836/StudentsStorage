FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/StudentsStorage-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
#docker build -t viktoragafonov/students_storage . упаковать
#docker run -d -p 8080:8080 -t viktoragafonov/students_storage  запустить

#docker stop "id" остановить
#docker rmi -f "id" удалить докер образ
