create database if not exists NEIGHBORHOODPSS;

use NEIGHBORHOODPSS;

grant all on NEIGHBORHOODPSS.* to 'NHPSSUSER'@localhost identified by 'RESU#2008_2010';

create table if not exists DATA_TYPE
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
	objectType VARCHAR(250),
	dataType VARCHAR(250)
);

create table if not exists DATUM
(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	data_type_id INT REFERENCES DATA_TYPE.id,
	object_id INT,
	data_value VARCHAR(250)
);



