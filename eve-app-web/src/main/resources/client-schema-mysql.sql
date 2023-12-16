CREATE TABLE `persistent_logins` (
                                     `username` varchar(64) NOT NULL,
                                     `series` varchar(64) NOT NULL,
                                     `token` varchar(64) NOT NULL,
                                     `last_used` timestamp NOT NULL,
                                     PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budget_item_details` (
                                           `id` bigint NOT NULL,
                                           `created_at` datetime(6) DEFAULT NULL,
                                           `is_active` bit(1) NOT NULL,
                                           `updated_at` datetime(6) DEFAULT NULL,
                                           `uuid` varchar(255) DEFAULT NULL,
                                           `actual_amount` decimal(38,2) DEFAULT NULL,
                                           `actual_date` datetime(6) DEFAULT NULL,
                                           `done` bit(1) NOT NULL,
                                           `expected_amount` decimal(38,2) DEFAULT NULL,
                                           `expected_date` datetime(6) DEFAULT NULL,
                                           `is_success` bit(1) NOT NULL,
                                           `name` varchar(255) DEFAULT NULL,
                                           `notes` varchar(255) DEFAULT NULL,
                                           `planned` bit(1) NOT NULL,
                                           `budget_item_id` bigint DEFAULT NULL,
                                           PRIMARY KEY (`id`),
                                           KEY `FKbbsr71nciabr2uptgugawlq04` (`budget_item_id`),
                                           CONSTRAINT `FKbbsr71nciabr2uptgugawlq04` FOREIGN KEY (`budget_item_id`) REFERENCES `tbl_budget_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budget_item_details_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budget_items` (
                                    `id` bigint NOT NULL,
                                    `created_at` datetime(6) DEFAULT NULL,
                                    `is_active` bit(1) NOT NULL,
                                    `updated_at` datetime(6) DEFAULT NULL,
                                    `uuid` varchar(255) DEFAULT NULL,
                                    `actual_amount` decimal(38,2) DEFAULT NULL,
                                    `actual_date` datetime(6) DEFAULT NULL,
                                    `done` bit(1) NOT NULL,
                                    `expected_amount` decimal(38,2) DEFAULT NULL,
                                    `expected_date` datetime(6) DEFAULT NULL,
                                    `is_success` bit(1) NOT NULL,
                                    `item_name` varchar(255) DEFAULT NULL,
                                    `notes` varchar(255) DEFAULT NULL,
                                    `planned` bit(1) NOT NULL,
                                    `budget_id` bigint DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `FKqtv4iec6t24bghd7px6n89ta1` (`budget_id`),
                                    CONSTRAINT `FKqtv4iec6t24bghd7px6n89ta1` FOREIGN KEY (`budget_id`) REFERENCES `tbl_budgets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budget_items_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budgets` (
                               `id` bigint NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `is_active` bit(1) NOT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `uuid` varchar(255) DEFAULT NULL,
                               `actual_amount` decimal(38,2) DEFAULT NULL,
                               `begins_at` datetime(6) DEFAULT NULL,
                               `budget_comment` varchar(255) DEFAULT NULL,
                               `budget_name` varchar(255) DEFAULT NULL,
                               `budget_type` varchar(255) DEFAULT NULL,
                               `done` bit(1) NOT NULL,
                               `ends_at` datetime(6) DEFAULT NULL,
                               `expected_amount` decimal(38,2) DEFAULT NULL,
                               `is_success` bit(1) NOT NULL,
                               `group_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FK3dl1fwa54umei4r7bogv92d1o` (`group_id`),
                               CONSTRAINT `FK3dl1fwa54umei4r7bogv92d1o` FOREIGN KEY (`group_id`) REFERENCES `tbl_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_budgets_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_groups` (
                              `id` bigint NOT NULL,
                              `created_at` datetime(6) DEFAULT NULL,
                              `is_active` bit(1) NOT NULL,
                              `updated_at` datetime(6) DEFAULT NULL,
                              `uuid` varchar(255) DEFAULT NULL,
                              `group_name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_groups_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_roles` (
                             `id` bigint NOT NULL,
                             `created_at` datetime(6) DEFAULT NULL,
                             `is_active` bit(1) NOT NULL,
                             `updated_at` datetime(6) DEFAULT NULL,
                             `uuid` varchar(255) DEFAULT NULL,
                             `role_name` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_roles_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_user_groups` (
                                   `id` bigint NOT NULL,
                                   `created_at` datetime(6) DEFAULT NULL,
                                   `is_active` bit(1) NOT NULL,
                                   `updated_at` datetime(6) DEFAULT NULL,
                                   `uuid` varchar(255) DEFAULT NULL,
                                   `valid_from` datetime(6) DEFAULT NULL,
                                   `valid_till` datetime(6) DEFAULT NULL,
                                   `app_group_id` bigint DEFAULT NULL,
                                   `user_id` bigint DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `FK8nbykqylulkxr8rxy1gt3apsf` (`app_group_id`),
                                   KEY `FKoaj42o39kviaok2xk2e10q0o3` (`user_id`),
                                   CONSTRAINT `FK8nbykqylulkxr8rxy1gt3apsf` FOREIGN KEY (`app_group_id`) REFERENCES `tbl_groups` (`id`),
                                   CONSTRAINT `FKoaj42o39kviaok2xk2e10q0o3` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_user_groups_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_user_roles` (
                                  `id` bigint NOT NULL,
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `is_active` bit(1) NOT NULL,
                                  `updated_at` datetime(6) DEFAULT NULL,
                                  `uuid` varchar(255) DEFAULT NULL,
                                  `valid_from` datetime(6) DEFAULT NULL,
                                  `valid_till` datetime(6) DEFAULT NULL,
                                  `role_id` bigint DEFAULT NULL,
                                  `user_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKhyxi2wldijd6vrfaoaad6lgs8` (`role_id`),
                                  KEY `FKm7ynujk9mh232fvdwx0qfl1m3` (`user_id`),
                                  CONSTRAINT `FKhyxi2wldijd6vrfaoaad6lgs8` FOREIGN KEY (`role_id`) REFERENCES `tbl_roles` (`id`),
                                  CONSTRAINT `FKm7ynujk9mh232fvdwx0qfl1m3` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_user_roles_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_users` (
                             `id` bigint NOT NULL,
                             `created_at` datetime(6) DEFAULT NULL,
                             `is_active` bit(1) NOT NULL,
                             `updated_at` datetime(6) DEFAULT NULL,
                             `uuid` varchar(255) DEFAULT NULL,
                             `email` varchar(255) DEFAULT NULL,
                             `first_name` varchar(255) DEFAULT NULL,
                             `is_locked` bit(1) NOT NULL,
                             `joined_at` datetime(6) DEFAULT NULL,
                             `last_name` varchar(255) DEFAULT NULL,
                             `password` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_users_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
