# DROP TABLE IF EXISTS `q_user`;
# DROP TABLE IF EXISTS `q_match`;
# DROP TABLE IF EXISTS `q_user_match`;
# DROP TABLE IF EXISTS `q_match_process`;
# DROP TABLE IF EXISTS `q_user_match_process`;
# DROP TABLE IF EXISTS `phone_auth`;
# DROP TABLE IF EXISTS `access_log`;

CREATE TABLE `q_user` (
                          `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                          `user_role`	varchar(10)	NOT NULL	DEFAULT 'MEMBER',
                          `login_id`	varchar(50)	NOT NULL,
                          `password`	varchar(100)	NOT NULL,
                          `username`	VARCHAR(20)	NULL,
                          `birthday`	date	NULL,
                          `gender`	varchar(10)	NULL,
                          `proficiency`	int	NOT NULL	DEFAULT 0,
                          `phone`	VARCHAR(13)	NULL,
                          `fcm_token`	varchar(255)	NULL,
                          `profile_image`	varchar(255)	NULL,
                          `address`	varchar(50)	NULL,
                          `detail_address`	varchar(50)	NULL,
                          `join_staff`	bigint	NULL,
                          `is_resting`	tinyint(1)	NOT NULL	DEFAULT false,
                          `is_leaving`	tinyint(1)	NOT NULL	DEFAULT false,
                          `phone_auth_code`	varchar(50)	NULL,
                          `phone_auth_at`	datetime	NULL,
                          `is_phone_auth`	tinyint(1)	NULL	DEFAULT false,
                          `join_at`	datetime	NOT NULL	DEFAULT now(),
                          `join_agree`	tinyint(1)	NOT NULL	DEFAULT false,
                          `agree_update_at`	datetime	NULL,
                          `access_at`	datetime	NULL,
                          `access_count`	int	NOT NULL	DEFAULT 0,
                          `status_message`	varchar(255)	NULL,
                          `average`	int	NOT NULL	DEFAULT 0,
                          `match_count`	int	NOT NULL	DEFAULT 0,
                          `high_run`	int	NOT NULL	DEFAULT 0,
                          `total_inning_count`	int	NOT NULL	DEFAULT 0,
                          `succeed_inning_count`	int	NOT NULL	DEFAULT 0,
                          `failed_inning_count`	int	NOT NULL	DEFAULT 0,
                          `slugging_count`	int	NOT NULL	DEFAULT 0,
                          `batting_average`	int	NOT NULL	DEFAULT 0,
                          `slugging_percentage`	int	NOT NULL	DEFAULT 0,
                          FOREIGN KEY (join_staff) REFERENCES q_user (id)
);

CREATE TABLE `q_match` (
                           `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                           `match_type_code`	int	NOT NULL,
                           `match_type_name`	varchar(10)	NOT NULL,
                           `created_at`	datetime	NOT NULL	DEFAULT now(),
                           `updated_at`	datetime	NOT NULL	DEFAULT now() on update current_timestamp,
                           `is_deleted`	tinyint(1)	NOT NULL	DEFAULT false,
                           `is_valid`	tinyint(1)	NULL,
                           `end_at`	datetime	NULL,
                           `duration`	time	NULL,
                           `user_count`	int	NOT NULL	DEFAULT 1
);

CREATE TABLE `q_user_match` (
                                `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                                `user_id`	bigint	NOT NULL,
                                `match_id`	bigint	NOT NULL,
                                `target_score`	int	NOT NULL,
                                `finish_cushion_target_score`	int	NULL	DEFAULT 0,
                                `finish_bank_shot_target_score`	int	NULL	DEFAULT 0,
                                `created_at`	datetime	NOT NULL	DEFAULT now(),
                                `updated_at`	datetime	NOT NULL	DEFAULT now() on update current_timestamp,
                                `is_deleted`	tinyint(1)	NOT NULL	DEFAULT false,
                                `score`	int	NULL,
                                `finish_cushion_score`	int	NULL,
                                `finish_bank_shot_score`	int	NULL,
                                `ranking`	int	NULL,
                                `max_high_run`	int	NOT NULL	DEFAULT 0,
                                `average`	int	NULL,
                                `inning_count`	int	NOT NULL	DEFAULT 0,
                                `succeed_inning_count`	int	NOT NULL	DEFAULT 0,
                                `failed_inning_count`	int	NOT NULL	DEFAULT 0,
                                `slugging_count`	int	NOT NULL	DEFAULT 0,
                                FOREIGN KEY (user_id) REFERENCES q_user (id),
                                FOREIGN KEY (match_id) REFERENCES q_match (id)
);

CREATE TABLE `q_match_process` (
                                   `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                                   `match_id`	bigint	NOT NULL,
                                   `player_id`	bigint	NOT NULL,
                                   `duration`	time	NOT NULL,
                                   `process_count`	int	NOT NULL,
                                   `turn_count`	int	NOT NULL,
                                   `phase_count`	int	NOT NULL,
                                   `is_valid`	tinyint(1)	NOT NULL	DEFAULT true,
                                   FOREIGN KEY (match_id) REFERENCES q_match (id),
                                   FOREIGN KEY (player_id) REFERENCES q_user (id)
);

CREATE TABLE `q_user_match_process` (
                                        `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                                        `user_id`	bigint	NOT NULL,
                                        `match_process_id`	bigint	NOT NULL,
                                        `score`	int	NOT NULL	DEFAULT 0,
                                        `finish_cushion_score`	int	NOT NULL	DEFAULT 0,
                                        `finish_bank_shot_score`	int	NOT NULL	DEFAULT 0,
                                        `ranking`	int	NOT NULL	DEFAULT 99,
                                        `status`	varchar(10)	NOT NULL,
                                        `max_high_run`	int	NOT NULL	DEFAULT 0,
                                        `high_run`	int	NOT NULL	DEFAULT 0,
                                        `delta_score`	int	NOT NULL	DEFAULT 0,
                                        `turn_type`	varchar(10)	NOT NULL,
                                        `is_my_turn`	tinyint(1)	NOT NULL	DEFAULT false,
                                        `inning_count`	int	NOT NULL,
                                        `succeed_inning_count`	int	NOT NULL,
                                        `failed_inning_count`	int	NOT NULL,
                                        FOREIGN KEY (match_process_id) REFERENCES q_match_process (id),
                                        FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `phone_auth` (
                              `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                              `phone`	varchar(13)	NOT NULL,
                              `phone_code`	varchar(45)	NOT NULL,
                              `created_at`	datetime	NOT NULL	DEFAULT now(),
                              `updated_at`	datetime	NOT NULL	DEFAULT now() on update current_timestamp,
                              `is_deleted`	tinyint(1)	NOT NULL	DEFAULT false,
                              `expired_at`	datetime	NOT NULL,
                              `is_phone_auth`	tinyint(1)	NOT NULL	DEFAULT false
);

CREATE TABLE `access_log` (
                              `id`	bigint	NOT NULL	PRIMARY KEY AUTO_INCREMENT,
                              `user_id`	bigint	NOT NULL,
                              `access_ipv4`	int	NULL,
                              `access_ipv6`	int	NULL,
                              `access_at`	datetime	NOT NULL	DEFAULT now(),
                              `access_path`	varchar(45)	NULL,
                              FOREIGN KEY (user_id) REFERENCES q_user (id)
);
