FROM maven:3.6.3-jdk-11 as initial

RUN mkdir -p /root/.ssh/
# Put your ARI git private key in the id_rsa file to be able to access the ARI repository
ADD ssh_keys/id_rsa /root/.ssh/id_rsa
RUN chmod 700 /root/.ssh/id_rsa


RUN touch /root/.ssh/known_hosts
RUN echo "Host gitlab.com\n\tStrictHostKeyChecking no\n" >> /root/.ssh/config
RUN git clone git@gitlab.com:i3-market/code/wp4/i3market-sdk.git


WORKDIR /i3market-sdk
RUN git checkout develop
RUN mvn install

FROM jetty


USER root
ADD https://github.com/keycloak/keycloak/releases/download/12.0.4/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz /var/lib/jetty/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz
RUN tar -xzf /var/lib/jetty/keycloak-oidc-jetty94-adapter-12.0.4.tar.gz
USER jetty
RUN echo $JETTY_HOME
RUN java -jar "$JETTY_HOME/start.jar" --add-to-start=keycloak
COPY --from=initial /i3market-sdk/target/SdkGenerator.war /var/lib/jetty/webapps/SdkGenerator.war



EXPOSE 8080
