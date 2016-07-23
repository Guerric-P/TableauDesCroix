-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: tableaudescroix
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personne` (
  `id_personne` int(11) NOT NULL AUTO_INCREMENT,
  `trigramme` varchar(3) DEFAULT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `id_ilot` int(11) DEFAULT NULL,
  `a_mis_sa_croix` bit(1) DEFAULT NULL,
  `nb_croix` int(11) DEFAULT NULL,
  `date_croissants` datetime DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `mot_de_passe` varchar(45) DEFAULT NULL,
  `est_admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_personne`),
  KEY `FK_ID_ILOT_idx` (`id_ilot`),
  CONSTRAINT `FK_ID_ILOT` FOREIGN KEY (`id_ilot`) REFERENCES `ilot` (`id_ilot`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1,'CBX','Boibieux','Clément',2,'\0',0,'2017-03-23 00:00:00','cboibieux','xGdooY+6V8q1cze7mR9TjQ==','\0'),(2,'GPU','Phalippou','Guerric',1,'\0',0,'2017-03-22 00:00:00','gphalippou','Mb62E7j6ap2/SoCQa2dTNQ==',''),(3,'MRE','Ramaugé','Maude',2,'\0',0,NULL,'mramauge','mramauge','\0'),(4,'TTE','Tekrane','Taïb',2,'\0',0,NULL,'ttekrane','ttekrane','\0'),(5,'MBN','Belin','Maxime',2,'\0',0,NULL,'mbelin','mbelin','\0'),(6,'VMN','Merlin','Vincent',2,'\0',0,NULL,'vmerlin','vmerlin','\0'),(7,'QGD','Gaillard','Quentin',2,'\0',0,NULL,'qgaillard','qgaillard','\0'),(8,'JNT','Nallet','Jean-Baptiste',2,'\0',0,NULL,'jnallet','jnallet','\0'),(9,'BJD','Jomard','Benjamin',3,'\0',0,NULL,'bjomard','bjomard','\0'),(10,'TLT','Lazert','Thibault',3,'\0',0,NULL,'tlazert','tlazert','\0'),(11,'MSI','Sadowski','Mickaël',3,'\0',0,NULL,'msadowski','msadowski','\0'),(12,'TDT','Daumont','Thibaut',4,'\0',0,NULL,'tdaumont','tdaumont','\0'),(13,'STN','Soutthavone','Tran',4,'\0',0,NULL,'stran','stran','\0'),(14,'BHS','Hours','Sébastien',5,'\0',0,NULL,'shours','shours','\0'),(15,'NDE','Delesalle','Nicolas',5,'\0',0,NULL,'ndelesalle','ndelesalle','\0'),(16,'DPS','Paquis','Déborah',3,'\0',0,NULL,'dpaquis','dpaquis','\0'),(17,'SMO','Mahéo','Sébastien',3,'\0',0,NULL,'smaheo','smaheo','\0');
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-24  1:30:06
