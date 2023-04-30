ALTER TABLE `business_cards`
    ADD COLUMN `name` varchar(255) NOT NULL AFTER `address_uuid`;

ALTER TABLE `business_cards`
    ADD COLUMN `email` varchar(255) NOT NULL AFTER `name`;

ALTER TABLE `business_cards`
    ADD COLUMN `rank` varchar(255) NULL AFTER `email`;

ALTER TABLE `business_cards`
    ADD COLUMN `phone_number` varchar(255) NULL AFTER `rank`;

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

