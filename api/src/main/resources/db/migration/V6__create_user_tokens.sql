CREATE TABLE `user_tokens` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `uuid` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `user_uuid` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `user_token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               PRIMARY KEY (`id`)
) ;