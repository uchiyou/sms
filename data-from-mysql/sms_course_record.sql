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
-- Table structure for table `course_record`
--

DROP TABLE IF EXISTS `course_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_record` (
  `course_record_id` int(11) NOT NULL,
  `sequence` int(11) NOT NULL,
  `course_content` text,
  `type` set('lecture','solve exercise','course test','review') NOT NULL,
  `course_number` int(20) DEFAULT NULL,
  KEY `course_record_record_relation` (`course_record_id`),
  KEY `course_record_course_number` (`course_number`),
  CONSTRAINT `course_record_course_number` FOREIGN KEY (`course_number`) REFERENCES `course` (`course_number`),
  CONSTRAINT `course_record_record_relation` FOREIGN KEY (`course_record_id`) REFERENCES `course_record_relation` (`course_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_record`
--

LOCK TABLES `course_record` WRITE;
/*!40000 ALTER TABLE `course_record` DISABLE KEYS */;
INSERT INTO `course_record` VALUES (22,1,'this is the first class, so i just teacher the outline of this semestes, the duration of my teacher is a short time,but we are happy to share the past holiday','lecture',1000),(23,1,'this is the first class, so i just teacher the outline of this semestes, the duration of my teacher is a short time,but we are happy to share the past holiday','lecture',1001),(24,1,'here is actual the second class for synchnoloy . yeal, the first class was cancle for a reson I don\'t know','lecture',1004),(25,2,'1000 is the course number of database guide','solve exercise',1000),(26,3,'just test','review',1000),(27,4,'test','lecture',1000),(28,5,'have a rest , tomorrow continue','lecture',1000),(29,6,'good night','solve exercise',1000),(30,2,'ok','course test',1001),(31,7,'null','lecture',1000),(32,3,'绍兴水城','lecture',1001),(33,2,'数学课程记录','lecture',1004),(34,4,'为什么呢????','lecture',1001),(35,3,'不是数学课程记录','lecture',1004),(36,5,'只为了一缕牵挂，','lecture',1001),(37,6,'','lecture',1001),(37,9,'','lecture',1000),(38,7,'','lecture',1001),(39,8,'随便试试哈','lecture',1001),(40,4,'仅仅只是测试','lecture',1004),(41,10,'another test','lecture',1000),(41,11,'yes oks','lecture',1000),(42,9,'一只羊，两只羊','lecture',1001),(43,10,'bug 恢复了？？？？？？？？？？？？？？？？？？？？？','lecture',1001),(44,11,'test3','lecture',1001);
/*!40000 ALTER TABLE `course_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-30 17:22:34
