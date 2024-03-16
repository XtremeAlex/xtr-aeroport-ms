# The deployment Image
FROM docker.io/oraclelinux:8-slim

WORKDIR /opt/deployment/

# Copy the native executable into the containers
COPY ../target/*-jar xtreme-application.jar

#RUN  chmod +x (Se vuoi semplicemntete dare i permessi di esecuzione)
RUN chmod 775 xtreme-application

EXPOSE 8080

ENTRYPOINT ["java -jar xtreme-application.jar"]