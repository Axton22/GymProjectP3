create database if not exists gymReservations;
use gymReservations;

create table GymClientEntity (
clientID int auto_increment primary key,
clientName varchar (50),
mail varchar (30),
phone varchar (20),
subscriptionType varchar (10)
);

create table GymRoutineEntity (
routineID int auto_increment primary key,
routineName varchar (20),
dayWeek varchar (20),
starHour varchar (10),
endHour varchar (10)
);

create table ReservationEntity (
reservationID int auto_increment primary key,
clntID int,
routID int, 
reservationDate date,
reservationHour datetime,
state boolean,
foreign key (clntID) references GymClientEntity(clientID),
foreign key (routID) references GymRoutineEntity(routineID)
);

SHOW TABLES;

 