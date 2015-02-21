/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-17                                   */
/*==============================================================*/

drop table if exists SECURE_SALT;

create table SECURE_SALT
(
    RELATED_ENTITY_ID    varchar(64)   not null comment 'related entity id, such as customer_id for customer',
    RELATED_ENTITY_TYPE  varchar(64)   not null comment 'related entity type (class name), such as Customer/Restaurant',
    ALGO_PASSWORD        varchar(256)   not null comment 'password used in the PKCS algorithm',
    SALT_BASE64          varchar(64)    not null comment 'salt data (byte[]) in Base64 encoding format',
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (RELATED_ENTITY_ID)
);