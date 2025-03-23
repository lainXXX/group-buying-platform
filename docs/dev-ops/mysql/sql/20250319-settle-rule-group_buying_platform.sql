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

 Date: 20/03/2025 10:00:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crowd_tags
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags`;
CREATE TABLE `crowd_tags`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群ID',
  `tag_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群名称',
  `tag_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群描述',
  `statistics` int NOT NULL COMMENT '人群标签统计量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_tag_id`(`tag_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags
-- ----------------------------
INSERT INTO `crowd_tags` VALUES (1, 'RQ_KJHKL98UU78H66554GFDV', '潜在消费用户', '潜在消费用户', 2, '2025-03-03 14:17:08', '2025-03-03 22:04:05');

-- ----------------------------
-- Table structure for crowd_tags_detail
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags_detail`;
CREATE TABLE `crowd_tags_detail`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群标签ID',
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_tag_id_user_id`(`tag_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags_detail
-- ----------------------------
INSERT INTO `crowd_tags_detail` VALUES (11, 'RQ_KJHKL98UU78H66554GFDV', 'rem', '2025-03-03 22:04:03', '2025-03-03 22:04:03');
INSERT INTO `crowd_tags_detail` VALUES (12, 'RQ_KJHKL98UU78H66554GFDV', 'lain', '2025-03-03 22:04:04', '2025-03-03 22:04:04');
INSERT INTO `crowd_tags_detail` VALUES (13, 'RQ_KJHKL98UU78H66554GFDV', 'rem01', '2025-03-12 10:47:11', '2025-03-12 10:47:11');

-- ----------------------------
-- Table structure for crowd_tags_job
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags_job`;
CREATE TABLE `crowd_tags_job`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签ID',
  `batch_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次ID',
  `tag_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '标签类型（参与量、消费金额）',
  `tag_rule` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签规则（限定类型 N次）',
  `stat_begin_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计数据，开始时间',
  `stat_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计数据，结束时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态；0初始、1计划（进入执行阶段）、2重置、3完成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_batch_id`(`batch_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags_job
-- ----------------------------
INSERT INTO `crowd_tags_job` VALUES (1, 'RQ_KJHKL98UU78H66554GFDV', '10001', 0, '100', '2024-12-28 12:55:05', '2024-12-28 12:55:05', 0, '2024-12-28 12:55:05', '2024-12-28 12:55:05');

-- ----------------------------
-- Table structure for group_buy_order_list
-- ----------------------------
DROP TABLE IF EXISTS `group_buy_order_list`;
CREATE TABLE `group_buy_order_list`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼单组队ID',
  `order_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `begin_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `original_price` decimal(8, 2) NOT NULL COMMENT '原始价格',
  `discount_price` decimal(8, 2) NOT NULL COMMENT '折扣金额',
  `pay_price` decimal(8, 2) NOT NULL COMMENT '支付金额',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态；0初始锁定、1消费完成',
  `out_trade_no` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外部交易单号-确保外部调用唯一幂等',
  `out_trade_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `biz_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务唯一ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buy_order_list
-- ----------------------------
INSERT INTO `group_buy_order_list` VALUES (5, 'lain', '99359307', '153159785916', 100123, '2024-12-07 10:19:40', '2025-05-28 10:19:40', '9890001', 's01', 'c01', 100.00, 20.00, 1, '779144288971', '2025-03-19 19:51:50', '100123_null_lain_153159785916', '2025-03-17 16:56:02', '2025-03-17 16:59:34');
INSERT INTO `group_buy_order_list` VALUES (6, 'rem', '99359307', '115357604130', 100123, '2024-12-07 10:19:40', '2025-05-28 10:19:40', '9890001', 's01', 'c01', 100.00, 20.00, 1, '075116371415', '2025-03-20 07:09:01', '100123_99359307_rem_115357604130', '2025-03-17 16:58:12', '2025-03-19 23:58:28');

-- ----------------------------
-- Table structure for group_buying_activity
-- ----------------------------
DROP TABLE IF EXISTS `group_buying_activity`;
CREATE TABLE `group_buying_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `discount_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣ID',
  `group_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '拼团方式 【0-自动成团、 1-达成目标成团】',
  `take_limit_count` int NOT NULL DEFAULT 1 COMMENT '拼团次数限制',
  `target` int NOT NULL DEFAULT 1 COMMENT '拼团目标',
  `valid_time` int NOT NULL DEFAULT 15 COMMENT '拼团时长【分钟】',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '活动状态【0-创建、 1-生效、 2-过期、 3-废弃】',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `tag_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群标签规则标识',
  `tag_scope` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群标签规则范围【多选】',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_activity
-- ----------------------------
INSERT INTO `group_buying_activity` VALUES (1, 100123, '测试活动', '9890001', '25120207', 0, 1, 2, 15, 1, '2024-12-07 10:19:40', '2025-05-28 10:19:40', 'RQ_KJHKL98UU78H66554GFDV', '', '2024-12-07 10:19:40', '2025-03-12 09:39:29');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团折扣表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_discount
-- ----------------------------
INSERT INTO `group_buying_discount` VALUES (1, 25120207, '直减优惠20元', '直减优惠20元', 0, 'ZJ', '20', NULL, '2024-12-07 10:20:15', '2025-03-03 09:47:48');
INSERT INTO `group_buying_discount` VALUES (2, 25120208, '满减优惠100-10元', '满减优惠100-10元', 0, 'MJ', '100,10', NULL, '2024-12-07 10:20:15', '2024-12-22 12:09:47');
INSERT INTO `group_buying_discount` VALUES (3, 25120209, '折扣优惠8折', '折扣优惠8折', 0, 'ZK', '0.8', NULL, '2024-12-07 10:20:15', '2024-12-22 12:11:36');
INSERT INTO `group_buying_discount` VALUES (4, 25120210, 'N元购买优惠', 'N元购买优惠', 0, 'N', '1.99', NULL, '2024-12-07 10:20:15', '2024-12-22 12:11:39');

-- ----------------------------
-- Table structure for group_buying_order
-- ----------------------------
DROP TABLE IF EXISTS `group_buying_order`;
CREATE TABLE `group_buying_order`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼单组队ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `original_price` decimal(8, 2) NOT NULL COMMENT '原始价格',
  `discount_price` decimal(8, 2) NOT NULL COMMENT '折扣价格',
  `pay_price` decimal(8, 2) NOT NULL COMMENT '支付价格',
  `target_count` int NOT NULL COMMENT '目标数量',
  `complete_count` int NOT NULL COMMENT '完成数量',
  `lock_count` int NOT NULL COMMENT '锁单数量',
  `notify_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回调url',
  `valid_begin_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '有效开始时间',
  `valid_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '有效结束时间',
  `status` tinyint(1) NOT NULL COMMENT '状态（0-拼单中、1-完成、2-失败）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_team_id`(`team_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_order
-- ----------------------------
INSERT INTO `group_buying_order` VALUES (4, '99359307', 100123, 'c01', 's01', 100.00, 20.00, 80.00, 2, 2, 2, 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', '2025-03-19 19:50:56', '2026-01-01 19:50:56', 1, '2025-03-17 16:56:02', '2025-03-19 23:58:28');

-- ----------------------------
-- Table structure for notify_task
-- ----------------------------
DROP TABLE IF EXISTS `notify_task`;
CREATE TABLE `notify_task`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼团组队ID',
  `notify_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回调接口',
  `notify_count` int NOT NULL COMMENT '回调次数',
  `notify_status` tinyint(1) NOT NULL COMMENT '回调状态【0初始、1完成、2重试、3失败】',
  `parameter_json` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数对象',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '任务回调表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notify_task
-- ----------------------------
INSERT INTO `notify_task` VALUES (10, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 6, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-18 09:52:59', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (11, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 5, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:34:12', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (12, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 4, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:48:50', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (13, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 3, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:50:07', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (14, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 2, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:56:13', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (15, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 2, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:58:18', '2025-03-19 23:58:29');

-- ----------------------------
-- Table structure for sc_sku_activity
-- ----------------------------
DROP TABLE IF EXISTS `sc_sku_activity`;
CREATE TABLE `sc_sku_activity`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_id` bigint NOT NULL,
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_source_channle_goods_id`(`source` ASC, `channel` ASC, `goods_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '渠道商品活动配置关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_sku_activity
-- ----------------------------
INSERT INTO `sc_sku_activity` VALUES (1, '9890001', 100123, 's01', 'c01', '2025-03-05 09:04:47', '2025-03-05 09:04:47');

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
