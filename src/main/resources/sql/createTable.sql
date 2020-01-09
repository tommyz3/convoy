-- auto-generated definition
create table convoy.attendance
(
	ID int auto_increment
		primary key,
	DATE varchar(10) null,
	NUM int default '0' null,
	DEPARTMENT varchar(255) null
)
;

-- auto-generated definition
create table convoy.attendance_detail
(
	ATT_ID int not null,
	PHONE varchar(11) not null,
	NAME varchar(255) null,
	ADDRESS varchar(255) null,
	TIME varchar(255) null,
	primary key (ATT_ID, PHONE)
)
;

create index attendance_detail_fk_phone
	on attendance_detail (PHONE)
;

-- auto-generated definition
create table convoy.holiday
(
	PHONE varchar(11) null,
	BEGIN varchar(16) null,
	END varchar(16) null,
	APPROVE tinyint default '0' null,
	DEPARTMENT varchar(255) null,
	TYPE varchar(255) null,
	REASON varchar(255) null,
	TIME varchar(16) null
)
;

create index HOLIDAY_user_PHONE_fk
	on holiday (PHONE)
;

-- auto-generated definition
create table convoy.trip
(
	PHONE varchar(11) null,
	BEGIN varchar(16) null,
	END varchar(16) null,
	APPROVE tinyint default '0' null,
	DEPARTMENT varchar(255) null,
	ADDRESS varchar(255) null,
	REASON varchar(255) null,
	TIME varchar(16) null,
	CITY_FROM varchar(255) null,
	CITY_TO varchar(255) null,
	TRANSPORT varchar(255) null
)
;

create index trip_user_PHONE_fk
	on trip (PHONE)
;

-- auto-generated definition
create table convoy.user
(
	PHONE varchar(11) not null
		primary key,
	PASSWORD varchar(255) not null,
	DEPARTMENT varchar(255) null,
	TITLE int null,
	NAME varchar(255) null
)
;

