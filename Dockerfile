FROM adoptopenjdk/openjdk11:alpine as builder
ADD . /src
WORKDIR /src
RUN ./gradlew bootJar

FROM adoptopenjdk/openjdk11:alpine
LABEL maintainer="Igor Anufriev sacmi@pm.me"
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=builder /src/build/libs/temperature-service-*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]