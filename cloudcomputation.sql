/*
 Navicat Premium Data Transfer

 Source Server         : 测试用连接
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : cloudcomputation

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/05/2024 10:30:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `userId` int(11) NOT NULL,
  `opType` int(11) NULL DEFAULT NULL,
  `vmid` int(11) NOT NULL,
  `createTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 1, 0, '2024-05-27 10:18:11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userPrimary` int(11) NULL DEFAULT NULL,
  `userPassword` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userScore` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `userName`(`userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 4, 'lucifer', 'lucifer', NULL);
INSERT INTO `user` VALUES (3, 1, 'lxf', 'lxf', NULL);
INSERT INTO `user` VALUES (4, 1, '123', '123', -1426);

-- ----------------------------
-- Table structure for vm
-- ----------------------------
DROP TABLE IF EXISTS `vm`;
CREATE TABLE `vm`  (
  `vmid` int(11) NOT NULL AUTO_INCREMENT,
  `mips` int(11) NULL DEFAULT NULL,
  `size` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `os` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pesNumber` int(11) NULL DEFAULT NULL,
  `vmm` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `isAutoScaling` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`vmid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vm
-- ----------------------------
INSERT INTO `vm` VALUES (2, 1000, 10000, 512, 'Linux', 1, 'Xen', 1, 1);
INSERT INTO `vm` VALUES (3, 1, 1000, 1000, 'Linux', 100, NULL, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
