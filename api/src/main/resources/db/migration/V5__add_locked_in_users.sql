ALTER TABLE `users`
    ADD COLUMN `locked` tinyint not null default 0 after `password`;