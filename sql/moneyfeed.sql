/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : moneyfeed

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-01-15 15:55:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cs_attachments
-- ----------------------------
DROP TABLE IF EXISTS `cs_attachments`;
CREATE TABLE `cs_attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `master_id` bigint(20) DEFAULT NULL COMMENT '业务库主键',
  `real_file_name` varchar(50) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `file_type` varchar(10) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `save_address` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `uploaded` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_attachments
-- ----------------------------

-- ----------------------------
-- Table structure for cs_user_feedbacks
-- ----------------------------
DROP TABLE IF EXISTS `cs_user_feedbacks`;
CREATE TABLE `cs_user_feedbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `user_id` bigint(20) DEFAULT NULL,
  `feedback_time` date DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `feedback_number` varchar(20) DEFAULT NULL COMMENT '留言编号',
  `feedback_desc` varchar(255) DEFAULT NULL,
  `feedback_status` varchar(36) DEFAULT NULL COMMENT '问题状态 \n            1.未处理\n            2.已处理\n            3.已关闭',
  `channel` varchar(20) DEFAULT NULL COMMENT '反馈渠道',
  `intention_shop_id` bigint(20) DEFAULT NULL COMMENT '意向商户id',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '分配商户id',
  `distributed_desc` varchar(255) DEFAULT NULL COMMENT '分配/平台完结留言记录',
  `distributed_creator` bigint(20) DEFAULT NULL COMMENT '分配人',
  `distributed_created` datetime DEFAULT NULL COMMENT '分配时间',
  `official_accounts_mobile` varchar(16) DEFAULT NULL COMMENT '公众号(意见反馈)手机号',
  `official_accounts_name` varchar(64) DEFAULT NULL COMMENT '公众号(意见反馈)姓名',
  `message_label` varchar(200) DEFAULT NULL COMMENT '新建留言标签',
  `message_label_type` varchar(20) DEFAULT NULL COMMENT '留言分类()',
  `data_status` varchar(20) DEFAULT NULL COMMENT '逻辑删除符号：normal正常,disable禁用,delete删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_user_feedbacks
-- ----------------------------

