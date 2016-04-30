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
-- Table structure for table `detail_question`
--

DROP TABLE IF EXISTS `detail_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_question` (
  `main_question_id` int(11) NOT NULL,
  `detail_question_number` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '1',
  `type` enum('basic','integrity','advance') NOT NULL DEFAULT 'basic',
  `detail_question_id` int(11) NOT NULL AUTO_INCREMENT,
  `easy_level` enum('difficult','trouble some','normal','easy') DEFAULT NULL,
  PRIMARY KEY (`detail_question_id`),
  UNIQUE KEY `detail_question_id` (`detail_question_id`),
  KEY `detail_question_main` (`main_question_id`),
  CONSTRAINT `detail_question_main` FOREIGN KEY (`main_question_id`) REFERENCES `main_question` (`main_question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_question`
--

LOCK TABLES `detail_question` WRITE;
/*!40000 ALTER TABLE `detail_question` DISABLE KEYS */;
INSERT INTO `detail_question` VALUES (1,1,3,'basic',1,'normal'),(1,2,3,'integrity',2,'easy'),(1,3,3,'integrity',3,'trouble some'),(1,9,3,'basic',4,'easy'),(1,10,3,'basic',5,'easy'),(2,1,3,'basic',6,'difficult'),(2,2,3,'basic',7,'easy'),(3,1,3,'basic',8,'easy');
/*!40000 ALTER TABLE `detail_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-30 17:22:31
