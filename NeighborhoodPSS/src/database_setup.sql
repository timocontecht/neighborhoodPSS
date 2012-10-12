use NEIGHBORHOODPSS;

create table if not exists SCENARIO
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	label VARCHAR(250),
	description VARCHAR(1000),
	parent_id INT REFERENCES SCENARIO.id
); 


create table if not exists BUILDING
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	industry VARCHAR(250),
	scenario_id INT REFERENCES SCENARIO.id
);

create table if not exists BUILDING_COORDINATE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	latitude DOUBLE,
	longitude DOUBLE,
	building_id INT REFERENCES BUILDING.id
);