-- ----------------------------
-- Table structure for cs_user_feedback_solves
-- ----------------------------
DROP TABLE IF EXISTS `cs_user_feedback_solves`;
CREATE TABLE `cs_user_feedback_solves` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `feedback_id` bigint(20) DEFAULT NULL,
  `solve_user_id` bigint(20) DEFAULT NULL,
  `solve_time` date DEFAULT NULL,
  `solve_desc` varchar(255) DEFAULT NULL,
  `solve_type` varchar(36) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL COMMENT '当前用户提前反馈问题时所在的商户ID',
  `data_status` varchar(20) DEFAULT NULL COMMENT '逻辑删除符号：normal正常,disable禁用,delete删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_user_feedback_solves
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_category
-- ----------------------------
DROP TABLE IF EXISTS `ebs_category`;
CREATE TABLE `ebs_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `category_id` varchar(32) DEFAULT NULL COMMENT 'ebs品类主键ID',
  `one_cat` varchar(32) DEFAULT NULL COMMENT '一级分类id',
  `one_cat_desc` varchar(32) DEFAULT NULL COMMENT '一级分类描述',
  `two_cat` varchar(32) DEFAULT NULL COMMENT '二级分类ID',
  `two_cat_desc` varchar(32) DEFAULT NULL COMMENT '二级分类描述',
  `three_cat` varchar(32) DEFAULT NULL COMMENT '三级分类ID',
  `three_cat_desc` varchar(32) DEFAULT NULL COMMENT '三级分类描述',
  `four_cat` varchar(32) DEFAULT NULL COMMENT '四级分类ID',
  `four_cat_desc` varchar(32) DEFAULT NULL COMMENT '四级分类描述',
  PRIMARY KEY (`id`),
  KEY `ebs_category_n1` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_category
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_company
-- ----------------------------
DROP TABLE IF EXISTS `ebs_company`;
CREATE TABLE `ebs_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `company_id` varchar(100) NOT NULL COMMENT '公司主键ID',
  `company_address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `company_name` varchar(100) NOT NULL COMMENT '公司主键ID',
  `short_code` varchar(100) NOT NULL COMMENT '公司简码',
  `status` varchar(100) DEFAULT NULL COMMENT '状态，normal正常，disable，禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_company
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_customer
-- ----------------------------
DROP TABLE IF EXISTS `ebs_customer`;
CREATE TABLE `ebs_customer` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `org_id` varchar(20) DEFAULT NULL COMMENT '公司ID',
  `company_short_code` varchar(50) DEFAULT NULL COMMENT '公司短码',
  `customer_num` varchar(50) DEFAULT NULL COMMENT '客户编码',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `customer_know_as` varchar(50) DEFAULT NULL COMMENT '客户别名',
  `tax_reference` varchar(50) DEFAULT NULL COMMENT '纳税登记编号或个人身份证',
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `county` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `category1` varchar(50) DEFAULT NULL COMMENT '客户-性质',
  `category2` varchar(50) DEFAULT NULL COMMENT '客户分类-渠道',
  `category3` varchar(50) DEFAULT NULL COMMENT '客户分类-交易关系',
  `guimo` varchar(50) DEFAULT NULL COMMENT '客户养殖规模',
  `person_id` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL COMMENT 'A代表有效， I失效',
  PRIMARY KEY (`id`),
  KEY `ebs_customer_n1` (`customer_num`) USING BTREE,
  KEY `ebs_customer_n2` (`customer_num`,`company_short_code`,`person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_customer
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_customer_bill
-- ----------------------------
DROP TABLE IF EXISTS `ebs_customer_bill`;
CREATE TABLE `ebs_customer_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `begin_plan_payment` decimal(12,4) DEFAULT NULL COMMENT '期初待还款（元）',
  `end_plan_payment` decimal(12,4) DEFAULT NULL COMMENT '期末待还额（元）',
  `pay_quantity` int(11) DEFAULT NULL COMMENT '交易数(笔)   ',
  `buy_quantity` decimal(12,4) DEFAULT NULL COMMENT '购买量（KG）',
  `give_quantity` decimal(12,4) DEFAULT NULL COMMENT '赠包数（KG）\n            ',
  `must_payable` decimal(12,4) DEFAULT NULL COMMENT '应付货款（元）',
  `real_payable` decimal(12,4) DEFAULT NULL COMMENT '实付货款（元）',
  `balance_pay` decimal(12,4) DEFAULT NULL COMMENT '余额支付（元）\n            ',
  `card_pay` decimal(12,4) DEFAULT NULL COMMENT '银行卡支付（元）',
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `sync_detail_flag` varchar(10) DEFAULT NULL COMMENT '明细同步标识',
  PRIMARY KEY (`id`),
  KEY `ebs_customer_bill_n1` (`org_id`,`customer_no`,`year`,`month`) USING BTREE,
  KEY `ebs_customer_bill_n2` (`org_id`,`sync_detail_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户账单(按月汇总)';

-- ----------------------------
-- Records of ebs_customer_bill
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_customer_bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `ebs_customer_bill_detail`;
CREATE TABLE `ebs_customer_bill_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `customer_code` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `picking_date` datetime DEFAULT NULL COMMENT '提货日期',
  `bill_type` varchar(50) DEFAULT NULL COMMENT '账单类型(1.线上 online;2.线下 offline)',
  `docment_num` varchar(50) DEFAULT NULL COMMENT '单据号',
  `car_num` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `materiel_num` varchar(50) DEFAULT NULL COMMENT '物料编码',
  `materiel_name` varchar(50) DEFAULT NULL COMMENT '物料名称(含赠包信息)',
  `quantity` decimal(12,4) DEFAULT NULL COMMENT '购买量（KG）',
  `uom_code` varchar(10) DEFAULT NULL COMMENT '单位',
  `base_price` decimal(12,4) DEFAULT NULL COMMENT '厂价单价',
  `sale_price` decimal(12,4) DEFAULT NULL COMMENT '销售单价',
  `t_are_fold` decimal(12,4) DEFAULT NULL COMMENT '吨均现折',
  `account_receivable` decimal(12,4) DEFAULT NULL COMMENT '单个饲料应收款',
  `funds_received` decimal(12,4) DEFAULT NULL COMMENT '单个饲料实收款',
  `balance` decimal(12,4) DEFAULT NULL COMMENT '余额',
  `balance_pay` decimal(12,4) DEFAULT NULL COMMENT '订单余额支付金额',
  `card_pay` decimal(12,4) DEFAULT NULL COMMENT '订单银行卡支付金额',
  PRIMARY KEY (`id`),
  KEY `ebs_customer_bill_detail_n1` (`org_id`,`customer_code`,`year`,`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户账单明细';

