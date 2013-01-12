create database if not exists NEIGHBORHOODPSS;

use NEIGHBORHOODPSS;

grant all on NEIGHBORHOODPSS.* to 'NHPSSUSER'@localhost identified by 'RESU#2008_2010';

create table if not exists USER
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	email VARCHAR(250),
	password VARCHAR(32)
);

create table if not exists PROJECT
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	latitude DOUBLE,
	longitude DOUBLE,
	parent_scenario_id INT REFERENCES SCENARIO.id
);

create table if not exists PROJECT_USER
(
	user_id INT REFERENCES USER.id,
	project_id INT REFERENCES PROJECT.id
);

create table if not exists SCENARIO
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	label VARCHAR(250),
	description VARCHAR(1000),
	parent_id INT REFERENCES SCENARIO.id,
	project_id INT REFERENCES PROJECT.id
); 

create table if not exists BUILDING
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	industry VARCHAR(250),
	scenario_id INT REFERENCES SCENARIO.id
);

create table if not exists GEO_COORDINATE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	latitude DOUBLE,
	longitude DOUBLE,
	building_id INT REFERENCES BUILDING.id
);

create table if not exists NETWORK
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	scenario_id INT REFERENCES SCENARIO.id
);

create table if not exists NODE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	network_id INT REFERENCES NETWORK.id,
	latitude DOUBLE,
	longitude DOUBLE,
	inflow DOUBLE,
	outflow DOUBLE
);

create table if not exists EDGE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	network_id INT REFERENCES NETWORK.id,
	start_node_id INT REFERENCES NODE.id,
	end_node_id INT REFERENCES NODE.id,
	capacity DOUBLE
);