-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: QLCH
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `registered_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (88141,'Nguyễn Hải','Cần Thơ','0847480889','nguyen.hai22@outlook.com','2025-04-24 03:16:23'),(88178,'Phan Châu','Hải Phòng','0890372060','phan.chau76@yahoo.com','2025-04-24 03:16:23'),(88192,'Lê Hạnh','Biên Hòa','0839667097','le.hanh33@gmail.com','2025-04-24 03:16:23'),(88249,'Phạm Hương','Hà Nội','0857964071','pham.huong29@outlook.com','2025-04-24 03:16:23'),(88277,'Phan Phúc','Bình Dương','0885997083','phan.phuc71@yahoo.com','2025-04-24 03:16:23'),(88325,'Huỳnh Anh','Cần Thơ','0860094413','huynh.anh41@yahoo.com','2025-04-24 03:16:23'),(88398,'Lê Phúc','Cần Thơ','0893091915','le.phuc83@icloud.com','2025-04-24 03:16:23'),(88452,'Đặng Trang','Bình Dương','0827150339','đang.trang72@gmail.com','2025-04-24 03:16:23'),(88505,'Trần Tùng','Nha Trang','0869103461','tran.tung23@yahoo.com','2025-04-24 03:16:23'),(88551,'Nguyễn Nhung','Biên Hòa','0811997894','nguyen.nhung13@gmail.com','2025-04-24 03:16:23'),(88567,'Huỳnh Dũng','Vũng Tàu','0806521289','huynh.dung66@icloud.com','2025-04-24 03:16:23'),(88587,'Phan Hải','Hà Nội','0878670201','phan.hai28@gmail.com','2025-04-24 03:16:23'),(88606,'Hoàng Nhung','Bình Dương','0877110360','hoang.nhung5@yahoo.com','2025-04-24 03:16:23'),(88624,'Bùi Linh','Biên Hòa','0849413824','bui.linh48@yahoo.com','2025-04-24 03:16:23'),(88692,'Lê Bình','Huế','0878416271','le.binh17@outlook.com','2025-04-24 03:16:23'),(88755,'Phan Anh','Hà Nội','0878187851','phan.anh17@yahoo.com','2025-04-24 03:16:23'),(88834,'Hoàng Minh','Biên Hòa','0874367984','hoang.minh16@yahoo.com','2025-04-24 03:16:23'),(88847,'Vũ Trang','Huế','0813394958','vu.trang97@gmail.com','2025-04-24 03:16:23'),(88854,'Nguyễn Bình','TP.HCM','0874370819','nguyen.binh70@outlook.com','2025-04-24 03:16:23'),(88863,'Phan Phúc','Hà Nội','0835276358','phan.phuc21@gmail.com','2025-04-24 03:16:23'),(88864,'Trương Giang','Hà Giang','0817456378','truong.giang@gmail.com','2025-04-27 11:27:13');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `position` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11760 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (11138,'Lê','Quỳnh','Nhân viên','Huế','0871256021','lequynh71@yahoo.com',NULL),(11221,'Lê','Quân','Nhân viên','Vũng Tàu','0885242980','lequan41@icloud.com',NULL),(11237,'Đặng','Hải','Nhân viên','Hải Phòng','0863029786','đanghai2@icloud.com',NULL),(11463,'Đặng','Quân','Nhân viên','Vũng Tàu','0819831365','đangquan94@gmail.com',NULL),(11501,'Lê','Phúc','Nhân viên','Nha Trang','0891893340','lephuc48@gmail.com',NULL),(11609,'Hoàng','Châu','Nhân viên','Biên Hòa','0861257070','hoangchau50@outlook.com',NULL),(11693,'Huỳnh','Trang','Nhân viên','Biên Hòa','0889798442','huynhtrang17@gmail.com',NULL),(11758,'Bùi','Nam','Nhân viên','Cần Thơ','0847357948','buinam2@gmail.com',NULL),(11759,'Sái','Thắng','Nhân viên','Hà Nội','0856432774','saithang22@gmail.com','2024-05-01');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventories`
--

