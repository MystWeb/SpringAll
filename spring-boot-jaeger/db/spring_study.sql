/*
 Navicat Premium Data Transfer

 Source Server         : CentOS 7.6 Java MySQL-8.0.15
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 192.168.100.130:3306
 Source Schema         : spring_study

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/06/2020
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`
(
    `id`        int(11)      NOT NULL AUTO_INCREMENT COMMENT '员工ID',
    `last_name` varchar(255) NULL DEFAULT NULL COMMENT '姓名',
    `email`     varchar(255) NULL DEFAULT NULL COMMENT '邮箱',
    `gender`    int(2)       NULL DEFAULT NULL COMMENT '性别',
    `d_id`      int(11)      NULL DEFAULT NULL COMMENT '部门ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '员工';