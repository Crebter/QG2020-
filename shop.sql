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

 Date: 09/05/2020 14:17:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for complain
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contenttime` datetime(0) NOT NULL,
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `replytime` datetime(0) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES (8, '233', '2020-05-09 11:38:25', '好的', '2020-05-09 11:38:38', '233');
INSERT INTO `complain` VALUES (10, '哈哈哈哈哈哈哈哈哈', '2020-05-09 13:35:02', NULL, NULL, 'emmm');

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
  `valid` int(255) NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (23, '笑脸', '2', 2, 2, 1, 8, '。.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (24, '电协', '电协体验营', 200, 10, 4, 15, '体验活动名单.png', 'admin', 1, NULL);
INSERT INTO `product` VALUES (25, '您就是列文虎克？', '51', 23, 52, 2, 14, '广工行政楼.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (26, '测试样例', '广工', 22, 7, 2, 13, '4e508d1f7dc8ce4fe11c6408db9ef6854da2a2b6.jpg_64x64.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (27, '11', '2', 2, 2, 2, 14, '14910303263423.png', 'admin', 1, NULL);
INSERT INTO `product` VALUES (29, '1', '1', 1, 2, 1, 8, '炉石.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (31, '45', '12', 564, 5, 1, 8, 'wallhaven-qd3yol.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (32, '54', '265', 34, 65, 1, 8, '广工军训时间安排.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (34, '43', '123', 31, 123, 1, 8, '广工正门.jpg', 'admin', 2, '虚假');
INSERT INTO `product` VALUES (36, '23', '25', 24, 65, 1, 8, 'wallhaven-x11xmz.jpg', 'admin', 1, NULL);
INSERT INTO `product` VALUES (37, '大换血成功', '2315', 232, 63, 1, 8, 'wallhaven-96e3jx.png', 'admin', 2, '最后一个');

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
  `seller` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopcar
-- ----------------------------
INSERT INTO `shopcar` VALUES (15, 'wallhaven-qd3yol.jpg', '45', 564, 1, 5, 31, '888', 1, 'admin');
INSERT INTO `shopcar` VALUES (16, '广工行政楼.jpg', '您就是列文虎克？', 23, 1, 52, 25, '888', 1, 'admin');
INSERT INTO `shopcar` VALUES (24, '4e508d1f7dc8ce4fe11c6408db9ef6854da2a2b6.jpg_64x64.jpg', '测试样例', 22, 3, 10, 26, 'admin', 1, 'admin');

-- ----------------------------
-- Table structure for shoporder
-- ----------------------------
DROP TABLE IF EXISTS `shoporder`;
CREATE TABLE `shoporder`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `uaddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createtime` datetime(0) NOT NULL,
  `cost` int(255) NOT NULL,
  `status` int(255) NOT NULL,
  `type` int(255) NOT NULL,
  `seller` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` int(255) NOT NULL,
  `pid` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoporder
-- ----------------------------
INSERT INTO `shoporder` VALUES (1, 'admin', '我怎么知道', '2020-05-07 16:39:06', 22, 1, 1, 'admin', 1, 26);
INSERT INTO `shoporder` VALUES (2, 'admin', '博客', '2020-05-07 16:39:06', 200, 2, 2, 'admin', 1, 24);
INSERT INTO `shoporder` VALUES (3, 'admin', '博客', '2020-05-07 17:11:17', 22, 1, 1, 'admin', 1, 26);
INSERT INTO `shoporder` VALUES (8, 'admin', '博客', '2020-05-07 23:00:47', 1000, 1, 1, 'admin', 5, 24);
INSERT INTO `shoporder` VALUES (9, 'admin', '博客', '2020-05-08 10:11:58', 102, 2, 3, 'admin', 3, 32);
INSERT INTO `shoporder` VALUES (11, 'admin', '博客', '2020-05-08 14:22:20', 66, 1, 3, 'admin', 3, 26);
INSERT INTO `shoporder` VALUES (12, 'admin', '博客', '2020-05-08 14:46:14', 66, 3, 3, 'admin', 3, 26);

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
INSERT INTO `user` VALUES ('233', '吴东龙', 'T', '2001-02-22', '233', '233', '233', '233', '233', 1, 'E165421110BA03099A1C0393373C5B43');
INSERT INTO `user` VALUES ('admin', '博客', 'T', '2001-02-28', '4400', '4400', '4400', '博客', '博客', 2, '21232F297A57A5A743894A0E4A801FC3');

SET FOREIGN_KEY_CHECKS = 1;
