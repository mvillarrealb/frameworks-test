FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/customers-micronaut
WORKDIR /home/app/customers-micronaut

RUN native-image --no-server -cp build/libs/customers-micronaut-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/customers-micronaut/customers-micronaut /app/customers-micronaut
ENTRYPOINT ["/app/customers-micronaut"]