-- drop flyway init table
-- drop table if exists flyway_schema_history;

-- table init
create table if not exists users
(
    id bigint not null auto_increment,
    uuid       varchar(36)  not null,
    username         varchar(255) not null,
    password   varchar(255) not null,
    deleted tinyint not null default 0,
    created_at datetime(6) not null,
    updated_at datetime(6) null,

    Primary Key (id)
);

create table if not exists user_emails
(
    id bigint not null auto_increment,
    uuid       varchar(36)  not null,
    user_uuid  varchar(36)  not null,
    email      varchar(255) not null,
    password   varchar(255) null,
    type       ENUM('GMAIL', 'NAVER') not null,
    deleted tinyint not null default 0,
    created_at datetime(6) not null,
    updated_at datetime(6) null,

    primary key (id)
);

create table if not exists address_groups
(
    id bigint not null auto_increment,
    uuid              varchar(36)  not null,
    user_uuid         varchar(36)  not null,
    name              varchar(255) not null,
    default_group tinyint not null default 1,
    has_parent_group  tinyint      not null default 0,
    parent_group_uuid varchar(36) null,
    deleted tinyint not null default 0,
    created_at        datetime(6) not null,
    updated_at        datetime(6) null,

    primary key (id)
);

create table if not exists addresses
(
    id bigint not null auto_increment,
    uuid              varchar(36)  not null,
    name              varchar(255) not null,
    group_uuid        varchar(36)  not null,
    `rank`              varchar(255) NULL,
    `department`      varchar(255) NULL,
    `company_name`    varchar(255) NULL,
    email             varchar(255) not null,
    phone_number      varchar(255) null,
    use_count         bigint       not null default 0,
    has_business_card tinyint      not null default 0,
    deleted tinyint not null default 0,
    created_at        datetime(6) not null,
    updated_at        datetime(6) null,

    primary key (id)
);

create table if not exists business_cards
(
    id bigint not null auto_increment,
    uuid         varchar(36) not null,
    address_uuid varchar(36) not null,
    content      text        not null,
    has_image    tinyint(1) not null default 0,
    deleted tinyint not null default 0,
    created_at   datetime(6) not null,
    updated_at   datetime(6) null,

    primary key (id)
);

create table if not exists business_card_images
(
    id bigint not null auto_increment,
    uuid         varchar(36) not null,
    business_card_uuid varchar(36) not null,
    image        blob null,
    deleted tinyint not null default 0,
    created_at   datetime(6) not null,
    updated_at   datetime(6) null,

    primary key (id)
);

create table if not exists mail_logs
(
    id bigint not null auto_increment,
    uuid               varchar(36) not null,
    user_uuid          varchar(36) not null,
    email_account_uuid varchar(36) not null,
    mail_uuid          varchar(36) not null,
    type               ENUM('CONVERSATION', 'MOVEMENT', 'RESERVATION') not null,
    action_at          datetime(6) not null,

    primary key (id)
);

create table if not exists mail_conversation_logs
(
    mail_log_uuid varchar(36) not null,
    with_address  tinyint     not null default 0,
    address_uuid  varchar(36) null,
    success       tinyint     not null default 1,
    type          ENUM('SEND', 'RECEIVE') not null,
    action_at     datetime(6) not null
);

create table if not exists mail_reservation_logs
(
    mail_log_uuid varchar(36) not null,
    with_address  tinyint     not null default 0,
    address_uuid  varchar(36) not null,
    success       tinyint     not null default 1,
    type          ENUM('RESERVE', 'RESERVE_SEND') not null,
    action_at     datetime(6) not null
);

create table if not exists mail_movement_logs
(
    mail_log_uuid varchar(36) not null,
    `from`        ENUM('IMPORTANT', 'NORMAL', 'TRASH') not null,
    `to`          ENUM('IMPORTANT', 'NORMAL', 'TRASH') not null,
    action_at     datetime(6) not null
);

