CREATE DATABASE  IF NOT EXISTS `goods_origin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `goods_origin`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: goods_origin
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `certificates`
--

DROP TABLE IF EXISTS `certificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `certificate_date` datetime(6) DEFAULT NULL,
  `invoice_data` varchar(256) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  `serial_number` varchar(16) NOT NULL,
  `consignee_id` int DEFAULT NULL,
  `exporter_id` int DEFAULT NULL,
  `transport_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fhimy9jsw510b0ga7b2wo2nw2` (`serial_number`),
  KEY `FKdax8xotk39n69qi8twm1bgavl` (`consignee_id`),
  KEY `FKhpvq2f252dmq153ao95h9xowf` (`exporter_id`),
  KEY `FK2usk6kxaww4xy664am4rllwah` (`transport_id`),
  CONSTRAINT `FK2usk6kxaww4xy664am4rllwah` FOREIGN KEY (`transport_id`) REFERENCES `transport` (`id`),
  CONSTRAINT `FKdax8xotk39n69qi8twm1bgavl` FOREIGN KEY (`consignee_id`) REFERENCES `consignees` (`id`),
  CONSTRAINT `FKhpvq2f252dmq153ao95h9xowf` FOREIGN KEY (`exporter_id`) REFERENCES `exporters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificates`
--

LOCK TABLES `certificates` WRITE;
/*!40000 ALTER TABLE `certificates` DISABLE KEYS */;
INSERT INTO `certificates` VALUES (1,'2023-01-23 20:09:02.205000','123456 1/12/2023','','XY12345678',1,1,1),(2,'2023-01-28 20:09:24.260000','123456 1/12/2023','Dublicate','XY12345679',1,1,1);
/*!40000 ALTER TABLE `certificates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consignees`
--

DROP TABLE IF EXISTS `consignees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consignees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `address` varchar(64) NOT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_439qd0mjl4kvdm1rl2smtf5gq` (`name`),
  KEY `FK1yg1vdbxr2hwx7775qt9t6gad` (`country_id`),
  CONSTRAINT `FK1yg1vdbxr2hwx7775qt9t6gad` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consignees`
--

LOCK TABLES `consignees` WRITE;
/*!40000 ALTER TABLE `consignees` DISABLE KEYS */;
INSERT INTO `consignees` VALUES (1,'Asconi SRL','11 w 65 st Cimislia',1);
/*!40000 ALTER TABLE `consignees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1pyiwrqimi3hnl3vtgsypj5r` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'MD','Moldova'),(2,'DE','Germany');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currencies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(4) NOT NULL,
  `name` varchar(64) NOT NULL,
  `symbol` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'USD','United States Dollar','$'),(2,'EUR','Euro','€'),(3,'GBP','British Pound','£'),(4,'JPY','Japanese Yen','¥'),(5,'MDL','Moldovan Leu','L');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporters`
--

DROP TABLE IF EXISTS `exporters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `address` varchar(64) NOT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2ty9d0e16me7b777lb2ej0pfk` (`name`),
  KEY `FK3qixllq5lbxqf0duvd854x0f9` (`country_id`),
  CONSTRAINT `FK3qixllq5lbxqf0duvd854x0f9` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporters`
--

LOCK TABLES `exporters` WRITE;
/*!40000 ALTER TABLE `exporters` DISABLE KEYS */;
INSERT INTO `exporters` VALUES (1,'T.E.C. Erfurt','Hermsdorfer Str. 4, 99099 Erfurt',2);
/*!40000 ALTER TABLE `exporters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_details`
--

DROP TABLE IF EXISTS `goods_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_description` varchar(1024) NOT NULL,
  `gross_weight_or_other_units` varchar(256) DEFAULT NULL,
  `origin_criteria` varchar(256) DEFAULT NULL,
  `packing_and_marking` varchar(255) NOT NULL,
  `certificate_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoo0gcxgy7y49d769ivt55ja5d` (`certificate_id`),
  CONSTRAINT `FKoo0gcxgy7y49d769ivt55ja5d` FOREIGN KEY (`certificate_id`) REFERENCES `certificates` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_details`
--

LOCK TABLES `goods_details` WRITE;
/*!40000 ALTER TABLE `goods_details` DISABLE KEYS */;
INSERT INTO `goods_details` VALUES (4,'480 boxes Samsung RB34T600FEL, 355 l, 185.3 cm, A+','24000kg','W8418','No Marks',1),(7,'480 boxes Samsung RB34T600FEL, 355 l, 185.3 cm, A+','24000kg','W8418','No Marks',2);
/*!40000 ALTER TABLE `goods_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `key` varchar(128) NOT NULL,
  `value` varchar(1024) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES ('CURRENCY_ID','1'),('SITE_LOGO','/site-logo/origin.jpg'),('SITE_NAME','Certification');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sctk51rtjygmnantq9s609fw6` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
INSERT INTO `transport` VALUES (1,'Auto');
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-24 12:56:45
