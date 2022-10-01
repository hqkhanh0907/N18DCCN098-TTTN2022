CREATE DATABASE  IF NOT EXISTS `movie` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `movie`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: movie
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `ward_id` int DEFAULT NULL,
  `details_address` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone_number` char(13) DEFAULT NULL,
  `gender` tinyint NOT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

INSERT INTO `account` VALUES (1,'admin','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','khanhmeocon98@gmail.com','.\\data_image\\account\\admin.jpg','Huỳnh','Khánh','2000-07-09',1,'Số nhà 111','0822579381',0,_binary ''),(6,'khanh111','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','huynhquoc321khanh0907@gmail.com',NULL,'Huỳnh','Khánh','2000-07-09',NULL,NULL,NULL,0,_binary ''),(8,'khanhhuynh','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','huynhquockhanh091107@gmail.com',NULL,'Huỳnh','Khánh',NULL,NULL,NULL,NULL,1,_binary ''),(9,'khanh','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','khanhd8fhq9hsdg@gmail.com',NULL,'Huynhf','Khansh',NULL,NULL,NULL,NULL,1,_binary ''),(15,'feqwdfqs','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','dsa@dqaqd',NULL,'dsadas','đâs',NULL,NULL,NULL,NULL,1,_binary ''),(16,'khanh234','$2a$10$a0tibsM0b6pjVaat47HnCemW7ccmkNGndlHG.P4XkehsygydtXxay','huynhquockfewhanh0907@gmail.com',NULL,'Baksk','Aniooi','2022-01-08',NULL,NULL,NULL,1,_binary ''),(20,'hahahahah','$2a$10$pfzEyTdK8NORJnAVBrG.Ge4FcYT.GA5DCH/tn4Q.5nvL5GkluINjq','khanh@hnaha',NULL,'khanh','khanh',NULL,NULL,NULL,NULL,1,_binary ''),(25,'fdfsada','$2a$10$2xZvRLVkPKsiegA8amvWy.J4iQWz4fphQLGBfEKzvF4WRDmQf1XNq','dsdqsqs@sdqs',NULL,'bjodsjo','sadas',NULL,NULL,NULL,NULL,1,_binary ''),(28,'khanh123','$2a$10$8LC7kavaQ1zP/fuCRFx9gOpcWWyBxII445X0H1THkUONGgSGg4xVO','huynhquockhanh0901237@gmail.com',NULL,'Huỳnh','Khánh','2000-10-07',NULL,NULL,NULL,1,_binary ''),(29,'ledinhtrieu','$2a$10$WiKKY6SFfYPD.Qm9SC/Kb.BQVy0y79H59h4lm1.WS0Q6zt.w/Ci/W','huynhquockhanh0213907@gmail.com',NULL,'Lê','Triều',NULL,NULL,NULL,NULL,1,_binary ''),(30,'tester','$2a$10$rR3bzocUt8E5aNkx4nr7E.a6XaMoQiSzTFhMFcKhQZhBfa8hPE5ce','huynhquockhanh092131307@gmail.com',NULL,'None','Test',NULL,NULL,NULL,NULL,1,_binary ''),(31,'testuser','$2a$10$Si1Rwe.jnoBlPD7d5W2zPeLhW5N59R6rw69M2ZRUtJ5NT1.gCC7wm','huynhquockhanh0907@gmail.com',NULL,'User','Test','2013-01-01',NULL,NULL,NULL,1,_binary '');

--
-- Table structure for table `account_history`
--

DROP TABLE IF EXISTS `account_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_history`
--

INSERT INTO `account_history` VALUES (1,1,0,'2022-09-10'),(1,12,0,'2022-09-10'),(1,35,0,'2022-09-09'),(6,1,18.1822,'2022-08-26'),(6,35,20,'2022-08-25'),(28,1,0,'2022-09-03'),(28,12,3.90095,'2022-08-28'),(29,1,4.88617,'2022-08-29'),(29,12,0,'2022-08-29'),(30,1,0,'2022-09-10'),(30,38,0,'2022-08-29');

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

INSERT INTO `account_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

--
-- Table structure for table `billing_information`
--

