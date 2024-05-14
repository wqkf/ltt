/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : springboots8124

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 14/05/2024 17:54:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for approve
-- ----------------------------
DROP TABLE IF EXISTS `approve`;
CREATE TABLE `approve`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `role` tinyint(255) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `car_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approve
-- ----------------------------
INSERT INTO `approve` VALUES ('dfe', 'admin', 1, NULL, 45, '132653199845461146', '面包车', 'admin', '待审批', 2);

-- ----------------------------
-- Table structure for risk
-- ----------------------------
DROP TABLE IF EXISTS `risk`;
CREATE TABLE `risk`  (
  `id` int NOT NULL,
  `num` int NULL DEFAULT NULL,
  `stock_time` int NULL DEFAULT NULL,
  `heat` int NULL DEFAULT NULL,
  `humidity` int NULL DEFAULT NULL,
  `monitor_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `road` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of risk
-- ----------------------------
INSERT INTO `risk` VALUES (2, 34, 14, 32, 12, '2024-05-10', '双行道');
INSERT INTO `risk` VALUES (3, 12, 20, 10, 23, '2024-05-09', '单行道');
INSERT INTO `risk` VALUES (4, 52, 45, 85, 14, '2024-05-14', '无路');

-- ----------------------------
-- Table structure for risk_road
-- ----------------------------
DROP TABLE IF EXISTS `risk_road`;
CREATE TABLE `risk_road`  (
  `id` int NOT NULL,
  `road` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `heat` int NULL DEFAULT NULL,
  `humidity` int NULL DEFAULT NULL,
  `monitor_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of risk_road
-- ----------------------------
INSERT INTO `risk_road` VALUES (1, '2024-03-13', 53, 23, '2024-05-11');
INSERT INTO `risk_road` VALUES (2, '2024-04-14', 32, 12, '2024-05-10');
INSERT INTO `risk_road` VALUES (3, '2024-05-09', 10, 23, '2024-05-09');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `stock_time` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (2, '啊覅恶狗怕大姐夫', '小麦', 57, '2024-05-13');
INSERT INTO `stock` VALUES (3, '萨达姆擦圣诞快乐出', '玉米', 456, '2024-05-14');

-- ----------------------------
-- Table structure for threshold
-- ----------------------------
DROP TABLE IF EXISTS `threshold`;
CREATE TABLE `threshold`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `heat` int NULL DEFAULT NULL,
  `humidity` int NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `stock_time` int NULL DEFAULT NULL,
  `road` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of threshold
-- ----------------------------
INSERT INTO `threshold` VALUES (1, 30, 10, 12, 20, '双行道');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `role` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `car_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', 1, 4, 45, '145511223215', 'waef', '1351548998');
INSERT INTO `user` VALUES ('dfe', 'admin', 2, 3, 45, '132653199845461146', '都是', 'admin');
INSERT INTO `user` VALUES ('stock', 'admin', 3, 1, 33, '332134543', 'werfew', 'sdfafewf');
INSERT INTO `user` VALUES ('trans', 'admin', 4, 2, 12, '344442', '大法师', 'dafd');

SET FOREIGN_KEY_CHECKS = 1;
