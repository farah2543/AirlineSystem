create table ADMIN (
   ADMINID              int auto_increment   not null ,
   USERNAME             varchar(1024)        not null,
   PASSWORD             varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   constraint PK_ADMIN primary key nonclustered (ADMINID)
);


create table AIRCRAFT (
   AID                  int             not null,
   MODEL                varchar(1024)        not null,
   MANUFACTURE          varchar(1024)        not null,
   NAME                 varchar(1024)        not null,
   CAPACITY             smallint             not null,
   constraint PK_AIRCRAFT primary key nonclustered (AID)
);


create table BOOKING (
   NATIONALID2           varchar(100)              null,
   FID                  int             	null,
   TICKETID             int                   null,
   BID                  int auto_increment                  not null,
   constraint PK_BOOKING primary key (BID)
);



create table customer (
   NATIONALID2          varchar(100)               not null,
   USERNAME             varchar(1024)        not null,
   AGE                  varchar(1024)                  not null,
   PASSWORD             varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   constraint PK_COSTUMER primary key nonclustered (NATIONALID2)
);


create table FLIGHT (
   FID                  int auto_increment     not null,
   AID                 int      					,
   F_source              varchar(150)		 null,
   DESTINATION          varchar(250)          null,
   DEPARTURE__DATE      date              null,
   DEPARTURE_TIME       time             null,
   AVAILABILITY         bool                  null,
   ARRIVAL__DATE        date              null,
   ARRIVAL_TIME         time              null,
   constraint PK_FLIGHT primary key nonclustered (FID)
);



create table SEATS (
   SEAT_NUMBER         int default 1  not null,
   FID                 int            not null,
   BOOKED               bool   default 0          not null,
   constraint PK_SEATS primary key nonclustered (SEAT_NUMBER,FID)
);



create table TICKET (
   TICKETID             int auto_increment                 not null,
   FLIGHT_ID            int                  not null,
   SEATNUMBER           smallint             not null,
   PASSENGER_NATONAL_ID bigint               not null,
   CLASS                varchar(1024)        not null,
   constraint PK_TICKET primary key nonclustered (TICKETID)
);







alter table AIRCRAFT
   add constraint FK_AIRCRAFT_RELATIONS_FLIGHT foreign key (FID)
      references FLIGHT (FID);
	 

      
      
      
      
alter table BOOKING
   add constraint FK_BOOKING_BOOKING_COSTUMER foreign key (NATIONALID2)
      references customer (NATIONALID2);



alter table BOOKING
   add constraint FK_BOOKING_BOOKING2_FLIGHT foreign key (FID)
      references FLIGHT (FID);
      
      
 alter table BOOKING
   add constraint FK_BOOKING_BOOKING3_TICKET foreign key (TICKETID)
      references TICKET (TICKETID);
	  

      
      
alter table FLIGHT
   add constraint FK_FLIGHT_RELATIONS_AIRCRAFT foreign key (AID)
      references AIRCRAFT (AID) ;
      


      
 alter table SEATS
   add constraint FK_SEATS_HAS_FLIGHT foreign key (FID)
      references FLIGHT (FID);    





CREATE TRIGGER link AFTER INSERT ON flight FOR EACH ROW INSERT INTO seats (FID) VALUES (NEW.FID);
CREATE TRIGGER link2 AFTER INSERT ON aircraft FOR EACH ROW INSERT INTO flight (AID) VALUES (NEW.AID);




/*CREATE TRIGGER link2 AFTER INSERT ON flight FOR EACH ROW INSERT INTO booking (FID) VALUES (NEW.FID);
CREATE TRIGGER link4 AFTER INSERT ON ticket FOR EACH ROW INSERT INTO booking (TICKETID) VALUES (NEW.TICKETID);
CREATE TRIGGER link5 AFTER INSERT ON costumer FOR EACH ROW INSERT INTO booking (NATIONALID2) VALUES (NEW.NATIONALID2);*/




/*alter table seats
drop constraint FK_SEATS_HAS_FLIGHT;

drop table seats;

alter table flight
drop constraint FK_FLIGHT_RELATIONS_AIRCRAFT;

drop table flight;
drop table aircraft;*/

select *
from admin;

select * 
from aircraft;

select *
from flight;

select* 
from seats;




