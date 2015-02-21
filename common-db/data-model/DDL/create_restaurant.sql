/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-18                                   */
/*==============================================================*/

drop table if exists BUSINESS_RESTAURANTS;

drop table if exists RESTAURANT_COMMS;

drop table if exists RESTAURANT_HOURS;

drop table if exists RESTAURANT_INSTRUMENTS;

drop table if exists RESTAURANT_LICENSES;

drop table if exists RESTAURANT_TABLES;

drop table if exists RESTAURANT_POLICIES;

drop table if exists RESTAURANTS_PERSONALS;

/*==============================================================*/
/* Table:   BUSINESS_RESTAURANTS                                */
/*==============================================================*/
create table BUSINESS_RESTAURANTS
(
    RESTAURANT_ID       varchar(64)     not null,
    NAME                varchar(256)    not null,
    CATEGORY            varchar(32),
    SHORT_DESCRIPTION   varchar(512),
    FULL_DESCRIPTION      TEXT,
    TAX_EIN             varchar(64),
    PEOPLE_CAPACITY     INT,
    MAIN_PHONE          varchar(32),
    SIGNUP_STATUS       varchar(32)    not null,
    SIGNUP_STATUS_DATE  DATETIME       not null,
    RISK_STATUS         varchar(32),
    RISK_STATUS_DATE     DATETIME,
    HAS_BAR             BOOLEAN        not null,
    FOOD_STYLE          varchar(64)    not null,
    FOOD_SUB_STYLE      varchar(64),
    DEFAULT_CURRENCY    varchar(16),
    
    ENCRY_ADDR_LINE1    varchar(256),
    ENCRY_ADDR_LINE2    varchar(256),
    CITY                varchar(128)    not null,
    STATE               varchar(32)     not null,
    COUNTRY             varchar(64)     not null,
    ZIPCODE             varchar(32)     not null,
    IS_LINE_ENCRYPTED    BOOLEAN   comment 'for business never encrypted',
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (RESTAURANT_ID)
);

