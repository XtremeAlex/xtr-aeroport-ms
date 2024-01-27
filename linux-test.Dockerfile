FROM ubuntu:jammy
WORKDIR /work/

COPY target/*-app /work/application
RUN chmod 775 /work
EXPOSE 8080
CMD ["./application"]