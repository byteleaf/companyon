ARG BUILDER_IMAGE=maven:3-openjdk-14

# Builder

FROM $BUILDER_IMAGE AS builder

WORKDIR /usr/src/companyon

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B -DskipTests

COPY ./ ./

RUN mvn clean package -DskipTests

RUN mkdir -p target/dependency 
WORKDIR /usr/src/companyon/target/dependency

RUN jar -xf ../*.jar

# Runner

FROM openjdk:14-alpine

ENV JAVA_OPTS="-Dspring.profiles.active=prod"

RUN addgroup -S spring && \
    adduser -S spring -G spring
USER spring:spring

WORKDIR /usr/src/companyon

ARG DEPENDENCY=/usr/src/companyon/target/dependency

COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib ./lib
COPY --from=builder ${DEPENDENCY}/META-INF ./META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes ./

CMD ["sh", "-c", "java -cp .:lib/* ${JAVA_OPTS} de.byteleaf.companyon.CompanyonApplicationKt"]