/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2015-02-21                                   */
/*==============================================================*/

drop table if exists RESTAURANT_DISHMENU;

drop table if exists RESTAURANT_DISH_INGREDIENTS;

drop table if exists RESTAURANT_DRINKMENU;

drop table if exists RESTAURANT_DRINK_INGREDIENTS;

drop table if exists RESTAURANT_MENU_MEDIAS;

/*==============================================================*/
/* Table: RESTAURANT_DISHMENU                                   */
/*==============================================================*/

create table RESTAURANT_DISHMENU
(
    DISH_MENU_ID      bigint        not null,
    RESTAURANT_ID     varchar(64)   not null,
    DISH_NAME         varchar(64)   not null,
    DISH_TYPE         varchar(32)   not null comment 'dish type to follow the dish course, such as starter/salad/soup/main, etc',
    CATEGORY          varchar(64),
    SUB_CATEGORY      varchar(128),
    SHORT_DESC        varchar(256),
    LONG_DESC         text,
    LIST_PRICE        double,
    SALE_PRICE        double,
    SALE_START_DATE    datetime,
    SALE_END_DATE      datetime,
    SPICY_LEVEL     varchar(32),
    SERVE_INSTRUCT  varchar(512)         comment 'special serving instructions (optional)',
    CALORIES        double,
    AVAILABILITY    double,
    MENU_STATUS     varchar(32)    not null,
    MENU_STATUS_DATE  datetime     not null,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (DISH_MENU_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);

create index DISH_BYRESTAURANT on RESTAURANT_DISHMENU(RESTAURANT_ID);

/*==============================================================*/
/* Table: RESTAURANT_DISH_INGREDIENTS                           */
/*==============================================================*/

create table RESTAURANT_DISH_INGREDIENTS
(
    DISH_INGREDIENT_ID   bigint        not null,
    DISH_MENU_ID         bigint        not null,
    INGREDIENT_NAME      varchar(64)   not null,
    ORIGIN               varchar(128),
    DESCRIPTION          varchar(256),
    OPTIONAL             boolean,
    EXTRA_PRICE          double,
    CALORIES             double,
    STATUS               varchar(32),
    STATUS_DATE          datetime,
    
    primary key (DISH_INGREDIENT_ID),
    foreign key (DISH_MENU_ID) 
        REFERENCES RESTAURANT_DISHMENU(DISH_MENU_ID)
        ON DELETE CASCADE
);

/*==============================================================*/
/* Table: RESTAURANT_DRINKMENU                                  */
/*==============================================================*/

create table RESTAURANT_DRINKMENU
(
    DRINK_MENU_ID       bigint        not null,
    RESTAURANT_ID       varchar(64)   not null,
    DRINK_NAME          varchar(64)   not null,
    DRINK_TYPE          varchar(32)   not null comment 'drink type such as water/soda/beer/wine, etc',
    CATEGORY            varchar(64),
    SUB_CATEGORY        varchar(128),
    SHORT_DESC          varchar(256),
    LONG_DESC           text,
    LIST_PRICE          double,
    SALE_PRICE          double,
    SALE_START_DATE     datetime,
    SALE_END_DATE       datetime,
    ALCOHOL_LEVEL       double        not null,
    ORIGIN_PLACE        varchar(256),
    SERVE_INSTRUCT      varchar(512)        comment 'special serving instructions (optional)',
    CONTAINER           varchar(32),
    VOLUME              double,
    VOLUME_UNIT         varchar(32),
    CALORIES            double,
    AVAILABILITY        double,
    MENU_STATUS         varchar(32)    not null,
    MENU_STATUS_DATE    datetime     not null,
    
    CREATION_DATE        DATETIME       not null comment 'auditing column: the creation date/time',
    CREATED_BY           varchar(64)    not null comment 'auditing column: the creator name',
    LAST_UPDATED_DATE    TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment 'auditing column: the last updated date/time',
    LAST_UPDATED_BY      varchar(64)    not null comment 'auditing column: the last updated user name',
    
    primary key (DRINK_MENU_ID),
    foreign key (RESTAURANT_ID) 
        REFERENCES BUSINESS_RESTAURANTS(RESTAURANT_ID)
        ON DELETE CASCADE
);


create index DRINK_BYRESTAURANT on RESTAURANT_DRINKMENU(RESTAURANT_ID);

/*==============================================================*/
/* Table: RESTAURANT_DISH_INGREDIENTS                           */
/*==============================================================*/

create table RESTAURANT_DRINK_INGREDIENTS
(
    DRINK_INGREDIENT_ID   bigint        not null,
    DRINK_MENU_ID         bigint        not null,
    INGREDIENT_NAME       varchar(64)   not null,
    ORIGIN_PLACE          varchar(128),
    DESCRIPTION           varchar(256),
    OPTIONAL              boolean,
    EXTRA_PRICE           double,
    CALORIES              double,
    STATUS                varchar(32),
    STATUS_DATE           datetime,
    
    primary key (DRINK_INGREDIENT_ID),
    foreign key (DRINK_MENU_ID) 
        REFERENCES RESTAURANT_DRINKMENU(DRINK_MENU_ID)
        ON DELETE CASCADE
);

/*==============================================================*/
/* Table: RESTAURANT_MENU_MEDIAS                                */
/*==============================================================*/

create table RESTAURANT_MENU_MEDIAS
(
    MENU_MEDIA_ID         varchar(64)     not null,
    DISH_MENU_ID          bigint,
    DRINK_MENU_ID         bigint,
    MEDIA_NAME            varchar(128)    not null,
    MEDIA_TYPE            varchar(32)     not null,
    DETAIL_FORMAT         varchar(32),
    QUALITY               varchar(32),
    WIDTH_PIXEL           INT,
    HEIGHT_PIXEL          INT,
    VIDEO_LEN_SECONDS     bigint,
    DESCRIPTION           varchar(256),
    IS_EXPIRED            boolean,
    EXPIRATION_DATE       datetime,
    
    primary key (MENU_MEDIA_ID)
 );
 
 create index MEDIA_BY_DISH on RESTAURANT_MENU_MEDIAS(DISH_MENU_ID);
 
 create index MEDIA_BY_DRINK on RESTAURANT_MENU_MEDIAS(DRINK_MENU_ID);