SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_info_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `login_name` varchar(30) NOT NULL DEFAULT '' COMMENT '登录名',
  `login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '登录IP',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '上次登录IP',
  `last_login_time` datetime NOT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户登陆日志';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_opt
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_opt`;
CREATE TABLE `sys_log_opt` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_info_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `login_name` varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `action` tinyint(3) NOT NULL DEFAULT '0' COMMENT '动作',
  `remark` varchar(1023) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户操作日志表';

-- ----------------------------
-- Records of sys_log_opt
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `platform_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) NOT NULL COMMENT '菜单路径',
  `target_name` varchar(50) DEFAULT NULL COMMENT '目标名称',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单信息';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '2017-12-27 21:59:23', '2017-12-27 21:59:23', '10', '1', '1', '0', '系统管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('4', '2017-12-27 22:06:51', '2017-12-27 22:06:51', '10', '1', '1', '1', '用户管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('5', '2017-12-27 22:07:06', '2017-12-27 22:07:06', '10', '1', '1', '4', '用户管理', '/admin/sysUserInfo/list', 'admin-sysUserInfo', 'list', '');
INSERT INTO `sys_menu` VALUES ('12', '2017-12-27 22:20:56', '2017-12-27 22:20:56', '1', '1', '1', '1', '权限管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('13', '2017-12-27 22:21:36', '2017-12-27 22:21:36', '10', '1', '1', '12', '角色管理', '/admin/sysRole/list', 'admin-sysRole', 'list', '');
INSERT INTO `sys_menu` VALUES ('14', '2017-12-27 22:22:22', '2017-12-27 22:22:22', '8', '1', '1', '12', '菜单管理', '/admin/sysMenu/list', 'admin-sysMenu', 'list', '');
INSERT INTO `sys_menu` VALUES ('15', '2017-12-27 22:22:46', '2017-12-27 22:22:46', '1', '1', '1', '12', '平台管理', '/admin/sysPlatform/list', 'admin-sysPlatform', 'list', '');
INSERT INTO `sys_menu` VALUES ('76', '2018-04-10 11:19:48', '2018-04-10 11:19:48', '110', '1', '1', '0', '基础管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('77', '2018-04-10 11:20:25', '2018-04-10 11:20:25', '1', '1', '1', '76', '基础管理', '', '', 'list', '');
INSERT INTO `sys_menu` VALUES ('82', '2018-06-07 14:33:21', '2018-06-07 14:33:21', '111', '1', '1', '0', '项目管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('83', '2018-06-07 14:33:48', '2018-06-07 14:33:48', '12', '1', '1', '82', '项目管理', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('84', '2018-06-07 14:34:04', '2018-06-07 14:34:04', '13', '1', '1', '83', '项目列表', '/admin/project/list', 'admin-project', 'list', '');
INSERT INTO `sys_menu` VALUES ('85', '2018-06-07 16:12:56', '2018-06-07 16:12:56', '112', '1', '1', '0', '任务管理', '', '', '', null);
INSERT INTO `sys_menu` VALUES ('86', '2018-06-07 16:14:13', '2018-06-07 16:14:13', '112', '1', '1', '85', '任务列表', '', '', '', '');
INSERT INTO `sys_menu` VALUES ('87', '2018-06-07 16:14:40', '2018-06-07 16:14:40', '111', '1', '1', '86', '待分配任务', '/admin/task/personTask?personStatus=0', 'admin-person-task-0', 'list', '标注人员');
INSERT INTO `sys_menu` VALUES ('91', '2018-06-08 16:58:13', '2018-06-08 16:58:13', '10', '1', '1', '77', '公司管理', '/admin/company/list', 'admin-company', 'list', '');
INSERT INTO `sys_menu` VALUES ('98', '2018-06-09 13:26:21', '2018-06-09 13:26:21', '115', '1', '1', '86', '待检测任务', '/admin/task/initCheck?personStatus=2', 'admin-task-init', 'list', '检测人员');
INSERT INTO `sys_menu` VALUES ('101', '2018-06-11 15:41:27', '2018-06-11 15:41:27', '1', '1', '1', '77', '资源管理', '/admin/shareFolder/list', 'admin-shareFolder-list', 'list', '');
INSERT INTO `sys_menu` VALUES ('102', '2018-06-12 10:05:49', '2018-06-12 10:05:49', '113', '1', '1', '86', '待领取任务', '/admin/task/taskPool?teamStatus=0', 'admin-task-pool-team-w', 'list', '标注人员');
INSERT INTO `sys_menu` VALUES ('103', '2018-06-13 15:35:42', '2018-06-13 15:35:42', '1', '1', '1', '77', '成员管理', '/admin/sysUserInfo/member', 'admin-shareFolder-member', 'list', '');
INSERT INTO `sys_menu` VALUES ('104', '2018-06-14 13:50:42', '2018-06-14 13:50:42', '1', '1', '1', '77', '团队管理', '/admin/team/list', 'admin-team-list', 'list', '');
INSERT INTO `sys_menu` VALUES ('106', '2018-06-14 16:41:10', '2018-06-14 16:41:10', '110', '1', '1', '86', '已分配任务', '/admin/task/personTask?personStatus=1', 'admin-person-task-1', 'list', '标注人员');
INSERT INTO `sys_menu` VALUES ('107', '2018-06-14 16:49:46', '2018-06-14 16:49:46', '112', '1', '1', '86', '已领取任务', '/admin/task/taskPool?teamStatus=1', 'admin-task-pool-team-r', 'list', '团队经理菜单');
INSERT INTO `sys_menu` VALUES ('108', '2018-06-14 16:51:05', '2018-06-14 16:51:05', '114', '1', '1', '86', '已检测任务', '/admin/task/initCheck?personStatus=3', 'admin-task-finish', 'list', '检测人员');
INSERT INTO `sys_menu` VALUES ('109', '2018-06-21 17:03:31', '2018-06-21 17:03:31', '13', '1', '1', '4', '公司管理', '/admin/company/list', 'admin-compay', 'list', '');
INSERT INTO `sys_menu` VALUES ('110', '2018-06-22 13:21:59', '2018-06-22 13:21:59', '1', '1', '1', '77', '产品管理', '/admin/productInfo/list', 'admin-productInfo-list', 'list', '产品管理');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `platform_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1381 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('202', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '3', '2');
INSERT INTO `sys_menu_role` VALUES ('203', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '26', '2');
INSERT INTO `sys_menu_role` VALUES ('204', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '27', '2');
INSERT INTO `sys_menu_role` VALUES ('205', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '75', '2');
INSERT INTO `sys_menu_role` VALUES ('206', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '2', '2');
INSERT INTO `sys_menu_role` VALUES ('207', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '21', '2');
INSERT INTO `sys_menu_role` VALUES ('208', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '38', '2');
INSERT INTO `sys_menu_role` VALUES ('209', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '39', '2');
INSERT INTO `sys_menu_role` VALUES ('210', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '40', '2');
INSERT INTO `sys_menu_role` VALUES ('211', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '73', '2');
INSERT INTO `sys_menu_role` VALUES ('212', '2018-03-19 21:57:48', '2018-03-19 21:57:48', '1', '1', '1', '74', '2');
INSERT INTO `sys_menu_role` VALUES ('1127', '2018-06-15 09:43:59', '2018-06-15 09:43:59', '1', '1', '1', '85', '3');
INSERT INTO `sys_menu_role` VALUES ('1128', '2018-06-15 09:43:59', '2018-06-15 09:43:59', '1', '1', '1', '86', '3');
INSERT INTO `sys_menu_role` VALUES ('1129', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '102', '3');
INSERT INTO `sys_menu_role` VALUES ('1130', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '107', '3');
INSERT INTO `sys_menu_role` VALUES ('1131', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '76', '3');
INSERT INTO `sys_menu_role` VALUES ('1132', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '77', '3');
INSERT INTO `sys_menu_role` VALUES ('1133', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '104', '3');
INSERT INTO `sys_menu_role` VALUES ('1134', '2018-06-15 09:44:00', '2018-06-15 09:44:00', '1', '1', '1', '103', '3');
INSERT INTO `sys_menu_role` VALUES ('1135', '2018-06-20 14:17:52', '2018-06-20 14:17:52', '1', '1', '1', '85', '5');
INSERT INTO `sys_menu_role` VALUES ('1136', '2018-06-20 14:17:52', '2018-06-20 14:17:52', '1', '1', '1', '86', '5');
INSERT INTO `sys_menu_role` VALUES ('1137', '2018-06-20 14:17:52', '2018-06-20 14:17:52', '1', '1', '1', '87', '5');
INSERT INTO `sys_menu_role` VALUES ('1138', '2018-06-20 14:17:52', '2018-06-20 14:17:52', '1', '1', '1', '106', '5');
INSERT INTO `sys_menu_role` VALUES ('1143', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '82', '4');
INSERT INTO `sys_menu_role` VALUES ('1144', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '83', '4');
INSERT INTO `sys_menu_role` VALUES ('1145', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '84', '4');
INSERT INTO `sys_menu_role` VALUES ('1146', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '76', '4');
INSERT INTO `sys_menu_role` VALUES ('1147', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '77', '4');
INSERT INTO `sys_menu_role` VALUES ('1148', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '91', '4');
INSERT INTO `sys_menu_role` VALUES ('1149', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '103', '4');
INSERT INTO `sys_menu_role` VALUES ('1150', '2018-06-21 16:51:45', '2018-06-21 16:51:45', '1', '1', '1', '101', '4');
INSERT INTO `sys_menu_role` VALUES ('1309', '2018-06-22 14:12:50', '2018-06-22 14:12:50', '1', '1', '1', '85', '6');
INSERT INTO `sys_menu_role` VALUES ('1310', '2018-06-22 14:12:50', '2018-06-22 14:12:50', '1', '1', '1', '86', '6');
INSERT INTO `sys_menu_role` VALUES ('1311', '2018-06-22 14:12:50', '2018-06-22 14:12:50', '1', '1', '1', '98', '6');
INSERT INTO `sys_menu_role` VALUES ('1312', '2018-06-22 14:12:50', '2018-06-22 14:12:50', '1', '1', '1', '108', '6');
INSERT INTO `sys_menu_role` VALUES ('1370', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '76', '1');
INSERT INTO `sys_menu_role` VALUES ('1371', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '77', '1');
INSERT INTO `sys_menu_role` VALUES ('1372', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '101', '1');
INSERT INTO `sys_menu_role` VALUES ('1373', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '1', '1');
INSERT INTO `sys_menu_role` VALUES ('1374', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '4', '1');
INSERT INTO `sys_menu_role` VALUES ('1375', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '109', '1');
INSERT INTO `sys_menu_role` VALUES ('1376', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '5', '1');
INSERT INTO `sys_menu_role` VALUES ('1377', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '12', '1');
INSERT INTO `sys_menu_role` VALUES ('1378', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '13', '1');
INSERT INTO `sys_menu_role` VALUES ('1379', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '14', '1');
INSERT INTO `sys_menu_role` VALUES ('1380', '2018-06-29 05:02:16', '2018-06-29 05:02:16', '1', '1', '1', '15', '1');

