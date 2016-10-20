/*
Navicat MySQL Data Transfer

Source Server         : mysql5.6
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : seckilldb

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-10-20 09:52:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存Id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('2', '1000元秒杀iPhone6s', '92', '2016-10-20 00:00:10', '2016-10-20 13:22:56', '2016-10-06 13:22:59');
INSERT INTO `seckill` VALUES ('3', '500元秒杀iPad2', '197', '2016-10-19 01:19:10', '2016-10-28 13:11:25', '2016-10-06 13:22:59');
INSERT INTO `seckill` VALUES ('4', '200元秒杀iPod', '100', '2016-10-12 13:56:20', '2016-10-17 08:30:22', '2016-10-06 13:22:59');
INSERT INTO `seckill` VALUES ('5', '100元秒杀小米2', '100', '2016-10-16 13:56:28', '2016-10-16 08:30:26', '2016-10-06 13:22:59');
INSERT INTO `seckill` VALUES ('6', '100元秒杀红米note', '100', '2016-10-29 13:56:37', '2016-10-17 09:56:06', '2016-10-06 13:22:59');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` int(11) NOT NULL COMMENT '秒杀产品Id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号码',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态:-1:无效、0:成功、1:已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`,`user_phone`),
  CONSTRAINT `success_killed_ibfk_1` FOREIGN KEY (`seckill_id`) REFERENCES `seckill` (`seckill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('2', '12345674444', '0', '2016-10-18 14:23:58');
INSERT INTO `success_killed` VALUES ('2', '12345678989', '0', '2016-10-18 12:53:40');
INSERT INTO `success_killed` VALUES ('2', '13870777375', '0', '2016-10-20 00:00:18');
INSERT INTO `success_killed` VALUES ('2', '13899999999', '0', '2016-10-19 02:11:19');
INSERT INTO `success_killed` VALUES ('2', '15236987456', '0', '2016-10-19 01:17:46');
INSERT INTO `success_killed` VALUES ('2', '15270998540', '0', '2016-10-14 08:33:01');
INSERT INTO `success_killed` VALUES ('2', '15288889999', '0', '2016-10-17 09:35:38');
INSERT INTO `success_killed` VALUES ('2', '15898783214', '0', '2016-10-19 02:16:24');
INSERT INTO `success_killed` VALUES ('2', '18702604850', '0', '2016-10-16 20:23:54');
INSERT INTO `success_killed` VALUES ('2', '18702989999', '0', '2016-10-18 14:21:48');
INSERT INTO `success_killed` VALUES ('2', '187029811111', '0', '2016-10-18 14:52:00');
INSERT INTO `success_killed` VALUES ('2', '187029811112', '0', '2016-10-18 14:52:15');
INSERT INTO `success_killed` VALUES ('2', '187029811113', '0', '2016-10-18 17:30:20');
INSERT INTO `success_killed` VALUES ('2', '187029811118', '0', '2016-10-19 02:02:36');
INSERT INTO `success_killed` VALUES ('2', '187029811119', '0', '2016-10-19 01:14:25');
INSERT INTO `success_killed` VALUES ('3', '15236987456', '0', '2016-10-19 01:19:11');
INSERT INTO `success_killed` VALUES ('3', '15898783214', '0', '2016-10-19 02:17:38');
