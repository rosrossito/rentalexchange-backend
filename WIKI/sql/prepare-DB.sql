--user name: postgres
--password: qa1234
--host: localhost
--port: 5432

CREATE DATABASE rental;

CREATE SCHEMA rental;

CREATE TABLE activationlink
(
    id BIGINT PRIMARY KEY NOT NULL,
    uuid VARCHAR(64),
    type VARCHAR(64),
    systemuser_id BIGINT,
    effectivedate TIMESTAMP,
    FOREIGN KEY (systemuser_id) REFERENCES systemuser (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX activationlink_systemuser_id_key ON activationlink (systemuser_id);

CREATE TABLE activationlink_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);

CREATE TABLE activity
(
    id BIGINT PRIMARY KEY NOT NULL,
    system_user_id BIGINT NOT NULL,
    activity_type VARCHAR(64) NOT NULL,
    description VARCHAR(200),
    activity_time TIMESTAMP NOT NULL
);

CREATE TABLE activity_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);

CREATE TABLE databasechangelog
(
    id VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    dateexecuted TIMESTAMP NOT NULL,
    orderexecuted INT NOT NULL,
    exectype VARCHAR(10) NOT NULL,
    md5sum VARCHAR(35),
    description VARCHAR(255),
    comments VARCHAR(255),
    tag VARCHAR(255),
    liquibase VARCHAR(20),
    contexts VARCHAR(255),
    labels VARCHAR(255)
);

CREATE TABLE databasechangeloglock
(
    id INT PRIMARY KEY NOT NULL,
    locked BOOL NOT NULL,
    lockgranted TIMESTAMP,
    lockedby VARCHAR(255)
);

CREATE TABLE systemuser
(
    id BIGINT PRIMARY KEY NOT NULL,
    login VARCHAR(64),
    password VARCHAR(64),
    email VARCHAR(64) NOT NULL,
    image VARCHAR(600),
    status VARCHAR(64) NOT NULL
);

CREATE TABLE systemuser_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
