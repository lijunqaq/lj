//package com.example.lj.utils.utils;
//
//
//
//import java.util.*;
//
///**
// * 火车票类型转换帮助类
// *
// * @author baige
// * @date 2018/9/11 17:00
// */
//public class TypeConversion {
//    /**
//     * 支付类型
//     */
//    public static Map<Integer, String> PayModeType = new HashMap<Integer, String>() {{
//        put(1, "支付宝");
//        put(2, "微信");
//        put(3, "无忧付");
//        put(4, "余额");
//        put(5, "银联");
//    }};
//
//    /**
//     * 车票类型 1成人=0（接口方编号），2儿童=1（接口方编号）
//     */
//    private static List<IDType> TicketTypeLocal2APIMap = new ArrayList<IDType>() {{
//        add(new IDType("0", "1", "成人"));
//        add(new IDType("1", "2", "儿童"));
//    }};
//
//    /**
//     * 转换车票类型
//     * 找不到类型的话默认成人
//     *
//     * @param ourTicketTypeID ourTicketTypeID
//     * @return 转换结果
//     */
//    public static IDType ticketTypeConvert(String ourTicketTypeID) {
//        Optional<IDType> ticketTypeConvertResult = TicketTypeLocal2APIMap.stream().filter(t -> ourTicketTypeID.equals(t.getLocalID())).findFirst();
//        return ticketTypeConvertResult.orElseGet(() -> TicketTypeLocal2APIMap.get(0));
//    }
//
//    /**
//     * 证件类型转换(将我方的身份类型ID转换为19E的身份类型ID)
//     * 19E 1、一代身份证、２、二代身份证、３、港澳通行证、４、台湾通行证、５、护照
//     * 本地 1.身份证 2.护照  3.军人证，4：回乡证(对应19E港澳通行证)，5：台胞证，6：港澳通行证，7：其他
//     *
//     * @param idType 证件类型
//     * @return 转换结果
//     */
//    public static int getCardType(byte idType) {
//        switch (idType) {
//            case 1:
//                return 2;
//            case 2:
//                return 5;
//            case 5:
//                return 4;
//            case 4:
//                return 3;
//            default:
//                return 2;
//        }
//    }
//
//    /**
//     * 座位类型组装转换
//     * 本地(9:商务座； / P:特等座； / M:一等座； / O:二等座 / 6:高级软卧； / 4:软卧； / 3:硬卧； / 2:软座； / 1:硬座 / 10:无座)
//     * 19E （0、商务座1、特等座2、一等座3、二等座4、高级软卧5、软卧6、硬卧7、软座8、硬座9、无座10、其他）
//     *
//     * @param seatType 坐席类别
//     * @return 转换结果
//     */
//    public static String getSeatType(String seatType) {
//        switch (seatType) {
//            case "9":
//                return "0";
//            case "P":
//                return "1";
//            case "M":
//                return "2";
//            case "O":
//                return "3";
//            case "6":
//                return "4";
//            case "4":
//                return "5";
//            case "3":
//                return "6";
//            case "2":
//                return "7";
//            case "1":
//                return "8";
//            case "10":
//                return "9";
//            default:
//                return "";
//        }
//    }
//
//    /**
//     * 同程改签占座通知转换-改签类型
//     * 同程：0：未改签；1：无差价；2：高改低（原票价高于改签后票价）；3：低改高（原票价低于改签后票价）
//     * 本地：1：低改高 2.平改 3.高改低
//     *
//     * @param changedType 改签类型
//     * @return 转换结果
//     */
//    public static String getChangedType(String changedType) {
//        switch (changedType) {
//            case "1":
//                return "2";
//            case "2":
//                return "3";
//            case "3":
//                return "1";
//            default:
//                return String.valueOf(changedType);
//        }
//    }
//
//    /**
//     * 火车票订单来源转换
//     *
//     * @param orderSourceID 订单来源ID
//     * @return 名称
//     */
//    public static String getOrderSourceName(byte orderSourceID) {
//        switch (orderSourceID) {
//            case 1:
//                return "Web";
//            case 2:
//                return "Android";
//            case 3:
//                return "IOS";
//            case 4:
//                return "线下订单";
//            default:
//                return "";
//        }
//    }
//
//    /**
//     * 同城坐席转本地坐席类型
//     * 本地(9:商务座； / P:特等座； / M:一等座； / O:二等座 / 6:高级软卧； / 4:软卧； / 3:硬卧； / 2:软座； / 1:硬座 / 10:无座)',
//     * 同城 （商务座11、特等座12、一等座3、二等座4、高级软卧14、软卧9、硬卧6、软座2、硬座1、无座10、其他）
//     *
//     * @param seatClass 坐席类别
//     * @return 转换结果
//     */
//    public static String getLocalSeatTypeTC(String seatClass) {
//        switch (seatClass) {
//            case "11":
//                return "9";
//            case "12":
//                return "P";
//            case "3":
//                return "M";
//            case "4":
//                return "O";
//            case "14":
//                return "6";
//            case "9":
//                return "4";
//            case "6":
//                return "3";
//            case "2":
//                return "2";
//            case "1":
//                return "1";
//            case "10":
//                return "10";
//            default:
//                return "";
//        }
//    }
//
//    /**
//     * 1:     "硬座";
//     * 2:     "软座";
//     * 3:     "一等座";
//     * 33:   "一等软座"//已合并到一等座，废弃
//     * 4:     "二等座";
//     * 44:   "二等软座"//已合并到二等座，废弃
//     * 5:     "硬卧上铺";
//     * 6:     "硬卧";
//     * 7:     "硬卧下铺";
//     * 8:     "软卧上铺";
//     * 9:     "软卧";
//     * 10:   "无座";
//     * 11:   "商务座";
//     * 12:   "特等座";
//     * 13:   "其它";
//     * 14:   "高级软卧";
//     * 15:   "动卧上铺"
//     * 16:   "动卧下铺"
//     * 17:   "高级动卧上铺"
//     * 18:   "高级动卧下铺"
//     * 19:   "高级软卧上铺"
//     * 20:   "高级软卧下铺"
//     *
//     * @param seatClass 座位类型
//     * @return 转换结果
//     */
//    public static String getLocalSeatNameTC(String seatClass) {
//        switch (seatClass) {
//            case "1":
//                return "硬座";
//            case "2":
//                return "软座";
//            case "3":
//                return "一等座";
//            case "4":
//                return "二等座";
//            case "5":
//                return "硬卧上铺";
//            case "6":
//                return "硬卧";
//            case "7":
//                return "硬卧下铺";
//            case "8":
//                return "软卧上铺";
//            case "9":
//                return "软卧";
//            case "10":
//                return "无座";
//            case "11":
//                return "商务座";
//            case "12":
//                return "特等座";
//            case "13":
//                return "其它";
//            case "14":
//                return "高级软卧";
//            case "15":
//                return "动卧上铺";
//            case "16":
//                return "动卧下铺";
//            case "17":
//                return "高级动卧上铺";
//            case "18":
//                return "高级动卧下铺";
//            case "19":
//                return "高级软卧上铺";
//            case "20":
//                return "高级软卧下铺";
//            default:
//                return seatClass;
//        }
//    }
//
//    /**
//     * 得到外采渠道名称
//     *
//     * @param channelID 渠道ID
//     * @return 渠道名称
//     */
//    public static String getChannelName(int channelID) {
//        switch (channelID) {
//            case 1:
//                return "大拓";
//            case 2:
//                return "19E";
//            case 7:
//                return "同程";
//            default:
//                return String.valueOf(channelID);
//        }
//    }
//
//    private static List<MsgEntity> MessageInfo = new ArrayList<MsgEntity>() {
//        {
//            add(new MsgEntity(-1, "12306系统异常，请稍后再试"));
//            add(new MsgEntity(0, "表示成功"));
//            add(new MsgEntity(1, "系统异常"));
//            add(new MsgEntity(2, "系统异常"));
//            add(new MsgEntity(3, "系统异常"));
//            add(new MsgEntity(4, "系统异常"));
//            add(new MsgEntity(5, "系统异常"));
//            add(new MsgEntity(6, "系统异常"));
//            add(new MsgEntity(7, "系统异常"));
//            add(new MsgEntity(100, "订单查询异常"));
//            add(new MsgEntity(101, "系统异常"));
//            add(new MsgEntity(102, "输入车站名为空，请确认!"));
//            add(new MsgEntity(103, "出发车站错误。"));
//            add(new MsgEntity(104, "到达车站错误。"));
//            add(new MsgEntity(105, "输入车站名错误，请确认"));
//            add(new MsgEntity(106, "查询返回数据失败，暂无列车信息"));
//            add(new MsgEntity(107, "车次信息有误，暂无该列车信息"));
//            add(new MsgEntity(200, "下订单异常"));
//            add(new MsgEntity(201, "重复下单异常"));
//            add(new MsgEntity(202, "不在受理时间内，拒绝下订单操作，请在规定时间执行此操作。"));
//            add(new MsgEntity(203, "一个订单最多允许订购五张票。"));
//            add(new MsgEntity(204, "系统异常"));
//            add(new MsgEntity(300, "本次购票与其他订单行程冲突"));
//            add(new MsgEntity(301, "订单未找到（订单号错误或不存在）"));
//            add(new MsgEntity(400, "取消订单异常"));
//            add(new MsgEntity(401, "订单已出票成功，无法取消，请走退票退款流程。"));
//            add(new MsgEntity(402, "不在受理退款时间内，拒绝取消订单操作, 请在规定时间执行此操作。"));
//            add(new MsgEntity(500, "查询途经站信息异常"));
//            add(new MsgEntity(501, "暂时没有该途经站信息。"));
//            add(new MsgEntity(600, "系统异常"));
//            add(new MsgEntity(601, "系统异常"));
//            add(new MsgEntity(602, "系统异常"));
//            add(new MsgEntity(603, "系统异常"));
//            add(new MsgEntity(604, "超过约定支付有效时间期，该订单已禁止支付。"));
//            add(new MsgEntity(700, "保险查询过程异常"));
//            add(new MsgEntity(701, "订单下无相关保险信息"));
//            add(new MsgEntity(602, "当前订单状态下不能进行退票"));
//            add(new MsgEntity(603, "当前要退的车票状态不符合退票要求"));
//            add(new MsgEntity(604, "服务内部错误：设置车票正在退票失败"));
//            add(new MsgEntity(701, "未找到要改签的车票"));
//            add(new MsgEntity(800, "改签过程异常"));
//            add(new MsgEntity(801, "拒绝不存在订单改签和重复申请订单改签。"));
//            add(new MsgEntity(802, "出票成功的订单才可以申请改签。"));
//            add(new MsgEntity(888, "风速过大，请稍后再试。"));
//            add(new MsgEntity(999, "出票异常，客服紧急处理中！"));
//        }
//    };
//
//    /**
//     * 扩展方法 根据状态id取得状态名
//     *
//     * @param k 状态id
//     * @return 状态对象
//     */
//    public static MsgEntity k2e(int k) {
//        Optional<MsgEntity> first = MessageInfo.stream().filter(t -> t.getCode() == k).findFirst();
//        if (!first.isPresent()) {
//            first = MessageInfo.stream().filter(t -> t.getCode() == -1).findFirst();
//        }
//        return first.orElse(new MsgEntity(-1, "12306系统异常，请稍后再试"));
//    }
//}
//
