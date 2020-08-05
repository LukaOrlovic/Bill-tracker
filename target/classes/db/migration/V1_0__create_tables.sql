/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.6-MariaDB : Database - billtracker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`billtracker` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `billtracker`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `billId` int(11) NOT NULL AUTO_INCREMENT,
  `payingCompanyId` int(11) DEFAULT NULL,
  `receivingCompanyId` int(11) DEFAULT NULL,
  `amountValue` double DEFAULT NULL,
  `currency` text DEFAULT NULL,
  PRIMARY KEY (`billId`),
  KEY `FKdlikq9m7j2nw8qmrlvrf1qb2r` (`payingCompanyId`),
  KEY `FKagl4ocoe25ku38dh2v6chtrvj` (`receivingCompanyId`),
  CONSTRAINT `FKagl4ocoe25ku38dh2v6chtrvj` FOREIGN KEY (`receivingCompanyId`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FKdlikq9m7j2nw8qmrlvrf1qb2r` FOREIGN KEY (`payingCompanyId`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`billId`,`payingCompanyId`,`receivingCompanyId`,`amountValue`,`currency`) values 
(1,20,24,120,'Dollar'),
(51,24,20,151,'Dollar');

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` text DEFAULT NULL,
  `parentCompany` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

/*Data for the table `company` */

insert  into `company`(`companyId`,`companyName`,`parentCompany`) values 
(20,'Assecco SEE',1),
(24,'NCR',0),
(48,'FPN',0),
(49,'Nordeus',0),
(50,'Microsoft',0);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `countryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` text DEFAULT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `country` */

insert  into `country`(`countryId`,`name`) values 
(1,'Germany'),
(36,'Japan');

/*Table structure for table `vat` */

DROP TABLE IF EXISTS `vat`;

CREATE TABLE `vat` (
  `vatId` int(11) NOT NULL AUTO_INCREMENT,
  `parentCompanyId` int(11) DEFAULT NULL,
  `countryId` int(11) DEFAULT NULL,
  `amountValue` double DEFAULT NULL,
  `currency` text DEFAULT NULL,
  PRIMARY KEY (`vatId`),
  KEY `FK4uiklmp4l6lh5ueh7hy6yra7b` (`parentCompanyId`),
  KEY `FKpmxfqqgu3pn0jv3bqsntpojqh` (`countryId`),
  CONSTRAINT `FK4uiklmp4l6lh5ueh7hy6yra7b` FOREIGN KEY (`parentCompanyId`) REFERENCES `company` (`companyId`),
  CONSTRAINT `FKpmxfqqgu3pn0jv3bqsntpojqh` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `vat` */

insert  into `vat`(`vatId`,`parentCompanyId`,`countryId`,`amountValue`,`currency`) values 
(1,20,1,150,'Dollar');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
