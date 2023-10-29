DROP TABLE IF EXISTS `phone_auth`;
DROP TABLE IF EXISTS `access_log`;

DROP TABLE IF EXISTS `q_user_match_process`;
DROP TABLE IF EXISTS `q_match_process`;
DROP TABLE IF EXISTS `q_user_match`;
DROP TABLE IF EXISTS `q_match`;

DROP TABLE IF EXISTS `q_notice_board_pinned`;
DROP TABLE IF EXISTS `q_comment_reply`;
DROP TABLE IF EXISTS `q_post_likes`;
DROP TABLE IF EXISTS `q_scrap`;
DROP TABLE IF EXISTS `q_comment`;
DROP TABLE IF EXISTS `q_post`;
DROP TABLE IF EXISTS `q_notice_board`;
DROP TABLE IF EXISTS `q_hashtag`;

DROP TABLE IF EXISTS `q_user`;



CREATE TABLE `q_user`
(
    `id`                   bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_role`            varchar(10)  NOT NULL DEFAULT 'MEMBER',
    `login_id`             varchar(50)  NOT NULL,
    `password`             varchar(100) NOT NULL,
    `username`             VARCHAR(50)  NOT NULL,
    `birthday`             date         NULL,
    `gender`               varchar(10)  NULL,
    `proficiency`          int          NOT NULL DEFAULT 0,
    `phone`                VARCHAR(13)  NULL,
    `fcm_token`            varchar(255) NULL,
    `profile_image`        text         NULL,
    `address`              varchar(100) NULL,
    `detail_address`       varchar(100) NULL,
    `join_staff`           bigint       NULL,
    `is_resting`           tinyint(1)   NOT NULL DEFAULT false,
    `is_leaving`           tinyint(1)   NOT NULL DEFAULT false,
    `phone_auth_code`      varchar(50)  NULL,
    `phone_auth_at`        datetime     NULL,
    `is_phone_auth`        tinyint(1)   NULL     DEFAULT false,
    `join_at`              datetime     NOT NULL,
    `join_agree`           tinyint(1)   NOT NULL DEFAULT false,
    `agree_update_at`      datetime     NULL,
    `access_at`            datetime     NULL,
    `access_count`         int          NOT NULL DEFAULT 0,
    `status_message`       varchar(255) NULL,
    `average`              int          NOT NULL DEFAULT 0,
    `match_count`          int          NOT NULL DEFAULT 0,
    `high_run`             int          NOT NULL DEFAULT 0,
    `total_inning_count`   int          NOT NULL DEFAULT 0,
    `succeed_inning_count` int          NOT NULL DEFAULT 0,
    `failed_inning_count`  int          NOT NULL DEFAULT 0,
    `slugging_count`       int          NOT NULL DEFAULT 0,
    `batting_average`      int          NOT NULL DEFAULT 0,
    `slugging_percentage`  int          NOT NULL DEFAULT 0,
    FOREIGN KEY (join_staff) REFERENCES q_user (id),
    CONSTRAINT user_login_id_uq UNIQUE (login_id),
    CONSTRAINT user_username_uq UNIQUE (username)
);

CREATE TABLE `q_match`
(
    `id`              bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `match_type_code` int         NOT NULL,
    `match_type_name` varchar(10) NOT NULL,
    `created_at`      datetime    NOT NULL,
    `updated_at`      datetime    NOT NULL,
    `is_deleted`      tinyint(1)  NOT NULL DEFAULT false,
    `is_valid`        tinyint(1)  NULL,
    `end_at`          datetime    NULL,
    `duration`        time        NULL,
    `user_count`      int         NOT NULL DEFAULT 1
);

CREATE TABLE `q_user_match`
(
    `id`                            bigint     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`                       bigint     NOT NULL,
    `match_id`                      bigint     NOT NULL,
    `target_score`                  int        NOT NULL,
    `finish_cushion_target_score`   int        NULL     DEFAULT 0,
    `finish_bank_shot_target_score` int        NULL     DEFAULT 0,
    `created_at`                    datetime   NOT NULL,
    `updated_at`                    datetime   NOT NULL,
    `is_deleted`                    tinyint(1) NOT NULL DEFAULT false,
    `score`                         int        NULL,
    `finish_cushion_score`          int        NULL,
    `finish_bank_shot_score`        int        NULL,
    `ranking`                       int        NULL,
    `max_high_run`                  int        NOT NULL DEFAULT 0,
    `average`                       int        NULL,
    `inning_count`                  int        NOT NULL DEFAULT 0,
    `succeed_inning_count`          int        NOT NULL DEFAULT 0,
    `failed_inning_count`           int        NOT NULL DEFAULT 0,
    `slugging_count`                int        NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES q_user (id),
    FOREIGN KEY (match_id) REFERENCES q_match (id)
);

