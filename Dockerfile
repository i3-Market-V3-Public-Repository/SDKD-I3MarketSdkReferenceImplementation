FROM maven:3.6.3-jdk-11 as initial

RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository

COPY settings.xml /root/.m2

COPY . /sdk-ri

WORKdir /sdk-ri

RUN mvn install

FROM jetty

USER root
ADD https://github.com/keycloak/keycloak/releases/download/12.0.4/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz /var/lib/jetty/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz
RUN tar -xzf /var/lib/jetty/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz
USER jetty
RUN echo $JETTY_HOME
RUN java -jar "$JETTY_HOME/start.jar" --add-to-start=keycloak
COPY --from=initial /sdk-ri/target/SdkRefImpl.war /var/lib/jetty/webapps/SdkRefImpl.war

EXPOSE 8080