/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     5/18/2023 6:38:50 PM                         */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ADMIN') and o.name = 'FK_ADMIN_INHERITAN_USER')
alter table ADMIN
   drop constraint FK_ADMIN_INHERITAN_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('AIRCRAFT') and o.name = 'FK_AIRCRAFT_RELATIONS_FLIGHT')
alter table AIRCRAFT
   drop constraint FK_AIRCRAFT_RELATIONS_FLIGHT
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BOOKING') and o.name = 'FK_BOOKING_BOOKING_COSTUMER')
alter table BOOKING
   drop constraint FK_BOOKING_BOOKING_COSTUMER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BOOKING') and o.name = 'FK_BOOKING_BOOKING2_FLIGHT')
alter table BOOKING
   drop constraint FK_BOOKING_BOOKING2_FLIGHT
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('BOOKING') and o.name = 'FK_BOOKING_BOOKING3_TICKET')
alter table BOOKING
   drop constraint FK_BOOKING_BOOKING3_TICKET
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('COSTUMER') and o.name = 'FK_COSTUMER_INHERITAN_USER')
alter table COSTUMER
   drop constraint FK_COSTUMER_INHERITAN_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FLIGHT') and o.name = 'FK_FLIGHT_RELATIONS_AIRCRAFT')
alter table FLIGHT
   drop constraint FK_FLIGHT_RELATIONS_AIRCRAFT
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEATS') and o.name = 'FK_SEATS_HAS_FLIGHT')
alter table SEATS
   drop constraint FK_SEATS_HAS_FLIGHT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ADMIN')
            and   name  = 'INHERITANCE_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index ADMIN.INHERITANCE_2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ADMIN')
            and   type = 'U')
   drop table ADMIN
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('AIRCRAFT')
            and   name  = 'RELATIONSHIP_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index AIRCRAFT.RELATIONSHIP_2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('AIRCRAFT')
            and   type = 'U')
   drop table AIRCRAFT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BOOKING')
            and   name  = 'BOOKING3_FK'
            and   indid > 0
            and   indid < 255)
   drop index BOOKING.BOOKING3_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BOOKING')
            and   name  = 'BOOKING2_FK'
            and   indid > 0
            and   indid < 255)
   drop index BOOKING.BOOKING2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('BOOKING')
            and   name  = 'BOOKING_FK'
            and   indid > 0
            and   indid < 255)
   drop index BOOKING.BOOKING_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BOOKING')
            and   type = 'U')
   drop table BOOKING
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('COSTUMER')
            and   name  = 'INHERITANCE_1_FK'
            and   indid > 0
            and   indid < 255)
   drop index COSTUMER.INHERITANCE_1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('COSTUMER')
            and   type = 'U')
   drop table COSTUMER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FLIGHT')
            and   name  = 'RELATIONSHIP_3_FK'
            and   indid > 0
            and   indid < 255)
   drop index FLIGHT.RELATIONSHIP_3_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('FLIGHT')
            and   type = 'U')
   drop table FLIGHT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SEATS')
            and   name  = 'HAS_FK'
            and   indid > 0
            and   indid < 255)
   drop index SEATS.HAS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEATS')
            and   type = 'U')
   drop table SEATS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TICKET')
            and   type = 'U')
   drop table TICKET
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"USER"')
            and   type = 'U')
   drop table "USER"
go

/*==============================================================*/
/* Table: ADMIN                                                 */
/*==============================================================*/
create table ADMIN (
   ADMINID              int                  not null,
   USERNAME             varchar(1024)        null,
   PASSWORD             varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   TYPE                 varchar(1024)        not null,
   constraint PK_ADMIN primary key nonclustered (ADMINID)
)
go

/*==============================================================*/
/* Index: INHERITANCE_2_FK                                      */
/*==============================================================*/
create index INHERITANCE_2_FK on ADMIN (
USERNAME ASC
)
go

