/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : springboot_aop

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 25/10/2019 12:23:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for web_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `web_visit_log`;
CREATE TABLE `web_visit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `error` tinyint(4) DEFAULT NULL COMMENT '访问接口是否出错',
  `operation_name` varchar(255) DEFAULT NULL COMMENT '被访问接口的功能',
  `request` text COMMENT '访问接口的请求内容',
  `response` text COMMENT '接口的响应内容',
  `stack` text COMMENT '异常调用栈信息',
  `take_time` bigint(20) DEFAULT NULL COMMENT '访问消耗时间',
  `visit_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
