/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-17                                   */
/*==============================================================*/

drop table if exists ADDRESSES;

/*==============================================================*/
/* Table: ADDRESSES                                             */
/*==============================================================*/
create table ADDRESSES
(
    ADDRESS_ID           varchar(64)      not null,
    RELATED_TYPE         varchar(64),
    RELATED_ID           varchar(64),
    ENCRY_ADDR_LINE1     varchar(256),
    ENCRY_ADDR_LINE2     varchar(256),
    CITY                 varchar(128),
    STATE                varchar(32)     not null,
    COUNTRY              varchar(64)     not null,
    ZIPCODE              varchar(32),
    IS_LINE_ENCRYPTED    boolean,
    ADDRESS_TYPE         varchar(32),
    LOCATION_LAT_DD      DOUBLE,
    LOCATION_LON_DD      DOUBLE,
    LOCATION_ELEV        DOUBLE  comment 'elevation for the location (in meters)',
    ADDR_STATUS          varchar(32),
    ADDR_STATUS_DATE     DATETIME,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (ADDRESS_ID)
);

create index ADDRESS_RELATED_ID on ADDRESSES (RELATED_ID);

create index ADDRESS_ZIPCODE on ADDRESSES (ZIPCODE);

create index ADDRESS_STATECITY on ADDRESSES (COUNTRY,STATE,CITY);