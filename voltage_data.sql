/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : plugdev

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-01-06 13:28:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for voltage_data
-- ----------------------------
DROP TABLE IF EXISTS `voltage_data`;
CREATE TABLE `voltage_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `imei` char(16) DEFAULT NULL COMMENT '终端设备唯一编号',
  `voltage` char(16) DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '定位时间',
  PRIMARY KEY (`id`),
  KEY `i_imei` (`imei`) USING BTREE,
  KEY `i_time` (`time`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=602034 DEFAULT CHARSET=latin1;