-- ----------------------------
-- Table structure for sys_platform
-- ----------------------------
DROP TABLE IF EXISTS `sys_platform`;
CREATE TABLE `sys_platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `platform_app_id` char(34) NOT NULL DEFAULT '' COMMENT '平台APPID',
  `platform_app_secret` char(64) NOT NULL DEFAULT '' COMMENT '平台APPSECRET',
  `platform_name` varchar(255) NOT NULL DEFAULT '' COMMENT '平台名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_id` (`platform_app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='授权客户端表';

-- ----------------------------
-- Records of sys_platform
-- ----------------------------
INSERT INTO `sys_platform` VALUES ('1', '2017-12-27 21:31:16', '2017-12-27 21:31:16', '1', '1', 'RC9560472864454143847a270bd4436532', '86aa6ab90d5b4379afaeb905fc1c7019', 'Arcsoft项目管理后台', '我的平台');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `platform_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID',
  `role_name` varchar(50) NOT NULL COMMENT '名称',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2017-12-28 09:58:04', '2017-12-28 09:58:04', '1', '1', '1', '超级管理员', '超级管理员');
INSERT INTO `sys_role` VALUES ('3', '2018-06-08 15:18:49', '2018-06-08 15:18:49', '2', '1', '1', '团队经理', '团队经理');
INSERT INTO `sys_role` VALUES ('4', '2018-06-09 10:20:05', '2018-06-09 10:20:05', '1', '1', '1', '项目经理', '管理项目');
INSERT INTO `sys_role` VALUES ('5', '2018-06-09 10:20:39', '2018-06-09 10:20:39', '1', '1', '1', '标注人员', '执行标注任务');
INSERT INTO `sys_role` VALUES ('6', '2018-06-09 10:21:20', '2018-06-09 10:21:20', '1', '2', '1', '质检人员', '执行检测任务');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `platform_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `user_info_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色用户关联表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '2017-12-28 12:28:51', '2017-12-28 12:28:51', '1', '1', '1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2018-03-19 21:59:05', '2018-03-19 21:59:05', '1', '1', '1', '2', '2');
INSERT INTO `sys_role_user` VALUES ('3', '2018-06-08 15:34:32', '2018-06-08 15:34:32', '1', '1', '1', '3', '3');
INSERT INTO `sys_role_user` VALUES ('4', '2018-06-09 10:24:42', '2018-06-09 10:24:42', '1', '1', '1', '5', '4');
INSERT INTO `sys_role_user` VALUES ('5', '2018-06-09 13:48:36', '2018-06-09 13:48:36', '1', '1', '1', '6', '5');
INSERT INTO `sys_role_user` VALUES ('8', '2018-06-13 18:34:05', '2018-06-13 18:34:05', '1', '1', '1', '5', '3');
INSERT INTO `sys_role_user` VALUES ('14', '2018-06-13 19:13:48', '2018-06-13 19:13:48', '1', '1', '1', '5', '13');
INSERT INTO `sys_role_user` VALUES ('15', '2018-06-13 19:15:44', '2018-06-13 19:15:44', '1', '1', '1', '6', '14');
INSERT INTO `sys_role_user` VALUES ('17', '2018-06-14 18:47:48', '2018-06-14 18:47:48', '1', '1', '1', '5', '16');
INSERT INTO `sys_role_user` VALUES ('18', '2018-06-15 16:55:41', '2018-06-15 16:55:41', '1', '1', '1', '3', '15');
INSERT INTO `sys_role_user` VALUES ('20', '2018-06-21 14:03:17', '2018-06-21 14:03:17', '1', '1', '1', '5', '11');
INSERT INTO `sys_role_user` VALUES ('21', '2018-06-21 14:11:25', '2018-06-21 14:11:25', '1', '1', '1', '5', '10');
INSERT INTO `sys_role_user` VALUES ('23', '2018-06-21 16:38:39', '2018-06-21 16:38:39', '1', '1', '1', '4', '6');
INSERT INTO `sys_role_user` VALUES ('24', '2018-06-21 16:51:59', '2018-06-21 16:51:59', '1', '1', '1', '4', '23');
INSERT INTO `sys_role_user` VALUES ('26', '2018-06-22 15:48:24', '2018-06-22 15:48:24', '1', '1', '1', '3', '25');
INSERT INTO `sys_role_user` VALUES ('27', '2018-06-22 15:59:24', '2018-06-22 15:59:24', '1', '1', '1', '5', '29');
INSERT INTO `sys_role_user` VALUES ('28', '2018-06-22 16:41:23', '2018-06-22 16:41:23', '1', '1', '1', '5', '17');
INSERT INTO `sys_role_user` VALUES ('29', '2018-06-22 18:31:30', '2018-06-22 18:31:30', '1', '1', '1', '5', '31');
INSERT INTO `sys_role_user` VALUES ('30', '2018-06-22 18:35:38', '2018-06-22 18:35:38', '1', '1', '1', '5', '32');
INSERT INTO `sys_role_user` VALUES ('31', '2018-06-22 18:58:18', '2018-06-22 18:58:18', '1', '1', '1', '3', '33');
INSERT INTO `sys_role_user` VALUES ('32', '2018-06-29 06:32:37', '2018-06-29 06:32:37', '1', '1', '1', '4', '34');
INSERT INTO `sys_role_user` VALUES ('33', '2018-07-01 21:42:16', '2018-07-01 21:42:16', '1', '1', '1', '6', '35');
INSERT INTO `sys_role_user` VALUES ('34', '2018-07-02 02:11:11', '2018-07-02 02:11:11', '1', '1', '1', '5', '36');
INSERT INTO `sys_role_user` VALUES ('35', '2018-07-02 02:12:11', '2018-07-02 02:12:11', '1', '1', '1', '4', '37');
INSERT INTO `sys_role_user` VALUES ('36', '2018-07-02 02:13:13', '2018-07-02 02:13:13', '1', '1', '1', '3', '38');

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status_id` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 正常 2 冻结 9 注销',
  `user_type` tinyint(3) DEFAULT NULL COMMENT '1 内部 2 外部',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名(工号)',
  `nick_name` varchar(50) NOT NULL COMMENT '姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` char(11) DEFAULT NULL COMMENT '手机',
  `addr` varchar(255) DEFAULT NULL COMMENT '地址',
  `salt` char(32) DEFAULT NULL COMMENT '密码盐',
  `pwd` char(32) DEFAULT NULL COMMENT '登录密码',
  `company_id` bigint(20) DEFAULT '0' COMMENT '所属公司Id',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户信息';

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('1', '2017-12-27 10:14:14', '2017-12-27 10:14:14', '1', '1', '1', 'admin', 'feng', 'xxx@qq.com', '18333333333', '测试地址', 'b38c649a32cd418487bc4927bdf74ff6', 'f908e5c74e3b13ae14388afd42f3f13d', '1', '虹软');
INSERT INTO `sys_user_info` VALUES ('3', '2018-06-08 15:22:20', '2018-06-08 15:22:20', '1', '1', '1', 'gjf6316', 'gjf', '1091561148@qq.com', '17215611483', '', '9d6e057a0f364f3981d7889e99a3a74f', 'c98fbe163ce15e6a3c513aba36f5ffa7', '1', 'arc');
INSERT INTO `sys_user_info` VALUES ('36', '2018-07-02 02:10:59', '2018-07-02 02:10:59', '1', '1', '1', 'mark123', 'mark', '11111@123.com', '13224141244', '4', 'f1a2eb25347b480eb35199a2a1bee59b', '9224f1a613caf7fe8ec4c0b54e9bf906', '7', '虹软');
INSERT INTO `sys_user_info` VALUES ('37', '2018-07-02 02:11:55', '2018-07-02 02:11:55', '1', '1', '1', 'sdf1111', '史蒂夫', '1231@qq.com', '13223333333', 'asd', '459a2b76e3334c65866820fba5452cd9', '4c2fb24fb7dd12bf3877c3ac910127d3', '7', '虹软');
INSERT INTO `sys_user_info` VALUES ('38', '2018-07-02 02:13:04', '2018-07-02 02:13:04', '1', '1', '1', 'tft555', '太妃糖', '1091561148@qq.com', '13222222233', '', '443ff936fc944cbea4ed9023281d1451', 'f4c3dcf87e0d4b5a1d4316d1919c4f83', '7', '虹软');
INSERT INTO `sys_user_info` VALUES ('39', '2018-07-02 02:14:00', '2018-07-02 02:14:00', '1', '1', '1', 'aoding123', '奥丁', '108222@qq.com', '13222222222', '', '8057cda48d21441d8d9c7c07e6e24865', '16c6680d22eaef83cf05a4b21c826f21', '7', '虹软');
