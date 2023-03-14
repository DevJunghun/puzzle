-- drop flyway init table
drop table flyway_schema_history if exist;

-- table init
create table users
(
    uuid       not null varchar(36),
    id         not null varchar(255),
    password   not null varchar(255),
    created_at not null datetime(6),
    updated_at null datetime(6),

    Primary Key (uuid)
);

create table user_emails
(
    uuid       not null varchar(36),
    user_uuid  not null varchar(36),
    email      not null varchar(255),
    password   not null varchar(255),
    type       not null ENUM('GMAIL', 'NAVER'),
    created_at not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table address_group
(
    uuid             not null varchar(36),
    user_uuid        not null varchar(36),
    name             not null varchar(255),
    has_parent_group not null tinyint default 0,
    parent_group_uuid null varchar (36),
    created_at       not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table address
(
    uuid              not null varchar(36),
    name              not null varchar(255),
    group_uuid        not null varchar(36),
    email             not null varchar(255),
    phone_number null varchar (255),
    use_count         not null bigint default 0,
    has_business_card not null tinyint default 0,
    created_at        not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table business_cards
(
    uuid         not null varchar(36),
    address_uuid not null varchar(36),
    content      not null text,
    has_image    not null tinyint(1) default 0,
    created_at   not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table business_card_image
(
    uuid         not null varchar(36),
    address_uuid not null varchar(36),
    image null blob,
    created_at   not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table mail_logs
(
    uuid varchar(36),
    user_uuid          not null varchar(36),
    email_account_uuid not null varchar(36),
    mail_uuid          not nul varchar (36),
    type               not null ENUM('CONVERSATION', 'MOVEMENT', 'RESERVATION'),
    action_at          not null datetime(6),

    primary key (uuid)
);

create table mail_conversation_logs
(
    mail_log_uuid not null varchar(36),
    with_address  not null tinyint default 0,
    address_uuid null varchar (36),
    success tinyint default 1,
    type    ENUM('SEND', 'RECEIVE'),
    action_at     not null datetime(6)
);

create table mail_reservation_logs
(
    mail_log_uuid not null varchar(36),
    with_address  not null tinyint default 0,
    address_uuid null varchar (36),
    success tinyint default 1,
    type    ENUM('RESERVE', 'RESERVE_SEND'),
    action_at     not null datetime(6)
);

create table mail_movement_logs
(
    mail_log_uuid not null varchar(36),
    from ENUM('IMPORTANT', 'NORMAL', 'TRASH'),
    to   ENUM('IMPORTANT', 'NORMAL', 'TRASH'),
    action_at     not null datetime(6)
);

create table template_groups
(
    uuid             not null varchar(36),
    user_uuid        not null varchar(36),
    name             not null varchar(255),
    has_parent_group not null tinyint default 0,
    parent_group_uuid null varchar (36),
    created_at       not null datetime(6),
    updated_at null datetime(6),

    primary key (uuid)
);

create table templates
(
    uuid       not null varchar(36),
    user_uuid  not null varchar(36),
    group_uuid not null varchar(36),
    name       not null varchar(255),
    contents   not null text,
    favorite   not null tinyint default 0,
    created_at not null datetime(6),
    updated_at not null datetime(6),

    primary key (uuid)
);

create table auth_logs
(
    user_uuid not null varchar(36),
    type      not null ENUM('LOGIN', 'LOGOUT'),
    success   not null tinyint default 1,
    error_message null text
);

create table reminders
(
    uuid      not null varchar(36),
    user_uuid not null varchar(36),
    type      not null ENUM('RECEIVE', 'SEND'),

    primary key (uuid)
);

create table receive_reminders
(
    uuid                  not null varchar(36),
    reminder_uuid         not null varchar(36),
    email_account_uuid    not null varchar(36),
    mail_uuid             not null varchar(36),
    read                  not null tinyint default 0,
    after_remind_datetime not null datetime(6),
    before_remind_datetime null datetime(6),

    primary key (uuid)
);

create table send_reminders
(
    uuid                  not null varchar(36),
    reminder_uuid         not null varchar(36),
    email_account_uuid    not null varchar(36),
    mail_uuid             not null varchar(36),
    read                  not null tinyint default 0,
    after_remind_datetime not null datetime(6),
    before_remind_datetime null datetime(6),

    primary key (uuid)
);

create table highlights
(
    user_uuid          not null varchar(36),
    email_account_uuid not null varchar(36),
    mail_uuid          not null varchar(36),
    from               not null varchar(255),
    to                 not null varchar(255),
    created_at         not null datetime(6),
    updated_at         not null datetime(6),
);
