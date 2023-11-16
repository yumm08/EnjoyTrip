use enjoytrip;

Drop table trip_member;
CREATE TABLE trip_member (
                         user_id       VARCHAR(20),
                         user_name     VARCHAR(20),
                         user_nickname	VARCHAR(20),
                         user_password    VARCHAR(20),
                         user_email		VARCHAR(20),
                         user_domain	VARCHAR(20),
                         PRIMARY KEY(user_id)
);

Drop table free_board;
CREATE TABLE `free_board` (
                    `article_no` int NOT NULL AUTO_INCREMENT,
                    `user_id` varchar(16) DEFAULT NULL,
                    `subject` varchar(100) DEFAULT NULL,
                    `content` varchar(1000) DEFAULT NULL,
                    `article_hit` int DEFAULT '1',
                    `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

Drop table plan_board;
CREATE TABLE `plan_board` (
                     `article_no` int NOT NULL AUTO_INCREMENT,
                     `user_id` varchar(16) DEFAULT NULL,
                     `article_title` varchar(100) DEFAULT NULL,
                     `article_hit` int DEFAULT '1',
                     `bookmark_cnt` int DEFAULT '0',
                     `plan_no`	int NOT NULL UNIQUE,
                     `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

Drop table plan_list;
CREATE TABLE plan_list (
                        user_id         VARCHAR(20),
                        plan_no         int NOT NULL AUTO_INCREMENT,
                        plan_title	    VARCHAR(100),
                        `update_time`   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY(plan_no, user_id)
);

Drop table plan_seq;
CREATE TABLE plan_seq (
                      plan_no       int,
                      plan_date	    int,
                      plan_seq		int,
                      content_id	int,
                      PRIMARY KEY(plan_no, plan_date, plan_seq)
);

Drop table bookmark;
CREATE TABLE bookmark (
                      user_id     	VARCHAR(20),
                      plan_no        int,
                      PRIMARY KEY(user_id, plan_no)
);