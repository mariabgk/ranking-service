--liquibase formatted sql

--changeset mbuzon:1
CREATE TABLE ranking (
    id INT PRIMARY KEY,
    firm_id INT,
    trend TEXT,
    diversity BOOLEAN,
    tier TEXT,
    position INT
);
--rollback drop table ranking;

--changeset mbuzon:2
CREATE TABLE firm (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT,
    image_url TEXT,
    website_url TEXT
);
--rollback drop table firm;

--changeset mbuzon:3
CREATE TABLE badge (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT
);
--rollback drop table badge;

--changeset mbuzon:4
CREATE TABLE firm_badge (
    firm_id INT,
    badge_id INT
);
--rollback drop table firm_badge;

--changeset mbuzon:5
CREATE TABLE award (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT
);
--rollback drop table award;

--changeset mbuzon:6
CREATE TABLE firm_award (
    firm_id INT,
    award_id INT
);
--rollback drop table firm_award;

--changeset mbuzon:7
CREATE TABLE firm_region (
    id INT PRIMARY KEY,
    firm_id INT,
    region_id INT,
    booking BOOLEAN,
    cross_border_capability TEXT,
    client_satisfaction_rating INT,
    expertise_and_reputation_rating INT
);
--rollback drop table firm_region;

--changeset mbuzon:8
CREATE TABLE region (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT,
    region_group_id INT
);
--rollback drop table region;

--changeset mbuzon:9
CREATE TABLE region_group (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT,
    region_area_id INT
);
--rollback drop table region_group;

--changeset mbuzon:10
CREATE TABLE region_area (
    id INT PRIMARY KEY,
    name TEXT,
    slug TEXT
);
--rollback drop table area;