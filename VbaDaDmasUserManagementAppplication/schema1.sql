-- MySQL dump 10.13  Distrib 5.5.18, for Win64 (x86)
--
-- Host: localhost    Database: schema1
-- ------------------------------------------------------
-- Server version	5.5.18

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
-- Table structure for table `access_def`
--

DROP TABLE IF EXISTS `access_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_def` (
  `id` varchar(80) NOT NULL,
  `access_code` varchar(10) NOT NULL,
  `access_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `access_code` (`access_code`),
  UNIQUE KEY `access_name` (`access_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_def`
--

LOCK TABLES `access_def` WRITE;
/*!40000 ALTER TABLE `access_def` DISABLE KEYS */;
INSERT INTO `access_def` VALUES ('b99154c5-34bd-4707-a6d3-bd11a99d314a','002','user'),('da5c0b53-6cb4-43c6-8313-ed47ff2dd085','001','admin');
/*!40000 ALTER TABLE `access_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_access`
--

DROP TABLE IF EXISTS `app_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_access` (
  `id` varchar(80) NOT NULL,
  `app_name` varchar(100) NOT NULL,
  `access_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK2CDFC1429EB63FBA` (`access_id`),
  CONSTRAINT `FK2CDFC1429EB63FBA` FOREIGN KEY (`access_id`) REFERENCES `access_def` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_access`
--

LOCK TABLES `app_access` WRITE;
/*!40000 ALTER TABLE `app_access` DISABLE KEYS */;
INSERT INTO `app_access` VALUES ('0f729805-20aa-4bdb-85c4-c2c4161ce564','app2','b99154c5-34bd-4707-a6d3-bd11a99d314a'),('117dbcf4-962a-4113-97dd-50973342f623','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085'),('2b1c7ee3-5666-4961-8ba3-0981edef26f4','app4','da5c0b53-6cb4-43c6-8313-ed47ff2dd085'),('3394bc2f-eda2-47b8-955f-0804c55cb65e','app100','b99154c5-34bd-4707-a6d3-bd11a99d314a'),('47125368-d0ea-4a2e-bf50-b4dc16fdecd2','app3','b99154c5-34bd-4707-a6d3-bd11a99d314a'),('5dd1d889-dbd5-4832-a027-d370a64e3772','app4','b99154c5-34bd-4707-a6d3-bd11a99d314a'),('60a03e2f-e353-4cbc-88ee-1473b7679acd','app3','da5c0b53-6cb4-43c6-8313-ed47ff2dd085'),('6b1782be-9dc4-4f0e-b9f0-6cc006af31ca','app1','da5c0b53-6cb4-43c6-8313-ed47ff2dd085'),('830dfdc9-15c3-45da-886c-3d9cad65b2bb','app1','b99154c5-34bd-4707-a6d3-bd11a99d314a'),('91ac2da4-cd6a-4462-bbcc-57e943c8c8df','app2','da5c0b53-6cb4-43c6-8313-ed47ff2dd085');
/*!40000 ALTER TABLE `app_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES ('app1'),('app100'),('app2'),('app3'),('app4');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` varchar(80) NOT NULL,
  `team_code` varchar(10) NOT NULL,
  `team_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `team_code` (`team_code`),
  UNIQUE KEY `team_name` (`team_name`),
  UNIQUE KEY `team_name_2` (`team_name`),
  UNIQUE KEY `team_code_2` (`team_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('28f66133-26c3-442b-a071-4d19d64ec0ae','003','test'),('b0add291-8a2c-4567-a0f1-8976322c746b','000','0000'),('b5a871a7-ea66-48fd-b790-39da1b2a4f4a','002','team2'),('d86a5cb8-d6a1-49c9-86fa-dbb429bc0890','001','team1');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_app_access`
--

DROP TABLE IF EXISTS `user_app_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_app_access` (
  `id` varchar(80) NOT NULL,
  `app_name` varchar(100) NOT NULL,
  `access_id` varchar(80) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7C746E369EB63FBA` (`access_id`),
  KEY `FK7C746E366EF72DA8` (`user_id`),
  CONSTRAINT `FK7C746E366EF72DA8` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`),
  CONSTRAINT `FK7C746E369EB63FBA` FOREIGN KEY (`access_id`) REFERENCES `access_def` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_app_access`
--

LOCK TABLES `user_app_access` WRITE;
/*!40000 ALTER TABLE `user_app_access` DISABLE KEYS */;
INSERT INTO `user_app_access` VALUES ('0d5ddbac-7cd6-44da-a738-42e46eed8f7b','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','06a97f8e-e214-4249-ae14-0dba209bdb7d'),('0fb0a32c-c8bc-4108-84d0-1a089bc3334c','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','a0cfc98f-b819-4fc7-8634-d267f748de20'),('12691f39-be35-4410-9c68-cc6566675a1d','app3','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','579e7f4e-7758-4eb8-9e37-adfcb7aa596a'),('137b6b13-89f9-4eac-8614-cec4ad782cc9','app3','b99154c5-34bd-4707-a6d3-bd11a99d314a','46f7590f-2447-4df6-a345-7b8fd25aa7e3'),('150e1cb1-cb48-48f2-a2bf-745c725fa261','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','46f7590f-2447-4df6-a345-7b8fd25aa7e3'),('285df7de-8f25-48f4-ad93-75dff95ee2f6','app4','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','46f7590f-2447-4df6-a345-7b8fd25aa7e3'),('2984da9f-cf23-4c6d-a5c9-1471588987f2','app1','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','46f7590f-2447-4df6-a345-7b8fd25aa7e3'),('2af52e6e-4488-472e-84c9-d66f8660ecd1','app2','b99154c5-34bd-4707-a6d3-bd11a99d314a','c90f4cff-768b-4009-85f7-862e4131a27c'),('3aa15164-6fc7-454e-b3f0-f9f59e74c31f','app3','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','c90f4cff-768b-4009-85f7-862e4131a27c'),('3dcc45a1-b228-4386-93e8-64dfdb405719','app1','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','c90f4cff-768b-4009-85f7-862e4131a27c'),('3e6c50a3-12cd-4886-b80c-797f4d4d649d','app2','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','a0cfc98f-b819-4fc7-8634-d267f748de20'),('73aa6a5f-2a6e-4ad4-8557-09ae92485ce8','app2','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','06a97f8e-e214-4249-ae14-0dba209bdb7d'),('7ddad55d-f93d-4daf-99db-bf0b6a8f19a9','app1','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','a0cfc98f-b819-4fc7-8634-d267f748de20'),('7e0fcd3d-ed8c-45f2-b45d-7f9618bebb1d','app3','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','974a4bae-3e75-448b-8c2e-66610fefdf80'),('863f70ff-a1d5-4b65-824e-fa6e18fb97a6','app4','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','a0cfc98f-b819-4fc7-8634-d267f748de20'),('8e460e9b-67be-4336-824b-f79e841ea6f1','app4','b99154c5-34bd-4707-a6d3-bd11a99d314a','06a97f8e-e214-4249-ae14-0dba209bdb7d'),('9004bcf9-829f-4f00-a329-fbaedc659501','app3','b99154c5-34bd-4707-a6d3-bd11a99d314a','06a97f8e-e214-4249-ae14-0dba209bdb7d'),('a6681238-4444-40a8-b6d1-60c6eb00ea18','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','c90f4cff-768b-4009-85f7-862e4131a27c'),('a781ee34-2961-4b64-b618-4709151f7d8d','app2','b99154c5-34bd-4707-a6d3-bd11a99d314a','46f7590f-2447-4df6-a345-7b8fd25aa7e3'),('b6324ed8-72b1-4f41-bbd6-ac5c3dfea829','app3','b99154c5-34bd-4707-a6d3-bd11a99d314a','a0cfc98f-b819-4fc7-8634-d267f748de20'),('ca2a24e2-b5a4-4818-a2e2-eb8e72381da6','app100','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','974a4bae-3e75-448b-8c2e-66610fefdf80'),('dde4fd24-505a-4b76-ac20-d66e26eff300','app4','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','974a4bae-3e75-448b-8c2e-66610fefdf80'),('e319ebff-7cb9-4c77-af77-24a4accdce1e','app1','b99154c5-34bd-4707-a6d3-bd11a99d314a','06a97f8e-e214-4249-ae14-0dba209bdb7d'),('ee95ea19-40f8-4beb-833b-858d1f524cec','app4','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','c90f4cff-768b-4009-85f7-862e4131a27c'),('fb85e44d-bace-4cfe-8f3f-e27ac07cde25','app1','da5c0b53-6cb4-43c6-8313-ed47ff2dd085','974a4bae-3e75-448b-8c2e-66610fefdf80');
/*!40000 ALTER TABLE `user_app_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` varchar(100) NOT NULL,
  `alert` longtext,
  `approver_email` varchar(50) DEFAULT NULL,
  `approver_fullname` varchar(100) DEFAULT NULL,
  `approver_phone` varchar(30) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `login` bigint(20) DEFAULT NULL,
  `password` varchar(80) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `request_detail` longtext,
  `status` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `team_id` varchar(80) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `FK1437D8A29690111A` (`team_id`),
  CONSTRAINT `FK1437D8A29690111A` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('06a97f8e-e214-4249-ae14-0dba209bdb7d',NULL,'','','','2014-01-28 13:13:33','test@qq.com','sdsd','hjhjhj',1392001515805,'e10adc3949ba59abbe56e057f20f883e','566456','','active','creator','creator1','d86a5cb8-d6a1-49c9-86fa-dbb429bc0890',NULL),('20ab3749-5d43-4322-9051-2679046cf50b',NULL,'appemail@yahoo.com','approverfullname','11111','2014-02-06 08:31:14','email@yahoo.com','first','lastname',0,'pass','00000','','active','user','dualboxUsername',NULL,NULL),('46f7590f-2447-4df6-a345-7b8fd25aa7e3',NULL,'','','','2014-02-10 08:26:48','debalguha@gmail.com','Debal','Guha',0,'123456','919830276056','','active','user','debal',NULL,NULL),('579e7f4e-7758-4eb8-9e37-adfcb7aa596a',NULL,'','','','2014-01-28 13:15:11','sasklkl@yahoo.com','sdsdgg','hghgh',0,'123456','4575','','inactive','user','zeal','d86a5cb8-d6a1-49c9-86fa-dbb429bc0890',NULL),('974a4bae-3e75-448b-8c2e-66610fefdf80',NULL,'','','','2014-02-10 08:35:12','muniam@damcosolutions.com','Munia','Mukherjee',0,'123456','919831499356','','active','creator','munia',NULL,NULL),('a0cfc98f-b819-4fc7-8634-d267f748de20',NULL,'','','','2014-01-28 13:15:39','reza@yahoo.com','sdsd','sds',0,'123456','856868','','active','user','Reza','28f66133-26c3-442b-a071-4d19d64ec0ae',NULL),('b1a94839-d648-4e07-94a3-ae4aaba34255',NULL,'','','','2014-02-06 09:30:13','saefsd@yahoo.com','waD','sa',0,'sdfsd','45454','','active','user','ssaddgfg',NULL,NULL),('c90f4cff-768b-4009-85f7-862e4131a27c',NULL,'','','','2014-01-28 13:13:33','test1@qq.com','dfdfs','sfdsdf',0,'e10adc3949ba59abbe56e057f20f883e','75','','active','creator','creator2','28f66133-26c3-442b-a071-4d19d64ec0ae',NULL),('ed6852df-dc90-4ce4-829f-3fb00db6945a',NULL,'','','','2014-02-05 13:19:37','rjalali@yahoo.com','swiss','jalali',0,'1213456','5665656','','active','user','ajax',NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_team_allocation`
--

DROP TABLE IF EXISTS `user_team_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_team_allocation` (
  `team_id` varchar(80) NOT NULL DEFAULT '',
  `user_id` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`team_id`,`user_id`),
  KEY `FKB7EB838E9690111A` (`team_id`),
  KEY `FKB7EB838E6EF72DA8` (`user_id`),
  CONSTRAINT `FKB7EB838E6EF72DA8` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`),
  CONSTRAINT `FKB7EB838E9690111A` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_team_allocation`
--

LOCK TABLES `user_team_allocation` WRITE;
/*!40000 ALTER TABLE `user_team_allocation` DISABLE KEYS */;
INSERT INTO `user_team_allocation` VALUES ('28f66133-26c3-442b-a071-4d19d64ec0ae','974a4bae-3e75-448b-8c2e-66610fefdf80'),('b5a871a7-ea66-48fd-b790-39da1b2a4f4a','974a4bae-3e75-448b-8c2e-66610fefdf80'),('b5a871a7-ea66-48fd-b790-39da1b2a4f4a','c90f4cff-768b-4009-85f7-862e4131a27c'),('d86a5cb8-d6a1-49c9-86fa-dbb429bc0890','974a4bae-3e75-448b-8c2e-66610fefdf80'),('d86a5cb8-d6a1-49c9-86fa-dbb429bc0890','c90f4cff-768b-4009-85f7-862e4131a27c');
/*!40000 ALTER TABLE `user_team_allocation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-10 10:11:48
