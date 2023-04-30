ALTER TABLE `addresses`
    ADD COLUMN `rank` varchar(255) NULL AFTER `group_uuid`;

ALTER TABLE `addresses`
    ADD COLUMN `department` varchar(255) NULL AFTER `rank`;

ALTER TABLE `addresses`
    ADD COLUMN `company_name` varchar(255) NULL AFTER `department`;