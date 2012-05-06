use NEIGHBORHOODPSS;

create table if not exists SCENARIO
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	label VARCHAR(250),
	description VARCHAR(1000)
); 
