CREATE DATABASE  IF NOT EXISTS `sms` /*!40100 DEFAULT CHARACTER SET latin1 */;
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
-- Table structure for table `course_record_relation`
--

DROP TABLE IF EXISTS `course_record_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_record_relation` (
  `course_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_number` int(20) NOT NULL,
  `class_number` int(20) NOT NULL,
  PRIMARY KEY (`course_record_id`),
  KEY `record_relation_course` (`course_number`),
  KEY `record_relation_class` (`class_number`),
  CONSTRAINT `record_relation_class` FOREIGN KEY (`class_number`) REFERENCES `class` (`class_number`),
  CONSTRAINT `record_relation_course` FOREIGN KEY (`course_number`) REFERENCES `course` (`course_number`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_record_relation`
--

LOCK TABLES `course_record_relation` WRITE;
/*!40000 ALTER TABLE `course_record_relation` DISABLE KEYS */;
INSERT INTO `course_record_relation` VALUES (1,1000,13011),(2,1001,13011),(3,1001,14011),(4,1001,15011),(22,1000,13011),(23,1001,13011),(24,1003,13011),(25,1000,13011),(26,1000,14011),(27,1000,15011),(28,1001,15011),(29,1001,14011),(30,1001,13011),(31,1003,13011),(32,1003,14011),(33,1003,15011),(34,1004,15011),(35,1004,14011),(36,1004,13011),(37,1005,13011),(38,1005,14011),(39,1005,15011);
/*!40000 ALTER TABLE `course_record_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-24 10:56:40
