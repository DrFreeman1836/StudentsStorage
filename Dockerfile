FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
COPY target/StudentsStorage-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
#docker build -t viktoragafonov/students_storage . упаковать
#docker run -d -p 8080:8080 -t viktoragafonov/students_storage  запустить

#docker stop "id" остановить
#docker rmi -f "id" удалить докер образ