CREATE TABLE `q_match_process`
(
    `id`            bigint     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `match_id`      bigint     NOT NULL,
    `player_id`     bigint     NOT NULL,
    `duration`      time       NOT NULL,
    `process_count` int        NOT NULL,
    `turn_count`    int        NOT NULL,
    `phase_count`   int        NOT NULL,
    `is_valid`      tinyint(1) NOT NULL DEFAULT true,
    FOREIGN KEY (match_id) REFERENCES q_match (id),
    FOREIGN KEY (player_id) REFERENCES q_user (id)
);

CREATE TABLE `q_user_match_process`
(
    `id`                     bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`                bigint      NOT NULL,
    `match_process_id`       bigint      NOT NULL,
    `score`                  int         NOT NULL,
    `finish_cushion_score`   int         NOT NULL,
    `finish_bank_shot_score` int         NOT NULL,
    `ranking`                int         NOT NULL,
    `status`                 varchar(10) NOT NULL,
    `max_high_run`           int         NOT NULL DEFAULT 0,
    `high_run`               int         NOT NULL DEFAULT 0,
    `delta_score`            int         NOT NULL DEFAULT 0,
    `turn_type`              varchar(10) NOT NULL,
    `is_my_turn`             tinyint(1)  NOT NULL,
    `inning_count`           int         NOT NULL,
    `succeed_inning_count`   int         NOT NULL,
    `failed_inning_count`    int         NOT NULL,
    FOREIGN KEY (match_process_id) REFERENCES q_match_process (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `phone_auth`
(
    `id`            bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `phone`         varchar(13) NOT NULL,
    `phone_code`    varchar(6)  NOT NULL,
    `created_at`    datetime    NOT NULL,
    `updated_at`    datetime    NOT NULL,
    `is_deleted`    tinyint(1)  NOT NULL DEFAULT false,
    `expired_at`    datetime    NOT NULL,
    `is_phone_auth` tinyint(1)  NOT NULL DEFAULT false
);

CREATE TABLE `access_log`
(
    `id`          bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`     bigint      NOT NULL,
    `access_ipv4` int         NULL,
    `access_ipv6` int         NULL,
    `access_at`   datetime    NOT NULL,
    `access_path` varchar(45) NULL,
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `q_notice_board`
(
    `id`                  bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `board_name`          varchar(100) NOT NULL,
    `anonymous_possible`  tinyint(1)   NOT NULL DEFAULT true,
    `is_admin_possible`   tinyint(1)   NOT NULL DEFAULT true,
    `is_manager_possible` tinyint(1)   NOT NULL DEFAULT true,
    `is_member_possible`  tinyint(1)   NOT NULL DEFAULT true,
    `created_at`          datetime     NOT NULL,
    `updated_at`          datetime     NOT NULL,
    `is_deleted`          tinyint(1)   NOT NULL DEFAULT false,
    CONSTRAINT notice_board_board_name_uq UNIQUE (board_name)
);

CREATE TABLE `q_notice_board_pinned`
(
    `id`              bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `notice_board_id` bigint NOT NULL,
    `user_id`         bigint NOT NULL,
    FOREIGN KEY (notice_board_id) REFERENCES q_notice_board (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `q_hashtag`
(
    `id`   bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) NULL,
    CONSTRAINT hashtag_name_uq UNIQUE (name)
);

CREATE TABLE `q_post`
(
    `id`              bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `notice_board_id` bigint       NOT NULL,
    `user_id`         bigint       NOT NULL,
    `hashtag_id`      bigint       NULL,
    `is_anonymous`    tinyint(1)   NOT NULL,
    `title`           varchar(100) NULL,
    `content`         text         NULL,
    `created_at`      datetime     NOT NULL,
    `updated_at`      datetime     NOT NULL,
    `is_deleted`      tinyint(1)   NOT NULL DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES q_user (id),
    FOREIGN KEY (notice_board_id) REFERENCES q_notice_board (id),
    FOREIGN KEY (hashtag_id) REFERENCES q_hashtag (id)
);

CREATE TABLE `q_comment`
(
    `id`           bigint     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id`      bigint     NOT NULL,
    `post_id`      bigint     NOT NULL,
    `content`      text       NULL,
    `created_at`   datetime   NOT NULL,
    `updated_at`   datetime   NOT NULL,
    `is_anonymous` tinyint(1) NOT NULL,
    `is_deleted`   tinyint(1) NOT NULL DEFAULT false,
    FOREIGN KEY (post_id) REFERENCES q_post (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `q_scrap`
(
    `id`      bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `post_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    FOREIGN KEY (post_id) REFERENCES q_post (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `q_post_likes`
(
    `id`      bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `post_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    FOREIGN KEY (post_id) REFERENCES q_post (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);

CREATE TABLE `q_comment_reply`
(
    `id`         bigint     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `comment_id` bigint     NOT NULL,
    `user_id`    bigint     NOT NULL,
    `created_at` datetime   NOT NULL,
    `updated_at` datetime   NOT NULL,
    `is_deleted` tinyint(1) NOT NULL DEFAULT false,
    FOREIGN KEY (comment_id) REFERENCES q_comment (id),
    FOREIGN KEY (user_id) REFERENCES q_user (id)
);