DROP TABLE IF EXISTS `billing_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing_information` (
  `account_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `promotion_id` int DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`account_id`,`movie_id`),
  KEY `FK_account_id_billing` (`account_id`),
  KEY `FK_movie_id_billing` (`movie_id`),
  KEY `FK_promotion_id_billing` (`promotion_id`),
  CONSTRAINT `FK_account_id_billing` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_movie_id_billing` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_promotion_id_billing` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_information`
--

INSERT INTO `billing_information` VALUES (1,1,4,1),(1,12,1,1),(6,1,4,1),(28,1,1,1),(28,12,6,1),(29,1,8,1),(29,12,4,1),(30,1,9,1),(30,38,9,1);

--
-- Table structure for table `cast`
--

DROP TABLE IF EXISTS `cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cast` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `story` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast`
--

INSERT INTO `cast` VALUES (3,'.\\data_image\\cast\\licensed-image.jpg.jpg','Stephen Chow','Stephen Chow was born in Hong Kong on 22 June 1962 to Ling Po-yee (凌寶兒), an alumna of Guangzhou Normal University, and Chiau Yik-sheung (周驛尚), an immigrant from Ningbo, Zhejiang.[4] Chow has an elder sister named Chiau Man-kei (周文姬) and a younger sister named Chiau Sing-ha (周星霞).[5] Chow\'s given name \"Sing-chi\" (星馳) derives from Tang dynasty (618–907) Chinese poet Wang Bo\'s essay Preface to the Prince of Teng\'s Pavilion.[6] After his parents divorced when he was seven, Chow was raised by his mother.[6] Chow attended Heep Woh Primary School, a missionary school attached to the Hong Kong Council of the Church of Christ in China in Prince Edward Road, Kowloon Peninsula.[7] When he was nine, he saw Bruce Lee\'s film The Big Boss, which inspired him to become a martial arts star.[8] Chow entered San Marino Secondary School, where he studied alongside Lee Kin-yan.[8] After graduation, he was accepted to TVB\'s acting classes.[9]','2000-01-01'),(4,'.\\data_image\\cast\\images.jpg.jpg','Tom Holland','dsqdfws','1999-01-03'),(6,'.\\data_image\\cast\\Mark_Ruffalo_(44707380685)_(cropped).jpg.jpg','Mark Ruffalo','Nice','1992-01-01'),(7,'.\\data_image\\cast\\74371_v9_bc.jpg.jpg','Josh Brolin','Rugged features and a natural charm have worked for Josh Brolin, the son of actor James Brolin. He has played roles as a policeman, a hunter, and the President of the United States.\n\nBrolin was born February 12, 1968 in Santa Monica, California, to Jane Cameron (Agee), a Texas-born wildlife activist, and James Brolin. Josh was not interested at first in the lifestyle of the entertainment business, in light of his parents\' divorce, and both of them being actors. However, during junior year in high school, he took an acting class to see what it was like. He played Stanley in \"A Streetcar Named Desire\" and became hooked. His first major screen role was as the older brother in the film The Goonies (1985), based on a story by Steven Spielberg. He then immediately moved on to work on television, taking roles on such series as Private Eye: Pilot (1987) and The Young Riders (1989). \"Private Eye\" was a chance for Brolin to play a detective. \"The Young Riders\" was set just before the Civil War, and was co-directed by Brolin\'s father, James Brolin.\n\nAfter The Young Riders (1989), Brolin moved back to the big screen, with mediocre success. He played a supporting role in The Road Killers (1994), but the film was not a success. He followed up with the crime film Gang in Blue (1996), the romantic film Bed of Roses (1996), the thriller film Nightwatch (1997), and appeared with his father in My Brother\'s War (1997). However, nothing truly stuck out, especially not the box office flop The Mod Squad (1999). The 2000s initially brought no significant change in Brolin\'s career. He appeared in the independent film Slow Burn (2000), the sci-if thriller Hollow Man (2000) and starred on the television series Mister Sterling (2003). In 2004, he married actress Diane Lane but later divorced in 2013.\n\nIt was not until 2007 that Brolin received much acclaim for his films. He took a supporting role in the Quentin Tarantino-written Grindhouse (2007) which was a two-part film accounting two horror stories. He also played two policemen that year: corrupt officer Nick Trupo in the crime epic American Gangster (2007), and an honest police chief in the emotional drama In the Valley of Elah (2007) which starred Tommy Lee Jones and was directed by Paul Haggis. However, it was his involvement in No Country for Old Men (2007) that truly pushed him into the limelight. The film, directed by the Coen brothers, was about a man (Brolin) who finds a satchel containing two million dollars in cash. He is pursued by an unstoppable assassin (Javier Bardem, who won an Oscar for his work) and his friend, a local sheriff (Tommy Lee Jones). The film won four Oscars, including Best Picture and Best Director.\n\nBrolin found high-profile work the next year, being cast as Supervisor Dan White in the film Milk (2008). His performance as the weak and bitter politician earned him an Oscar nomination, and Brolin received more praise for his fascinating portrayal of George W. Bush in the Oliver Stone film W. (2008). Despite the mediocre success of W. (2008), he was recognized as the best part of the film, and Milk (2008) was another triumph, critically and commercially.\n\nBrolin then acted in the smaller comedy Women in Trouble (2009) before landing a number of large roles in 2010. The first of these was the film based on the comic book figure Jonah Hex (2010). The film was a box office flop and critically panned, but Brolin also forged a second collaboration with legendary director Oliver Stone for Wall Street: Money Never Sleeps (2010). Brolin played a large role alongside such young stars as Carey Mulligan and Shia LaBeouf, and older thespians such as Michael Douglas, Eli Wallach, and Frank Langella. Brolin\'s character was Bretton James, a top banker in the film, and also the film\'s chief antagonist. Brolin also appeared in Woody Allen\'s London-based film You Will Meet a Tall Dark Stranger (2010) and a second collaboration with the Coen Brothers, which was a remake of True Grit (1969).\n\nDespite his earlier mediocre success and fame, Brolin has maintained a choosiness in his films and, recently, these choices have paid off profoundly. Hopefully, he continues this streak of good fortune that his talents have finally given him.','1991-01-01');

--
-- Table structure for table `cast_of_movie`
--

DROP TABLE IF EXISTS `cast_of_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cast_of_movie` (
  `movie_id` int NOT NULL,
  `cast_id` int NOT NULL,
  `cast_character` varchar(255) NOT NULL,
  PRIMARY KEY (`movie_id`,`cast_id`),
  KEY `FK_cast_of_movie_movieCast` (`cast_id`),
  KEY `FK_cast_of_movie_movieDetail` (`movie_id`),
  CONSTRAINT `FK_cast_of_movie_movieCast` FOREIGN KEY (`cast_id`) REFERENCES `cast` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_cast_of_movie_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast_of_movie`
--

INSERT INTO `cast_of_movie` VALUES (33,3,'teast'),(33,4,'dsqdsq'),(35,3,'Doremon'),(35,4,'Nobita');

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `story` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

INSERT INTO `director` VALUES (4,'.\\data_image\\director\\licensed-image.jpg.jpg','Anthony Russo','dsad','1992-01-01'),(5,'.\\data_image\\director\\303029_v9_bb.jpg.jpg','Joe Russo','apple','2000-01-03');

--
-- Table structure for table `director_of_movie`
--

DROP TABLE IF EXISTS `director_of_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director_of_movie` (
  `movie_id` int NOT NULL,
  `dricetor_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`dricetor_id`),
  KEY `FK_fk_driector_movieDriector` (`dricetor_id`),
  KEY `FK_fk_driector_movieDetail` (`movie_id`),
  CONSTRAINT `FK_fk_driector_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FK_fk_driector_movieDriector` FOREIGN KEY (`dricetor_id`) REFERENCES `director` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director_of_movie`
--

INSERT INTO `director_of_movie` VALUES (1,4),(33,4),(35,4),(1,5),(35,5);

--
-- Table structure for table `favorite_movie`
--

DROP TABLE IF EXISTS `favorite_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_movie` (
  `account_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`account_id`,`movie_id`),
  KEY `FK_favorite_movie_account` (`account_id`),
  KEY `FK_favorite_movie_movieDetail` (`movie_id`),
  CONSTRAINT `FK_favorite_movie_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_favorite_movie_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_movie`
--

INSERT INTO `favorite_movie` VALUES (1,1,'2022-08-22'),(1,12,'2022-09-10'),(1,35,'2022-09-09'),(6,1,'2022-08-24'),(6,35,'2022-08-23'),(28,1,'2022-09-03'),(29,1,'2022-08-29');

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` VALUES (17,'Comedy'),(18,'Adventure'),(22,'Cartoon'),(23,'Action'),(24,'Fantasy'),(25,'Horror'),(26,'Mystery');

--
-- Table structure for table `genre_of_movie`
--

DROP TABLE IF EXISTS `genre_of_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre_of_movie` (
  `movie_id` int NOT NULL,
  `genre_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`),
  KEY `FK_fk_genre_movieGenre` (`genre_id`),
  KEY `FK_fk_genre_movieDetail` (`movie_id`),
  CONSTRAINT `FK_fk_genre_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `FK_fk_genre_movieGenre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_of_movie`
--

INSERT INTO `genre_of_movie` VALUES (35,17),(1,22),(35,22);

--
-- Table structure for table `group_of_roles`
--

DROP TABLE IF EXISTS `group_of_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_of_roles` (
  `role_id` int NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `FK_group_of_roles_account` (`account_id`),
  KEY `FK_group_of_roles_account_role` (`role_id`),
  CONSTRAINT `FK_group_of_roles_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_group_of_roles_account_role` FOREIGN KEY (`role_id`) REFERENCES `account_role` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_of_roles`
--

INSERT INTO `group_of_roles` VALUES (1,1),(2,1),(2,6),(1,8),(2,8),(1,9),(1,15),(1,16),(2,16),(1,20),(2,20),(1,25),(2,25),(2,28),(2,29),(2,30),(2,31);

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `poster` varchar(500) NOT NULL,
  `slug` varchar(500) NOT NULL,
  `image_showing` varchar(500) NOT NULL,
  `description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `quality` varchar(30) NOT NULL,
  `movie_status` int NOT NULL,
  `link_trailer` varchar(1000) DEFAULT NULL,
  `link_movie` varchar(1000) NOT NULL,
  `release_date` date NOT NULL,
  `movie_duration` float NOT NULL,
  `view_num` int DEFAULT NULL,
  `country_code` varchar(3) NOT NULL,
  `translation_status` int NOT NULL,
  `movie_price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` VALUES (1,'Doraemon: Nobita\'s Dinosaur','https://mega.com.vn/media/news/1406_hinh-nen-doraemon-4k56.jpg','doraemon-nobitas-dinosaur','https://www.themoviedb.org/t/p/original/9pUXcCMpuxJScQUyphA6V9lHG1Z.jpg','Doraemon: Nobita\'s Dinosaur (ドラえもん のび太の恐竜 Doraemon: Nobita no Kyouryu) is the first installment of the Doraemon feature films. It is adapted from the first volume of Daichōhen Doraemon of the same name. It also received a live-action adaptation. The movie was remade in 2006, titled Nobita\'s Dinosaur 2006. After reviving a fossilized dinosaur egg using Doraemon’s time cloth, Nobita must return the now-full-grown plesiosaur back to its own time.','1080',1,'https://www.youtube.com/embed/llLgcb-rHhk','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2000-01-01',30,1000,'USA',1,100),(12,'Avengers: Endgame 2019','https://i.ibb.co/zRphQcq/movie-avengers-endgame-art.jpg','avengers-endgame-2019','https://i.ibb.co/WgtjXjR/poster-avengers-endgame-33.jpg','After half of all life is snapped away by Thanos, the Avengers are left scattered and divided. Now with a way to reverse the damage, the Avengers and their allies must assemble once more and learn to put differences aside in order to work together and set things right. Along the way, the Avengers realize that sacrifices must be made as they prepare for the ultimate final showdown with Thanos, which will result in the heroes fighting the biggest battle they have ever faced.','1080',1,'https://www.youtube.com/embed/TcMBFSGVi1c','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2022-08-01',30,NULL,'USA',0,20),(33,'Avengers: Infinity War','https://i.ibb.co/ns3cfwF/wp2520192-captain-america-infinity-war-wallpapers.jpg','avengers-infinity-war','https://i.ibb.co/G9L3mPP/Avengers-Infinity-War-ELLE-Man-3.jpg','fvsdfdas','1080',1,'https://www.youtube.com/embed/6ZfuNTqbHE8','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2022-08-05',30,NULL,'DZA',3,0),(34,'The Avengers (2012)','https://i.ibb.co/bdT5tcz/v-Dy3s-Ub-the-avenger-wallpaper-hd.jpg','the-avengers-2012','https://i.ibb.co/DL3TzgR/RYMX2wc-KCBAr24-Uy-PD7xwmja-Tn.jpg','fwedfvsd','1080',1,'https://www.youtube.com/embed/eOrNdBpGMv8','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2022-08-01',30,NULL,'VNM',0,0),(35,'Avengers: Age of Ultron','https://i.ibb.co/93VCDjy/wp1818445-ultron-wallpapers.jpg','avengers-age-of-ultron','https://i.ibb.co/0j5yg13/23fe91b12ba2f8d066ce973dd250e87a.jpg','Doraemon and friends','1080',1,'https://www.youtube.com/embed/tmeOjFno6Do','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2022-08-01',30,NULL,'VNM',0,0),(38,'Star Wars: The Rise of Skywalker','https://i.ibb.co/zP3wvsj/4k.jpg','star-wars-the-rise-of-skywalker','https://i.ibb.co/zFB2tBW/tyler-wetta-starwars-riseofskywalker24x36v2smaller-copy.jpg','Test 29-08-2022','1080',0,'https://www.youtube.com/embed/8Qn_spdM5Zg','https://stream1.linhminaz.com/stream/1080/eEWW7fNerMXCNWe/__001','2000-01-01',30,NULL,'VNM',0,29);

--
-- Table structure for table `movie_evaluate`
--

DROP TABLE IF EXISTS `movie_evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_evaluate` (
  `account_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `rate` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`account_id`,`movie_id`),
  KEY `FK_move_evaluate_movieDetail` (`movie_id`),
  KEY `FK_move_evaluate_account` (`account_id`),
  CONSTRAINT `FK_move_evaluate_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_move_evaluate_movieDetail` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_evaluate`
--

INSERT INTO `movie_evaluate` VALUES (1,12,'Phim rất tuyệt vời','2022-09-09 14:31:01',5,1),(1,35,'Phim hay lắm nha','2022-09-08 10:34:52',1,1),(6,1,'Phim rất là hay','2022-08-26 21:58:17',5,1),(6,35,'sqdwdqwdwdưqdq','2022-08-22 00:00:00',0,1),(8,35,'dewcwew','2022-08-21 00:00:00',4,1),(9,35,'dewcwew','2022-08-20 00:00:00',4,1),(15,35,'dewcwew','2022-08-19 00:00:00',4,1),(16,35,'dewcwew','2022-08-18 00:00:00',4,1),(20,35,'dewcwew','2022-08-17 00:00:00',4,1),(25,35,'dewcwew','2022-08-16 00:00:00',4,1),(29,1,'Phim tuyệt vời','2022-08-29 09:59:36',5,1),(30,38,'very good movie','2022-08-29 10:15:01',5,1);

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_name` varchar(20) NOT NULL,
  `description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `percent_discount` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code_name` (`code_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` VALUES (1,'DEFAULT','defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcodedefaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcodedefaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcodedefaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcodedefaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcodedefaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode defaultcode','0999-01-01','9999-12-31',0),(4,'DEFAULT12','defaultcode','2000-01-01','2021-01-01',30),(5,'DEFAULT122123','defaultcode','2000-02-02','2022-01-01',20),(6,'ACVBGFRD','fwfq','2022-08-16','2022-08-31',99),(7,'HAHAH','cndoqsdqnio','2022-07-01','2022-08-09',12),(8,'NAMMOI202312','defaultcode','2000-01-01','2021-01-01',30),(9,'PROMO29','Test proomo 29','2022-08-29','2022-09-11',50);

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `token_content` varchar(255) DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKftkstvcfb74ogw02bo5261kno` (`account_id`),
  CONSTRAINT `FKftkstvcfb74ogw02bo5261kno` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

INSERT INTO `token` VALUES (1,'2022-08-04 17:05:58.090170','355ba3f5-1bfe-442a-9cc6-e4db49236009',1),(6,'2022-08-07 20:12:53.224550','6cc2924e-842d-49d6-b7a4-55df15647b36',6),(7,'2022-08-13 13:32:10.658163','dae81eec-2275-4f13-bf39-394fcce15fca',16),(9,'2022-08-27 11:03:55.153521','68bf7fdd-8f7e-4217-a70d-a64468b7b322',28),(10,'2022-08-29 10:17:10.182348','97359c4f-87fc-4863-adb5-59e42de61d8f',31);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
