CREATE TABLE tbl_Countries
(
     CountryID INT PRIMARY KEY
    ,CountryName VARCHAR(50)
     
);
select * from tbl_countries;

INSERT INTO tbl_Countries VALUES 
(1,'Afghanistan','AF','AFG')
,(2,'Aland Islands','AX','ALA');

INSERT INTO tbl_Countries VALUES 
(1,'Afghanistan')
,(2,'Aland Islands')
,(3,'Albania')
,(4,'Algeria')
,(5,'American Samoa')
,(6,'Andorra')
,(7,'India');

CREATE TABLE tbl_States
(
     StateID INT PRIMARY KEY
    ,StateName VARCHAR(50),
    CountryID INT
     
);

select * from tbl_States;

INSERT INTO tbl_States VALUES 
(1,'New Jesry',1)
,(2,'California',1)
,(3,'Kolkata',7)
,(4,'Andhra Pradesh',7)
,(5,'Telangana',5)
,(6,'Bangalore',7)
,(7,'US',1);

CREATE TABLE tbl_Cities
(
     CitiesID INT PRIMARY KEY
    ,CityName VARCHAR(50)
     , StateID INT
);

select * from tbl_countries;
select * from tbl_Cities;
select * from tbl_States;

INSERT INTO tbl_Cities VALUES 
(1,'Hyderbad',5)
,(2,'Vizag',4)
,(3,'Marthalli',6)
,(4,'Guntur',4)
,(5,'Ammerpet',5)
,(6,'Vijayawad',4);

CREATE TABLE tbl_Users
(
     User_ID INT PRIMARY KEY
    ,FNAME VARCHAR(250) 
	,LNAME VARCHAR(250)
	,EMAIL VARCHAR (250) UNIQUE
	,PHNO INT 
	,DOB DATE 
	,GENDER VARCHAR(250)
	,COUNTRY INT
	,STATE INT
	,CITY INT
	,USER_PWD VARCHAR(250)
	,ACC_STATUS VARCHAR(250)
     
);

SELECT * FROM tbl_Users
