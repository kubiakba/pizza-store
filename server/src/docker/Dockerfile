FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8081
RUN mkdir -p /app/
ADD pizza-store-*.jar /app/pizza-store.jar
ENTRYPOINT ["java", "-jar", "/app/pizza-store.jar"]

