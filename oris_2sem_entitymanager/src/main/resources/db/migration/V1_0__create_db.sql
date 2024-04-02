create table musictrack (
	id integer,
	name varchar(512),
	length integer,
	musician integer,
	author integer,
	date char(10),
	genre integer,
	constraint musictrack_pk primary key (id)
);
