/*
 Navicat Premium Data Transfer

 Source Server         : 华为云
 Source Server Type    : MySQL
 Source Server Version : 90100
 Source Host           : 110.41.180.185:3306
 Source Schema         : group_buying_platform

 Target Server Type    : MySQL
 Target Server Version : 90100
 File Encoding         : 65001

 Date: 02/03/2025 09:37:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group_buying_activity
-- ----------------------------
DROP TABLE IF EXISTS `group_buying_activity`;
CREATE TABLE `group_buying_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `goods_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `discount_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣ID',
  `group_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '拼团方式 【0-自动成团、 1-达成目标成团】',
  `take_limit_count` int NOT NULL DEFAULT 1 COMMENT '拼团次数限制',
  `target` int NOT NULL DEFAULT 1 COMMENT '拼团目标',
  `valid_time` int NOT NULL DEFAULT 15 COMMENT '拼团时长【分钟】',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '活动状态【0-创建、 1-生效、 2-过期、 3-废弃】',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `tag_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群标签规则标识',
  `tag_scope` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人群标签规则范围【多选】',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_activity
-- ----------------------------
INSERT INTO `group_buying_activity` VALUES (1, 100123, '测试活动', 's01', 'c01', '9890001', '25120207', 0, 1, 1, 15, 0, '2024-12-07 10:19:40', '2024-12-07 10:19:40', '1', '1', '2024-12-07 10:19:40', '2025-02-27 20:27:21');

-- ----------------------------
-- Table structure for group_buying_discount
-- ----------------------------
DROP TABLE IF EXISTS `group_buying_discount`;
CREATE TABLE `group_buying_discount`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `discount_id` int NOT NULL COMMENT '折扣ID',
  `discount_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣标题',
  `discount_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣描述',
  `discount_type` tinyint(1) NOT NULL COMMENT '折扣类型【0-base、 1-tag】',
  `marketing_plan` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营销优惠计划【ZJ-直减、MJ-满减、N元购】',
  `marketing_expr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营销优惠表达式',
  `tag_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人群标签，特定优惠限定',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_discount_id`(`discount_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团折扣表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_discount
-- ----------------------------
INSERT INTO `group_buying_discount` VALUES (1, 25120207, '测试优惠', '测试优惠', 0, 'ZJ', '20', NULL, '2024-12-07 10:20:15', '2025-03-02 09:35:29');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `original_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_goods_id`(`goods_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 's01', 'c01', '9890001', '《手写MyBatis：渐进式源码实践》', 100.00, '2024-12-21 11:10:06', '2024-12-21 11:10:06');

SET FOREIGN_KEY_CHECKS = 1;
