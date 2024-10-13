--liquibase formatted sql
--changeset elie.ahd:v0.1.0-0003

CREATE TABLE tracker
(
    id                  SERIAL PRIMARY KEY,
    tracker_template_id INT REFERENCES tracker_template (id) NOT NULL,
    interval            VARCHAR(100)                         NOT NULL,
    member_id           INT REFERENCES member (id)           NOT NULL,
    created_date        DATE                                 NOT NULL
);

-- rollback DROP TABLE tracker;