-- ----------------------------
-- Records of ebs_customer_bill_detail
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_customer_item_temp
-- ----------------------------
DROP TABLE IF EXISTS `ebs_customer_item_temp`;
CREATE TABLE `ebs_customer_item_temp` (
  `customer_no` varchar(100) NOT NULL COMMENT '客户编码',
  `item_no` varchar(100) NOT NULL COMMENT '物料编码',
  `org_code` varchar(10) DEFAULT NULL COMMENT '公司简码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_customer_item_temp
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_material
-- ----------------------------
DROP TABLE IF EXISTS `ebs_material`;
CREATE TABLE `ebs_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(20) NOT NULL COMMENT '创建人ID',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `company_name` varchar(100) DEFAULT NULL,
  `org_id` varchar(100) DEFAULT NULL,
  `company_short_code` varchar(100) DEFAULT NULL COMMENT '公司简码',
  `organization_name` varchar(100) DEFAULT NULL COMMENT '库存组织名称',
  `organization_code` varchar(100) DEFAULT NULL COMMENT '库存组织简码',
  `item_number` varchar(100) DEFAULT NULL COMMENT '物料编码',
  `item_id` varchar(100) DEFAULT NULL,
  `item_desc` varchar(100) DEFAULT NULL,
  `guige` varchar(100) DEFAULT NULL COMMENT '规格',
  `secondary_uom` varchar(100) DEFAULT NULL COMMENT '辅助单位',
  `calculate` decimal(16,2) DEFAULT NULL,
  `primary_uom` varchar(100) DEFAULT NULL COMMENT '主单位',
  `pp_code` varchar(100) DEFAULT NULL COMMENT '品牌code',
  `pp_des` varchar(100) DEFAULT NULL COMMENT '品牌描述',
  `breed_code` varchar(100) DEFAULT NULL COMMENT '养殖品种代码',
  `breed_des` varchar(100) DEFAULT NULL COMMENT '养殖品种描述',
  `pm_code` varchar(100) DEFAULT NULL COMMENT '品名代码',
  `pm_des` varchar(100) DEFAULT NULL COMMENT '品名描述',
  `one_cat` varchar(100) DEFAULT NULL COMMENT '一级分类',
  `one_cat_des` varchar(100) DEFAULT NULL,
  `two_cat` varchar(100) DEFAULT NULL,
  `two_cat_des` varchar(100) DEFAULT NULL,
  `three_cat` varchar(100) DEFAULT NULL,
  `three_cat_des` varchar(100) DEFAULT NULL,
  `four_cat` varchar(100) DEFAULT NULL,
  `four_cat_des` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL COMMENT '物料状态  Active Inactive OPM',
  `base_price` decimal(12,4) DEFAULT NULL COMMENT '基础价格及是出厂单价',
  `organization_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ebs_material_n1` (`item_number`) USING BTREE,
  KEY `ebs_material_n2` (`item_id`,`org_id`) USING BTREE,
  KEY `ebs_material_n3` (`item_number`,`organization_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_material
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order`;
CREATE TABLE `ebs_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_order_no` varchar(50) DEFAULT NULL,
  `sale_order_id` varchar(50) DEFAULT NULL,
  `ebs_order_no` varchar(50) DEFAULT NULL,
  `ebs_order_id` varchar(50) DEFAULT NULL,
  `order_type` varchar(50) DEFAULT NULL COMMENT '订单类型\n            1.直接订单，系统自动算\n            2.需审核订单，由后勤计算价格',
  `order_create_status` varchar(50) DEFAULT NULL COMMENT '1.未提交\n            2.处理中\n            3.业务处理失败\n            4.业务处理成功\n            5.调用EBS接口网络不通 （失败）\n            6.等待EBS处理结果超时（成功）',
  `order_pay_status` varchar(50) DEFAULT NULL COMMENT '1.未提交\n            2.处理中\n            3.业务处理失败\n            4.业务处理成功\n            5.调用EBS接口网络不通 （失败）\n            6.等待EBS处理结果超时（成功）',
  `order_cancel_status` varchar(50) DEFAULT NULL COMMENT '1.未提交\n            2.处理中\n            3.业务处理失败\n            4.业务处理成功\n            5.调用EBS接口网络不通 （失败）\n            6.等待EBS处理结果超时（成功）',
  `order_update_status` varchar(50) DEFAULT NULL COMMENT '1.未提交\n            2.处理中\n            3.业务处理失败\n            4.业务处理成功\n            5.调用EBS接口网络不通 （失败）\n            6.等待EBS处理结果超时（成功）',
  `total_amount` decimal(12,4) DEFAULT NULL,
  `ebs_pay_amount` decimal(12,4) DEFAULT NULL,
  `card_pay_amount` decimal(12,4) DEFAULT NULL,
  `ebs_org_id` varchar(50) DEFAULT NULL,
  `ebs_customer_no` varchar(50) DEFAULT NULL,
  `bank_pay_no` varchar(255) DEFAULT NULL,
  `pay_type` varchar(50) DEFAULT NULL COMMENT '支付方式\n            1.余额支付\n            2.银行卡支付\n            3.混合支付',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_detail`;
CREATE TABLE `ebs_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `order_create_date` datetime DEFAULT NULL COMMENT '订单创建日期',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单中间件ID',
  `ebs_order_status` varchar(50) DEFAULT NULL COMMENT '1	已关闭	出库业务完成\n            2	已输入	录入状态，还未支付\n            3	已取消	取消\n            4	已登记	已收款待发货\n            5.             订单创建失败   没有创建成功的订单\n            6.              支付失败   \n            7             商城调EBS 取消失败\n            ',
  `car_in_time` datetime DEFAULT NULL COMMENT '进厂时间',
  `close_order_time` datetime DEFAULT NULL COMMENT '订单关闭时间',
  `invoice_order_time` datetime DEFAULT NULL COMMENT '订单开票时间',
  `cancel_order_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  `car_out_time` datetime DEFAULT NULL COMMENT '出厂时间',
  `car_no` varchar(50) DEFAULT NULL COMMENT 'EBS车牌号',
  `in_car_weight` decimal(18,4) DEFAULT NULL COMMENT '进厂车辆重量',
  `out_car_weight` decimal(18,4) DEFAULT NULL COMMENT '出厂车辆重量',
  `stock_can_use` tinyint(1) DEFAULT NULL COMMENT '是否库存不足',
  `plan_transport_time` datetime DEFAULT NULL COMMENT '计划拉货日期',
  `weight_num` varchar(50) DEFAULT NULL COMMENT '磅单编号',
  `material_changed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_from_ebs
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_from_ebs`;
CREATE TABLE `ebs_order_from_ebs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间件订单ID',
  `ebs_order_josn` text COMMENT 'EBS订单JSON',
  `plan_total_amount` decimal(12,4) DEFAULT NULL COMMENT '订单总额（厂价）',
  `discount_amount` decimal(12,4) DEFAULT NULL COMMENT '折扣金额',
  `real_pay_amount` decimal(12,4) DEFAULT NULL COMMENT '实际付款金额',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_from_ebs
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_from_ebs_items
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_from_ebs_items`;
CREATE TABLE `ebs_order_from_ebs_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间订单ID',
  `order_from_ebs_id` bigint(20) DEFAULT NULL COMMENT 'ebs订单详情ID',
  `item_type` varchar(50) DEFAULT NULL COMMENT '明细行类型\n            1.正常商品\n            2.赠品',
  `materiel_no` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `quantity` decimal(12,2) DEFAULT NULL COMMENT '数量',
  `factor` decimal(8,2) DEFAULT NULL COMMENT '转换率',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `second_unit` varchar(20) DEFAULT NULL COMMENT '副单位',
  `plan_amount` decimal(12,4) DEFAULT NULL COMMENT '厂价',
  `discount_amount` decimal(12,4) DEFAULT NULL COMMENT '折扣价',
  `real_pay_amount` decimal(12,4) DEFAULT NULL COMMENT '实际付款价',
  `organization_code` varchar(50) DEFAULT NULL COMMENT '库存组织编号',
  `ebs_order_item_json` varchar(4000) DEFAULT NULL COMMENT '订单明细JSON',
  `org_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_from_ebs_items
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_from_sale
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_from_sale`;
CREATE TABLE `ebs_order_from_sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间件订单ID',
  `sale_order_josn` text COMMENT 'EBS订单JSON',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `plan_pickup_date` date DEFAULT NULL COMMENT '计划拉货时间',
  `shop_id` varchar(50) DEFAULT NULL COMMENT '商场ID',
  `car_no` varchar(50) DEFAULT NULL COMMENT '车牌号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_from_sale
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_from_sale_items
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_from_sale_items`;
CREATE TABLE `ebs_order_from_sale_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间订单ID',
  `order_from_sale_id` bigint(20) DEFAULT NULL COMMENT '中间商城订单详情ID',
  `materiel_no` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `quantity` decimal(12,2) DEFAULT NULL COMMENT '数量',
  `factor` decimal(8,2) DEFAULT NULL COMMENT '转换率',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `second_unit` varchar(20) DEFAULT NULL COMMENT '副单位',
  `organization_code` varchar(50) DEFAULT NULL COMMENT '库存组织编号',
  `sale_order_item_json` varchar(4000) DEFAULT NULL COMMENT '订单明细JSON',
  `org_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_from_sale_items
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_log
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_log`;
CREATE TABLE `ebs_order_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `sale_order_id` varchar(50) DEFAULT NULL,
  `log_type` varchar(50) DEFAULT NULL COMMENT '日志类型，\n            1.调用EBS创建订单\n            2.调用EBS支付订单\n            3.抵用EBS取消订单\n            4.EBS更新订单状态\n            5.通知商场-创建订单结果\n            6.通知商场-支付结果\n            7.通知商场-取消订单结果\n            8.通知商场-更新订单状态结果',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `error_msg` varchar(4000) DEFAULT NULL COMMENT '商场原因',
  `msg_json` text COMMENT '发送报文JSON',
  `fail_count` int(11) DEFAULT NULL COMMENT '失败次数',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `operation_result` tinyint(4) DEFAULT NULL COMMENT '成功/失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_log
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_low_stocks
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_low_stocks`;
CREATE TABLE `ebs_order_low_stocks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间订单ID',
  `order_detail_id` bigint(20) DEFAULT NULL COMMENT '中间订单详情ID',
  `materiel_no` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `stock_quantity` decimal(12,2) DEFAULT NULL COMMENT '库存数量',
  `quantity` decimal(12,2) DEFAULT NULL COMMENT '当前使用数量',
  `factor` decimal(8,2) DEFAULT NULL COMMENT '转换率',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `second_unit` varchar(20) DEFAULT NULL COMMENT '副单位',
  `organization_code` varchar(50) DEFAULT NULL COMMENT '库存组织编号',
  `org_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单库存不足物料';

-- ----------------------------
-- Records of ebs_order_low_stocks
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_order_payment
-- ----------------------------
DROP TABLE IF EXISTS `ebs_order_payment`;
CREATE TABLE `ebs_order_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间件订单ID',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `pay_type` varchar(50) DEFAULT NULL COMMENT '支付方式，余额支付/银行卡支付',
  `total_pay_amount` decimal(12,4) DEFAULT NULL COMMENT '支付总金额',
  `balance_amount` decimal(12,4) DEFAULT NULL COMMENT '余额支付时填写',
  `discount_amount` decimal(12,4) DEFAULT NULL COMMENT '本次使用折扣金额【余额支付】方式时，=MIN(EBS在客户可用余额信息接口 “保证金”金额,订单金额)；',
  `currency` varchar(50) DEFAULT NULL COMMENT '币钟',
  `temp_owe` tinyint(1) DEFAULT NULL COMMENT '是否临欠客户',
  `pay_date` datetime DEFAULT NULL COMMENT '支付日期',
  `moneyfeed_pay_no` varchar(50) DEFAULT NULL COMMENT '商城支付流水号,银行卡支付时使用',
  `acc_no` varchar(50) DEFAULT NULL COMMENT '银行流水号（银行卡支付）',
  `ebs_real_account` varchar(50) DEFAULT NULL COMMENT 'EBS最终收款账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebs_order_payment
-- ----------------------------

-- ----------------------------
-- Table structure for ebs_recharge
-- ----------------------------
DROP TABLE IF EXISTS `ebs_recharge`;
CREATE TABLE `ebs_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '中间件订单ID，当是订单充值的时使用',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_status` varchar(50) DEFAULT NULL,
  `pay_type` varchar(20) DEFAULT NULL COMMENT '支付方式，订单充值/账户充值',
  `total_pay_amount` decimal(12,4) DEFAULT NULL COMMENT '支付总金额',
  `currency` varchar(50) DEFAULT NULL COMMENT '币钟',
  `temp_owe` tinyint(1) DEFAULT NULL COMMENT '是否临欠客户',
  `pay_date` datetime DEFAULT NULL COMMENT '支付日期',
  `moneyfeed_pay_no` varchar(50) DEFAULT NULL COMMENT '商城支付流水号,银行卡支付时使用',
  `acc_no` varchar(50) DEFAULT NULL COMMENT '银行流水号（银行卡支付）',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `pay_status` varchar(50) DEFAULT NULL COMMENT '支付状态',
  `org_id` varchar(50) DEFAULT NULL COMMENT '组织id',
  `ebs_real_account` varchar(50) DEFAULT NULL COMMENT 'EBS最终收款账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值表';

-- ----------------------------
-- Records of ebs_recharge
-- ----------------------------
