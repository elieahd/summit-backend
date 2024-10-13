--liquibase formatted sql
--changeset elie.ahd:v0.1.0-0002

CREATE TABLE tracker_template
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(1000) NOT NULL,
    interval    VARCHAR(100)
);

-- rollback DROP TABLE tracker_template;
