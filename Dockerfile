FROM postgres:15.2-alpine

ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD qwerty
ENV POSTGRES_DB senebank_db

EXPOSE 5432