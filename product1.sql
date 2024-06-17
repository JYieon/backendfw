-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: product1
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Current Database: `product1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `product1` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `product1`;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `titleEng` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'음료수','beverage'),(2,'주류','liquor'),(3,'과자','confectionery');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoryId` int NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (118,'TOP',1,800,50),(119,'고래밥',3,500,80),(120,'꼬깔콘',3,1000,30),(121,'꼬북칩',3,800,200),(122,'꽃개랑',3,1000,30),(123,'다이제스티브',3,700,50),(124,'데미소다',1,400,59),(125,'레쓰비',1,700,75),(126,'마라렛트',3,800,50),(127,'막걸리',2,1100,80),(128,'맛동산',3,900,20),(129,'맥주',2,2500,100),(130,'맥주라이트',2,2500,50),(131,'맥콜',1,300,15),(132,'먹태깡',3,1100,80),(133,'몽셀',3,1200,90),(134,'밀키스',1,700,75),(135,'바나나우유',1,1000,50),(136,'바나나쿠키',3,1500,10),(137,'베지밀',1,900,20),(138,'비락수정과',1,700,50),(139,'비락식혜',1,500,80),(140,'사이다',1,1000,20),(141,'산도',3,700,90),(142,'새우깡',3,700,75),(143,'소주',2,1300,150),(144,'스프라이트',1,1000,110),(145,'양파링',3,400,59),(146,'에이스',3,1800,150),(147,'오감자',3,500,80),(148,'오예스',3,500,80),(149,'오징어땅콩',3,500,80),(150,'옥수수빵',3,1000,20),(151,'옥수수차',1,300,15),(152,'옥수수칩',3,2500,100),(153,'와인',2,15000,10),(154,'왕고래밥',3,2500,50),(155,'우유',1,800,200),(156,'웰치스청포도',1,800,50),(157,'웰치스포도',1,900,34),(158,'죠리퐁',3,900,34),(159,'초록매실',1,400,59),(160,'초코파이',3,300,15),(161,'초코하임',3,1000,110),(162,'칙촉',3,900,100),(163,'칠성사이다',1,300,80),(164,'카스',2,2500,20),(165,'카스타드',3,1300,30),(166,'칸쵸',3,500,120),(167,'칸추리콘',3,1000,50),(168,'칸타타',1,900,34),(169,'콜라',1,1800,150),(170,'쿠크다스',3,1300,150),(171,'탑씨',1,1300,30),(172,'트레비',1,500,80),(173,'펩시콜라',1,1200,90),(174,'포카칩',3,300,80),(175,'하이트',2,2500,25),(176,'홈런볼',3,800,50),(177,'환타',1,1500,10),(178,'환타오렌지',1,800,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22  9:38:11
