-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: news_db
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createby` varchar(255) DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'vu','2023-04-26 01:17:04.163000','vu','2023-04-26 01:17:04.163000','the-thao','Thể thao'),(2,'vu','2023-04-26 01:17:39.419000','vu','2023-04-26 01:17:39.419000','giai-tri','Giải trí');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createby` varchar(255) DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime(6) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `shortdescription` varchar(255) NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(150) NOT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtb27em0j94bhyeoh276a5uk0c` (`category_id`),
  CONSTRAINT `FKtb27em0j94bhyeoh276a5uk0c` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

/*!40000 ALTER TABLE `new` DISABLE KEYS */;
INSERT INTO `new` VALUES (1,'vu','2023-04-26 01:19:36.961000','vu','2023-04-26 01:19:36.961000','text text text 17','text 111111','','text 11111111',2),(2,'vu','2023-04-26 01:23:36.557000','vu','2023-04-26 01:23:36.557000','text text text 2','text 222222','','text 2222222',2),(3,'vu','2023-04-26 01:23:54.055000','vu','2023-04-26 01:23:54.055000','text text text 2','text 222222','','text 222223333',2),(4,'vu','2023-04-26 01:30:39.747000','vu','2023-04-26 01:30:39.747000','text text text 4','text 222222','','text 222224444',1),(5,'vu','2023-04-26 01:31:26.056000','vu','2023-04-26 01:31:26.056000','text text text 4','text 222222','','text 222225555',1),(6,'vu','2023-04-26 01:36:51.831000','vu','2023-04-26 01:36:51.831000','text text text 6','text 666666','','text 222226666',1),(7,'vu','2023-04-26 01:47:02.970000','vu','2023-04-26 01:47:02.970000','text text text 6','text 666666','','text 222226666',1),(8,'vu','2023-04-26 01:54:19.539000','vu','2023-04-26 01:54:19.539000','text text text 6','text 666666','','text 222226666',1);
/*!40000 ALTER TABLE `new` ENABLE KEYS */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createby` varchar(255) DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'ROLE_ADMIN','Admin'),(2,NULL,NULL,NULL,NULL,'ROLE_EDITOR','Editor'),(3,NULL,NULL,NULL,NULL,'ROLE_USER','User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `expired` bit(1) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `revoked` bit(1) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwicm9sZXMiOiJbUk9MRV9VU0VSXSIsImlhdCI6MTY4MjQxNzgxMywiZXhwIjoxNjgyNDE3OTMzfQ.7rzzWB1O8ggPFeDxQcHe0LlYAeFVquqyxsCRbFZ2Owb23o0kK7ORZJOgOyOCDxQe_QiOW-NszTmQsmQJgnLB0Q',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwiaWF0IjoxNjgyNDE3ODEzLCJleHAiOjE2ODI0MTk2MTN9.CoTQ6Fr_Vc4wxGgFmBs8r9AR89I9ue_Q4_xkRval5LTVTzUnLVwC-eh5NRm7nIINecegXxNMbTq5GK1OVRgjGg',_binary '',4),(3,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwicm9sZXMiOiJbUk9MRV9VU0VSXSIsImlhdCI6MTY4MjQxNzg0MiwiZXhwIjoxNjgyNDE3OTYyfQ.KIW4NWlt4LaOq2vsWury-_qKE3oH2AaFKSxmBAq9BCRFC85yS_SZr0quTWCa0xOI9PPak-VPNMVhu3f-Z0Ghyw',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwiaWF0IjoxNjgyNDE3ODQyLCJleHAiOjE2ODI0MTk2NDJ9.54iYSwePkZmjD40CuhESMr0R59QU8-eOFaQtS6W-CcsHQ6vsGzqr6CIJUX1AM7wN_4vJkNqx7nhxayT3F7lMVg',_binary '',4),(4,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQxOTM3MiwiZXhwIjoxNjgyNDE5NDkyfQ.5Tf9hTt2gy6KqtfjojOJFZFX4WeFCnnRxahsQFEZiHzHsBVmBt9ygrC39SlJ1zeVO-8SPYHOM9qp8EsYBNYyMQ',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQxOTM3MiwiZXhwIjoxNjgyNDIxMTcyfQ.64yslD0jsvCVaNqUKMxnaK3oHT-5sZ9HUj3nV_ooMcYI8Ho6WnwH_J1BwLFbrbdRmLcH4BdbvqmV1lOx4c7zqA',_binary '',1),(5,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0MTA0NCwiZXhwIjoxNjgyNDQxMTY0fQ.fCgXvIqlcf-7qvUXY-uBX4vN-gDZ8kRTvf6n-UgKuevvxxfksh_oPbkIWAVUB_cN5_TsOnUxfjHWmei0ddzZBQ',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0MTA0NCwiZXhwIjoxNjgyNDQyODQ0fQ.v-AquwYRLoGVQcefnDLP3mPlSH8Ml0Avu8d4wnn4SwkwKdzRqdHJIQYZpweda9sMT1F1_YysKAA9kjClhA1ZMw',_binary '',1),(6,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwicm9sZXMiOiJbUk9MRV9VU0VSXSIsImlhdCI6MTY4MjQ0NjU0OCwiZXhwIjoxNjgyNDQ2NjY4fQ.e2JI9cGJDV0vF4bvDgrAj4VjruFPe_cMy8Wlf_S0VyqcPDAQcVewOudamhq1M8sAnm1rYW3pUctm8l_MRDN51g',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwiaWF0IjoxNjgyNDQ2NTQ4LCJleHAiOjE2ODI0NDgzNDh9._blhelvGhGfbX8jUUBkdeeagiOX_pYB-KU1xAFLcD1G2rDZpLvVaWI2GGDUWd5N_NMV1Cu9Yn5KVKh3-_f0pUQ',_binary '',4),(7,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0NjU5NiwiZXhwIjoxNjgyNDQ2NzE2fQ.UDWWRiK4FMo4meXu_tBS_sKsOvapTcb6AjDitcqIiB6pmqWzDCon1bTGYCP1bmXxSY1gt_-Hlf0THpJFk__0XA',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0NjU5NiwiZXhwIjoxNjgyNDQ4Mzk2fQ.WIlRkNaqlcaHi4G8KeWsZgo5cekRAKBvl6oFbADa2WrJFD_Vw8WGxAcGDeQdzmiajMSPLyxkC_nt7BvCtRCg1g',_binary '',1),(8,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0Njk5NiwiZXhwIjoxNjgyNDQ3MTE2fQ.D2_vH6aUrdQ0srdb2eVvjL5EA5eqGhuT2w1PMN9ZuepSc8k8xViJbkQUeGyZA87gdll-HQwPSwNG5ThkgmelBw',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0Njk5NiwiZXhwIjoxNjgyNDQ4Nzk2fQ.0UXUPlH9xK47rhzgmOQdiSGkuOd8EMLVwfPbLq1Y5f7_6xGgM1FJ1JKJ_sj1llkrm-qdru6TDmh0XBcaEQCEGg',_binary '',1),(9,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0NzAyMiwiZXhwIjoxNjgyNDQ3MTQyfQ.l4L1LgwJi5ggtXiVcKOcmcVxpiNs-pUNuihgWTK6-N2zy2ZEbPPi5HyC1_GJlCyWfYzN6oZ5K3AeN8yHgTvTwA',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0NzAyMiwiZXhwIjoxNjgyNDQ4ODIyfQ.pnvyNDDc6iC0WWk3eL14btMpJQ9lJrkerYAQPsJE-pG-m_i8_Kt5ZO22dtPCW-Fs00rPHhy2Rs4aolT2OTqokQ',_binary '',1),(10,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0NzM4NCwiZXhwIjoxNjgyNDQ3NTA0fQ.WZpXcpKpwYTGcS1C7bgFBuQ_gvP3rBWWVGBbUS2-OD-Y08Y4c0Kymu-eLkgTUXLVONDVV_QSm8KZuJd3aJbDuw',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0NzM4NCwiZXhwIjoxNjgyNDQ5MTg0fQ.pd3nj5AOtYy0l4OAB5CvqtS2WPINxTALYXO8EN9f5T7EBGq4yAdKRTvFda6kDyPndp6pT9UxwzG6qft7nKTufw',_binary '',1),(11,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0NzQ3NiwiZXhwIjoxNjgyNDQ3NTk2fQ.LrBn2p4KeZDEzqVF3TEqRTWYXVOggP3bCMTT8eVDKCbDLz44hjUgeblu_DCwTs_4bdY7e-CkcPKvatBeFbjbvg',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0NzQ3NiwiZXhwIjoxNjgyNDQ5Mjc2fQ.g7IFIiU31wia--Rc2ufnZeeDYuA4T3tEOOJLRzeaNNvVKFmGKGjSVj-mDBNiO0YxLUng98MhSnbAJa85LMQVRA',_binary '',1),(12,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0Nzc1MCwiZXhwIjoxNjgyNDQ3ODcwfQ.1wpdJ5ZOBtcLtC2fgeL6Hrjmydo6Mwd8oSrekcVTijtdJBNsNA51OEK2aavp_troQxXlFGX3nSFEsncNWX_pyA',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0Nzc1MCwiZXhwIjoxNjgyNDQ5NTUwfQ.bjSjCIiCxhjJcAXC6l-RwGfwSqm8KxRFrkdqP35-XgoT7JVO4Z5c9d3S_RAbAGLCLcOeP1hFr0FQ33q7pOhT6w',_binary '',1),(13,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0NzgwNiwiZXhwIjoxNjgyNDQ3OTI2fQ.QPtci02z4i2SiKA0SZoAzzcBZzh79Rqnqr-D__OOY7wZHSqGRg_8PdxEM-kS_Kd6rWDa1fqjAfgAR3NzUUz-Zg',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0NzgwNiwiZXhwIjoxNjgyNDQ5NjA2fQ.up6eW3DuRqC1bAdrFvHltrwzlQTtP8OhGu08r2WqPdfiSDagnV7aTpgFxBn9I9vB-ADpFfttD3SDRLST0QjDfw',_binary '',1),(14,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0ODM5NiwiZXhwIjoxNjgyNDQ4NTE2fQ.Pq2Pq1edCV3FNyKdicWJuaxLbLkRHCt84BpogwrgkG88-ONWPxG_0ym1H3Y4U9rDaYcn_F0teyAlZDo-bHMUFA',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0ODM5NiwiZXhwIjoxNjgyNDUwMTk2fQ.je9vecq04d7GOcqidFGAMNtjua0au2lwVdhT0bKcqUUovtWUbWa8vPUP-itfpJkQ_0JEKcuAEdRqUZn5aIgpkg',_binary '',1),(15,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0ODc3OCwiZXhwIjoxNjgyNDQ4ODk4fQ.0lKmo4lm-NVL868rKsyP4OX_43bdcLRK_L8IknuPiBu0enbEVcYz8jjABYqXQP1UUPHYk0NmHMNrv_q6Qvq0Og',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0ODc3OCwiZXhwIjoxNjgyNDUwNTc4fQ.DopIGUF1Cf4q8CqA3sXYgAiPD5URkXlAfKfOHzAohDC_hx8uV8F3jmiWuEsroRnT0UBk_cS7dskaAClJFJrEJA',_binary '',1),(16,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsInJvbGVzIjoiW1JPTEVfQURNSU4sIFJPTEVfRURJVE9SXSIsImlhdCI6MTY4MjQ0ODgwMiwiZXhwIjoxNjgyNDQ4OTIyfQ.zum9Uq9HFJbV7v57AFFLaBQ8D8jHB5R_MEzQP4JYa_DfhufjCB0mUZusSrZqjBYqg6iwQNCZJdTUK3iwu-lf_Q',NULL,NULL,_binary '\0','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLHZ1IiwiaXNzIjoiRXhhbXBsZSIsImlhdCI6MTY4MjQ0ODgwMiwiZXhwIjoxNjgyNDUwNjAyfQ.nXJCw6ZHxIZPf8zazMMVoVq5uWasvv1JVfL9fdzbplRwQwCfEM_E6o7q4zN-2fNntk49HAS5laJ0H6b5cUfiGQ',_binary '\0',1),(17,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwicm9sZXMiOiJbUk9MRV9VU0VSXSIsImlhdCI6MTY4MjQ1MjE1MSwiZXhwIjoxNjgyNDUyMjcxfQ.LEHCk_gyDcAzpEZwVSRB2qSLTYWZFDUzL2tBdIyoyaZqctPdZ5bCJgqfFh6SDtb0FL1fdoXhxydfssTn6_BNOA',NULL,NULL,_binary '','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0LHZ1X3VzZXIiLCJpc3MiOiJFeGFtcGxlIiwiaWF0IjoxNjgyNDUyMTUxLCJleHAiOjE2ODI0NTM5NTF9.k80r2WylBcMVxw-FWkX0srO8V0QP6f0iEygY82RtNnw06tpHFooIJgG5MgZz0oymLZ9DVkEwtoGRaZzxVXNyYw',_binary '',4);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createby` varchar(255) DEFAULT NULL,
  `createdate` datetime(6) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime(6) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anonymousUser','2023-04-25 16:00:22.979000','anonymousUser','2023-04-25 16:00:22.979000',NULL,'$2a$10$MDjHv6ZQN1FU8zWb0D/6Z.57eB9nf/bkMx09OexDeFyb9ws7zaU7q',NULL,'vu'),(4,'anonymousUser','2023-04-25 16:36:33.487000','anonymousUser','2023-04-25 16:36:33.487000',NULL,'$2a$10$dv6OCp3qjdafrkMNhnLUj.w3Dd.roCF6lxfTVQkF4jsUbkRE8Nr3S',NULL,'vu_user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2),(4,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-26  8:31:21
