/*
Navicat MySQL Data Transfer

Source Server         : tule
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : tule

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-05-11 22:01:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tule_airline_companey
-- ----------------------------
DROP TABLE IF EXISTS `tule_airline_companey`;
CREATE TABLE `tule_airline_companey` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '航空公司编号',
  `name` varchar(10) DEFAULT NULL COMMENT '航空公司名称',
  `safety` varchar(10) DEFAULT NULL COMMENT '安全率',
  `logo` varchar(100) NOT NULL COMMENT '公司logo路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_airline_companey
-- ----------------------------

-- ----------------------------
-- Table structure for tule_air_body
-- ----------------------------
DROP TABLE IF EXISTS `tule_air_body`;
CREATE TABLE `tule_air_body` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '飞机体型编号',
  `explain` varchar(20) DEFAULT NULL COMMENT '飞机体型描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_air_body
-- ----------------------------

-- ----------------------------
-- Table structure for tule_air_model
-- ----------------------------
DROP TABLE IF EXISTS `tule_air_model`;
CREATE TABLE `tule_air_model` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL COMMENT '机型名称',
  `body_id` int(10) DEFAULT NULL COMMENT '飞机体型编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tule_air_model
-- ----------------------------

-- ----------------------------
-- Table structure for tule_air_station
-- ----------------------------
DROP TABLE IF EXISTS `tule_air_station`;
CREATE TABLE `tule_air_station` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL COMMENT '机场名称',
  `city_id` int(10) DEFAULT NULL COMMENT '所属城市编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tule_air_station
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car
-- ----------------------------
DROP TABLE IF EXISTS `tule_car`;
CREATE TABLE `tule_car` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `car_body_id` int(10) DEFAULT NULL COMMENT '汽车体型编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_car_arrange`;
CREATE TABLE `tule_car_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '汽车安排编号',
  `start_time` time DEFAULT NULL COMMENT '发车时间',
  `start_id` int(10) DEFAULT NULL COMMENT '发车站编号',
  `end_id` int(10) DEFAULT NULL,
  `car_id` int(10) DEFAULT NULL COMMENT '汽车编号',
  `price` int(5) DEFAULT NULL COMMENT '票价',
  `car_trip_id` int(10) DEFAULT NULL COMMENT '汽车行程编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car_arrange
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car_body
-- ----------------------------
DROP TABLE IF EXISTS `tule_car_body`;
CREATE TABLE `tule_car_body` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '汽车体型编号',
  `explain` varchar(10) DEFAULT NULL COMMENT '体型描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car_body
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car_date_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_car_date_arrange`;
CREATE TABLE `tule_car_date_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `car_arrange_id` int(10) DEFAULT NULL COMMENT '汽车安排编号',
  `explain` text COMMENT '剩余票数及座位分布情况',
  `day` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car_date_arrange
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car_station
-- ----------------------------
DROP TABLE IF EXISTS `tule_car_station`;
CREATE TABLE `tule_car_station` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '汽车站编号',
  `name` varchar(10) DEFAULT NULL COMMENT '汽车站名称',
  `city_id` int(10) DEFAULT NULL COMMENT '城市编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car_station
-- ----------------------------

-- ----------------------------
-- Table structure for tule_car_trip
-- ----------------------------
DROP TABLE IF EXISTS `tule_car_trip`;
CREATE TABLE `tule_car_trip` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `explain` varchar(200) DEFAULT NULL COMMENT '行程描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_car_trip
-- ----------------------------

-- ----------------------------
-- Table structure for tule_citizen
-- ----------------------------
DROP TABLE IF EXISTS `tule_citizen`;
CREATE TABLE `tule_citizen` (
  `id` int(10) NOT NULL COMMENT '公民编号',
  `name` varchar(15) DEFAULT NULL COMMENT '公民姓名',
  `personal_id` varchar(20) DEFAULT NULL COMMENT '公民证件号',
  `paper_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tule_citizen
-- ----------------------------
INSERT INTO `tule_citizen` VALUES ('1', '李元浩', '321081199710252718', '二代身份证', '男');
INSERT INTO `tule_citizen` VALUES ('2', 'lyh', '321081199710252719', '二代身份证', '男');

-- ----------------------------
-- Table structure for tule_city
-- ----------------------------
DROP TABLE IF EXISTS `tule_city`;
CREATE TABLE `tule_city` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  `name` varchar(20) DEFAULT NULL COMMENT '城市名称',
  `pinyin` varchar(20) DEFAULT NULL COMMENT '城市拼音',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_city
-- ----------------------------
INSERT INTO `tule_city` VALUES ('6', '徐州', 'xuzhou');
INSERT INTO `tule_city` VALUES ('7', '宿州', 'suzhou');
INSERT INTO `tule_city` VALUES ('8', '南京', 'nanjing');
INSERT INTO `tule_city` VALUES ('9', '阿克苏', 'akesu');
INSERT INTO `tule_city` VALUES ('10', '安康', 'ankang');
INSERT INTO `tule_city` VALUES ('11', '安陆', 'anlu');
INSERT INTO `tule_city` VALUES ('12', '安庆', 'anqin');
INSERT INTO `tule_city` VALUES ('13', '鞍山', 'anshan');
INSERT INTO `tule_city` VALUES ('14', '安顺', 'anshun');
INSERT INTO `tule_city` VALUES ('15', '安阳', 'anyang');
INSERT INTO `tule_city` VALUES ('16', '白城', 'baicheng');
INSERT INTO `tule_city` VALUES ('17', '保定', 'baoding');
INSERT INTO `tule_city` VALUES ('18', '宝鸡', 'baoji');
INSERT INTO `tule_city` VALUES ('19', '长春', 'changchun');
INSERT INTO `tule_city` VALUES ('20', '长沙', 'changsha');
INSERT INTO `tule_city` VALUES ('21', '大理', 'dali');
INSERT INTO `tule_city` VALUES ('22', '大连', 'dalian');
INSERT INTO `tule_city` VALUES ('23', '大庆', 'daqing');
INSERT INTO `tule_city` VALUES ('24', '峨眉', 'emei');
INSERT INTO `tule_city` VALUES ('25', '恩施', 'enshi');
INSERT INTO `tule_city` VALUES ('26', '鄂州', 'ezhou');
INSERT INTO `tule_city` VALUES ('27', '佛山', 'foshan');
INSERT INTO `tule_city` VALUES ('28', '福安', 'fuan');
INSERT INTO `tule_city` VALUES ('29', '福州', 'fuzhou');
INSERT INTO `tule_city` VALUES ('30', '广州', 'guangzhou');
INSERT INTO `tule_city` VALUES ('31', '桂林', 'guilin');
INSERT INTO `tule_city` VALUES ('32', '贵阳', 'guiyang');
INSERT INTO `tule_city` VALUES ('33', '海口', 'haikou');
INSERT INTO `tule_city` VALUES ('34', '杭州', 'hangzhou');
INSERT INTO `tule_city` VALUES ('35', '汉中', 'hanzhong');
INSERT INTO `tule_city` VALUES ('36', '吉林', 'jilin');
INSERT INTO `tule_city` VALUES ('37', '荆州', 'jinzhou');
INSERT INTO `tule_city` VALUES ('38', '金华', 'jinhua');
INSERT INTO `tule_city` VALUES ('39', '开封', 'kaifeng');
INSERT INTO `tule_city` VALUES ('40', '昆明', 'kunming');
INSERT INTO `tule_city` VALUES ('41', '昆山', 'kushan');
INSERT INTO `tule_city` VALUES ('42', '兰州', 'lanzhou');
INSERT INTO `tule_city` VALUES ('43', '连云港', 'lianyungang');
INSERT INTO `tule_city` VALUES ('44', '洛阳', 'luoyang');
INSERT INTO `tule_city` VALUES ('45', '马鞍山', 'maanshan');
INSERT INTO `tule_city` VALUES ('46', '漠河', 'mohe');
INSERT INTO `tule_city` VALUES ('47', '牡丹江', 'mudanjiang');
INSERT INTO `tule_city` VALUES ('48', '南昌', 'nanchang');
INSERT INTO `tule_city` VALUES ('49', '南通', 'nantong');
INSERT INTO `tule_city` VALUES ('50', '攀枝花', 'panzhihua');
INSERT INTO `tule_city` VALUES ('51', '平顶山', 'pingdingshan');
INSERT INTO `tule_city` VALUES ('52', '萍乡', 'pingxiang');
INSERT INTO `tule_city` VALUES ('53', '青岛', 'qingdao');
INSERT INTO `tule_city` VALUES ('54', '泉州', 'quanzhou');
INSERT INTO `tule_city` VALUES ('55', '秦皇岛', 'qinhuangdao');
INSERT INTO `tule_city` VALUES ('56', '日照', 'rizhao');
INSERT INTO `tule_city` VALUES ('57', '如皋', 'rugao');
INSERT INTO `tule_city` VALUES ('58', '瑞安', 'ruian');
INSERT INTO `tule_city` VALUES ('59', '上海', 'shanghai');
INSERT INTO `tule_city` VALUES ('60', '石家庄', 'shijiazhuang');
INSERT INTO `tule_city` VALUES ('61', '深圳', 'shenzhen');
INSERT INTO `tule_city` VALUES ('62', '泰安', 'taian');
INSERT INTO `tule_city` VALUES ('63', '泰州', 'taizhou');
INSERT INTO `tule_city` VALUES ('64', '天津', 'tianjin');
INSERT INTO `tule_city` VALUES ('65', '温州', 'wenzhou');
INSERT INTO `tule_city` VALUES ('66', '无锡', 'wuxi');
INSERT INTO `tule_city` VALUES ('67', '武汉', 'wuhan');
INSERT INTO `tule_city` VALUES ('68', '西安', 'xian');
INSERT INTO `tule_city` VALUES ('69', '厦门', 'xiamen');
INSERT INTO `tule_city` VALUES ('71', '延安', 'yanan');
INSERT INTO `tule_city` VALUES ('72', '盐城', 'yancheng');
INSERT INTO `tule_city` VALUES ('73', '银川', 'yinchuan');
INSERT INTO `tule_city` VALUES ('74', '郑州·', 'zhenzhou');
INSERT INTO `tule_city` VALUES ('75', '中山', 'zhogshan');
INSERT INTO `tule_city` VALUES ('76', '珠海', 'zhuhai');

-- ----------------------------
-- Table structure for tule_flight
-- ----------------------------
DROP TABLE IF EXISTS `tule_flight`;
CREATE TABLE `tule_flight` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '航班编号',
  `air_model_id` int(10) NOT NULL COMMENT '机型编号',
  `air_company_id` int(10) NOT NULL COMMENT '航空公司编号',
  `on_time_performance` varchar(10) DEFAULT NULL COMMENT '准点率',
  `age` int(5) DEFAULT NULL COMMENT '机龄',
  `name` varchar(10) DEFAULT NULL COMMENT '航班名称',
  `explain` varchar(255) DEFAULT NULL COMMENT '舱段描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_flight
-- ----------------------------

-- ----------------------------
-- Table structure for tule_flight_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_flight_arrange`;
CREATE TABLE `tule_flight_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '航班安排编号',
  `start_id` int(10) DEFAULT NULL COMMENT '始发站编号',
  `end_id` int(10) DEFAULT NULL COMMENT '到达站编号',
  `arrive_time` time DEFAULT NULL COMMENT '到达时间',
  `total_time` varchar(50) DEFAULT NULL COMMENT '总时间',
  `explain` varchar(200) DEFAULT NULL COMMENT '航班描述',
  `flight_trip_id` int(10) DEFAULT NULL COMMENT '航班行程编号',
  `flight_id` int(10) DEFAULT NULL COMMENT '航班编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_flight_arrange
-- ----------------------------

-- ----------------------------
-- Table structure for tule_flight_date_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_flight_date_arrange`;
CREATE TABLE `tule_flight_date_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flight_arrange_id` int(10) DEFAULT NULL COMMENT '航班安排编号',
  `explain` varchar(255) DEFAULT NULL COMMENT '航班-日期安排描述',
  `date` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_flight_date_arrange
-- ----------------------------

-- ----------------------------
-- Table structure for tule_flight_trip
-- ----------------------------
DROP TABLE IF EXISTS `tule_flight_trip`;
CREATE TABLE `tule_flight_trip` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '航班行程编号',
  `trip_arrange` varchar(255) DEFAULT NULL COMMENT '行程安排',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_flight_trip
-- ----------------------------

-- ----------------------------
-- Table structure for tule_insurance
-- ----------------------------
DROP TABLE IF EXISTS `tule_insurance`;
CREATE TABLE `tule_insurance` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '保险编号',
  `name` varchar(15) DEFAULT NULL COMMENT '保险名称',
  `price` int(5) DEFAULT NULL COMMENT '保险价格',
  `cmp_id` int(11) DEFAULT NULL COMMENT '保险公司编号',
  `type` varchar(10) DEFAULT NULL COMMENT '保险类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_insurance
-- ----------------------------
INSERT INTO `tule_insurance` VALUES ('1', '火车意外伤害', '10', '1', '火车');

-- ----------------------------
-- Table structure for tule_insurance_cmp
-- ----------------------------
DROP TABLE IF EXISTS `tule_insurance_cmp`;
CREATE TABLE `tule_insurance_cmp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL COMMENT '保险公司名称',
  `service_phone` varchar(15) DEFAULT NULL COMMENT '客服电话',
  `complaint_phone` varchar(15) DEFAULT NULL COMMENT '投诉电话',
  `explain` longtext COMMENT '订单说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_insurance_cmp
-- ----------------------------
INSERT INTO `tule_insurance_cmp` VALUES ('1', '众安在线财产保险股份有限公司', '10086', '10000', '一、保险产品说明：\r\n1、保险责任：航空意外伤害320万元/航段，指被保险人以乘客身份乘坐电子保单载明的客运飞机班次，并遵守承运人关于安全乘坐的规定，自持有效机票检票并进入所乘客运飞机客舱时起至抵达机票载明的终点离开所乘客运飞机客舱的期间内遭受意外伤害所导致的保险责任\r\n2、保险费：30元/航段\r\n3、保险期间：指被保险人进入所乘客运飞机客舱时起至抵达机票载明的终点离开所乘客运飞机客舱\r\n4、销售限制：购买上限1份，计划起飞时间之前退票可退保\r\n5、电子保单：可在携程对应的订单中保险详情页或保险公司网站上查询和下载，数据电文是合法的合同表现形式，电子保单与纸质保单具有同等法律效力，请妥善保存\r\n6、报销凭证：提供保险电子发票或定额发票作为报销凭证\r\n7、本产品由众安在线财产保险股份有限公司承保，保险产品的销售服务方为深圳众诚泰保险经纪有限公司重庆分公司\r\n二、理赔说明：\r\n航意理赔请咨询保险公司客服热线：10109955\r\n三、重要说明\r\n1、保单投诉渠道热线：021-80399188\r\n2、理赔款支付方式：银行转账\r\n3、保险费及退保金支付方式：1）信用卡；2）借记卡；3）银联手机支付；4）支付宝；5）财付通等\r\n4、告知义务及违法义务可能产生的后果：\r\n订立保险合同，保险人就保险标的或者被保险人的有关情况提出询问的，投保人应当如实告知。投保人故意或者因重大过失未履行前款规定的如实告知义务，足以影响保险人决定是否同意承保或者提高保险费率的，保险人有权解除合同。具体可详见条款\r\n5、携程严格遵守现行的关于个人信息、数据及隐私保护的法律法规，采取充分的技术手段和制度管理，保护您提供的个人信息、数据和隐私不受到非法的泄露或披露给未获授权的第三方\r\n6、产品销售范围：全国区域内销售\r\n7、其它未尽事宜请见具体保险条款\r\n四、除外责任\r\n被保险人因下列原因而导致或在下列期间发生身故或伤残的，保险人不承担给付保险金责任：\r\n1、投保人的故意或重大过失行为；\r\n2、被保险人故意自伤或自杀；\r\n3、被保险人故意犯罪、拒捕、挑衅或故意行为而导致的打斗、被袭击或被谋杀；\r\n4、被保险人因妊娠（含宫外孕）、流产（含人工流产）、分娩（含剖宫产） 而造成的伤害；\r\n5、被保险人接受包括美容、整容、整形手术在内的任何医疗行为而造成的伤害；\r\n6、被保险人因药物过敏或未遵医嘱服用、涂用、注射药物而造成的伤害；\r\n7、被保险人受酒精、毒品、管制药物的影响；\r\n8、疾病，包括但不限于高原反应、中暑；\r\n9、猝死；\r\n10、非因意外伤害导致的细菌或病毒感染；\r\n11、任何生物、化学、原子能武器，原子能或核能装置所造成的爆炸、灼伤、 污染或辐射；\r\n12、战争、军事行动、武装叛乱或暴乱、恐怖袭击；\r\n13、被保险人乘坐的飞机不属于保险合同所载行程的民航客机；\r\n14、被保险人乘坐民航客机时未持有有效机票；\r\n15、被保险人通过安全检查后又离开候机区，在候机区外遭受意外伤害；\r\n16、被保险人从事违法、犯罪活动行为或被依法拘留、服刑、在逃期间；\r\n17、被保险人存在精神和行为障碍（以世界卫生组织颁布的《疾病和有关健 康问题的国际统计分类（ICD-10）》为准）期间。\r\n五、产品备案号\r\n(众安在线)(备-普通意外保险)【2017】(主)011号');

-- ----------------------------
-- Table structure for tule_insurance_order
-- ----------------------------
DROP TABLE IF EXISTS `tule_insurance_order`;
CREATE TABLE `tule_insurance_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `start_time` date DEFAULT NULL COMMENT '生效日期',
  `end_time` date DEFAULT NULL COMMENT '结束日期',
  `insurance_cmp_id` int(10) DEFAULT NULL COMMENT '保险公司编号',
  `price` int(5) DEFAULT NULL COMMENT '价格',
  `order_id` int(10) DEFAULT NULL COMMENT '被保订单编号',
  `passenger_id` int(10) DEFAULT NULL COMMENT '被保乘客编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_insurance_order
-- ----------------------------

-- ----------------------------
-- Table structure for tule_key
-- ----------------------------
DROP TABLE IF EXISTS `tule_key`;
CREATE TABLE `tule_key` (
  `id` int(10) NOT NULL COMMENT '主键表主键',
  `table_name` varchar(15) DEFAULT NULL COMMENT '表名称',
  `start_key` int(10) DEFAULT NULL COMMENT '主键起始位置',
  `add_num` int(10) DEFAULT NULL COMMENT '自增间隔值',
  `key_num` int(10) DEFAULT NULL COMMENT '当前主键值',
  `state` varchar(5) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tule_key
-- ----------------------------
INSERT INTO `tule_key` VALUES ('1', 'tule_passenger', '10000', '1', '10000', '1');

-- ----------------------------
-- Table structure for tule_manager
-- ----------------------------
DROP TABLE IF EXISTS `tule_manager`;
CREATE TABLE `tule_manager` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(15) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(32) DEFAULT NULL COMMENT '管理员密码',
  `salt` varchar(36) DEFAULT NULL COMMENT '管理员盐值',
  `phone` varchar(15) DEFAULT NULL COMMENT '管理员的登录账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tule_manager
-- ----------------------------
INSERT INTO `tule_manager` VALUES ('1', null, 'A98BF0C49CD21DED22C9CA612F3B222A', 'C207BB26-F12B-4082-9F95-04FFB10F5738', '10001');
INSERT INTO `tule_manager` VALUES ('2', null, '8E87439CFBD6EB89E267DB78924F7E63', '0C4FF99F-3620-46A1-8CFB-9BD1BED5FCF8', '10002');

-- ----------------------------
-- Table structure for tule_order
-- ----------------------------
DROP TABLE IF EXISTS `tule_order`;
CREATE TABLE `tule_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` int(10) DEFAULT NULL COMMENT '用户编号',
  `status` varchar(4) DEFAULT NULL COMMENT '订单状态 0:订单已删除1:订单成交2:订单取消3:订单退票4:未付款',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `traffic_date_arrange_id` int(10) DEFAULT NULL COMMENT '交通-日期安排编号',
  `totle_price` varchar(100) DEFAULT NULL COMMENT '订单总价及金额明细',
  `passenger_id` varchar(20) DEFAULT NULL COMMENT '乘客编号',
  `type` varchar(5) DEFAULT NULL COMMENT '订单类型',
  `oexplain` varchar(200) DEFAULT NULL COMMENT '订单说明',
  `reservation` varchar(10) DEFAULT NULL COMMENT '预定方式',
  `return_price` int(10) DEFAULT '0' COMMENT '退换金额',
  `distribution_address` varchar(255) DEFAULT NULL COMMENT '配送地址',
  `contact_phone` varchar(15) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系人手机号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_close` datetime NOT NULL COMMENT '订单的超时时间',
  `state` varchar(5) DEFAULT NULL COMMENT '订单的删除情况',
  `comment_status` varchar(4) DEFAULT NULL COMMENT '订单评价状态',
  `comment` varchar(100) DEFAULT NULL COMMENT '订单描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_order
-- ----------------------------
INSERT INTO `tule_order` VALUES ('206', '21', '2', '2019-05-11 19:46:50', '1', '车票:200', '&81&', '火车票', '硬卧,0:8:2', '电脑预定', '0', null, '123', '2019-05-11 19:46:50', '2019-05-11 20:06:50', '0', null, null);
INSERT INTO `tule_order` VALUES ('207', '21', '2', '2019-05-11 19:47:42', '1', '车票:200', '&81&', '火车票', '硬卧,0:8:2', '电脑预定', '0', null, '123', '2019-05-11 19:47:42', '2019-05-11 20:07:42', '0', null, null);
INSERT INTO `tule_order` VALUES ('208', '21', '2', '2019-05-11 19:48:42', '1', '车票:200', '&81&', '火车票', '硬卧,0:8:2', '电脑预定', '0', null, '123', '2019-05-11 19:48:42', '2019-05-11 20:08:42', '0', null, null);
INSERT INTO `tule_order` VALUES ('209', '21', '2', '2019-05-11 19:49:01', '28', '车票:200', '&81&', '火车票', '硬卧,0:0:0', '电脑预定', '0', null, '123', '2019-05-11 19:49:01', '2019-05-11 20:09:01', '0', null, null);
INSERT INTO `tule_order` VALUES ('210', '21', '2', '2019-05-11 21:55:06', '30', '车票:200', '&81&', '火车票', '硬卧,0:0:0', '电脑预定', '0', null, '123', '2019-05-11 21:55:06', '2019-05-11 22:15:06', '1', null, null);
INSERT INTO `tule_order` VALUES ('211', '21', '2', '2019-05-11 21:55:27', '28', '车票:200', '&81&', '火车票', '硬卧,0:0:0', '电脑预定', '0', null, '123', '2019-05-11 21:55:27', '2019-05-11 22:15:27', '1', null, null);

-- ----------------------------
-- Table structure for tule_passenger
-- ----------------------------
DROP TABLE IF EXISTS `tule_passenger`;
CREATE TABLE `tule_passenger` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '乘客编号',
  `name` varchar(15) DEFAULT NULL COMMENT '乘客姓名',
  `personal_id` varchar(40) DEFAULT NULL COMMENT '乘客证件号',
  `type` varchar(20) DEFAULT NULL COMMENT '乘客类型',
  `user_id` int(10) DEFAULT NULL COMMENT '所属用户编号',
  `state` varchar(5) DEFAULT '1' COMMENT '乘客信息状态0：删除 1：有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_passenger
-- ----------------------------
INSERT INTO `tule_passenger` VALUES ('00000000081', '李元浩', '321081199710252718', '二代身份证', '21', '1');
INSERT INTO `tule_passenger` VALUES ('00000000082', 'lyh', '321081199710252719', '二代身份证', '21', '1');

-- ----------------------------
-- Table structure for tule_train
-- ----------------------------
DROP TABLE IF EXISTS `tule_train`;
CREATE TABLE `tule_train` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '火车编号',
  `name` varchar(10) DEFAULT NULL COMMENT '火车名称',
  `deptno` int(10) DEFAULT NULL COMMENT '所属部门编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train
-- ----------------------------
INSERT INTO `tule_train` VALUES ('1', 'K1234', '1');
INSERT INTO `tule_train` VALUES ('2', 'D52', '2');
INSERT INTO `tule_train` VALUES ('5', 'G456', '1');
INSERT INTO `tule_train` VALUES ('6', 'G345', '1');
INSERT INTO `tule_train` VALUES ('7', 'Z7890', '1');
INSERT INTO `tule_train` VALUES ('8', 'Z890', '1');
INSERT INTO `tule_train` VALUES ('9', 'G890', '1');

-- ----------------------------
-- Table structure for tule_train_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_train_arrange`;
CREATE TABLE `tule_train_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `start_id` int(10) DEFAULT NULL COMMENT '起始站编号',
  `end_id` int(10) DEFAULT NULL COMMENT '终点站编号',
  `train_id` int(10) DEFAULT NULL COMMENT '火车编号',
  `train_trip_id` int(10) DEFAULT NULL COMMENT '火车行程编号',
  `start_time` time DEFAULT NULL COMMENT '开车时间',
  `end_time` time DEFAULT NULL COMMENT '到达时间',
  `t_time` varchar(10) DEFAULT NULL COMMENT '总时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train_arrange
-- ----------------------------
INSERT INTO `tule_train_arrange` VALUES ('1', '1', '5', '1', '1', '10:10:00', '12:20:00', '2小时');
INSERT INTO `tule_train_arrange` VALUES ('2', '2', '6', '2', '2', '10:10:00', '12:20:00', '2小时');
INSERT INTO `tule_train_arrange` VALUES ('3', '1', '3', '1', '1', '10:10:00', '11:10:00', '1小时');
INSERT INTO `tule_train_arrange` VALUES ('4', '3', '5', '1', '1', '11:20:00', '12:20:00', '1小时');
INSERT INTO `tule_train_arrange` VALUES ('5', '2', '4', '2', '2', '10:10:00', '11:10:00', '1小时');
INSERT INTO `tule_train_arrange` VALUES ('6', '4', '6', '2', '2', '11:20:00', '12:20:00', '1小时');
INSERT INTO `tule_train_arrange` VALUES ('29', '1', '5', '1', '9', '10:00:00', '12:00:00', '2.0小时');
INSERT INTO `tule_train_arrange` VALUES ('30', '1', '5', '8', '9', '10:00:00', '12:00:00', '2.0小时');

-- ----------------------------
-- Table structure for tule_train_date_arrange
-- ----------------------------
DROP TABLE IF EXISTS `tule_train_date_arrange`;
CREATE TABLE `tule_train_date_arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `day` date DEFAULT NULL COMMENT '日期',
  `train_arrange_id` int(10) DEFAULT NULL COMMENT '火车安排编号',
  `explain` text COMMENT '座位及剩余描述',
  `group_id` int(10) DEFAULT NULL COMMENT '火车日期安排组号',
  `version0` int(10) unsigned DEFAULT '0',
  `version1` int(10) unsigned DEFAULT '0',
  `version2` int(10) unsigned DEFAULT '0',
  `version3` int(10) unsigned DEFAULT '0',
  `end_day` varchar(255) DEFAULT NULL COMMENT '到达时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train_date_arrange
-- ----------------------------
INSERT INTO `tule_train_date_arrange` VALUES ('1', '2018-10-18', '1', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":34,\"hardSeatLayout\":[[[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":19,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[1,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '1', '2643', '669', '12', '7', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('2', '2018-10-18', '2', '[{\"count\":96,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[1,1,1,1,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"商务座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":99,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"一等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"二等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '2', '2', '15', '6', '0', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('3', '2018-10-18', '3', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":34,\"hardSeatLayout\":[[[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":19,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[1,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '1', '2643', '669', '12', '7', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('4', '2018-10-18', '4', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":34,\"hardSeatLayout\":[[[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,1],[1,1,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":19,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[1,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '1', '23', '276', '12', '7', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('5', '2018-10-18', '5', '[{\"count\":96,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[1,1,1,1,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"商务座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":99,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"一等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"二等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '2', '2', '15', '6', '0', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('6', '2018-10-18', '6', '[{\"count\":97,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[1,0,1,1,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"商务座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":99,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"一等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"二等座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '2', '2', '15', '6', '0', '2018-10-18');
INSERT INTO `tule_train_date_arrange` VALUES ('28', '2019-05-11', '29', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":59,\"hardSeatLayout\":[[[0,1,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":20,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '14', '0', '11', '0', '0', '2019-05-11');
INSERT INTO `tule_train_date_arrange` VALUES ('29', '2019-05-13', '30', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":60,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":20,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '15', '0', '0', '0', '0', '2019-05-13');
INSERT INTO `tule_train_date_arrange` VALUES ('30', '2019-05-17', '1', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":60,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":20,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '21', '0', '2', '0', '0', '2019-05-17');
INSERT INTO `tule_train_date_arrange` VALUES ('31', '2019-05-17', '3', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":60,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":20,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '21', '0', '2', '0', '0', '2019-05-17');
INSERT INTO `tule_train_date_arrange` VALUES ('32', '2019-05-17', '4', '[{\"count\":100,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":300,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":60,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":200,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"硬卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":40,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":100,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"软卧\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]},{\"count\":20,\"hardSeatLayout\":[[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]],\"noSeatLayout\":[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]],\"price\":50,\"seatLayout\":[[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]],\"seatType\":\"无座\",\"softSeatLayout\":[[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]]}]', '21', '0', '2', '0', '0', '2019-05-17');

-- ----------------------------
-- Table structure for tule_train_dept
-- ----------------------------
DROP TABLE IF EXISTS `tule_train_dept`;
CREATE TABLE `tule_train_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '火车部门编号',
  `name` varchar(20) DEFAULT NULL COMMENT '火车部门名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train_dept
-- ----------------------------

-- ----------------------------
-- Table structure for tule_train_station
-- ----------------------------
DROP TABLE IF EXISTS `tule_train_station`;
CREATE TABLE `tule_train_station` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '火车站名称',
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train_station
-- ----------------------------
INSERT INTO `tule_train_station` VALUES ('1', '徐州站', '6');
INSERT INTO `tule_train_station` VALUES ('2', '徐州东站', '6');
INSERT INTO `tule_train_station` VALUES ('3', '宿州站', '7');
INSERT INTO `tule_train_station` VALUES ('4', '宿州东站', '7');
INSERT INTO `tule_train_station` VALUES ('5', '南京站', '8');
INSERT INTO `tule_train_station` VALUES ('6', '南京南站', '8');
INSERT INTO `tule_train_station` VALUES ('9', '杭州东站', '34');
INSERT INTO `tule_train_station` VALUES ('10', '福州东站', '29');

-- ----------------------------
-- Table structure for tule_train_trip
-- ----------------------------
DROP TABLE IF EXISTS `tule_train_trip`;
CREATE TABLE `tule_train_trip` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `explain` text COMMENT '行程途径站说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_train_trip
-- ----------------------------
INSERT INTO `tule_train_trip` VALUES ('1', '[{\"arriveTime\":\"16:20:00\",\"startTime\":\"zz:zz:zz\",\"station\":\"徐州站\",\"stopTime\":\"10分钟\"},{\"arriveTime\":\"12:10:00\",\"startTime\":\"14:20:00\",\"station\":\"宿州站\",\"stopTime\":\"10分钟\"},{\"arriveTime\":\"zz:zz:zz\",\"startTime\":\"10:10:00\",\"station\":\"南京站\",\"stopTime\":\"10分钟\"}]');
INSERT INTO `tule_train_trip` VALUES ('2', '[{\"arriveTime\":\"16:20:00\",\"startTime\":\"zz:zz:zz\",\"station\":\"徐州东站\",\"stopTime\":\"10分钟\"},{\"arriveTime\":\"12:10:00\",\"startTime\":\"14:20:00\",\"station\":\"宿州东站\",\"stopTime\":\"10分钟\"},{\"arriveTime\":\"zz:zz:zz\",\"startTime\":\"10:10:00\",\"station\":\"南京南站\",\"stopTime\":\"10分钟\"}]\r\n');
INSERT INTO `tule_train_trip` VALUES ('9', '[{\"arriveTime\":\"zz:zz:zz\",\"startTime\":\"10:00:00\",\"station\":\"徐州站\",\"stopTime\":\"0分钟\"},{\"arriveTime\":\"12:00:00\",\"startTime\":\"zz:zz:zz\",\"station\":\"南京站\",\"stopTime\":\"0分钟\"}]');

-- ----------------------------
-- Table structure for tule_user
-- ----------------------------
DROP TABLE IF EXISTS `tule_user`;
CREATE TABLE `tule_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `name` varchar(10) DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(15) DEFAULT '' COMMENT '手机号',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `live_city` int(10) DEFAULT NULL COMMENT '常住城市编号',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `married` varchar(10) DEFAULT NULL COMMENT '婚姻情况',
  `job` varchar(20) DEFAULT NULL COMMENT '职业',
  `education` varchar(10) DEFAULT NULL COMMENT '学位',
  `password` varchar(32) DEFAULT NULL,
  `pass_question` varchar(255) DEFAULT NULL COMMENT '密保问题',
  `personal_id` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `paper_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `sex` varchar(2) DEFAULT NULL,
  `salt` varchar(36) DEFAULT NULL COMMENT '盐值',
  `head_image` varchar(50) DEFAULT NULL COMMENT '用户头像路径',
  `money` int(10) DEFAULT NULL COMMENT '用户账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tule_user
-- ----------------------------
INSERT INTO `tule_user` VALUES ('21', 'qqqq', '', '15720786592', '2019-02-06', 'qweqeqeeo@163.com', '6', '南京市', '已婚', '工业/服务业人员', '大专', 'FC0C37279ECE4B7C1C0CABAABF081C0B', null, null, null, '男', 'B6ED8BC0-41F7-4728-B21E-CB1D50FC9886', 'D:\\tuleUserImage\\21headImage.jpg', '360');
