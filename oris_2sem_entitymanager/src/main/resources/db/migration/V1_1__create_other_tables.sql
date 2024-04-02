create table country (
	id integer,
	name varchar(255),
	constraint country_pk primary key (id)
);

create table genre (
	id integer,
	name varchar(255),
	constraint genre_pk primary key (id)
);

create table musician (
	id integer,
	name varchar(255),
	country integer,
	constraint musician_pk primary key (id)
);

create table author (
	id integer,
	name varchar(255),
	country integer,
	constraint author_pk primary key (id)
);