create table if not exists template_groups
(
    id bigint not null auto_increment,
    uuid              varchar(36)  not null,
    user_uuid         varchar(36)  not null,
    name              varchar(255) not null,
    has_parent_group  tinyint      not null default 0,
    parent_group_uuid varchar(36) null,
    deleted tinyint not null default 0,
    created_at        datetime(6) not null,
    updated_at        datetime(6) null,

    primary key (id)
);

create table if not exists templates
(
    id bigint not null auto_increment,
    uuid       varchar(36)  not null,
    user_uuid  varchar(36)  not null,
    group_uuid varchar(36)  not null,
    name       varchar(255) not null,
    contents   text         not null,
    favorite   tinyint      not null default 0,
    deleted tinyint not null default 0,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,

    primary key (id)
);

create table if not exists auth_logs
(
    id bigint not null auto_increment,
    user_uuid     varchar(36) not null,
    type          ENUM('LOGIN', 'LOGOUT') not null,
    success       tinyint     not null default 1,
    error_message text null,

    primary key (id)
);

create table if not exists reminders
(
    id bigint not null auto_increment,
    uuid      varchar(36) not null,
    user_uuid varchar(36) not null,
    type      ENUM('RECEIVE', 'SEND') not null,

    primary key (id)
);

create table if not exists receive_reminders
(
    id bigint not null auto_increment,
    uuid                   varchar(36) not null,
    reminder_uuid          varchar(36) not null,
    email_account_uuid     varchar(36) not null,
    mail_uuid              varchar(36) not null,
    `read`                 tinyint     not null default 0,
    after_remind_datetime  datetime(6) not null,
    before_remind_datetime datetime(6) null,

    primary key (id)
);

create table if not exists send_reminders
(
    id bigint not null auto_increment,
    uuid                   varchar(36) not null,
    reminder_uuid          varchar(36) not null,
    email_account_uuid     varchar(36) not null,
    mail_uuid              varchar(36) not null,
    `read`                 tinyint     not null default 0,
    after_remind_datetime  datetime(6) not null,
    before_remind_datetime datetime(6) null,

    primary key (id)
);

create table if not exists highlights
(
    user_uuid          varchar(36)  not null,
    email_account_uuid varchar(36)  not null,
    mail_uuid          varchar(36)  not null,
    `from`             varchar(255) not null,
    `to`               varchar(255) not null,
    created_at         datetime(6) not null,
    updated_at         datetime(6) not null
);


create table if not exists poll_results
(
    job     ENUM('STUDENT', 'EMPLOYEE', 'TEACHER') not null,
    purpose ENUM('NO_THANKS') not null
);

ALTER TABLE `users`
    ADD COLUMN `email` varchar(255) NOT NULL AFTER `username`;

ALTER TABLE `business_cards`
    ADD COLUMN `name` varchar(255) NOT NULL AFTER `address_uuid`;

ALTER TABLE `business_cards`
    ADD COLUMN `email` varchar(255) NOT NULL AFTER `name`;

ALTER TABLE `business_cards`
    ADD COLUMN `rank` varchar(255) NULL AFTER `email`;

ALTER TABLE `business_cards`
    ADD COLUMN `department` varchar(255) NULL AFTER `rank`;

ALTER TABLE `business_cards`
    ADD COLUMN `phone_number` varchar(255) NULL AFTER `department`;

ALTER TABLE `business_cards`
    ADD COLUMN `company_name` varchar(255) NULL AFTER `phone_number`;

ALTER TABLE `business_cards`
    ADD COLUMN `company_url` varchar(255) NULL AFTER `company_name`;

ALTER TABLE `business_cards`
    ADD COLUMN `company_address` varchar(255) NULL AFTER `company_url`;

ALTER TABLE `business_cards`
    DROP COLUMN `content`;

ALTER TABLE `business_cards`
    DROP COLUMN `has_image`;



