

CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `poster` varchar(500) NOT NULL,
  `slug` varchar(500) NOT NULL,
  `image_showing` varchar(500) NOT NULL,
  `description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `quality`  varchar(30) NOT NULL,
  `movie_status` int NOT NULL,
  /*
  Have 3 status:
  - premiere soon (0)
  - Premiered (1)
  - cancle (2)
  */
  `link_trailer` varchar(1000) DEFAULT NULL,
  `link_movie` varchar(1000) NOT NULL,
  `release_date` date NOT NULL,
  `movie_duration` float NOT NULL,
  /*
  - Time is in seconds
  */
  `view_num` int DEFAULT NULL,
  `country_code` int NOT NULL,
  `translation_status` int NOT NULL,
  /*
  Have  :
  - default (0)
  - dub movie (1)
  - narration movie (2)
  - vietsub (3)
  */
  `movie_price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` boolean DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthday` date NOT NULL,
  `ward_id` int DEFAULT NULL,
  `details_address` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone_number` char(13) DEFAULT NULL,
  `gender` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`),
  UNIQUE KEY `unique_email` (`email`)
);
CREATE TABLE `account_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `cast` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `story` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `director`
--

CREATE TABLE `director` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `story` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cast_of_movie` (
  `movie_id` int NOT NULL,
  `cast_id` int NOT NULL,
  `cast_character` varchar(255) NOT NULL,
  PRIMARY KEY (`movie_id`,`cast_id`),
  KEY `FK_cast_of_movie_movieCast` (`cast_id`),
  KEY `FK_cast_of_movie_movieDetail` (`movie_id`),
  CONSTRAINT `FK_cast_of_movie_movieCast` FOREIGN KEY (`cast_id`) REFERENCES `cast` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_cast_of_movie_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
);

CREATE TABLE `director_of_movie` (
  `movie_id` int NOT NULL,
  `dricetor_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`dricetor_id`),
  KEY `FK_fk_driector_movieDriector` (`dricetor_id`),
  KEY `FK_fk_driector_movieDetail` (`movie_id`),
  CONSTRAINT `FK_fk_driector_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FK_fk_driector_movieDriector` FOREIGN KEY (`dricetor_id`) REFERENCES `director` (`id`)
);

--
-- Table structure for table `genrer_of_movie`
--
CREATE TABLE `genre_of_movie` (
  `movie_id` int NOT NULL,
  `genre_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`),
  KEY `FK_fk_genre_movieGenre` (`genre_id`),
  KEY `FK_fk_genre_movieDetail` (`movie_id`),
  CONSTRAINT `FK_fk_genre_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FK_fk_genre_movieGenre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
);

--
-- Table structure for table `move_evaluate`
--

CREATE TABLE `movie_evaluate` (
  `account_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `evaluate_Time` date NOT NULL,
  `evaluate_content` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `evaluate_rate` int NOT NULL,
  `evalute_status` int NOT NULL,
  PRIMARY KEY (`account_id`,`movie_id`),
  KEY `FK_move_evaluate_movieDetail` (`movie_id`),
  KEY `FK_move_evaluate_account` (`account_id`),
  CONSTRAINT `FK_move_evaluate_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_move_evaluate_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
);



CREATE TABLE `promotion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_name` varchar(20) NOT NULL,
  `description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `percent_discount` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code_name` (`code_name`),
  KEY `FK_acc_create` (`account_id`),
  CONSTRAINT `FK_acc_create` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE
);
--
-- Table structure for table `cast`
--

CREATE TABLE `billing_information` (
	`account_id` int NOT NULL,
    `movie_id` int NOT NULL,
    `promotion_id` int,
    `status` int NOT NULL,
    /*
    Have 2 status:
		- cancle (0)
		- success (1)
        - pedding (2)
    */    
  PRIMARY KEY (`account_id`, `movie_id`),
  KEY `FK_account_id_billing` (`account_id`),
  CONSTRAINT `FK_account_id_billing` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  KEY `FK_movie_id_billing` (`movie_id`),
  CONSTRAINT `FK_movie_id_billing` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE,
  KEY `FK_promotion_id_billing` (`promotion_id`),
  CONSTRAINT `FK_promotion_id_billing` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON UPDATE CASCADE
);

CREATE TABLE `group_of_roles` (
  `role_id` int NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `FK_group_of_roles_account` (`account_id`),
  KEY `FK_group_of_roles_account_role` (`role_id`),
  CONSTRAINT `FK_group_of_roles_account_role` FOREIGN KEY (`role_id`) REFERENCES `account_role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_group_of_roles_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE
);
CREATE TABLE `account_history` (
  `account_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `time_watched` float NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`account_id`,`movie_id`),
  KEY `FK_account_history_account` (`account_id`),
  KEY `FK_account_history_movieDetail` (`movie_id`),
  CONSTRAINT `FK_account_history_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_account_history_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
);
CREATE TABLE `favorite_movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `movie_id` int DEFAULT NULL,
  `favorite_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_favorite_movie_account` (`account_id`),
  KEY `FK_favorite_movie_movieDetail` (`movie_id`),
  CONSTRAINT `FK_favorite_movie_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_favorite_movie_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
)

