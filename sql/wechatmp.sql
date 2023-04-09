/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : wechatmp

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 09/04/2023 10:29:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `className` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名',
  `courseName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `courseWeekly` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '周次',
  `courseWeek` int(0) NOT NULL COMMENT '上课的时间（周几）',
  `courseSection` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '第几节课（1-2）',
  `weekType` tinyint(0) NOT NULL COMMENT '单双周（0-单双周,1-单周）',
  `courseAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上课地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '20计算机科学与技术1班', '网络安全', '3-6,8-19', 2, '1-2', 0, '快乐楼_502_计算机网路综合实验室（3）');
INSERT INTO `course` VALUES (2, '20计算机科学与技术1班', '网络安全', '3-6,8-19', 2, '3-4', 1, '快乐楼_502_计算机网路综合实验室（3）');
INSERT INTO `course` VALUES (3, '20计算机科学与技术1班', '就业指导', '3-6', 4, '1-2', 0, '快乐楼_408_1类多媒体');
INSERT INTO `course` VALUES (4, '20计算机科学与技术1班', '网络工程与系统集成', '3-16,18-19', 5, '5-6', 0, '快乐楼_502_计算机网路综合实验室（3）');
INSERT INTO `course` VALUES (5, '20计算机科学与技术1班', '网络工程与系统集成', '3-16,18-19', 5, '7-8', 0, '快乐楼_502_计算机网路综合实验室（3）');
INSERT INTO `course` VALUES (6, '20计算机科学与技术1班', '概率论与数理统计', '17', 4, '11-12', 0, '快乐楼_409_1类多媒体');
INSERT INTO `course` VALUES (7, '20计算机科学与技术1班', '概率论与数理统计', '3-5,7-16,19-20', 4, '11-12', 0, '快乐楼_409_1类多媒体');
INSERT INTO `course` VALUES (8, '20计算机科学与技术1班', '形势与政策6', '10-12', 3, '5-6', 0, '快乐楼_206_2类多媒体');
INSERT INTO `course` VALUES (9, '20计算机科学与技术1班', '移动开发编程', '3-6,8-19', 3, '1-2', 0, '快乐楼_303_计算机图形1');
INSERT INTO `course` VALUES (10, '20计算机科学与技术1班', '移动开发编程', '3-6,8-19', 3, '3-4', 0, '快乐楼_303_计算机图形1');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `className` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名',
  `courseName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `beginTime` datetime(0) NOT NULL COMMENT '开始时间',
  `endTime` datetime(0) NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (1, '20计算机科学与技术1班', '测试1', '2023-04-07 10:03:20', '2023-04-12 10:03:31');

SET FOREIGN_KEY_CHECKS = 1;
