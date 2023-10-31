/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : takeout

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-31 14:32:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cart_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `food_id` int NOT NULL,
  `specification` varchar(200) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('0000000006', 'Chalotte', '87', '中份', '2');
INSERT INTO `cart` VALUES ('0000000007', 'Chalotte', '85', '不辣', '1');
INSERT INTO `cart` VALUES ('0000000008', 'Chalotte', '95', '加汤', '2');

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `food_id` int NOT NULL,
  `food_name` varchar(20) NOT NULL,
  `uname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('0000000019', '88', '锅包肉', 'asd');
INSERT INTO `collection` VALUES ('0000000045', '178', 'test煎饺', 'Chalotte');
INSERT INTO `collection` VALUES ('0000000047', '85', '鱼香肉丝', 'Chalotte');

-- ----------------------------
-- Table structure for `food`
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `food_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `image` varchar(100) DEFAULT NULL,
  `food_name` varchar(20) NOT NULL,
  `ingredients` varchar(100) DEFAULT NULL,
  `specification` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `classification` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `price` double(10,2) NOT NULL,
  `sales_volume` int(10) unsigned zerofill DEFAULT NULL,
  `status` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  FULLTEXT KEY `findex` (`food_name`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('0000000085', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/99a988c1901c4c8783c2a8fdb919081f.jpg', '鱼香肉丝', '猪肉', '不辣/微辣', '川菜', '12.00', '0000000019', '0000000000');
INSERT INTO `food` VALUES ('0000000086', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/fb6069deab9140f8bb16c720b77a3bfa.jpg', '大盘鸡', '鸡肉、土豆、红枣', '不辣/微辣/重辣', '川菜', '30.00', '0000000004', '0000000000');
INSERT INTO `food` VALUES ('0000000087', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/38ca24df0af64ab89f5884ca910686ab.jpg', '葱油饼', '面粉、葱花', '小份/中份/大份', '北方小吃', '5.50', '0000000009', '0000000000');
INSERT INTO `food` VALUES ('0000000088', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/3d60610b7165463aae247e41a549d44a.jpg', '锅包肉', '猪肉', '不辣/微辣/麻辣', '东北菜', '18.00', '0000000000', '0000000000');
INSERT INTO `food` VALUES ('0000000089', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/7ae4a64f0f014534a3210998f1b0f634.jpg', '牛肉面', '手擀面、牛肉、大骨汤、小葱、豆芽', '普通/加辣/特辣', '快餐', '12.50', '0000000001', '0000000000');
INSERT INTO `food` VALUES ('0000000090', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/7526d7a23cc74117859e9d695dc39cf7.jpg', '烤肉饼', '猪瘦肉、面饼、青菜、葱、酱料', '单份/双份', '中式快餐', '8.50', '0000000002', '0000000000');
INSERT INTO `food` VALUES ('0000000091', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/f277e8d8aa8544099bb6110d74e84e3b.jpg', '意大利面套餐', '意大利面、西红柿肉酱、意大利香肠、意式蔬菜沙拉', '小份/大份/加生菜', '西餐', '32.00', '0000000003', '0000000000');
INSERT INTO `food` VALUES ('0000000092', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/d8f45c7377764e6c8920433edc800d14.jpg', '锅包肉', '猪里脊肉、青椒、豌豆、姜蒜、玉米淀粉', '小份/中份/大份', '中式快餐', '28.00', '0000000000', '0000000000');
INSERT INTO `food` VALUES ('0000000093', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000094', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000095', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000096', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000001', '0000000001');
INSERT INTO `food` VALUES ('0000000097', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000098', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000099', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000100', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000101', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000102', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000103', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000104', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000105', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000106', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000107', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000108', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000109', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000110', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000111', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000112', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000113', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000114', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000115', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000116', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000117', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000118', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000119', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000120', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000121', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000122', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000123', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000124', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000125', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000126', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000127', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000128', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000129', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000130', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000131', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000132', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000133', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000134', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000135', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000136', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000137', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000138', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000139', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000140', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000141', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000142', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000143', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000144', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000145', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000146', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000147', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000148', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000149', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000150', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000151', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000152', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000153', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000154', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000155', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000156', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/9fe4310aa62248b48225b7001b2eb47e.jpg', '煎饺', '猪肉、大葱', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000000');
INSERT INTO `food` VALUES ('0000000157', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000158', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000159', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000160', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000161', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000162', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000163', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000164', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000165', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000166', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000167', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000168', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000169', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000170', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000171', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000172', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000173', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000174', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000175', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000176', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000177', 'img/testImg.jpg', '煎饺', '猪肉、大葱、皮薄易爆', '普通/加汤', '中式快餐', '10.00', '0000000000', '0000000001');
INSERT INTO `food` VALUES ('0000000178', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/62f69dd488494baf94d572cc2e179f8e.jpg', '煎饺', '猪肉、大葱', '普通/加汤', '中式快餐', '5.50', '0000000000', '0000000000');

-- ----------------------------
-- Table structure for `ordertab`
-- ----------------------------
DROP TABLE IF EXISTS `ordertab`;
CREATE TABLE `ordertab` (
  `order_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `food_id` int DEFAULT NULL,
  `specification` varchar(200) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `total_price` double(10,2) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `orderStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of ordertab
-- ----------------------------
INSERT INTO `ordertab` VALUES ('0000000007', 'Chalotte', '90', '单份', '1', '8.50', 'address', '18776554321', '2023-06-14 17:44:31', '待接单');
INSERT INTO `ordertab` VALUES ('0000000008', 'Chalotte', '87', '中份', '2', '6.00', 'address', '18776554321', '2023-06-14 21:35:24', '已接单');
INSERT INTO `ordertab` VALUES ('0000000009', 'Chalotte', '91', '小份', '2', '64.00', 'address', '18776554321', '2023-06-16 10:05:43', '已接单');
INSERT INTO `ordertab` VALUES ('0000000010', 'Chalotte', '86', '微辣', '1', '30.00', 'address', '18776554321', '2023-06-16 10:06:29', '待接单');
INSERT INTO `ordertab` VALUES ('0000000011', 'Chalotte', '87', '小份', '1', '3.00', 'address', '18159523456', '2023-06-16 10:40:08', '待接单');
INSERT INTO `ordertab` VALUES ('0000000012', 'Chalotte', '86', '不辣', '1', '30.00', 'address', '18776554321', '2023-06-16 11:26:47', '待接单');
INSERT INTO `ordertab` VALUES ('0000000015', 'Chalotte', '85', '微辣', '2', '24.00', 'address', '18159523456', '2023-06-16 17:59:00', '待接单');
INSERT INTO `ordertab` VALUES ('0000000017', 'Chalotte', '89', '普通', '1', '12.50', 'address', '18776554321', '2023-06-16 18:00:44', '待接单');
INSERT INTO `ordertab` VALUES ('0000000018', 'Chalotte', '85', '微辣', '2', '24.00', 'address', null, '2023-06-19 23:47:10', '待接单');
INSERT INTO `ordertab` VALUES ('0000000024', 'Kate', '87', '小份', '4', '22.00', 'address', null, '2023-10-08 12:02:41', '待接单');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uname` varchar(20) NOT NULL,
  `user_image` varchar(100) DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_type` int(1) unsigned zerofill NOT NULL,
  `create_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('asd', null, '4QrcOUm6Wau+VuBX8g+IPg==', '123@qq.com', '0', '1', null);
INSERT INTO `user` VALUES ('Chalotte', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/c8b7d336d5564942b913d1b38a816c3d.jpg', 'kz74n0I1/cy90hDp6lUC3Q==', 'cyk92633@qq.com', '18159554321', '0', '2023-06-11 15:48:45');
INSERT INTO `user` VALUES ('Kate', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/1f1a9b825973437ba53ddf4cad15ccff.jpg', 'Qpf0SxOVUjUkWySXOZ16kw==', '1450596137@qq.com', '18776554310', '0', '2023-04-24 22:01:18');
INSERT INTO `user` VALUES ('user01', 'https://buckchtest.oss-cn-hangzhou.aliyuncs.com/fdedcbcd99e04c3ca6612980ef71b8a0.jpg', '4QrcOUm6Wau+VuBX8g+IPg==', 'cyk9263@qq.com', '1811234567', '0', '2023-06-20 01:01:36');
