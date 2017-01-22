CREATE DATABASE  IF NOT EXISTS `coolalbum` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `coolalbum`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: coolalbum
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `account_infor`
--

DROP TABLE IF EXISTS `account_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_infor` (
  `id` int(255) NOT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(80) NOT NULL,
  `gender` int(255) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `album_num` int(255) NOT NULL,
  `pictures_num` int(255) NOT NULL,
  `friends_num` int(255) NOT NULL,
  `message_num` int(255) NOT NULL,
  `image` varchar(100) NOT NULL,
  `state` int(255) NOT NULL,
  `identity` int(255) NOT NULL,
  `passwdQuestion` varchar(45) NOT NULL,
  `passwdAnswer` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_infor`
--

LOCK TABLES `account_infor` WRITE;
/*!40000 ALTER TABLE `account_infor` DISABLE KEYS */;
INSERT INTO `account_infor` VALUES (1,'weiziyun','weiziyun','14302010039@fudan.edu.cn',0,'我是魏子耘',0,13,0,0,'headPortrait/default.png',0,1,'你最喜欢的奥特曼？','赛文'),(2,'魏子耘','weiziyun','947369608@qq.com',0,'大家好，我是魏子耘,欢迎光临我的相册！',0,0,0,0,'headPortrait/default.png',0,0,'','');
/*!40000 ALTER TABLE `account_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album_infor`
--

DROP TABLE IF EXISTS `album_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album_infor` (
  `id` int(255) NOT NULL,
  `album` varchar(45) NOT NULL,
  `albumId` int(255) NOT NULL AUTO_INCREMENT,
  `num` int(255) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`albumId`),
  KEY `id_idx` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album_infor`
--

LOCK TABLES `album_infor` WRITE;
/*!40000 ALTER TABLE `album_infor` DISABLE KEYS */;
INSERT INTO `album_infor` VALUES (1,'相册1',1,13,'2015-07-24');
/*!40000 ALTER TABLE `album_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments_infor`
--

DROP TABLE IF EXISTS `comments_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments_infor` (
  `comment` text,
  `observer` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `picId` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments_infor`
--

LOCK TABLES `comments_infor` WRITE;
/*!40000 ALTER TABLE `comments_infor` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends_class`
--

DROP TABLE IF EXISTS `friends_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends_class` (
  `id_class` int(255) NOT NULL,
  `classId` int(255) NOT NULL AUTO_INCREMENT,
  `classification` varchar(45) NOT NULL,
  `num` int(255) DEFAULT NULL,
  PRIMARY KEY (`classId`),
  KEY `id_class_idx` (`id_class`),
  CONSTRAINT `id_class` FOREIGN KEY (`id_class`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends_class`
--

LOCK TABLES `friends_class` WRITE;
/*!40000 ALTER TABLE `friends_class` DISABLE KEYS */;
INSERT INTO `friends_class` VALUES (1,1,'未分类',1);
/*!40000 ALTER TABLE `friends_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends_infor`
--

DROP TABLE IF EXISTS `friends_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends_infor` (
  `id_friend` int(255) NOT NULL,
  `classification` varchar(45) DEFAULT NULL,
  `friendId` int(255) DEFAULT NULL,
  `id` int(255) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id_friend_idx` (`id_friend`),
  CONSTRAINT `id_friend` FOREIGN KEY (`id_friend`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends_infor`
--

LOCK TABLES `friends_infor` WRITE;
/*!40000 ALTER TABLE `friends_infor` DISABLE KEYS */;
INSERT INTO `friends_infor` VALUES (1,'未分类',2,1);
/*!40000 ALTER TABLE `friends_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotpictures`
--

DROP TABLE IF EXISTS `hotpictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotpictures` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `idAccount` int(255) DEFAULT NULL,
  `idPic` int(255) DEFAULT NULL,
  `htext` varchar(45) DEFAULT NULL,
  `hspan` varchar(45) DEFAULT NULL,
  `introduction` text,
  PRIMARY KEY (`id`),
  KEY `idAccount_idx` (`idAccount`),
  KEY `idPic_idx` (`idPic`),
  CONSTRAINT `idAccount` FOREIGN KEY (`idAccount`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idPic` FOREIGN KEY (`idPic`) REFERENCES `pics_infor` (`picId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotpictures`
--

LOCK TABLES `hotpictures` WRITE;
/*!40000 ALTER TABLE `hotpictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotpictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `reciever` varchar(45) NOT NULL,
  `sender` varchar(45) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `date` datetime NOT NULL,
  `state` int(11) NOT NULL,
  `id_message` int(255) NOT NULL,
  `id` int(255) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `reciever_idx` (`id_message`),
  CONSTRAINT `id_message` FOREIGN KEY (`id_message`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pics_infor`
--

DROP TABLE IF EXISTS `pics_infor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pics_infor` (
  `id_pics` int(255) NOT NULL,
  `album` varchar(45) NOT NULL,
  `pics_url` varchar(100) NOT NULL,
  `picId` int(255) NOT NULL AUTO_INCREMENT,
  `picName` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `visit` int(255) NOT NULL,
  `comments` int(255) NOT NULL,
  PRIMARY KEY (`picId`),
  KEY `id_idx` (`id_pics`),
  CONSTRAINT `id_pics` FOREIGN KEY (`id_pics`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pics_infor`
--

LOCK TABLES `pics_infor` WRITE;
/*!40000 ALTER TABLE `pics_infor` DISABLE KEYS */;
INSERT INTO `pics_infor` VALUES (1,'相册1','image1.jpg',1,'图片1','2015-07-22 08:00:00',1,1),(1,'相册1','image2.jpg',2,'1.png','2015-07-26 21:12:11',0,0),(1,'相册1','image3.jpg',3,'1.png','2015-07-26 21:18:22',0,0),(1,'相册1','image4.jpg',4,'back.jpg','2015-07-26 21:45:20',0,0),(1,'相册1','image4.jpg',5,'1.png','2015-07-27 08:08:54',0,0),(1,'相册1','image5.jpg',6,'2.png','2015-07-27 08:08:54',0,0),(1,'相册1','image6.jpg',7,'3.png','2015-07-27 08:08:55',0,0),(1,'相册1','image7.jpg',8,'4.png','2015-07-27 08:08:56',0,0),(1,'相册1','1438075436610.jpg',10,'Hydrangeas.jpg','2015-07-28 17:23:56',0,0),(1,'相册1','1438075437344.jpg',11,'Jellyfish.jpg','2015-07-28 17:23:57',0,0),(1,'相册1','1438075438009.jpg',12,'Chrysanthemum.jpg','2015-07-28 17:23:58',0,0),(1,'相册1','1438075438696.jpg',13,'Koala.jpg','2015-07-28 17:23:58',0,0);
/*!40000 ALTER TABLE `pics_infor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `share`
--

DROP TABLE IF EXISTS `share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `share` (
  `id_share` int(255) NOT NULL,
  `pictureId` int(255) NOT NULL,
  `id` int(255) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id_share_idx` (`id_share`),
  KEY `pictureId_idx` (`pictureId`),
  CONSTRAINT `id_share` FOREIGN KEY (`id_share`) REFERENCES `account_infor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pictureId` FOREIGN KEY (`pictureId`) REFERENCES `pics_infor` (`picId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share`
--

LOCK TABLES `share` WRITE;
/*!40000 ALTER TABLE `share` DISABLE KEYS */;
/*!40000 ALTER TABLE `share` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-29  8:03:07
