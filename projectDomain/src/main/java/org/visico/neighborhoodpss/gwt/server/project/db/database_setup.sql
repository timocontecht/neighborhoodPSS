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

# create views to allow for tomcat user management
create view tc_realm_users as
select 
	name as username, 
	sha2(password, 256) as passwordhash
from USER;

create view tc_realm_groups as
select
	USER.name as username,
	'user' as groupname
from USER;


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
	scenario_id INT REFERENCES SCENARIO.id
);

create table if not exists GEOCOORDINATE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	latitude DOUBLE,
	longitude DOUBLE,
	building_id INT REFERENCES BUILDING.id
);

create table if not exists GEONETWORK
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	scenario_id INT REFERENCES SCENARIO.id,
	color VARCHAR(250)
);

create table if not exists BUILDINGNETWORK
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	scenario_id INT REFERENCES SCENARIO.id,
	color VARCHAR(250)
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

create table if not exists GEOEDGE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	network_id INT REFERENCES GEONETWORK.id,
	start_node_id INT REFERENCES NODE.id,
	end_node_id INT REFERENCES NODE.id,
	capacity DOUBLE
);

create table if not exists BUILDINGEDGE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	network_id INT REFERENCES BUILDINGNETWORK.id,
	start_building_id INT REFERENCES BUILDING.id,
	end_building_id INT REFERENCES BUILDING.id,
	capacity DOUBLE
);

create table if not exists NETWORK_BUILDING
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	network_id INT REFERENCES BUILDINGNETWORK.id,
	building_id INT REFERENCES BUILDING.id
);

create table if not exists BUILDING_DATA_TYPE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) UNIQUE,
	type VARCHAR(10),
	maximum DOUBLE,
	minimum DOUBLE,
	default_val VARCHAR(250)
);

create table if not exists BUILDING_DATA_TYPE_PROJECT
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	building_data_type_id INT REFERENCES BUILDING_DATA_TYPE.id,
	project_id INT REFERENCES project.id
);

create table if not exists EDGE_DATA_TYPE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) UNIQUE,
	type VARCHAR(10),
	maximum DOUBLE,
	minimum DOUBLE,
	default_val VARCHAR(250)
);

create table if not exists EDGE_DATA_TYPE_PROJECT
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	edge_data_type_id INT REFERENCES EDGGE_DATA_TYPE.id,
	project_id INT REFERENCES project.id
);

create table if not exists NODE_DATA_TYPE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) UNIQUE,
	type VARCHAR(10),
	maximum DOUBLE,
	minimum DOUBLE,
	default_val VARCHAR(250)
);

create table if not exists NODE_DATA_TYPE_PROJECT
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	node_data_type_id INT REFERENCES NODE_DATA_TYPE.id,
	project_id INT REFERENCES project.id
);

create table if not exists BUILDING_DATA
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	type_id INT REFERENCES DATA_BUILDING.id,
	value VARCHAR(250),
	building_id INT REFERENCES BUILDING.id
);

create table if not exists EDGE_DATA
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	type_id INT REFERENCES DATA_EDGE.id,
	value VARCHAR(250),
	building_id INT REFERENCES EDGE.id
);

create table if not exists NODE_DATA
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	type_id INT REFERENCES DATA_NODE.id,
	value VARCHAR(250),
	building_id INT REFERENCES NODE.id
);