DROP TABLE IF EXISTS `inventories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventories` (
  `item_id` int NOT NULL,
  `reorder_level` int NOT NULL DEFAULT '0',
  `sold_quantity` int NOT NULL DEFAULT '0' COMMENT 'Số lượng hàng đã bán ra',
  `total_quantity` int NOT NULL DEFAULT '0' COMMENT 'Tổng số hàng nhập vào kho',
  `stock_on_hand` int GENERATED ALWAYS AS ((`total_quantity` - `sold_quantity`)) STORED COMMENT 'Số lượng hàng tồn kho = total_quantity - sold_quantity',
  PRIMARY KEY (`item_id`),
  CONSTRAINT `inventories_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `menu_items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventories`
--

LOCK TABLES `inventories` WRITE;
/*!40000 ALTER TABLE `inventories` DISABLE KEYS */;
INSERT INTO `inventories` (`item_id`, `reorder_level`, `sold_quantity`, `total_quantity`) VALUES (1,9,4,199),(2,6,15,22),(3,7,13,86),(4,8,8,22),(5,7,14,48),(6,7,14,42),(7,10,15,157),(8,6,6,95),(9,7,13,59),(10,8,42,513),(11,10,5,458),(12,5,3,99),(13,10,4,92),(14,6,5,44),(15,10,17,88),(16,9,6,551),(17,8,1,178),(18,7,3,58),(19,6,25,138),(20,9,25,33),(21,6,6,119),(22,9,10,151),(23,9,22,165),(24,7,5,8),(25,9,14,528),(26,5,8,22),(27,9,7,26),(28,7,4,44),(29,6,3,16),(30,0,2,27);
/*!40000 ALTER TABLE `inventories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_details`
--

DROP TABLE IF EXISTS `invoice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `item_id` int NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(12,2) NOT NULL,
  `line_total` decimal(12,2) GENERATED ALWAYS AS ((`quantity` * `unit_price`)) STORED,
  PRIMARY KEY (`id`),
  KEY `fk_invdet_invoice` (`invoice_id`),
  KEY `fk_invdet_item` (`item_id`),
  CONSTRAINT `fk_invdet_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`invoice_id`),
  CONSTRAINT `fk_invdet_item` FOREIGN KEY (`item_id`) REFERENCES `menu_items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_details`
--

LOCK TABLES `invoice_details` WRITE;
/*!40000 ALTER TABLE `invoice_details` DISABLE KEYS */;
INSERT INTO `invoice_details` (`id`, `invoice_id`, `item_id`, `quantity`, `unit_price`) VALUES (1,29,26,1,18.63),(2,21,27,3,49.21),(3,2,18,2,16.94),(4,30,16,3,40.71),(5,18,16,1,40.71),(6,14,15,5,30.90),(7,8,27,4,49.21),(8,4,13,3,44.52),(9,26,5,5,20.56),(10,21,20,4,46.51),(11,23,23,2,24.21),(12,8,25,3,40.00),(13,23,25,4,40.00),(14,14,7,4,20.24),(15,3,8,5,18.01),(16,28,11,1,39.04),(17,24,8,1,18.01),(18,29,6,3,18.39),(19,14,9,3,23.42),(20,28,9,4,23.42),(21,20,23,5,24.21),(22,24,18,1,16.94),(23,25,5,2,20.56),(24,13,10,3,26.13),(25,17,20,5,46.51),(26,2,16,1,40.71),(27,9,6,5,18.39),(28,19,10,3,26.13),(29,27,6,2,18.39),(30,15,23,4,24.21),(31,15,7,4,20.24),(32,9,24,1,40.45),(33,28,22,3,31.38),(34,24,22,5,31.38),(35,28,26,3,18.63),(36,2,20,5,46.51),(37,10,28,1,47.28),(38,27,15,4,30.90),(39,5,19,5,24.22),(40,3,10,2,26.13),(41,11,9,1,23.42),(42,28,5,3,20.56),(43,1,12,3,48.49),(44,24,6,4,18.39),(45,11,23,5,24.21),(46,15,10,2,26.13),(47,13,4,2,41.28),(48,26,22,1,31.38),(49,26,7,2,20.24),(50,16,4,5,41.28),(51,4,19,4,24.22),(52,8,29,3,31.00),(53,23,19,4,24.22),(54,9,5,4,20.56),(55,1,10,4,26.13),(56,10,13,1,44.52),(57,9,2,1,17.94),(58,24,19,5,24.22),(59,5,20,3,46.51),(60,11,3,4,44.13),(61,21,17,1,36.14),(62,8,21,1,23.55),(63,30,21,5,23.55),(64,25,25,5,40.00),(65,28,20,1,46.51),(66,7,7,1,20.24),(67,30,14,2,35.72),(68,18,19,4,24.22),(69,10,15,3,30.90),(70,9,2,5,17.94),(71,20,11,4,39.04),(72,30,1,1,48.59),(73,26,4,1,41.28),(74,18,22,1,31.38),(75,26,2,5,17.94),(76,10,19,3,24.22),(77,16,30,2,35.20),(78,4,25,2,40.00),(79,10,20,3,46.51),(80,19,10,3,26.13),(81,23,10,5,26.13),(82,29,26,2,18.63),(83,28,3,3,44.13),(84,27,16,1,40.71),(85,14,23,2,24.21),(86,3,28,1,47.28),(87,20,24,4,40.45),(88,8,20,4,46.51),(89,6,2,2,17.94),(90,29,1,3,48.59),(91,9,3,1,44.13),(92,13,14,3,35.72),(93,10,23,4,24.21),(94,11,26,2,18.63),(95,30,28,2,47.28),(96,20,2,2,17.94),(97,2,7,4,20.24),(98,20,15,5,30.90),(99,2,3,5,44.13),(100,28,9,5,23.42),(101,31,10,10,26.13);
/*!40000 ALTER TABLE `invoice_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invoice_details_before_insert` BEFORE INSERT ON `invoice_details` FOR EACH ROW BEGIN
    DECLARE current_price DECIMAL(12,2);
    SELECT unit_price INTO current_price
    FROM menu_items
    WHERE item_id = NEW.item_id;

    SET NEW.unit_price = current_price;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invoice_details_after_insert` AFTER INSERT ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE invoices
  SET total_amount = (
    SELECT SUM(quantity * unit_price)
    FROM invoice_details
    WHERE invoice_id = NEW.invoice_id
  )
  WHERE invoice_id = NEW.invoice_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invdet_sold_after_insert` AFTER INSERT ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE inventories
  SET sold_quantity = sold_quantity + NEW.quantity
  WHERE item_id = NEW.item_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invoice_details_before_update` BEFORE UPDATE ON `invoice_details` FOR EACH ROW BEGIN
    DECLARE current_price DECIMAL(12,2);
    SELECT unit_price INTO current_price
    FROM menu_items
    WHERE item_id = NEW.item_id;

    SET NEW.unit_price = current_price;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invoice_details_after_update` AFTER UPDATE ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE invoices
  SET total_amount = (
    SELECT SUM(quantity * unit_price)
    FROM invoice_details
    WHERE invoice_id = NEW.invoice_id
  )
  WHERE invoice_id = NEW.invoice_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invdet_sold_after_update` AFTER UPDATE ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE inventories
  SET sold_quantity = GREATEST(sold_quantity - OLD.quantity, 0) + NEW.quantity
  WHERE item_id = NEW.item_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invoice_details_after_delete` AFTER DELETE ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE invoices
  SET total_amount = (
    SELECT IFNULL(SUM(quantity * unit_price), 0)
    FROM invoice_details
    WHERE invoice_id = OLD.invoice_id
  )
  WHERE invoice_id = OLD.invoice_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_invdet_sold_after_delete` AFTER DELETE ON `invoice_details` FOR EACH ROW BEGIN
  UPDATE inventories
  SET sold_quantity = GREATEST(sold_quantity - OLD.quantity, 0)
  WHERE item_id = OLD.item_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoices` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `invoice_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_amount` decimal(12,2) NOT NULL,
  `tax` decimal(12,2) GENERATED ALWAYS AS ((`total_amount` * 0.002)) STORED,
  `discount` decimal(12,2) GENERATED ALWAYS AS ((`total_amount` + `tax`)) STORED,
  `status` enum('due','partial','paid') NOT NULL DEFAULT 'due',
  PRIMARY KEY (`invoice_id`),
  KEY `customer_id` (`customer_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `invoices_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`invoice_id`, `customer_id`, `employee_id`, `invoice_date`, `total_amount`, `status`) VALUES (1,88567,11758,'2025-04-02 20:30:36',249.99,'paid'),(2,88847,11758,'2025-04-01 10:38:25',608.75,'paid'),(3,88178,11501,'2025-04-01 08:10:33',189.59,'paid'),(4,88606,11237,'2025-04-04 18:14:45',310.44,'paid'),(5,88847,11758,'2025-04-03 13:35:04',260.63,'paid'),(6,88692,11138,'2025-04-05 16:31:12',35.88,'paid'),(7,88834,11501,'2025-04-07 18:24:50',20.24,'paid'),(8,88847,11609,'2025-04-04 19:19:51',619.43,'paid'),(9,88624,11758,'2025-04-02 10:45:12',366.41,'paid'),(10,88192,11501,'2025-04-03 19:20:29',493.53,'paid'),(11,88192,11609,'2025-04-07 13:04:27',358.25,'paid'),(12,88847,11221,'2025-04-01 19:09:28',0.00,'paid'),(13,88567,11138,'2025-04-05 15:40:02',268.11,'paid'),(14,88624,11501,'2025-04-04 17:51:28',354.14,'paid'),(15,88551,11609,'2025-04-02 12:56:37',230.06,'paid'),(16,88847,11463,'2025-04-07 18:06:20',276.80,'paid'),(17,88606,11693,'2025-04-04 09:45:58',232.55,'paid'),(18,88755,11237,'2025-04-07 19:26:51',168.97,'paid'),(19,88192,11221,'2025-04-06 16:37:49',156.78,'paid'),(20,88178,11138,'2025-04-01 13:16:06',629.39,'paid'),(21,88141,11758,'2025-04-03 15:36:57',369.81,'paid'),(22,88178,11138,'2025-04-03 17:41:22',0.00,'paid'),(23,88854,11463,'2025-04-06 11:15:58',435.95,'paid'),(24,88692,11758,'2025-04-07 16:43:52',386.51,'paid'),(25,88834,11138,'2025-04-03 08:48:25',241.12,'paid'),(26,88863,11758,'2025-04-07 11:32:34',305.64,'paid'),(27,88551,11221,'2025-04-03 11:51:23',201.09,'paid'),(28,88847,11501,'2025-04-06 10:53:09',640.43,'paid'),(29,88249,11221,'2025-04-03 14:01:21',256.83,'paid'),(30,88587,11693,'2025-04-07 10:19:42',454.47,'paid'),(31,88863,11237,'2025-04-27 00:29:40',261.30,'paid');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_items`
--

DROP TABLE IF EXISTS `menu_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `unit_price` decimal(12,2) NOT NULL,
  `cost_price` decimal(12,2) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `status` enum('active','inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_items`
--

LOCK TABLES `menu_items` WRITE;
/*!40000 ALTER TABLE `menu_items` DISABLE KEYS */;
INSERT INTO `menu_items` VALUES (1,'Khoai tây chiên',48.59,9.31,'Kẹo','active'),(2,'Bim bim rong biển',17.94,14.02,'Kẹo','active'),(3,'Bắp rang bơ',44.13,16.86,'Snack','active'),(4,'Hạt điều rang muối',41.28,20.62,'Đồ uống','active'),(5,'Hạt dẻ cười',20.56,17.31,'Kẹo','active'),(6,'Snack phô mai',18.39,9.76,'Đồ uống','active'),(7,'Snack tôm cay',20.24,16.27,'Bánh','active'),(8,'Mì ly ăn liền',18.01,9.93,'Đồ uống','active'),(9,'Mì gói Hàn Quốc',23.42,13.86,'Bánh','active'),(10,'Chân gà sả tắc',26.13,24.65,'Đồ uống','active'),(11,'Xoài sấy dẻo',39.04,6.68,'Bánh','active'),(12,'Me ngào đường',48.49,14.60,'Snack','active'),(13,'Mứt dừa non',44.52,7.72,'Snack','active'),(14,'Mứt gừng lát',35.72,23.40,'Bánh','active'),(15,'Kẹo dẻo Haribo',30.90,15.34,'Đồ uống','active'),(16,'Kẹo cao su không đường',40.71,7.42,'Kẹo','active'),(17,'Sô cô la đen',36.14,14.51,'Bánh','active'),(18,'Sô cô la hạnh nhân',16.94,8.04,'Kẹo','active'),(19,'Bánh quy bơ',24.22,13.29,'Đồ uống','active'),(20,'Bánh quy socola chip',46.51,7.73,'Bánh','active'),(21,'Trà đào lon',23.55,5.44,'Đồ uống','active'),(22,'Trà sữa lon',31.38,20.13,'Snack','active'),(23,'Nước cam ép',24.21,17.58,'Bánh','active'),(24,'Nước dừa tươi đóng lon',40.45,14.62,'Đồ uống','active'),(25,'Coca Cola lon',40.00,17.33,'Snack','active'),(26,'Pepsi lon',18.63,12.35,'Kẹo','active'),(27,'Sprite lon',49.21,9.83,'Đồ uống','active'),(28,'Nước suối Lavie',47.28,21.42,'Snack','active'),(29,'Bia Heineken lon',31.00,19.41,'Bánh','active'),(30,'Bia Sapporo lon',35.20,23.90,'Đồ uống','active'),(31,'Bánh gạo',50.00,12.00,'Bánh','active');
/*!40000 ALTER TABLE `menu_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_financial_summary_table`
--

DROP TABLE IF EXISTS `monthly_financial_summary_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monthly_financial_summary_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_month` varchar(7) NOT NULL COMMENT 'Tháng báo cáo theo định dạng YYYY-MM',
  `monthly_revenue` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT 'Tổng doanh thu trong tháng',
  `monthly_purchase_cost` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT 'Tổng chi phí nhập hàng trong tháng',
  `monthly_salary_cost` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT 'Tổng chi phí lương nhân viên trong tháng',
  `monthly_rent` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT 'Chi phí thuê mặt bằng cố định hàng tháng',
  `monthly_profit` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT 'Lãi = Doanh thu - Chi phí nhập hàng - Chi phí lương - Thuê mặt bằng',
  `calculated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Thời điểm dữ liệu được tính toán hoặc cập nhật lần cuối',
  PRIMARY KEY (`id`),
  UNIQUE KEY `report_month` (`report_month`),
  KEY `idx_report_month` (`report_month`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_financial_summary_table`
--

LOCK TABLES `monthly_financial_summary_table` WRITE;
/*!40000 ALTER TABLE `monthly_financial_summary_table` DISABLE KEYS */;
INSERT INTO `monthly_financial_summary_table` VALUES (22,'2025-04',9383.09,60142.04,9930.01,1000.00,-61688.96,'2025-04-27 13:34:58');
/*!40000 ALTER TABLE `monthly_financial_summary_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `pay_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(12,2) NOT NULL,
  `method` enum('cash','card','transfer') NOT NULL,
  `status` enum('pending','completed','failed') NOT NULL DEFAULT 'completed',
  PRIMARY KEY (`payment_id`),
  KEY `invoice_id` (`invoice_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order_details`
--

DROP TABLE IF EXISTS `purchase_order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order_details` (
  `pod_id` int NOT NULL AUTO_INCREMENT,
  `po_id` int NOT NULL,
  `item_id` int NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(12,2) NOT NULL,
  `line_total` decimal(12,2) GENERATED ALWAYS AS ((`quantity` * `unit_price`)) STORED,
  `received_date` date DEFAULT NULL,
  PRIMARY KEY (`pod_id`),
  KEY `item_id` (`item_id`),
  KEY `purchase_order_details_ibfk_1` (`po_id`),
  CONSTRAINT `purchase_order_details_ibfk_1` FOREIGN KEY (`po_id`) REFERENCES `purchase_orders` (`po_id`),
  CONSTRAINT `purchase_order_details_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `menu_items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order_details`
--

LOCK TABLES `purchase_order_details` WRITE;
/*!40000 ALTER TABLE `purchase_order_details` DISABLE KEYS */;
INSERT INTO `purchase_order_details` (`pod_id`, `po_id`, `item_id`, `quantity`, `unit_price`, `received_date`) VALUES (1,7,22,43,20.62,'2025-04-15'),(2,12,23,50,17.33,'2025-04-15'),(3,10,15,49,21.42,'2025-04-15'),(4,2,28,16,13.86,'2025-04-15'),(5,18,3,19,23.40,'2025-04-15'),(6,5,26,9,7.73,'2025-04-15'),(7,26,4,22,20.62,'2025-04-15'),(8,30,27,6,15.34,'2025-04-15'),(9,17,7,47,15.34,'2025-04-15'),(10,3,21,13,9.31,'2025-04-15'),(11,6,9,10,14.51,'2025-04-15'),(12,30,18,47,17.31,'2025-04-15'),(13,14,5,48,13.86,'2025-04-15'),(14,23,12,33,21.42,'2025-04-15'),(15,27,21,32,20.13,'2025-04-15'),(16,13,21,23,14.62,'2025-04-15'),(17,12,17,20,19.41,'2025-04-15'),(18,15,19,39,13.86,'2025-04-15'),(19,9,2,10,9.83,'2025-04-15'),(20,7,13,21,7.73,'2025-04-15'),(21,12,12,10,13.29,'2025-04-15'),(22,12,3,40,14.51,'2025-04-15'),(23,12,18,11,14.51,'2025-04-15'),(24,20,1,15,19.41,'2025-04-15'),(25,29,3,27,12.35,'2025-04-15'),(26,5,13,6,21.42,'2025-04-15'),(27,29,22,6,14.51,'2025-04-15'),(28,22,7,50,13.29,'2025-04-15'),(29,6,28,28,14.62,'2025-04-15'),(30,30,19,23,8.04,'2025-04-15'),(31,18,14,44,14.51,'2025-04-15'),(32,28,22,44,14.02,'2025-04-15'),(33,6,15,21,13.86,'2025-04-15'),(34,4,9,49,20.13,'2025-04-15'),(35,30,27,20,23.90,'2025-04-15'),(36,30,6,23,6.68,'2025-04-15'),(37,5,19,44,17.58,'2025-04-15'),(38,18,2,12,14.62,'2025-04-15'),(39,20,1,32,7.72,'2025-04-15'),(40,18,8,8,6.68,'2025-04-15'),(41,14,29,16,21.42,'2025-04-15'),(42,4,19,32,7.73,'2025-04-15'),(43,17,7,11,23.90,'2025-04-15'),(44,23,7,49,5.44,'2025-04-15'),(45,28,8,49,8.04,'2025-04-15'),(46,10,13,40,9.83,'2025-04-15'),(47,7,13,25,14.60,'2025-04-15'),(48,24,1,43,8.04,'2025-04-15'),(49,11,24,8,17.58,'2025-04-15'),(50,22,12,45,16.86,'2025-04-15'),(51,24,8,38,23.90,'2025-04-15'),(52,14,1,12,14.02,'2025-04-15'),(53,8,20,33,23.90,'2025-04-15'),(54,8,6,19,5.44,'2025-04-15'),(55,29,1,50,7.73,'2025-04-15'),(56,20,23,39,20.13,'2025-04-15'),(57,6,22,33,9.76,'2025-04-15'),(58,18,1,47,23.90,'2025-04-15'),(59,29,22,25,17.33,'2025-04-15'),(60,4,12,11,9.76,'2025-04-15'),(61,4,23,41,17.33,'2025-04-15'),(62,10,15,6,14.02,'2025-04-15'),(63,2,30,7,12.35,'2025-04-15'),(64,28,21,40,13.86,'2025-04-15'),(65,25,23,35,24.65,'2025-04-15'),(66,3,15,7,17.31,'2025-04-15'),(67,29,15,5,16.27,'2025-04-15'),(68,12,17,8,15.34,'2025-04-15'),(69,23,21,11,14.62,'2025-04-15'),(70,11,26,13,23.40,'2025-04-15'),(71,21,11,100,6.68,'2025-04-15'),(72,19,25,57,17.33,'2025-04-15'),(73,1,25,75,17.33,'2025-04-15'),(74,21,10,58,24.65,'2025-04-15'),(75,1,10,42,24.65,'2025-04-15'),(76,1,16,60,7.42,'2025-04-15'),(77,19,10,52,24.65,'2025-04-15'),(78,16,16,87,7.42,'2025-04-15'),(79,1,16,41,7.42,'2025-04-15'),(80,16,16,69,7.42,'2025-04-15'),(81,1,11,48,6.68,'2025-04-15'),(82,1,25,73,17.33,'2025-04-15'),(83,21,11,92,6.68,'2025-04-15'),(84,21,25,86,17.33,'2025-04-15'),(85,1,16,89,7.42,'2025-04-15'),(86,21,16,42,7.42,'2025-04-15'),(87,19,16,41,7.42,'2025-04-15'),(88,16,10,76,24.65,'2025-04-15'),(89,1,10,79,24.65,'2025-04-15'),(90,1,10,67,24.65,'2025-04-15'),(91,21,16,82,7.42,'2025-04-15'),(92,16,10,70,24.65,'2025-04-15'),(93,1,25,99,17.33,'2025-04-15'),(94,1,10,69,24.65,'2025-04-15'),(95,21,11,47,6.68,'2025-04-15'),(96,1,25,42,17.33,'2025-04-15'),(97,21,25,96,17.33,'2025-04-15'),(98,16,16,40,7.42,'2025-04-15'),(99,1,11,91,6.68,'2025-04-15'),(100,16,11,80,6.68,'2025-04-15'),(101,34,30,20,23.90,'2025-04-27'),(102,34,17,150,14.51,'2025-04-27');
/*!40000 ALTER TABLE `purchase_order_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_pod_before_insert` BEFORE INSERT ON `purchase_order_details` FOR EACH ROW BEGIN
  SET NEW.received_date =
    (SELECT expected_date
     FROM purchase_orders
     WHERE po_id = NEW.po_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_pod_after_insert` AFTER INSERT ON `purchase_order_details` FOR EACH ROW BEGIN
  UPDATE inventories i
  SET i.total_quantity = (
    SELECT COALESCE(SUM(quantity),0)
    FROM purchase_order_details
    WHERE item_id = NEW.item_id
  )
  WHERE i.item_id = NEW.item_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_pod_before_update` BEFORE UPDATE ON `purchase_order_details` FOR EACH ROW BEGIN
  SET NEW.received_date =
    (SELECT expected_date
     FROM purchase_orders
     WHERE po_id = NEW.po_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_pod_after_update` AFTER UPDATE ON `purchase_order_details` FOR EACH ROW BEGIN
  -- Nếu đổi cùng item_id, chỉ cần tính lại cho một
  IF OLD.item_id = NEW.item_id THEN
    UPDATE inventories i
    SET i.total_quantity = (
      SELECT COALESCE(SUM(quantity),0)
      FROM purchase_order_details
      WHERE item_id = NEW.item_id
    )
    WHERE i.item_id = NEW.item_id;
  ELSE
    -- Tính lại cho item cũ
    UPDATE inventories i
    SET i.total_quantity = (
      SELECT COALESCE(SUM(quantity),0)
      FROM purchase_order_details
      WHERE item_id = OLD.item_id
    )
    WHERE i.item_id = OLD.item_id;
    -- Tính lại cho item mới
    UPDATE inventories i
    SET i.total_quantity = (
      SELECT COALESCE(SUM(quantity),0)
      FROM purchase_order_details
      WHERE item_id = NEW.item_id
    )
    WHERE i.item_id = NEW.item_id;
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_pod_after_delete` AFTER DELETE ON `purchase_order_details` FOR EACH ROW BEGIN
  UPDATE inventories i
  SET i.total_quantity = (
    SELECT COALESCE(SUM(quantity),0)
    FROM purchase_order_details
    WHERE item_id = OLD.item_id
  )
  WHERE i.item_id = OLD.item_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `purchase_orders`
--

DROP TABLE IF EXISTS `purchase_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_orders` (
  `po_id` int NOT NULL AUTO_INCREMENT,
  `supplier_id` int NOT NULL,
  `expected_date` date DEFAULT NULL,
  `status` enum('pending','received','cancelled') NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`po_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `purchase_orders_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_orders`
--

LOCK TABLES `purchase_orders` WRITE;
/*!40000 ALTER TABLE `purchase_orders` DISABLE KEYS */;
INSERT INTO `purchase_orders` VALUES (1,44370,'2025-04-15','received'),(2,44840,'2025-04-15','received'),(3,44659,'2025-04-15','received'),(4,44418,'2025-04-15','received'),(5,44548,'2025-04-15','received'),(6,44370,'2025-04-15','received'),(7,44840,'2025-04-15','received'),(8,44659,'2025-04-15','received'),(9,44418,'2025-04-15','received'),(10,44548,'2025-04-15','received'),(11,44370,'2025-04-15','received'),(12,44840,'2025-04-15','received'),(13,44659,'2025-04-15','received'),(14,44418,'2025-04-15','received'),(15,44548,'2025-04-15','received'),(16,44370,'2025-04-15','received'),(17,44840,'2025-04-15','received'),(18,44659,'2025-04-15','received'),(19,44418,'2025-04-15','received'),(20,44548,'2025-04-15','received'),(21,44370,'2025-04-15','received'),(22,44840,'2025-04-15','received'),(23,44659,'2025-04-15','received'),(24,44418,'2025-04-15','received'),(25,44548,'2025-04-15','received'),(26,44370,'2025-04-15','received'),(27,44840,'2025-04-15','received'),(28,44659,'2025-04-15','received'),(29,44418,'2025-04-15','received'),(30,44548,'2025-04-15','received'),(31,44370,'2025-04-27','received'),(32,44370,'2025-04-27','cancelled'),(33,44370,'2025-04-27','received'),(34,44370,'2025-04-27','received');
/*!40000 ALTER TABLE `purchase_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT 'admin, staff, customer',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(3,'customer'),(2,'staff');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaries`
--

DROP TABLE IF EXISTS `salaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salaries` (
  `salary_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `base_salary` decimal(12,2) NOT NULL,
  `shifts_worked` int NOT NULL DEFAULT '0' COMMENT 'Số ca làm trong kỳ (từ work_schedules)',
  `monthly_salary` decimal(12,2) GENERATED ALWAYS AS ((`base_salary` * `shifts_worked`)) STORED COMMENT 'Lương tháng = base_salary * shifts_worked',
  PRIMARY KEY (`salary_id`),
  KEY `idx_salaries_emp_id` (`employee_id`),
  CONSTRAINT `salaries_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaries`
--

LOCK TABLES `salaries` WRITE;
/*!40000 ALTER TABLE `salaries` DISABLE KEYS */;
INSERT INTO `salaries` (`salary_id`, `employee_id`, `base_salary`, `shifts_worked`) VALUES (1,11138,154.16,8),(2,11221,161.90,7),(3,11237,188.54,7),(4,11463,175.11,7),(5,11501,180.35,7),(6,11609,150.67,7),(7,11693,189.59,7),(8,11758,196.23,7);
/*!40000 ALTER TABLE `salaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `contact_person` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44841 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (44370,'Công ty Huỳnh Trang','Bùi Linh','0842840408','Huế','congtyhuynhtrang97@gmail.com','2025-04-24 03:29:51'),(44418,'Công ty Lê Nga','Lê Hương','0871950751','Hải Phòng','congtylenga69@yahoo.com','2025-04-24 03:29:51'),(44548,'Công ty Lê Vân','Hoàng Trang','0888039362','Biên Hòa','congtylevan44@yahoo.com','2025-04-24 03:29:51'),(44659,'Công ty Lê Trang','Phan Quỳnh','0880725630','Vũng Tàu','congtyletrang90@gmail.com','2025-04-24 03:29:51'),(44840,'Công ty Vũ Khanh','Trần Châu','0811576733','Bình Dương','congtyvukhanh54@yahoo.com','2025-04-24 03:29:51');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  `employee_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  KEY `employee_id` (`employee_id`),
  KEY `fk_users_customer` (`customer_id`),
  CONSTRAINT `fk_users_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (30,'admin','admin',1,NULL,'2025-04-24 03:18:14',NULL),(31,'11237','123456',2,11237,'2025-04-24 03:18:14',NULL),(32,'11463','123456',2,11463,'2025-04-24 03:18:14',NULL),(33,'11221','123456',2,11221,'2025-04-24 03:18:14',NULL),(34,'11501','123456',2,11501,'2025-04-24 03:18:15',NULL),(35,'11609','123456',2,11609,'2025-04-24 03:18:15',NULL),(36,'11758','123456',2,11758,'2025-04-24 03:18:15',NULL),(37,'11693','123456',2,11693,'2025-04-24 03:18:15',NULL),(38,'11138','123456',2,11138,'2025-04-24 03:18:15',NULL),(39,'88141','456789',3,NULL,'2025-04-27 12:49:16',88141),(40,'88178','456789',3,NULL,'2025-04-27 12:49:16',88178),(41,'88192','456789',3,NULL,'2025-04-27 12:49:16',88192),(42,'88249','456789',3,NULL,'2025-04-27 12:49:16',88249),(43,'88277','456789',3,NULL,'2025-04-27 12:49:16',88277),(44,'88325','456789',3,NULL,'2025-04-27 12:49:16',88325),(45,'88398','456789',3,NULL,'2025-04-27 12:49:16',88398),(46,'88452','456789',3,NULL,'2025-04-27 12:49:16',88452),(47,'88505','456789',3,NULL,'2025-04-27 12:49:16',88505),(48,'88551','456789',3,NULL,'2025-04-27 12:49:16',88551),(49,'88567','456789',3,NULL,'2025-04-27 12:49:16',88567),(50,'88587','456789',3,NULL,'2025-04-27 12:49:16',88587),(51,'88606','456789',3,NULL,'2025-04-27 12:49:16',88606),(52,'88624','456789',3,NULL,'2025-04-27 12:49:16',88624),(53,'88692','456789',3,NULL,'2025-04-27 12:49:16',88692),(54,'88755','456789',3,NULL,'2025-04-27 12:49:16',88755),(55,'88834','456789',3,NULL,'2025-04-27 12:49:16',88834),(56,'88847','456789',3,NULL,'2025-04-27 12:49:16',88847),(57,'88854','456789',3,NULL,'2025-04-27 12:49:16',88854),(58,'88863','456789',3,NULL,'2025-04-27 12:49:16',88863),(59,'88864','456789',3,NULL,'2025-04-27 12:54:13',88864);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_schedules`
--

DROP TABLE IF EXISTS `work_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_schedules` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `work_date` date NOT NULL,
  `shift_number` tinyint NOT NULL COMMENT '1=Ca Sáng sớm, 2=Ca Sáng muộn, 3=Ca Chiều sớm, 4=Ca Chiều muộn',
  PRIMARY KEY (`schedule_id`),
  KEY `employee_id` (`employee_id`),
  KEY `idx_shift_number` (`shift_number`),
  CONSTRAINT `work_schedules_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_schedules`
--

LOCK TABLES `work_schedules` WRITE;
/*!40000 ALTER TABLE `work_schedules` DISABLE KEYS */;
INSERT INTO `work_schedules` VALUES (1,11138,'2025-04-01',1),(2,11221,'2025-04-01',1),(3,11237,'2025-04-01',2),(4,11463,'2025-04-01',2),(5,11501,'2025-04-01',3),(6,11609,'2025-04-01',3),(7,11693,'2025-04-01',4),(8,11758,'2025-04-01',4),(9,11138,'2025-04-02',1),(10,11221,'2025-04-02',1),(11,11237,'2025-04-02',2),(12,11463,'2025-04-02',2),(13,11501,'2025-04-02',3),(14,11609,'2025-04-02',3),(15,11693,'2025-04-02',4),(16,11758,'2025-04-02',4),(17,11138,'2025-04-03',1),(18,11221,'2025-04-03',1),(19,11237,'2025-04-03',2),(20,11463,'2025-04-03',2),(21,11501,'2025-04-03',3),(22,11609,'2025-04-03',3),(23,11693,'2025-04-03',4),(24,11758,'2025-04-03',4),(25,11138,'2025-04-04',1),(26,11221,'2025-04-04',1),(27,11237,'2025-04-04',2),(28,11463,'2025-04-04',2),(29,11501,'2025-04-04',3),(30,11609,'2025-04-04',3),(31,11693,'2025-04-04',4),(32,11758,'2025-04-04',4),(33,11138,'2025-04-05',1),(34,11221,'2025-04-05',1),(35,11237,'2025-04-05',2),(36,11463,'2025-04-05',2),(37,11501,'2025-04-05',3),(38,11609,'2025-04-05',3),(39,11693,'2025-04-05',4),(40,11758,'2025-04-05',4),(41,11138,'2025-04-06',1),(42,11221,'2025-04-06',1),(43,11237,'2025-04-06',2),(44,11463,'2025-04-06',2),(45,11501,'2025-04-06',3),(46,11609,'2025-04-06',3),(47,11693,'2025-04-06',4),(48,11758,'2025-04-06',4),(49,11138,'2025-04-07',1),(50,11221,'2025-04-07',1),(51,11237,'2025-04-07',2),(52,11463,'2025-04-07',2),(53,11501,'2025-04-07',3),(54,11609,'2025-04-07',3),(55,11693,'2025-04-07',4),(56,11758,'2025-04-07',4),(57,11138,'2025-04-25',1);
/*!40000 ALTER TABLE `work_schedules` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_ws_after_insert` AFTER INSERT ON `work_schedules` FOR EACH ROW BEGIN
  IF NEW.work_date BETWEEN '2025-04-01' AND CURDATE() THEN
    UPDATE salaries
    SET
      shifts_worked  = shifts_worked  + 1,
      monthly_salary = base_salary * (shifts_worked + 1)
    WHERE employee_id = NEW.employee_id;
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_ws_after_update` AFTER UPDATE ON `work_schedules` FOR EACH ROW BEGIN
  DECLARE old_in_period BOOLEAN DEFAULT (OLD.work_date BETWEEN '2025-04-01' AND CURDATE());
  DECLARE new_in_period BOOLEAN DEFAULT (NEW.work_date BETWEEN '2025-04-01' AND CURDATE());

  -- Giảm ca cũ nếu nó còn nằm trong khoảng
  IF old_in_period THEN
    UPDATE salaries
    SET
      shifts_worked  = GREATEST(shifts_worked - 1, 0),
      monthly_salary = base_salary * GREATEST(shifts_worked - 1, 0)
    WHERE employee_id = OLD.employee_id;
  END IF;

  -- Tăng ca mới nếu nó nằm trong khoảng
  IF new_in_period THEN
    UPDATE salaries
    SET
      shifts_worked  = shifts_worked + 1,
      monthly_salary = base_salary * (shifts_worked + 1)
    WHERE employee_id = NEW.employee_id;
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_ws_after_delete` AFTER DELETE ON `work_schedules` FOR EACH ROW BEGIN
  IF OLD.work_date BETWEEN '2025-04-01' AND CURDATE() THEN
    UPDATE salaries
    SET
      shifts_worked  = GREATEST(shifts_worked - 1, 0),
      monthly_salary = base_salary * GREATEST(shifts_worked - 1, 0)
    WHERE employee_id = OLD.employee_id;
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 13:35:49
