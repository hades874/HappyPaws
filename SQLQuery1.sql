create database HappyPaws;
use HappyPaws;

create table customer(
	
	Customer_ID int identity (1001,1) not null primary key,
	Customer_Name varchar (100) not null,
	Address varchar (80) not null,
	Mail varchar (80) not null,
	Phone_Number varchar (11) not null
)
/*drop table customer
drop table pets
drop table reservationInfo
drop table food
drop table medicine
drop table rooms
drop table bill*/
--select * from bill 


create table pets(

	Pet_Id int identity (2001,1) not null primary key,
	Pet_Name varchar (80) null,
	Breed varchar (80) not null,
	Food varchar (80) null,

	CustomerID int not null foreign key references customer(Customer_ID),
)

create table reservationInfo(
	
	Reservation_ID int identity (5001,1) not null primary key,
	Check_In_Date date null,
	Check_Out_Date date null,

	CustomerID int not null foreign key references customer(Customer_ID),
	Pet_ID int null foreign key references pets(Pet_ID),
)

create table food(
	
	Food_ID int identity (3001,1) not null primary key,
	Name varchar (80) not null,
	Type varchar (50) not null,
	Amount int not null,
	Expiry_Date date not null,
)

create table medicine(
	
	Medicine_ID int identity (4001,1) not null primary key,
	Medicine_Name varchar(80) null,
	Dose varchar (80) null,
	Expiry_Date date null,
	Quantity int null,

	Pet_ID int not null foreign key references pets(Pet_ID),
)


create table rooms(

	Room_ID int identity (6001,1) not null primary key,
	Type varchar(80) not null,
	Price money not null,
	Availability varchar(10) not null default 'YES', --status = availability

	Pet_ID int null foreign key references pets(Pet_ID),
)


create table bill(
	
	Bill_ID int identity (9001,1) not null primary key,
	Bill_Amount money not null,
	Bill_Date date not null,
	Payment_Method varchar (80) not null,

)

insert into rooms (Type, Price) values
('Tom', 650), ('Tom', 650),
('Tom', 650), ('Tom', 650),  
('Pluto', 1200), ('Pluto', 1200),
('Pluto', 1200), ('Pluto', 1200) 


