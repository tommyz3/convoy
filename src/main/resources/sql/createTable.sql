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
	NAME varchar(255) null,
	TITLE_DES varchar(255) null,
	IMG blob null
)
;

-- 2020.04.28新增
-- 申请车辆表
create table apply_car
(
	ID int auto_increment
		primary key,
	CAR_KIND int null,
	START_TIME timestamp null,
	END_TIME timestamp null,
	START_POSITION varchar(255) null,
	END_POSITION varchar(255) null,
	FOLLOW varchar(255) default '' null,
	REASON varchar(255) default '' null,
	WHO varchar(12) null,
	STATUS int default '0' null,
	DRIVER varchar(12) null,
	CAR int null
)
;

-- 审批车辆表
create table approval_car
(
	ID int auto_increment
		primary key,
	LEADER varchar(255) null,
	APPLY_ID int null, -- 申请编号
	KIND tinyint default '0' null, -- 标识是部门领导还是车队领导
	REASON varchar(255) null,
	STATUS tinyint default '1' null -- 1通过，0不通过
)
;

-- 有活干的车辆表
create table busy_car
(
	ID int auto_increment
		primary key,
	CAR int null,
	START timestamp null,
	END timestamp null,
	KIND int default '0' null
)
;

-- 有活干的司机表
create table busy_driver
(
	ID int auto_increment
		primary key,
	DRIVER varchar(12) null,
	START timestamp null,
	END timestamp null,
	LICENSE int default '0' null
)
;

-- 车辆信息表
create table car
(
	ID int auto_increment
		primary key,
	KIND int null,
	NUMBER_PLATE varchar(10) null,
	MODEL varchar(255) null,
	PEOPLE int default '5' null,
	COLOR varchar(50) default 'BLACK' null,
	LOAD_WEIGHT int default '70' null,
	FUEL int default '100' null,
	SERVICE_START timestamp null,
	INSPECT timestamp null,
	INSPECT_DDL timestamp null,
	SAFE timestamp null,
	SAFE_DDL timestamp null
)
;

-- 评价表
create table evaluation
(
	ID int null,
	SCORE int default '5' null,
	MESAGE varchar(255) null
)
;





