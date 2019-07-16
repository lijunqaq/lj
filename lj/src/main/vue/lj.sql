/*
Navicat MySQL Data Transfer

Source Server         : lj
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lj

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-02-13 15:24:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `KeyID` varchar(255) COLLATE utf8_bin NOT NULL,
  `DeptName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `State` bigint(20) DEFAULT NULL,
  `ParentID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`KeyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('100', '未来集团', '1', null);
INSERT INTO `dept` VALUES ('3d00290a-1af0-4c28-853e-29fbf96a2722', '市场部', '1', '100');
INSERT INTO `dept` VALUES ('402881e6624bd70601624c0c5cc60002', 'lala', '1', '100');
INSERT INTO `dept` VALUES ('4028827c4fb6202a014fb6209c730000', '杰信总裁办', '1', '100');
INSERT INTO `dept` VALUES ('4028827c4fb633bd014fb64467470000', '杰信', '1', '100');
INSERT INTO `dept` VALUES ('4028827c4fb645b0014fb64668550000', '船运部cgx', '1', '100');
INSERT INTO `dept` VALUES ('73f3fa2f-66a2-4d16-8306-78d89003031b', '网络部', '1', '100');
INSERT INTO `dept` VALUES ('97f88a8c-90fc-4d52-aed7-2046f62fb498', '总经办', '1', '100');
INSERT INTO `dept` VALUES ('aeb1c7d3-9a54-4f73-b0ec-0325b83aef45', '船运部', '1', '100');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `ModuleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `ParentID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ParentName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LayerNum` bigint(20) DEFAULT NULL,
  `IsLeaf` bigint(20) DEFAULT NULL,
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
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', null, '', '系统首页', '1', '0', '', '系统首页', 'home', '0', '1', '', '', null, 'home', '1', '', '', null, '', null);
INSERT INTO `module` VALUES ('101', '1', '系统首页', '我的留言板', '2', '0', '', '我的留言板', '', '1', '1', '', '', null, 'home', '6', '', '', null, '', null);
INSERT INTO `module` VALUES ('102', '1', '系统首页', '我的代办任务', '2', '0', '', '我的代办任务', '', '1', '1', '', '', null, 'home', '7', '', '', null, '', null);
INSERT INTO `module` VALUES ('103', '1', '系统首页', '请假单管理', '2', '0', '', '请假单管理', '', '1', '1', '', '', null, 'home', '8', '', '', null, '', null);
INSERT INTO `module` VALUES ('2', null, '', '货运管理', '1', '0', '', '货运管理', 'cargo', '0', '1', '', '', null, 'cargo', '2', '', '', null, '', null);
INSERT INTO `module` VALUES ('201', '2', '货运管理', '购销合同', '2', '0', '', '购销合同', 'cargo/contractAction_list', '1', '1', '', '', null, 'cargo', '9', '', '', null, '', null);
INSERT INTO `module` VALUES ('202', '2', '货运管理', '出货表', '2', '0', '', '出货表', 'cargo/outProductAction_toedit', '1', '1', '', '', null, 'cargo', '10', '', '', null, '', null);
INSERT INTO `module` VALUES ('203', '2', '货运管理', '合同管理', '2', '0', '', '合同管理', 'cargo/exportAction_contractList', '1', '1', '', '', null, 'cargo', '11', '', '', null, '', null);
INSERT INTO `module` VALUES ('204', '2', '货运管理', '出口报运', '2', '0', '', '出口报运', 'cargo/exportAction_list.action', '1', '1', '', '', null, 'cargo', '12', '', '', null, '', null);
INSERT INTO `module` VALUES ('205', '2', '货运管理', '装箱管理', '2', '0', '', '装箱管理', 'cargo/packingListAction_list', '1', '1', '', '', null, 'cargo', '22', '', '', null, '', null);
INSERT INTO `module` VALUES ('206', '2', '货运管理', '委托管理', '2', '0', '', '委托管理', 'cargo/shippingOrderAction_list', '1', '1', '', '', null, 'cargo', '23', '', '', null, '', null);
INSERT INTO `module` VALUES ('207', '2', '货运管理', '发票管理', '2', '0', '', '发票管理', 'cargo/invoiceAction_list', '1', '1', '', '', null, 'cargo', '24', '', '', null, '', null);
INSERT INTO `module` VALUES ('208', '2', '货运管理', '财务管理', '2', '0', '', '财务管理', 'cargo/financeAction_list', '1', '1', '', '', null, 'cargo', '25', '', '', null, '', null);
INSERT INTO `module` VALUES ('3', null, '', '统计分析', '1', '0', '', '统计分析', 'stat', '0', '1', '', '', null, 'stat', '3', '', '', null, '', null);
INSERT INTO `module` VALUES ('301', '3', '统计分析', '厂家销售情况', '2', '0', '', '厂家销售情况', 'stat/statChartAction_factorysale', '1', '1', '', '', null, 'stat', '13', '', '', null, '', null);
INSERT INTO `module` VALUES ('302', '3', '统计分析', '产品销售排行', '2', '0', '', '产品销售排行', 'stat/statChartAction_productsale', '1', '1', '', '', null, 'stat', '14', '', '', null, '', null);
INSERT INTO `module` VALUES ('303', '3', '统计分析', '系统访问压力', '2', '0', '', '系统访问压力', 'stat/statChartAction_onlineinfo', '1', '1', '', '', null, 'stat', '15', '', '', null, '', null);
INSERT INTO `module` VALUES ('4', null, '', '基础信息', '1', '0', '', '基础信息', 'baseinfo', '0', '1', '', '', null, 'baseinfo', '4', '', '', null, '', null);
INSERT INTO `module` VALUES ('401', '4', '基础信息', '系统代码', '2', '0', '', '系统代码', '', '1', '1', '', '', null, 'baseinfo', '16', '', '', null, '', null);
INSERT INTO `module` VALUES ('402', '4', '基础信息', '厂家信息', '2', '0', '', '厂家信息', '', '1', '1', '', '', null, 'baseinfo', '17', '', '', null, '', null);
INSERT INTO `module` VALUES ('402881e561f9a7790161f9cdc7a20000', '5', '系统管理', 'test2', '22', '0', '', 'test2', 'test2', '1', '0', '', '', '0', 'test2', '1', '', '', null, '', null);
INSERT INTO `module` VALUES ('4028827c4fbbba20014fbbbc80570000', null, '', '44', '44', null, '', '44', '44', '3', '1', '44', '44', null, '44', '44', '', '', null, '', null);
INSERT INTO `module` VALUES ('5', null, '', '系统管理', '1', '0', '', '系统管理', 'sysadmin', '0', '1', '', '', null, 'sysadmin', '5', '', '', null, '', null);
INSERT INTO `module` VALUES ('501', '5', '系统管理', '部门管理', '2', '0', '', '部门管理', 'system/dept', '1', '1', '', '', null, 'system', '18', '', '', null, '', null);
INSERT INTO `module` VALUES ('502', '5', '系统管理', '用户管理', '2', '0', '', '用户管理', 'system/user', '1', '1', '', '', null, 'system', '19', '', '', null, '', null);
INSERT INTO `module` VALUES ('503', '5', '系统管理', '角色管理', '2', '0', '', '角色管理', 'system/role', '1', '1', '', '', null, 'system', '20', '', '', null, '', null);
INSERT INTO `module` VALUES ('504', '5', '系统管理', '模块管理', '2', '0', '', '模块管理', 'system/module', '1', '1', '', '', null, 'system', '21', '', '', null, '', null);
INSERT INTO `module` VALUES ('6', null, '', '流程管理', '1', '0', '', '流程管理', 'activiti', '0', '1', '', '', null, 'activiti', '60', '', '', null, '', null);
INSERT INTO `module` VALUES ('601', '6', '流程管理', '部署流程', '2', '0', '', '部署流程', 'flow/flowAction_deploy.action', '1', '1', '', '', null, 'activiti', '61', '', '', null, '', null);
INSERT INTO `module` VALUES ('602', '6', '流程管理', '查询流程', '2', '0', '', '查询流程', 'flow/flowAction_queryProcessDefinition.action', '1', '1', '', '', null, 'activiti', '62', '', '', null, '', null);
INSERT INTO `module` VALUES ('603', '6', '流程管理', '添加采购单', '2', '0', '', '添加采购单', 'flow/orderFlowAction_addOrder.action', '1', '1', '', '', null, 'activiti', '63', '', '', null, '', null);
INSERT INTO `module` VALUES ('604', '6', '流程管理', '查询采购单', '2', '0', '', '查询采购单', 'flow/orderFlowAction_orderTaskList.action', '1', '1', '', '', null, 'activiti', '64', '', '', null, '', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OrderNo` int(11) DEFAULT NULL,
  `CreateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateDept` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `UpdateBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('402881e6624bd70601624c420eb60003', 't', '1', '1', '', '', null, '', null);
INSERT INTO `role` VALUES ('402881e6624c4a3001624c4ab77e0000', 'test', 'test2', '1', '', '', null, '', null);
INSERT INTO `role` VALUES ('402881e6624c4a3001624c4ab77e0010', 'test', 'test2', '1', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1c34ec2e5c8014ec2ebf8430001', '船运专员2', '1', '9', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1c34ec2e5c8014ec2ec38cc0002', '船运经理', '', '10', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0000', '装箱专员', '装箱专员', '11', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0001', '总经理', 'sysadmin', '1', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0002', '销售经理', 'salemanager', '2', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0003', '合同专责', 'contract', '3', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0004', '销售专责', 'sale', '4', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0005', '报运经理', 'transportation', '5', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0006', '报运专责', 'trans', '6', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0007', '财务经理', '', '7', '', '', null, '', null);
INSERT INTO `role` VALUES ('4028a1cd4ee2d9d6014ee2df4c6a0008', '财务专责', 'fin', '8', '', '', null, '', null);

-- ----------------------------
-- Table structure for rolemodule
-- ----------------------------
DROP TABLE IF EXISTS `rolemodule`;
CREATE TABLE `rolemodule` (
  `ModuleID` varchar(255) COLLATE utf8_bin NOT NULL,
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`RoleID`,`ModuleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rolemodule
-- ----------------------------
INSERT INTO `rolemodule` VALUES ('1', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('101', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('102', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('103', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('2', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('201', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('202', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('203', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('204', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('205', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('206', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('207', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('208', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('3', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('301', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('302', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('303', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('4', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('401', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('402', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('402881e561f9a7790161f9cdc7a20000', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('4028827c4fbbba20014fbbbc80570000', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('5', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('501', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('502', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('503', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('504', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('6', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('601', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('602', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('603', '4028a1cd4ee2d9d6014ee2df4c6a0007');
INSERT INTO `rolemodule` VALUES ('604', '4028a1cd4ee2d9d6014ee2df4c6a0007');

-- ----------------------------
-- Table structure for roleuser
-- ----------------------------
DROP TABLE IF EXISTS `roleuser`;
CREATE TABLE `roleuser` (
  `UserID` varchar(255) COLLATE utf8_bin NOT NULL,
  `RoleID` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`UserID`,`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of roleuser
-- ----------------------------
INSERT INTO `roleuser` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', '4028a1cd4ee2d9d6014ee2df4c6a0007');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `keyID` varchar(255) NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `PawwWord` varchar(255) DEFAULT NULL,
  `State` int(11) DEFAULT NULL,
  `CreateBy` varchar(255) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `UpdateBy` varchar(255) DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL,
  `DeptID` varchar(255) DEFAULT NULL,
  `isDelete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1902-8ecb33fe-274e-49a8-a828-19c8934ab648K', 'wu', '96E79218965EB72C92A549DD5A330112', '1', '伍六七', '2019-02-13 15:12:59', '伍六七', '2019-02-13 15:12:59', '100', '0');
INSERT INTO `user` VALUES ('1902-d205857b-f3fe-471b-838a-fa36f1c184caK', 'aa', '96E79218965EB72C92A549DD5A330112', '1', '伍六七', '2019-02-13 14:43:16', null, null, '100', '0');
INSERT INTO `user` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', 'cgx', '$2a$10$CJEjegklpk.PIBl0AsHZeuokU8EtpVeVMAhI3U9o2vNdf3lhLsL/C', '1', '', '2019-02-01 11:26:06', '', '2019-02-01 11:26:10', '4028827c4fb6202a014fb6209c730000', '0');
INSERT INTO `user` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc93', 'lj', '$10$CJEjegklpk.PIBl0AsHZeuokU8EtpVeVMAhI3U9o2vNdf3lhLsL/C', '1', null, '2019-02-19 14:16:16', null, '2019-02-13 14:16:19', '4028827c4fb6202a014fb6209c730000', '0');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `UserID` varchar(255) COLLATE utf8_bin NOT NULL,
  `ManagerID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
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
  `AddBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `AddDept` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `AddTime` datetime DEFAULT NULL,
  `ModifyBy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1902-8ecb33fe-274e-49a8-a828-19c8934ab648K', null, '翩若惊鸿', '2019-02-13 15:12:59', '10000', '2018-08-17 00:00:00', '1', '开发部', '1001', null, null, '13398120001', '伍六七', null, '2019-02-13 15:12:59', '伍六七', '2019-02-13 15:12:59');
INSERT INTO `userinfo` VALUES ('1902-d205857b-f3fe-471b-838a-fa36f1c184caK', null, '翩若惊鸿', '2019-02-13 14:43:16', '10000', '2018-08-17 00:00:00', '1', '开发部', '1001', null, null, '13398120001', '伍六七', null, '2019-02-13 14:43:18', null, null);
INSERT INTO `userinfo` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc92', 'e0de22fe-2c50-4216-ad75-ed0494d2dc93', '王瑶', '2019-02-11 17:01:18', '112', '2019-02-11 17:01:23', '男', '11', '133', '1', '1', '1011@qqcom', 'sys', 'sys', '2019-02-11 17:01:46', 'sys', '2019-02-11 17:01:43');
INSERT INTO `userinfo` VALUES ('e0de22fe-2c50-4216-ad75-ed0494d2dc93', 'e0de22fe-2c50-4216-ad75-ed0494d2dc92', 'l李俊', '2019-02-21 14:15:26', '100000', '2019-02-21 14:15:35', '男', null, '139', null, '1', '1011@qqcom', null, null, null, null, null);