/*==============================================================*/
/* Table: AIRCRAFT                                              */
/*==============================================================*/
create table AIRCRAFT (
   AID                  smallint             not null,
   FID                  numeric              null,
   MODEL                varchar(1024)        not null,
   MANUFACTURE          varchar(1024)        not null,
   NAME                 varchar(1024)        not null,
   CAPACITY             smallint             not null,
   constraint PK_AIRCRAFT primary key nonclustered (AID)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_2_FK on AIRCRAFT (
FID ASC
)
go

/*==============================================================*/
/* Table: BOOKING                                               */
/*==============================================================*/
create table BOOKING (
   NATIONALID2          bigint               not null,
   FID                  numeric              not null,
   TICKETID             int                  not null,
   BID                  int                  not null,
   constraint PK_BOOKING primary key (NATIONALID2, FID, TICKETID, BID)
)
go

/*==============================================================*/
/* Index: BOOKING_FK                                            */
/*==============================================================*/
create index BOOKING_FK on BOOKING (
NATIONALID2 ASC
)
go

/*==============================================================*/
/* Index: BOOKING2_FK                                           */
/*==============================================================*/
create index BOOKING2_FK on BOOKING (
FID ASC
)
go

/*==============================================================*/
/* Index: BOOKING3_FK                                           */
/*==============================================================*/
create index BOOKING3_FK on BOOKING (
TICKETID ASC
)
go

/*==============================================================*/
/* Table: COSTUMER                                              */
/*==============================================================*/
create table COSTUMER (
   NATIONALID2          bigint               not null,
   USERNAME             varchar(1024)        null,
   AGE                  int                  null,
   PASSWORD             varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   TYPE                 varchar(1024)        not null,
   constraint PK_COSTUMER primary key nonclustered (NATIONALID2)
)
go

/*==============================================================*/
/* Index: INHERITANCE_1_FK                                      */
/*==============================================================*/
create index INHERITANCE_1_FK on COSTUMER (
USERNAME ASC
)
go

/*==============================================================*/
/* Table: FLIGHT                                                */
/*==============================================================*/
create table FLIGHT (
   FID                  numeric              not null,
   AID                  smallint             not null,
   SOURCE               varchar(150)         not null,
   DESTINATION          varchar(250)         not null,
   DEPARTURE__DATE      datetime             not null,
   DEPARTURE_TIME       datetime             not null,
   AVAILABILITY         bit                  null,
   ARRIVAL__DATE        datetime             not null,
   ARRIVAL_TIME         datetime             not null,
   constraint PK_FLIGHT primary key nonclustered (FID)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_3_FK on FLIGHT (
AID ASC
)
go

/*==============================================================*/
/* Table: SEATS                                                 */
/*==============================================================*/
create table SEATS (
   SEAT_NUMBER          numeric              not null,
   FID                  numeric              not null,
   BOOKED               bit                  null,
   constraint PK_SEATS primary key nonclustered (SEAT_NUMBER)
)
go

/*==============================================================*/
/* Index: HAS_FK                                                */
/*==============================================================*/
create index HAS_FK on SEATS (
FID ASC
)
go

/*==============================================================*/
/* Table: TICKET                                                */
/*==============================================================*/
create table TICKET (
   TICKETID             int                  not null,
   FLIGHT_ID            int                  not null,
   SEATNUMBER           smallint             not null,
   PASSENGER_NATONAL_ID bigint               not null,
   CLASS                varchar(1024)        not null,
   constraint PK_TICKET primary key nonclustered (TICKETID)
)
go

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USER" (
   USERNAME             varchar(1024)        not null,
   PASSWORD             varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   TYPE                 varchar(1024)        not null,
   constraint PK_USER primary key nonclustered (USERNAME)
)
go

alter table ADMIN
   add constraint FK_ADMIN_INHERITAN_USER foreign key (USERNAME)
      references "USER" (USERNAME)
go

alter table AIRCRAFT
   add constraint FK_AIRCRAFT_RELATIONS_FLIGHT foreign key (FID)
      references FLIGHT (FID)
go

alter table BOOKING
   add constraint FK_BOOKING_BOOKING_COSTUMER foreign key (NATIONALID2)
      references COSTUMER (NATIONALID2)
go

alter table BOOKING
   add constraint FK_BOOKING_BOOKING2_FLIGHT foreign key (FID)
      references FLIGHT (FID)
go

alter table BOOKING
   add constraint FK_BOOKING_BOOKING3_TICKET foreign key (TICKETID)
      references TICKET (TICKETID)
go

alter table COSTUMER
   add constraint FK_COSTUMER_INHERITAN_USER foreign key (USERNAME)
      references "USER" (USERNAME)
go

alter table FLIGHT
   add constraint FK_FLIGHT_RELATIONS_AIRCRAFT foreign key (AID)
      references AIRCRAFT (AID)
go

alter table SEATS
   add constraint FK_SEATS_HAS_FLIGHT foreign key (FID)
      references FLIGHT (FID)
go

