/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-17                                   */
/*==============================================================*/

drop table if exists WEB_SESSIONS;

/*==============================================================*/
/* Table: WEB_SESSIONS                                          */
/*==============================================================*/
create table WEB_SESSIONS
(
    SESSION_ID            varchar(64)   not null comment 'session id',
    PREVIOUS_SESSION_ID   varchar(64)            comment 'previous related session id',
    USER_ID               varchar(64)            comment 'user id associated with this session, like customer_id',
    SESSION_TYPE          varchar(32)            comment 'the type of session',
    IS_ROBOT              boolean       not null comment 'is_robot flag',
    IS_EXPIRED            boolean                comment 'is_expired flag (optional)',
    IP_ADDR               varchar(64)   not null comment 'ip address of the client',
    USER_AGENT            varchar(256)           comment 'user agent of browser/app',
    DEVICE_ID             varchar(64)            comment 'device id (optional)',
        
    CREATION_DATE          DATETIME     not null comment 'the creation date/time',
    LAST_ACCESS_DATE       DATE         not null comment 'the last accessed date (no hour/minute/second), can be used for partition',
    LAST_ACCESS_DATETIME   DATETIME     not null comment 'the last accessed date/time',
    
    primary key (SESSION_ID)
);

create index WEB_SESSION_USER on table WEB_SESSIONS (USER_ID);