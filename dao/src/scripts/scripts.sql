CREATE DATABASE IF NOT EXISTS service;
use service;

CREATE TABLE `customer` (
  `customer_id` int(20) NOT NULL AUTO_INCREMENT,
  `organization` varchar(20) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `second_name` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `phone_number_a` varchar(20) DEFAULT NULL,
  `phone_number_b` varchar(20) DEFAULT NULL,
  `phone_number_fax` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item` (
  `ITEM_ID` int(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(16) DEFAULT NULL,
  `DATE` datetime NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `item_price` decimal(10,0) NOT NULL DEFAULT '0',
  `marking` varchar(25) DEFAULT NULL,
  `customer_id` int(20) DEFAULT NULL,
  `status` VARCHAR(20) NOT NULL DEFAULT 'in progress',
  PRIMARY KEY (`ITEM_ID`),
  KEY `customer_fk` (`customer_id`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `SECOND_NAME` varchar(20) DEFAULT NULL,
  `LOGIN` varchar(20) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  `position` varchar(10) NOT NULL DEFAULT 'инженер',
  `role` varchar(10) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `user_LOGIN_uindex` (`LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(20) DEFAULT NULL,
  `price` decimal(10,0) NOT NULL DEFAULT '0',
  `description` text,
  `user_id` int(20) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `customer_id` (`customer_id`),
  KEY `report_user_USER_ID_fk` (`user_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `report_user_USER_ID_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (FIRST_NAME, SECOND_NAME, LOGIN, PASSWORD, position, role)
VALUES ('admin','admin','admin','admin','admin','admin');