/*==============================================================*/
/* Table:   RESTAURANTS_PERSONALS                               */
/*==============================================================*/
create table RESTAURANTS_PERSONALS
(
    PERSONAL_ID     varchar(64)     not null,
    RESTAURANT_ID   varchar(64)     not null,
    FIRST_NAME      varchar(64),
    LAST_NAME       varchar(64)     not null,
    USER_ROLE       varchar(32)     not null,
    LOGIN           varchar(128)    not null,
    PASSWORD        varchar(256)    not null,
    SIGNUP_STATUS    varchar(32)    not null,
    SIGNUP_STATUS_DATE  DATETIME       not null,
    POSITION_TYPE    varchar(32),
    SEX              varchar(16),
    EMPLOYEE_NUMBER  varchar(64),
    SALARY_TYPE      varchar(32),
    IMAGE_REPO_NAME  varchar(32),
    IMAGE_REPO_URL   varchar(256),
    CITY             varchar(128),
    STATE            varchar(32)     not null,
    COUNTRY          varchar(64)     not null,
    ZIPCODE          varchar(32),
    /* comm */
    
    HIRE_DATE         DATE,
    TERMINATION_DATE  DATE,
    HIRE_AGENCY      varchar(128),
    NOTES            varchar(256),
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (PERSONAL_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create unique index PERSONAL_UNIQUE_LOGIN on RESTAURANTS_PERSONALS(LOGIN);

create index PERSONAL_BYRESTAURANT on RESTAURANTS_PERSONALS(RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_HOURS                                    */
/*==============================================================*/
create table RESTAURANT_HOURS
(
    HOUR_ID             BIGINT       not null  AUTO_INCREMENT,
    RESTAURANT_ID     varchar(64)    not null,
    DAY_OF_WEEK          INT         not null,
    START_HOUR           INT         not null,
    START_MINUTE         INT,
    END_HOUR             INT,
    END_MINUTE           INT,
    IS_HOLIDAY         BOOLEAN,
    NOTES            varchar(256),
    
    primary key (HOUR_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create index HOUR_BY_RESTAURANT on RESTAURANT_HOURS (RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_COMMS                                    */
/*==============================================================*/
create table RESTAURANT_COMMS
(
    RESTAURANT_COMM_ID  varchar(64)    not null,
    RESTAURANT_ID       varchar(64)    not null,
    COMM_TYPE          varchar(32)     not null,
    CATEGORY           varchar(64),
    COMM_NUMBER        varchar(256)    not null,
    IS_ENCRYPTED        BOOLEAN        not null,
    STATUS             varchar(32),
    STATUS_DATE         DATETIME,
    TAGS               varchar(256),
    NOTES              varchar(256),
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (RESTAURANT_COMM_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create index COMM_BY_RESTAURANT on RESTAURANT_COMMS (RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_INSTRUMENTS                              */
/*==============================================================*/
create table RESTAURANT_INSTRUMENTS
(
    RFINANCE_ID        varchar(64)    not null,
    RESTAURANT_ID      varchar(64)    not null,
    INSTRUMENT_TYPE    varchar(32)     not null,
    NAME               varchar(64),
    INSTRUMENT_NUMBER  varchar(256)    not null,
    IS_NUMBER_ENCRYPTED   BOOLEAN      not null,
    SIGNUP_STATUS      varchar(32)     not null,
    SIGNUP_STATUS_DATE    DATETIME     not null,
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
    
    primary key (RFINANCE_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create index FINANCE_BY_RESTAURANT on RESTAURANT_INSTRUMENTS (RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_LICENSES                                 */
/*==============================================================*/
create table RESTAURANT_LICENSES
(
    LICENSE_ID           BIGINT     not null  AUTO_INCREMENT,
    RESTAURANT_ID    varchar(64)    not null,
    LICENSE_TYPE     varchar(64)    not null,
    LICENSE_NAME     varchar(64)    not null,
    LICENSE_DOC_ID   varchar(128),
    LICENSE_AGENCY   varchar(128),
    GRANT_DATE        DATE,
    EXPIRED_DATE      DATE,
    APPLICANT_NAME   varchar(64),
    SPECIAL_NOTES    varchar(256),
    
    IMAGE_REPO_NAME   varchar(32),
    IMAGE_REPO_URL    varchar(512),
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (LICENSE_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create index LICENSE_BY_RESTAURANT on RESTAURANT_LICENSES (RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_TABLES                                   */
/*==============================================================*/
create table RESTAURANT_TABLES
(
    TABLE_ID             BIGINT    not null  AUTO_INCREMENT,
    RESTAURANT_ID   varchar(64)    not null,
    TABLE_NUMBER     INTEGER        not null,
    NAME            varchar(32),
    CAPACITY        INTEGER,
    RESERVABLE      BOOLEAN,
    TABLE_STATUS    varchar(32),
    STATUS_DATETIME   DATETIME,
    EQUIPMENT_TYPE  varchar(32),
    EQUIPMENT_ID    varchar(64),
    NOTES           varchar(256),
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (TABLE_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
create index TABLE_BY_RESTAURANT on RESTAURANT_TABLES (RESTAURANT_ID);

/*==============================================================*/
/* Table:   RESTAURANT_POLICIES                                 */
/*==============================================================*/
create table RESTAURANT_POLICIES
(
    POLICY_ID            BIGINT    not null  AUTO_INCREMENT,
    RESTAURANT_ID   varchar(64)    not null,
    POLICY_TYPE     varchar(32)    not null,
    SHORT_DESCRIPTION   varchar(256)  not null,
    FULL_TEXT           TEXT,
    EFFECTIVE_DATE      DATE       not null,
    IS_EXPIRED          BOOLEAN,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (POLICY_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);
