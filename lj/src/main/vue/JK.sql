/*
Navicat MySQL Data Transfer

Source Server         : www.coachtam.cn
Source Server Version : 50639
Source Host           : www.coachtam.cn:3306
Source Database       : jk

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-04-24 14:34:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for CONTRACT_C
-- ----------------------------
DROP TABLE IF EXISTS `CONTRACT_C`;
CREATE TABLE `CONTRACT_C` (
  `CONTRACT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `OFFEROR` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CONTRACT_NO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SIGNING_DATE` datetime DEFAULT NULL,
  `INPUT_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CHECK_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `INSPECTOR` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOTAL_AMOUNT` bigint(20) DEFAULT NULL,
  `CREQUEST` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOM_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SHIP_TIME` datetime DEFAULT NULL,
  `IMPORT_NUM` bigint(20) DEFAULT NULL,
  `DELIVERY_PERIOD` datetime DEFAULT NULL,
  `OLD_STATE` bigint(20) DEFAULT NULL,
  `OUT_STATE` bigint(20) DEFAULT NULL,
  `TRADE_TERMS` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRINT_STYLE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REMARK` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `STATE` bigint(20) DEFAULT NULL,
  `CREATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DEPT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`CONTRACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of CONTRACT_C
-- ----------------------------
INSERT INTO `CONTRACT_C` VALUES ('2c92804f6271b483016271b814750000', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '13', 'request', '客户名字', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '2', null, null, null, null, '2018-03-29 20:26:56');
INSERT INTO `CONTRACT_C` VALUES ('2c92804f6271b483016271b826b00001', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '0', 'request', '客户名字', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '0', null, null, null, null, null);
INSERT INTO `CONTRACT_C` VALUES ('4028ab54626fbcb701626fbdd2680000', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '0', 'request', '????', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '0', null, null, null, null, null);
INSERT INTO `CONTRACT_C` VALUES ('4028ab54626fc1a101626fc208aa0000', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '0', 'request', '????', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '0', null, null, null, null, null);
INSERT INTO `CONTRACT_C` VALUES ('4028ab54626fc28201626fc314c30000', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '0', 'request', '????', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '0', null, null, null, null, null);
INSERT INTO `CONTRACT_C` VALUES ('4028ab54626fc51501626fc56ef10000', '1', '1', '2018-12-12 00:00:00', 'cgx', 'cgx', 'cgx', '0', 'request', '????', '2018-12-12 00:00:00', '1', '2018-12-12 00:00:00', null, null, 'fob', '2', 'remark', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for CONTRACT_PRODUCT_C
-- ----------------------------
DROP TABLE IF EXISTS `CONTRACT_PRODUCT_C`;
CREATE TABLE `CONTRACT_PRODUCT_C` (
  `CONTRACT_PRODUCT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `FACTORY_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_NO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_IMAGE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_DESC` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOADING_RATE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BOX_NUM` bigint(20) DEFAULT NULL,
  `PACKING_UNIT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CNUMBER` bigint(20) DEFAULT NULL,
  `OUT_NUMBER` bigint(20) DEFAULT NULL,
  `FINISHED` bigint(20) DEFAULT NULL,
  `PRODUCT_REQUEST` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRICE` bigint(20) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `ORDER_NO` bigint(20) DEFAULT NULL,
  `CONTRACT_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FACTORY_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`CONTRACT_PRODUCT_ID`),
  KEY `FKmb68ehuj44mk5wmhhljscqpwp` (`CONTRACT_ID`),
  KEY `FK66lb31n2i84efqkcur0m8xaff` (`FACTORY_ID`),
  CONSTRAINT `FK66lb31n2i84efqkcur0m8xaff` FOREIGN KEY (`FACTORY_ID`) REFERENCES `FACTORY_C` (`FACTORY_ID`),
  CONSTRAINT `FKmb68ehuj44mk5wmhhljscqpwp` FOREIGN KEY (`CONTRACT_ID`) REFERENCES `CONTRACT_C` (`CONTRACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of CONTRACT_PRODUCT_C
-- ----------------------------
INSERT INTO `CONTRACT_PRODUCT_C` VALUES ('2c92804f6271b483016271b8b7450002', '晶晨', '1', '', '1', '1', '1', 'PCS', '1', null, null, '1', '1', '1', '1', '2c92804f6271b483016271b814750000', '10');
INSERT INTO `CONTRACT_PRODUCT_C` VALUES ('2c92804f6271b483016271b943fe0004', '晶晨', '1', '', '1', '1', '1', 'SETS', '1', null, null, '1', '1', '1', '1', '2c92804f6271b483016271b814750000', '10');

-- ----------------------------
-- Table structure for DEPT_P
-- ----------------------------
DROP TABLE IF EXISTS `DEPT`;
CREATE TABLE `DEPT` (
  `KeyID` varchar(255) COLLATE utf8_bin NOT NULL,
  `DeptName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `State` bigint(20) DEFAULT NULL,
  `ParentID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`KeyID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of DEPT_P
-- ----------------------------
INSERT INTO `DEPT` VALUES ('100', '杰信商贸集团2', '1', null);
INSERT INTO `DEPT` VALUES ('3d00290a-1af0-4c28-853e-29fbf96a2722', '市场部', '1', '100');
INSERT INTO `DEPT` VALUES ('402881e6624bd70601624c0c5cc60002', 'lala', '1', '100');
INSERT INTO `DEPT` VALUES ('4028827c4fb6202a014fb6209c730000', '杰信总裁办', '1', '100');
INSERT INTO `DEPT` VALUES ('4028827c4fb633bd014fb64467470000', '杰信', '1', '100');
INSERT INTO `DEPT` VALUES ('4028827c4fb645b0014fb64668550000', '船运部cgx', '1', '100');
INSERT INTO `DEPT` VALUES ('73f3fa2f-66a2-4d16-8306-78d89003031b', '网络部', '1', '100');
INSERT INTO `DEPT` VALUES ('97f88a8c-90fc-4d52-aed7-2046f62fb498', '总经办', '1', '100');
INSERT INTO `DEPT` VALUES ('aeb1c7d3-9a54-4f73-b0ec-0325b83aef45', '船运部', '1', '100');

-- ----------------------------
-- Table structure for EXPORT_C
-- ----------------------------
DROP TABLE IF EXISTS `EXPORT_C`;
CREATE TABLE `EXPORT_C` (
  `EXPORT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `INPUT_DATE` datetime DEFAULT NULL,
  `CONTRACT_IDS` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CUSTOMER_CONTRACT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LCNO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CONSIGNEE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SHIPMENT_PORT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESTINATION_PORT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TRANSPORT_MODE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRICE_CONDITION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REMARK` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BOX_NUMS` int(11) DEFAULT NULL,
  `GROSS_WEIGHTS` double DEFAULT NULL,
  `MEASUREMENTS` double DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `CREATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DEPT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`EXPORT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of EXPORT_C
-- ----------------------------
INSERT INTO `EXPORT_C` VALUES ('2c92804f6271b483016271b9ab580005', '2018-03-29 20:26:56', null, ' 1', '1', 'consignee', 'marks', 'shipmentPort', 'destinationPort', 'transportMode', 'priceCondition', '1', '12', null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for EXPORT_PRODUCT_C
-- ----------------------------
DROP TABLE IF EXISTS `EXPORT_PRODUCT_C`;
CREATE TABLE `EXPORT_PRODUCT_C` (
  `EXPORT_PRODUCT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRODUCT_NO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PACKING_UNIT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CNUMBER` bigint(20) DEFAULT NULL,
  `BOX_NUM` bigint(20) DEFAULT NULL,
  `GROSS_WEIGHT` bigint(20) DEFAULT NULL,
  `NET_WEIGHT` bigint(20) DEFAULT NULL,
  `SIZE_LENGTH` bigint(20) DEFAULT NULL,
  `SIZE_WIDTH` bigint(20) DEFAULT NULL,
  `SIZE_HEIGHT` bigint(20) DEFAULT NULL,
  `EX_PRICE` bigint(20) DEFAULT NULL,
  `PRICE` bigint(20) DEFAULT NULL,
  `TAX` bigint(20) DEFAULT NULL,
  `ORDER_NO` bigint(20) DEFAULT NULL,
  `FACTORY_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXPORT_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`EXPORT_PRODUCT_ID`),
  KEY `FK8qcn7yoy4w9a4qhytdxk4ad81` (`FACTORY_ID`),
  KEY `FKp0xqx2s6wyiajlx41sex6mqm8` (`EXPORT_ID`),
  CONSTRAINT `FK8qcn7yoy4w9a4qhytdxk4ad81` FOREIGN KEY (`FACTORY_ID`) REFERENCES `FACTORY_C` (`FACTORY_ID`),
  CONSTRAINT `FKp0xqx2s6wyiajlx41sex6mqm8` FOREIGN KEY (`EXPORT_ID`) REFERENCES `EXPORT_C` (`EXPORT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of EXPORT_PRODUCT_C
-- ----------------------------
INSERT INTO `EXPORT_PRODUCT_C` VALUES ('2c92804f6271b483016271b9ab580006', '1', 'SETS', '1', '1', '1', null, null, '1', '1', null, '1', '1', '1', null, '2c92804f6271b483016271b9ab580005');
INSERT INTO `EXPORT_PRODUCT_C` VALUES ('2c92804f6271b483016271b9ab580007', '1', 'PCS', '1', '1', null, '1', '1', null, null, '1', '1', null, '1', null, '2c92804f6271b483016271b9ab580005');

-- ----------------------------
-- Table structure for EXT_CPRODUCT_C
-- ----------------------------
DROP TABLE IF EXISTS `EXT_CPRODUCT_C`;
CREATE TABLE `EXT_CPRODUCT_C` (
  `EXT_CPRODUCT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `FACTORY_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_NO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_IMAGE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_DESC` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PACKING_UNIT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CNUMBER` bigint(20) DEFAULT NULL,
  `PRICE` bigint(20) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `PRODUCT_REQUEST` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_NO` bigint(20) DEFAULT NULL,
  `CONTRACT_PRODUCT_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FACTORY_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`EXT_CPRODUCT_ID`),
  KEY `FKj90tsfbd9ii4gngfsfk5b0r4v` (`CONTRACT_PRODUCT_ID`),
  KEY `FKp09f9euqhjbjvbxwmw0mf60n0` (`FACTORY_ID`),
  CONSTRAINT `FKj90tsfbd9ii4gngfsfk5b0r4v` FOREIGN KEY (`CONTRACT_PRODUCT_ID`) REFERENCES `CONTRACT_PRODUCT_C` (`CONTRACT_PRODUCT_ID`),
  CONSTRAINT `FKp09f9euqhjbjvbxwmw0mf60n0` FOREIGN KEY (`FACTORY_ID`) REFERENCES `FACTORY_C` (`FACTORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of EXT_CPRODUCT_C
-- ----------------------------
INSERT INTO `EXT_CPRODUCT_C` VALUES ('2c92804f6271b483016271b8e7f20003', '太谷PVC', '1', '', '1', 'PCS', '1', '11', '11', '1', '1', '2c92804f6271b483016271b8b7450002', '4028817a33de41d80133e982a18a001c');

-- ----------------------------
-- Table structure for EXT_EPRODUCT_C
-- ----------------------------
DROP TABLE IF EXISTS `EXT_EPRODUCT_C`;
CREATE TABLE `EXT_EPRODUCT_C` (
  `EXT_EPRODUCT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRODUCT_NO` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_IMAGE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRODUCT_DESC` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CNUMBER` bigint(20) DEFAULT NULL,
  `PACKING_UNIT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRICE` bigint(20) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `PRODUCT_REQUEST` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_NO` bigint(20) DEFAULT NULL,
  `FACTORY_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXPORT_PRODUCT_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`EXT_EPRODUCT_ID`),
  KEY `FKhm49reda0b27621ele55g9vp7` (`FACTORY_ID`),
  KEY `FKkkhvc9p19ay2lubvofv8bifqd` (`EXPORT_PRODUCT_ID`),
  CONSTRAINT `FKhm49reda0b27621ele55g9vp7` FOREIGN KEY (`FACTORY_ID`) REFERENCES `FACTORY_C` (`FACTORY_ID`),
  CONSTRAINT `FKkkhvc9p19ay2lubvofv8bifqd` FOREIGN KEY (`EXPORT_PRODUCT_ID`) REFERENCES `EXPORT_PRODUCT_C` (`EXPORT_PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of EXT_EPRODUCT_C
-- ----------------------------
INSERT INTO `EXT_EPRODUCT_C` VALUES ('2c92804f6271b483016271b9ab590008', '1', '', '1', '1', 'PCS', '11', '11', '1', '1', '4028817a33de41d80133e982a18a001c', '2c92804f6271b483016271b9ab580007');

-- ----------------------------
-- Table structure for FACTORY_C
-- ----------------------------
DROP TABLE IF EXISTS `FACTORY_C`;
CREATE TABLE `FACTORY_C` (
  `FACTORY_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `CTYPE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FULL_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FACTORY_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CONTACTS` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PHONE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FAX` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `INSPECTOR` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REMARK` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_NO` bigint(20) DEFAULT NULL,
  `STATE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DEPT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`FACTORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of FACTORY_C
-- ----------------------------
INSERT INTO `FACTORY_C` VALUES ('1', '货物', '祁县海洋厂', '升华', '刘生', '0354-5299987', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('10', '货物', '祁县晶晨厂', '晶晨', '续贵', '0354-5271999', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('12', '货物', '平遥远江厂', '平遥远江', '宏远', '', '13935499967', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('13', '货物', '平遥鸿艺厂', '平遥鸿艺', '雄飞', '0354-5940888', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('15', '货物', '南皮开发玻璃制品厂', '南皮开发', '舒东', '0317-8863999', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('16', '货物', '陕西康达彩印厂', '康达', '袁喜', '029-84528015', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('17', '货物', '陕西众鑫印务包装有限公司', '众鑫', '赵毅', '029-84341858', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('18', '货物', '翰林彩印厂', '翰林', '孙财', '029-88917456', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('19', '货物', '祁县综艺玻璃花纸厂', '综艺花纸', '王棕', '0354-5278918', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('2', '货物', '祁县光华厂', '光华', '薛成', '0354-5298981', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('20', '货物', '祁县喜福来玻璃加工厂', '喜福来', '王卫', '0354-5328850', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('21', '货物', '祁县美晶泡沫厂', '美晶', '范长', '0354-5071387', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('22', '货物', '祁县瑞成玻璃镀膜厂', '瑞成', '温明', '', '13834809374', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('3', '货物', '祁县会龙厂', '会龙', '李伟', '0354-5248696', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4', '货物', '祁县精艺厂', '精艺', '闫强', '0354-5047289', '', '', '', '高琴', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a33d4f8b40133d9989f5e0024', '附件', '闻喜民鑫厂', '民鑫', '李民', '0359-7472727', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a33de41d80133e982a18a001c', '附件', '太谷四通PVC厂', '太谷PVC', '张海', '0354-6226591', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a33ecdbf70133ee202e49000b', '附件', '祁县瑞成电镀厂', '瑞成电镀厂', '温民', '13835926900', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a34f93be50134f9df84470001', '附件', '神舟玻璃制品有限公司', '神舟厂', '申定', '0356-3961959', '13935672348', '0356-3991304', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a353b8d8e0135415bf0a90070', '附件', '陕西宇津进出口有限公司', '宇津水龙头厂', '任宁', '029-62990818', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a35e0895e0135ec7a4dbe0087', '附件', '西安方圆工贸有限公司', '西安方圆工贸', '许涛', '029-86539568', '', '029-86590565', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a3632e86601363344d16d0001', '附件', '淄博雷波陶瓷花纸厂', '淄博花纸厂', '吴璇', '0533-5176946', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a371a8d7f01372a74451f0068', '附件', '山西大德成工贸有限公司', '铁架厂', '高辉', '0354-5288919', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a37583d45013758d152450038', '附件', '祁县顺驰厂', '顺驰', '王明', '0354-5109398', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a38024a04013803e3a8a2004b', '附件', '祁县欣欣泡沫有限公司', '祁县欣欣泡沫厂', '许杰', '0354-3939793', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a382b4fd401382b9aad2a0008', '附件', '祁县仲玉玻璃厂', '仲玉', '吕军', '0354-5018888', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a38736298013874c51a800036', '附件', '汇融玻璃有限公司', '展鹏厂', '厂长', '0354-5326986', '13834893800', '0354-5826197', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a389cc7b701389d1efd940001', '附件', '宇虹包装彩印厂', '宇虹包装厂', '翟帅', '0358-3099923', '19033470988', '0358-3098183', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a39b2b37f0139b46268c40025', '附件', '祁县馨艺玻璃厂', '馨艺', '袁长', '0354-5041938', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a3aa9f950013ab0b6c98d0050', '附件', '祁县一先厂', '一先厂', '李刚', '0654-9018444', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a3aa9f950013ab97f64110053', '附件', '温县致远玻璃制品有限公司', '温县致远厂', '红升', '18936896666', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a3abe8f15013ac019a61f0031', '附件', '祁县喜福来电镀厂', '喜福来电镀厂', '王卫', '0154-5282888', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('4028817a3ae2ac42013ae3550357000d', '附件', '临潼华清蜡厂', '临潼华清蜡厂', '吴波', '13791903888', '', '', '', '', '', null, '0', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('5', '货物', '祁县汇越厂', '汇越', '建忠', '0354-5019656', '', '', '', '吕波', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('6', '货物', '祁县泰宇厂', '泰宇', '立东', '0354-5299160', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('7', '货物', '祁县天顺厂', '天顺', '渠海', '0354-5299499', '', '', '', '李光', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('8', '货物', '祁县天诚厂', '天诚', '庞正', '0354-5299539', '', '', '', '', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('9', '货物', '祁县华艺厂', '华艺', '史国', '0354-5041927', '', '', '', '高琴', '', null, '1', '', '', null, '', null);
INSERT INTO `FACTORY_C` VALUES ('ff808081301885760130189e47ca0013', '附件', '文水志远厂', '文水志远', '志远', '0358-3934083', '', '', '', '李光', '', null, '1', '', '', null, '', null);

-- ----------------------------
-- Table structure for MODULE_P
-- ----------------------------
DROP TABLE IF EXISTS `MODULE`;
CREATE TABLE `MODULE` (
  `ModuleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `ParentID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ParentName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LayerNum` bigint(20) DEFAULT NULL,
  `IsLeaf ` bigint(20) DEFAULT NULL,
  `Ico` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Cpermission` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Curl` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Ctype` bigint(20) DEFAULT NULL,
  `State` bigint(20) DEFAULT NULL,
  `Belong` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Cwihch` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `QuoteNum` bigint(20) DEFAULT NULL,
  `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OrderNo` bigint(20) DEFAULT NULL,
  `CreateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateDept` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `UpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ModuleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of MODULE_P
-- ----------------------------
INSERT INTO `MODULE` VALUES ('1', null, '', '系统首页', '1', '0', '', '系统首页', 'home', '0', '1', '', '', null, 'home', '1', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('101', '1', '系统首页', '我的留言板', '2', '0', '', '我的留言板', '', '1', '1', '', '', null, 'home', '6', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('102', '1', '系统首页', '我的代办任务', '2', '0', '', '我的代办任务', '', '1', '1', '', '', null, 'home', '7', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('103', '1', '系统首页', '请假单管理', '2', '0', '', '请假单管理', '', '1', '1', '', '', null, 'home', '8', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('2', null, '', '货运管理', '1', '0', '', '货运管理', 'cargo', '0', '1', '', '', null, 'cargo', '2', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('201', '2', '货运管理', '购销合同', '2', '0', '', '购销合同', 'cargo/contractAction_list', '1', '1', '', '', null, 'cargo', '9', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('202', '2', '货运管理', '出货表', '2', '0', '', '出货表', 'cargo/outProductAction_toedit', '1', '1', '', '', null, 'cargo', '10', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('203', '2', '货运管理', '合同管理', '2', '0', '', '合同管理', 'cargo/exportAction_contractList', '1', '1', '', '', null, 'cargo', '11', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('204', '2', '货运管理', '出口报运', '2', '0', '', '出口报运', 'cargo/exportAction_list.action', '1', '1', '', '', null, 'cargo', '12', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('205', '2', '货运管理', '装箱管理', '2', '0', '', '装箱管理', 'cargo/packingListAction_list', '1', '1', '', '', null, 'cargo', '22', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('206', '2', '货运管理', '委托管理', '2', '0', '', '委托管理', 'cargo/shippingOrderAction_list', '1', '1', '', '', null, 'cargo', '23', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('207', '2', '货运管理', '发票管理', '2', '0', '', '发票管理', 'cargo/invoiceAction_list', '1', '1', '', '', null, 'cargo', '24', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('208', '2', '货运管理', '财务管理', '2', '0', '', '财务管理', 'cargo/financeAction_list', '1', '1', '', '', null, 'cargo', '25', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('3', null, '', '统计分析', '1', '0', '', '统计分析', 'stat', '0', '1', '', '', null, 'stat', '3', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('301', '3', '统计分析', '厂家销售情况', '2', '0', '', '厂家销售情况', 'stat/statChartAction_factorysale', '1', '1', '', '', null, 'stat', '13', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('302', '3', '统计分析', '产品销售排行', '2', '0', '', '产品销售排行', 'stat/statChartAction_productsale', '1', '1', '', '', null, 'stat', '14', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('303', '3', '统计分析', '系统访问压力', '2', '0', '', '系统访问压力', 'stat/statChartAction_onlineinfo', '1', '1', '', '', null, 'stat', '15', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('4', null, '', '基础信息', '1', '0', '', '基础信息', 'baseinfo', '0', '1', '', '', null, 'baseinfo', '4', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('401', '4', '基础信息', '系统代码', '2', '0', '', '系统代码', '', '1', '1', '', '', null, 'baseinfo', '16', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('402', '4', '基础信息', '厂家信息', '2', '0', '', '厂家信息', '', '1', '1', '', '', null, 'baseinfo', '17', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('402881e561f9a7790161f9cdc7a20000', '5', '系统管理', 'test2', '22', '0', '', 'test2', 'test2', '1', '0', '', '', '0', 'test2', '1', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('4028827c4fbbba20014fbbbc80570000', null, '', '44', '44', null, '', '44', '44', '3', '1', '44', '44', null, '44', '44', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('5', null, '', '系统管理', '1', '0', '', '系统管理', 'sysadmin', '0', '1', '', '', null, 'sysadmin', '5', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('501', '5', '系统管理', '部门管理', '2', '0', '', '部门管理', 'system/dept', '1', '1', '', '', null, 'system', '18', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('502', '5', '系统管理', '用户管理', '2', '0', '', '用户管理', 'system/user', '1', '1', '', '', null, 'system', '19', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('503', '5', '系统管理', '角色管理', '2', '0', '', '角色管理', 'system/role', '1', '1', '', '', null, 'system', '20', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('504', '5', '系统管理', '模块管理', '2', '0', '', '模块管理', 'system/module', '1', '1', '', '', null, 'system', '21', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('6', null, '', '流程管理', '1', '0', '', '流程管理', 'activiti', '0', '1', '', '', null, 'activiti', '60', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('601', '6', '流程管理', '部署流程', '2', '0', '', '部署流程', 'flow/flowAction_deploy.action', '1', '1', '', '', null, 'activiti', '61', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('602', '6', '流程管理', '查询流程', '2', '0', '', '查询流程', 'flow/flowAction_queryProcessDefinition.action', '1', '1', '', '', null, 'activiti', '62', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('603', '6', '流程管理', '添加采购单', '2', '0', '', '添加采购单', 'flow/orderFlowAction_addOrder.action', '1', '1', '', '', null, 'activiti', '63', '', '', null, '', null);
INSERT INTO `MODULE` VALUES ('604', '6', '流程管理', '查询采购单', '2', '0', '', '查询采购单', 'flow/orderFlowAction_orderTaskList.action', '1', '1', '', '', null, 'activiti', '64', '', '', null, '', null);

-- ----------------------------
-- Table structure for ROLE_MODULE_P
-- ----------------------------
DROP TABLE IF EXISTS `ROLEMODULE`;
CREATE TABLE `ROLEMODULE` (
  `ModuleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`RoleID`,`ModuleID`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ROLEMODULE
-- ----------------------------
INSERT INTO `ROLEMODULE` VALUES ('1', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('101', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('102', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('103', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('2', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('201', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('202', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('203', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('204', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('205', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('206', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('207', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('208', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('3', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('301', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('302', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('303', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('4', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('401', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('402', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('402881e561f9a7790161f9cdc7a20000', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('4028827c4fbbba20014fbbbc80570000', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('5', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('501', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('502', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('503', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('504', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('6', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('601', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('602', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('603', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `ROLEMODULE` VALUES ('604', '4028a1cd4ee2d9d6014ee2df4c6a0007');

-- ----------------------------
-- Table structure for ROLE_P
-- ----------------------------
DROP TABLE IF EXISTS `ROLE_P`;
CREATE TABLE `ROLE` (
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OrderNo` int(11) DEFAULT NULL,
  `CreateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateDept` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `UpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ROLE_P
-- ----------------------------
INSERT INTO `Role` VALUES ('402881e6624bd70601624c420eb60003', 't', '1', '1', '', '', null, '', null);
INSERT INTO `Role` VALUES ('402881e6624c4a3001624c4ab77e0000', 'test', 'test2', '1', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1c34ec2e5c8014ec2ebf8430001', '船运专员2', '1', '9', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1c34ec2e5c8014ec2ec38cc0002', '船运经理', '', '10', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0000', '装箱专员', '装箱专员', '11', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0001', '总经理', 'sysadmin', '1', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0002', '销售经理', 'salemanager', '2', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0003', '合同专责', 'contract', '3', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0004', '销售专责', 'sale', '4', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0005', '报运经理', 'transportation', '5', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0006', '报运专责', 'trans', '6', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0007', '财务经理', '', '7', '', '', null, '', null);
INSERT INTO `Role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0008', '财务专责', 'fin', '8', '', '', null, '', null);

-- ----------------------------
-- Table structure for ROLE_USER_P
-- ----------------------------
DROP TABLE IF EXISTS `ROLEUSER`;
CREATE TABLE `ROLEUSER` (
  `UserID` varchar(255) COLLATE utf8_bin NOT NULL,
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`UserID`,`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ROLE_USER_P
-- ----------------------------
INSERT INTO `ROLEUSER` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', '4028a1cd4ee2d9d6014ee2df4c6a0007');

-- ----------------------------
-- Table structure for USER_INFO_P
-- ----------------------------
DROP TABLE IF EXISTS `USERINFO`;
  CREATE TABLE `USERINFO` (
    `UserID` varchar(255) COLLATE utf8_bin NOT NULL,
    `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `Salary` double DEFAULT NULL,
    `Birthday` datetime DEFAULT NULL,
    `Sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Station` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Telephone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Degree` int(11) DEFAULT NULL,
    `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `CreateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `CreateDept` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `UpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `UpdateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`USER_INFO_ID`),
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of USER_INFO_P
-- ----------------------------
INSERT INTO `USERINFO` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', '小陈', '2018-03-29 10:36:20', '1000', '2018-03-29 10:36:28', '1', '拍片', '1111', '1', '1', '1', '327395128@qq.com', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for USER_P
-- ----------------------------
DROP TABLE IF EXISTS `USER_P`;
CREATE TABLE `USER_P` (
   `KeyID` varchar(255) COLLATE utf8_bin NOT NULL,
    `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `CreateTime` datetime DEFAULT NULL,
    `Salary` double DEFAULT NULL,
    `Birthday` datetime DEFAULT NULL,
    `Sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Station` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Telephone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Degree` int(11) DEFAULT NULL,
    `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `CREATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DEPT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DEPT_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of USER_P
-- ----------------------------
INSERT INTO `USER_P` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', 'cgx', '$2a$10$CJEjegklpk.PIBl0AsHZeuokU8EtpVeVMAhI3U9o2vNdf3lhLsL/C', '1', null, null, null, null, null, null);
