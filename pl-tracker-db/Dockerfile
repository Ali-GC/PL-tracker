FROM postgres
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB matches
COPY 01-pl-tracker-database.sql /docker-entrypoint-initdb.d/
COPY 02-phase-2-patch.sql /docker-entrypoint-initdb.d/