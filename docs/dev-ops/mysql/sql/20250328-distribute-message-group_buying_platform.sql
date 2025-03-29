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

 Date: 29/03/2025 22:16:26
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
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_tag_id_user_id`(`tag_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags_detail
-- ----------------------------
INSERT INTO `crowd_tags_detail` VALUES (11, 'RQ_KJHKL98UU78H66554GFDV', 'rem', '2025-03-03 22:04:03', '2025-03-03 22:04:03');
INSERT INTO `crowd_tags_detail` VALUES (12, 'RQ_KJHKL98UU78H66554GFDV', 'lain', '2025-03-03 22:04:04', '2025-03-03 22:04:04');
INSERT INTO `crowd_tags_detail` VALUES (13, 'RQ_KJHKL98UU78H66554GFDV', 'rem01', '2025-03-12 10:47:11', '2025-03-12 10:47:11');
INSERT INTO `crowd_tags_detail` VALUES (14, 'RQ_KJHKL98UU78H66554GFDV', 'lain01', '2025-03-24 14:24:08', '2025-03-24 14:24:08');
INSERT INTO `crowd_tags_detail` VALUES (15, 'RQ_KJHKL98UU78H66554GFDV', '123', '2025-03-25 17:15:12', '2025-03-25 17:15:12');
INSERT INTO `crowd_tags_detail` VALUES (16, 'RQ_KJHKL98UU78H66554GFDV', 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '2025-03-25 21:40:15', '2025-03-25 21:40:15');

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
  `out_trade_no` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外部交易单号-确保外部调用唯一幂等',
  `out_trade_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `biz_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务唯一ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buy_order_list
-- ----------------------------
INSERT INTO `group_buy_order_list` VALUES (57, 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '50420559', '798483345821', 100126, '2025-03-26 10:39:04', '2028-01-26 10:39:07', '100010', 's01', 'c01', 299.00, 297.01, 1.99, 1, '592975979754', '2025-03-29 20:48:58', '100126_null_oElx26a5vWVLG_-AZ_e4ilgw8yK0_798483345821', '2025-03-29 20:47:50', '2025-03-29 20:52:07');
INSERT INTO `group_buy_order_list` VALUES (58, 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '25607736', '021854452793', 100123, '2024-12-07 10:19:40', '2025-05-28 10:19:40', '100007', 's01', 'c01', 299.00, 20.00, 279.00, 1, '903427327726', '2025-03-29 20:55:56', '100123_null_oElx26a5vWVLG_-AZ_e4ilgw8yK0_021854452793', '2025-03-29 20:55:12', '2025-03-29 20:56:21');
INSERT INTO `group_buy_order_list` VALUES (59, 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '18376703', '271827629434', 100124, '2025-03-20 10:20:56', '2026-03-26 10:21:03', '100003', 's01', 'c01', 299.00, 10.00, 289.00, 1, '239319676592', '2025-03-29 21:06:32', '100124_null_oElx26a5vWVLG_-AZ_e4ilgw8yK0_271827629434', '2025-03-29 21:03:29', '2025-03-29 21:06:37');
INSERT INTO `group_buy_order_list` VALUES (60, 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '61797405', '773066658728', 100124, '2025-03-20 10:20:56', '2026-03-26 10:21:03', '100003', 's01', 'c01', 299.00, 10.00, 289.00, 1, '407927800475', '2025-03-29 21:25:38', '100124_null_oElx26a5vWVLG_-AZ_e4ilgw8yK0_773066658728', '2025-03-29 21:25:21', '2025-03-29 21:25:39');
INSERT INTO `group_buy_order_list` VALUES (61, 'oElx26a5vWVLG_-AZ_e4ilgw8yK0', '26103945', '632134955838', 100126, '2025-03-26 10:39:04', '2028-01-26 10:39:07', '100010', 's01', 'c01', 299.00, 297.01, 1.99, 1, '050309509889', '2025-03-29 21:43:27', '100126_null_oElx26a5vWVLG_-AZ_e4ilgw8yK0_632134955838', '2025-03-29 21:43:09', '2025-03-29 21:43:27');

