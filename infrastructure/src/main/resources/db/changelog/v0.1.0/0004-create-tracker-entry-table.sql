--liquibase formatted sql
--changeset elie.ahd:v0.1.0-0004

CREATE TABLE tracker_entry
(
    id          SERIAL PRIMARY KEY,
    tracker_id  INT REFERENCES tracker (id) NOT NULL,
    description TEXT
);

-- rollback DROP TABLE tracker_entry;
