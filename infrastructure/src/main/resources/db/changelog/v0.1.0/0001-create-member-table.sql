--liquibase formatted sql
--changeset elie.ahd:v0.1.0-0001

CREATE TABLE member
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(32)  NOT NULL,
    name     VARCHAR(255) NOT NULL
);

-- rollback DROP TABLE member;
