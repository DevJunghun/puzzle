-- drop flyway init table
drop table flyway_schema_history if exist;

create table users
(
    uuid     not null varchar(36),
    id       not null varchar(255),
    password not null varchar(255),

    Primary Key (uuid)
);

create table user_emails
(
    uuid     not null varchar(36),
    email    not null varchar(255),
    password not null varchar(255),
    type     not null ENUM('GMAIL', 'NAVER'),

    primary key (uuid)
);

create table address_group
(
    uuid not null varchar(36),
    name not null varchar(255),

    primary key (uuid)
);

create table address
(
    uuid              not null varchar(36),
    name              not null varchar(255),
    email             not null varchar(255),
    phone_number null varchar (255),
    use_count         not null bigint default 0,
    has_business_card not null tinyint default 0,

    primary key (uuid)
);

create table business_cards
(
    uuid      not null varchar(36),
    content   not null text,
    has_image not null tinyint(1) default 0,

    primary key (uuid);
);

create table business_card_image
(
    uuid not null varchar(36),
    image null blob,

    primary key (uuid);
)