-- ----------------------------
-- Table structure for group_buying_activity
-- ----------------------------
DROP TABLE IF EXISTS `group_buying_activity`;
CREATE TABLE `group_buying_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_activity
-- ----------------------------
INSERT INTO `group_buying_activity` VALUES (1, 100123, '测试活动', '25120207', 0, 12, 1, 2400, 1, '2024-12-07 10:19:40', '2025-05-28 10:19:40', 'RQ_KJHKL98UU78H66554GFDV', '', '2024-12-07 10:19:40', '2025-03-26 19:49:13');
INSERT INTO `group_buying_activity` VALUES (2, 100124, '测试活动2', '25120208', 0, 23, 1, 2400, 1, '2025-03-20 10:20:56', '2026-03-26 10:21:03', 'RQ_KJHKL98UU78H66554GFDV', 'null', '2025-03-26 10:21:15', '2025-03-26 19:49:15');
INSERT INTO `group_buying_activity` VALUES (4, 100125, '测试活动3', '25120209', 0, 34, 1, 2400, 1, '2025-03-26 10:38:24', '2027-01-01 10:38:27', 'RQ_KJHKL98UU78H66554GFDV', '1', '2025-03-26 10:38:38', '2025-03-26 19:49:16');
INSERT INTO `group_buying_activity` VALUES (5, 100126, '测试活动4', '25120210', 0, 11, 1, 2400, 1, '2025-03-26 10:39:04', '2028-01-26 10:39:07', 'RQ_KJHKL98UU78H66554GFDV', '1', '2025-03-26 10:39:09', '2025-03-26 19:49:18');

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
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buying_order
-- ----------------------------
INSERT INTO `group_buying_order` VALUES (51, '50420559', 100126, 'c01', 's01', 299.00, 297.01, 1.99, 1, 1, 1, 'http://127.0.0.1:8080/pay/group_buying_notify', '2025-03-26 10:39:04', '2028-01-26 10:39:07', 1, '2025-03-29 20:47:49', '2025-03-29 20:52:13');
INSERT INTO `group_buying_order` VALUES (52, '25607736', 100123, 'c01', 's01', 299.00, 20.00, 279.00, 1, 1, 1, 'http://127.0.0.1:8080/pay/group_buying_notify', '2024-12-07 10:19:40', '2025-05-28 10:19:40', 1, '2025-03-29 20:55:12', '2025-03-29 20:56:21');
INSERT INTO `group_buying_order` VALUES (53, '18376703', 100124, 'c01', 's01', 299.00, 10.00, 289.00, 1, 1, 1, 'http://127.0.0.1:8080/pay/group_buying_notify', '2025-03-20 10:20:56', '2026-03-26 10:21:03', 1, '2025-03-29 21:03:29', '2025-03-29 21:06:37');
INSERT INTO `group_buying_order` VALUES (54, '61797405', 100124, 'c01', 's01', 299.00, 10.00, 289.00, 1, 1, 1, 'http://127.0.0.1:8080/pay/group_buying_notify', '2025-03-20 10:20:56', '2026-03-26 10:21:03', 1, '2025-03-29 21:25:21', '2025-03-29 21:25:39');
INSERT INTO `group_buying_order` VALUES (55, '26103945', 100126, 'c01', 's01', 299.00, 297.01, 1.99, 1, 1, 1, 'http://127.0.0.1:8080/pay/group_buying_notify', '2025-03-26 10:39:04', '2028-01-26 10:39:07', 1, '2025-03-29 21:43:09', '2025-03-29 21:43:27');

-- ----------------------------
-- Table structure for message_task
-- ----------------------------
DROP TABLE IF EXISTS `message_task`;
CREATE TABLE `message_task`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `msg_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息ID',
  `msg_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息类型',
  `delivery_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发送方式【MQ、HTTP、RPC】',
  `delivery_count` int NOT NULL COMMENT '发送次数',
  `retry_count` int NOT NULL DEFAULT 5 COMMENT '可重试次数',
  `retry_interval` int NOT NULL COMMENT '重试时间间隔',
  `msg_json` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'json格式内容',
  `notify_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http回调url',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态【0-创建 1-成功 2-重试 3-失败】',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_msg_id`(`msg_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_task
-- ----------------------------
INSERT INTO `message_task` VALUES (5, '71097985053', 'notify_settle_order', 'MQ', 1, 5, 3, '{\"teamId\":\"18376703\",\"outTradeNoList\":[\"239319676592\"]}', NULL, 1, '2025-03-29 21:06:58', '2025-03-29 21:20:13');
INSERT INTO `message_task` VALUES (6, '15914779643', 'notify_settle_order', 'MQ', 1, 5, 3, '{\"teamId\":\"61797405\",\"outTradeNoList\":[\"407927800475\"]}', NULL, 1, '2025-03-29 21:25:39', '2025-03-29 21:25:39');
INSERT INTO `message_task` VALUES (7, '71537994932', 'notify_settle_order', 'MQ', 1, 5, 3, '{\"teamId\":\"26103945\",\"outTradeNoList\":[\"050309509889\"]}', NULL, 1, '2025-03-29 21:43:27', '2025-03-29 21:43:27');

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '任务回调表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notify_task
-- ----------------------------
INSERT INTO `notify_task` VALUES (10, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 6, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-18 09:52:59', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (11, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 5, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:34:12', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (12, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 4, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:48:50', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (13, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 3, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:50:07', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (14, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 2, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:56:13', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (15, 100123, '99359307', 'http://127.0.0.1:8090/api/v1/test/group_buy_notify', 2, 1, '{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}', '2025-03-19 23:58:18', '2025-03-19 23:58:29');
INSERT INTO `notify_task` VALUES (16, 100123, '44503075', 'http://127.0.0.1:8080/pay/group_buying_notify', 2, 1, '{\"teamId\":\"44503075\",\"outTradeNoList\":[\"707142066493\"]}', '2025-03-25 10:24:07', '2025-03-25 11:19:07');
INSERT INTO `notify_task` VALUES (17, 100123, '24452624', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"24452624\",\"outTradeNoList\":[\"033336147093\"]}', '2025-03-26 10:03:03', '2025-03-26 10:03:11');
INSERT INTO `notify_task` VALUES (18, 100123, '93406179', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"93406179\",\"outTradeNoList\":[\"702567899387\"]}', '2025-03-26 10:14:48', '2025-03-26 10:26:02');
INSERT INTO `notify_task` VALUES (19, 100126, '30067357', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"30067357\",\"outTradeNoList\":[\"854014092292\"]}', '2025-03-26 17:27:08', '2025-03-26 17:27:17');
INSERT INTO `notify_task` VALUES (20, 100124, '26358906', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"26358906\",\"outTradeNoList\":[\"082768682847\"]}', '2025-03-26 19:43:28', '2025-03-26 19:43:37');
INSERT INTO `notify_task` VALUES (21, 100126, '67615580', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"67615580\",\"outTradeNoList\":[\"227983256076\"]}', '2025-03-26 19:50:23', '2025-03-26 19:50:32');
INSERT INTO `notify_task` VALUES (22, 100126, '28075663', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"28075663\",\"outTradeNoList\":[\"212005184973\"]}', '2025-03-26 20:02:43', '2025-03-26 20:02:52');
INSERT INTO `notify_task` VALUES (23, 100123, '60323292', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"60323292\",\"outTradeNoList\":[\"201296273023\"]}', '2025-03-26 22:17:34', '2025-03-26 22:17:42');
INSERT INTO `notify_task` VALUES (24, 100123, '44175111', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"44175111\",\"outTradeNoList\":[\"906756652343\"]}', '2025-03-26 22:36:51', '2025-03-26 22:37:00');
INSERT INTO `notify_task` VALUES (25, 100123, '51971326', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"51971326\",\"outTradeNoList\":[\"356234748506\"]}', '2025-03-26 22:40:23', '2025-03-26 22:40:32');
INSERT INTO `notify_task` VALUES (26, 100123, '37185443', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"37185443\",\"outTradeNoList\":[\"249618923552\"]}', '2025-03-26 22:43:28', '2025-03-26 22:43:37');
INSERT INTO `notify_task` VALUES (27, 100123, '09550964', 'http://127.0.0.1:8080/pay/group_buying_notify', 1, 1, '{\"teamId\":\"09550964\",\"outTradeNoList\":[\"219592349311\"]}', '2025-03-26 22:54:20', '2025-03-26 22:54:29');

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '渠道商品活动配置关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_sku_activity
-- ----------------------------
INSERT INTO `sc_sku_activity` VALUES (1, '9890001', 100123, 's01', 'c01', '2025-03-05 09:04:47', '2025-03-05 09:04:47');
INSERT INTO `sc_sku_activity` VALUES (2, '100002', 100123, 's01', 'c01', '2025-03-24 11:17:26', '2025-03-24 11:17:26');
INSERT INTO `sc_sku_activity` VALUES (3, '100003', 100124, 's01', 'c01', '2025-03-26 10:44:48', '2025-03-26 10:44:48');
INSERT INTO `sc_sku_activity` VALUES (4, '100004', 100124, 's01', 'c01', '2025-03-26 10:45:00', '2025-03-26 10:45:00');
INSERT INTO `sc_sku_activity` VALUES (5, '100006', 100125, 's01', 'c01', '2025-03-26 10:45:13', '2025-03-26 10:45:13');
INSERT INTO `sc_sku_activity` VALUES (6, '100007', 100123, 's01', 'c01', '2025-03-26 10:45:25', '2025-03-26 10:45:25');
INSERT INTO `sc_sku_activity` VALUES (7, '100010', 100126, 's01', 'c01', '2025-03-26 10:45:38', '2025-03-26 10:45:38');
INSERT INTO `sc_sku_activity` VALUES (8, '100011', 100126, 's01', 'c01', '2025-03-26 10:45:55', '2025-03-26 10:45:55');

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 's01', 'c01', '9890001', '《手写MyBatis：渐进式源码实践》', 100.00, '2024-12-21 11:10:06', '2024-12-21 11:10:06');
INSERT INTO `sku` VALUES (2, 's01', 'c01', '100002', '无双飞将小八', 299.00, '2025-03-24 11:12:05', '2025-03-24 11:12:05');
INSERT INTO `sku` VALUES (3, 's01', 'c01', '100003', 'Atri', 299.00, '2025-03-26 10:40:58', '2025-03-26 10:43:43');
INSERT INTO `sku` VALUES (4, 's01', 'c01', '100004', '吃面包的雷姆', 299.00, '2025-03-26 10:41:19', '2025-03-26 10:43:44');
INSERT INTO `sku` VALUES (5, 's01', 'c01', '100011', '傲娇初音未来', 299.00, '2025-03-26 10:41:42', '2025-03-26 10:43:45');
INSERT INTO `sku` VALUES (6, 's01', 'c01', '100006', '冬日雷姆', 299.00, '2025-03-26 10:41:55', '2025-03-26 10:43:46');
INSERT INTO `sku` VALUES (7, 's01', 'c01', '100007', 'wink atri', 299.00, '2025-03-26 10:42:05', '2025-03-26 10:43:47');
INSERT INTO `sku` VALUES (8, 's01', 'c01', '100010', '初音未来', 299.00, '2025-03-26 10:42:54', '2025-03-26 10:43:48');

SET FOREIGN_KEY_CHECKS = 1;
