/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 06/05/2020 14:40:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `orderid` int(255) NOT NULL,
  `pid` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `cost` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` int(255) NOT NULL,
  `stock` int(255) NOT NULL,
  `parentid` int(255) NULL DEFAULT NULL,
  `childid` int(255) NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (23, '手机', '2', 2, 2, 3, 5, 'aacf07ca26ee9c2f355a10f477d7da25c60e9ff1.jpg_64x64.jpg', '');
INSERT INTO `product` VALUES (24, '电协', '电协体验营', 200, 3, 4, 15, '体验活动名单.png', '');
INSERT INTO `product` VALUES (25, '我要传乱七八糟的东西式子', '51', 23, 52, 1, 8, '280d8bd425e5739637e87de70bb6b78861078a7a.jpg', '');
INSERT INTO `product` VALUES (26, '测试样例', '广工', 22, 11, 2, 13, '4e508d1f7dc8ce4fe11c6408db9ef6854da2a2b6.jpg_64x64.jpg', '');
INSERT INTO `product` VALUES (27, '11', '2', 2, 2, 2, 14, '14910303263423.png', '');
INSERT INTO `product` VALUES (28, '6', '2', 6, 1, 1, 10, '561.jpg', '');
INSERT INTO `product` VALUES (29, '1', '1', 1, 2, 1, 8, '炉石.jpg', '');
INSERT INTO `product` VALUES (30, '312', '123', 23, 124, 1, 8, '广工军训时间安排.jpg', '');
INSERT INTO `product` VALUES (31, '45', '12', 564, 5, 1, 8, 'wallhaven-qd3yol.jpg', '');
INSERT INTO `product` VALUES (32, '54', '265', 34, 65, 1, 8, '广工军训时间安排.jpg', '');
INSERT INTO `product` VALUES (33, '43', '52', 34, 13, 1, 9, '', '');
INSERT INTO `product` VALUES (34, '43', '123', 31, 123, 1, 8, '广工正门.jpg', '');
INSERT INTO `product` VALUES (35, '1245', '5346', 534, 12, 1, 8, 'aacf07ca26ee9c2f355a10f477d7da25c60e9ff1.jpg_64x64.jpg', '');

-- ----------------------------
-- Table structure for producttype
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parentid` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES (1, '生活用品', 0);
INSERT INTO `producttype` VALUES (2, '学习用品', 0);
INSERT INTO `producttype` VALUES (3, '电子产品', 0);
INSERT INTO `producttype` VALUES (4, '其他类型', 0);
INSERT INTO `producttype` VALUES (5, '手机', 3);
INSERT INTO `producttype` VALUES (6, '电脑', 3);
INSERT INTO `producttype` VALUES (7, '相机', 3);
INSERT INTO `producttype` VALUES (8, '自行车', 1);
INSERT INTO `producttype` VALUES (9, '沐浴露', 1);
INSERT INTO `producttype` VALUES (10, '洗发水', 1);
INSERT INTO `producttype` VALUES (11, '衣服', 1);
INSERT INTO `producttype` VALUES (12, '书', 2);
INSERT INTO `producttype` VALUES (13, '学习资料', 2);
INSERT INTO `producttype` VALUES (14, '试卷', 2);
INSERT INTO `producttype` VALUES (15, '其他', 4);

-- ----------------------------
-- Table structure for shopcar
-- ----------------------------
DROP TABLE IF EXISTS `shopcar`;
CREATE TABLE `shopcar`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` int(255) NULL DEFAULT NULL,
  `quantity` int(255) NULL DEFAULT NULL,
  `stock` int(255) NULL DEFAULT NULL,
  `pid` int(255) NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `valid` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopcar
-- ----------------------------
INSERT INTO `shopcar` VALUES (2, 'aacf07ca26ee9c2f355a10f477d7da25c60e9ff1.jpg_64x64.jpg', '手机', 2, 1, 2, 23, 'admin', 1);
INSERT INTO `shopcar` VALUES (3, 'aacf07ca26ee9c2f355a10f477d7da25c60e9ff1.jpg_64x64.jpg', '手机', 2, 2, 2, 23, 'admin', 1);

-- ----------------------------
-- Table structure for shoporder
-- ----------------------------
DROP TABLE IF EXISTS `shoporder`;
CREATE TABLE `shoporder`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `useraddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createtime` datetime(0) NOT NULL,
  `cost` int(255) NOT NULL,
  `status` int(255) NOT NULL,
  `type` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uaddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` int(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('888', '测试', 'T', '777', '888', '777', '777', '背景', '呼呼呼', 1, '888');
INSERT INTO `user` VALUES ('admin', '博客', 'T', '2001-02-28', '4400', '4400', '4400', '博客', '博客', 1, '233');

SET FOREIGN_KEY_CHECKS = 1;
