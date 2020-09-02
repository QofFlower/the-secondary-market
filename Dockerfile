FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=8086"]

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/app.jar"]