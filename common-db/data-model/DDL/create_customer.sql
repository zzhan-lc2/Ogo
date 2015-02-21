/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-18                                   */
/*==============================================================*/

drop table if exists CUSTOMERS;

drop table if exists CUSTOMER_COMMS;

drop table if exists CUST_FINANCE_INSTRUMENTS;

/*==============================================================*/
/* Table:   CUSTOMERS                                          */
/*==============================================================*/
create table CUSTOMERS
(
    CUSTOMER_ID        varchar(64)        not null,
    FIRST_NAME         varchar(64),
    LAST_NAME          varchar(64),
    PERSONAL_TYPE      varchar(32)        not null,
    LOGIN              varchar(128)       not null,
    PASSWORD           varchar(256)       not null,
    SIGNUP_STATUS      varchar(32)        not null,
    SIGNUP_STATUS_DATE  DATETIME           not null,
    RISK_STATUS        varchar(32),
    RISK_STATUS_DATE     DATETIME,
    
    ENCRY_ADDR_LINE1     varchar(256),
    ENCRY_ADDR_LINE2     varchar(256),
    CITY                 varchar(128),
    STATE                varchar(32)     not null,
    COUNTRY              varchar(64)     not null,
    ZIPCODE              varchar(32),
    IS_LINE_ENCRYPTED     BOOLEAN,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (CUSTOMER_ID)
);

create unique index CUSTOMER_BY_LOGIN on CUSTOMERS (LOGIN);

/*==============================================================*/
/* Table:   CUSTOMER_COMMS                                      */
/*==============================================================*/
create table CUSTOMER_COMMS
(
    CUSTOMER_COMM_ID   varchar(64)     not null,
    CUSTOMER_ID        varchar(64)     not null,
    COMM_TYPE          varchar(32)     not null,
    CATEGORY           varchar(64),
    COMM_NUMBER        varchar(256)    not null,
    IS_ENCRYPTED        BOOLEAN        not null,
    STATUS             varchar(32),
    STATUS_DATE        DATETIME,
    TAGS               varchar(256),
    NOTES              varchar(256),
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (CUSTOMER_COMM_ID),
    foreign key (CUSTOMER_ID) 
        REFERENCES CUSTOMERS(CUSTOMER_ID)
        ON DELETE CASCADE
);

create index COMM_BY_CUSTOMER on CUSTOMER_COMMS (CUSTOMER_ID);

/*==============================================================*/
/* Table:   CUST_FINANCE_INSTRUMENTS                            */
/*==============================================================*/
create table CUST_FINANCE_INSTRUMENTS
(
    CFINANCE_ID        varchar(64)     not null,
    CUSTOMER_ID        varchar(64)     not null,
    INSTRUMENT_TYPE    varchar(32)     not null,
    NAME               varchar(64),
    INSTRUMENT_NUMBER  varchar(256)    not null,
    IS_NUMBER_ENCRYPTED   BOOLEAN      not null,
    SIGNUP_STATUS      varchar(32)     not null,
    SIGNUP_STATUS_DATE    DATE         not null,
    EXPIRE_DATE           DATE,
    NOTES              varchar(256),
    
    BILL_ADDR_LINE1     varchar(256),
    BILL_ADDR_LINE2     varchar(256),
    BILL_CITY           varchar(128),
    BILL_STATE          varchar(32)     not null,
    BILL_COUNTRY        varchar(64)     not null,
    BILL_ZIPCODE        varchar(32)     not null,
    IS_LINE_ENCRYPTED     BOOLEAN,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (CFINANCE_ID),
    foreign key (CUSTOMER_ID) 
        REFERENCES CUSTOMERS(CUSTOMER_ID)
        ON DELETE CASCADE
);

create index CFINANCE_BY_CUSTOMER on CUST_FINANCE_INSTRUMENTS (CUSTOMER_ID);
