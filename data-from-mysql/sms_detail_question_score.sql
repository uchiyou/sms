CREATE DATABASE  IF NOT EXISTS `sms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sms`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: sms
-- ------------------------------------------------------
-- Server version	5.5.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detail_question_score`
--

DROP TABLE IF EXISTS `detail_question_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_question_score` (
  `course_score_id` int(11) NOT NULL,
  `detail_question_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  KEY `question_average_score_detail_question` (`detail_question_id`),
  KEY `question_average_score_score_relation` (`course_score_id`),
  CONSTRAINT `question_average_score_detail_question` FOREIGN KEY (`detail_question_id`) REFERENCES `detail_question` (`detail_question_id`),
  CONSTRAINT `question_average_score_score_relation` FOREIGN KEY (`course_score_id`) REFERENCES `course_score_relation` (`course_score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_question_score`
--

LOCK TABLES `detail_question_score` WRITE;
/*!40000 ALTER TABLE `detail_question_score` DISABLE KEYS */;
INSERT INTO `detail_question_score` VALUES (1,1,3),(1,2,3),(1,3,5),(2,3,5),(1,4,5),(1,5,2),(1,6,1),(1,7,3),(2,1,5),(2,2,2),(2,4,6),(2,5,5),(2,6,2),(3,1,2),(3,2,3),(3,3,3),(3,4,2),(3,5,5),(3,6,1);
/*!40000 ALTER TABLE `detail_question_score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-28  9:27:24
