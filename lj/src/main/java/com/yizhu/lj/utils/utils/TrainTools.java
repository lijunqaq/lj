package com.yizhu.lj.utils.utils;

/**
 * 火车票工具类
 * Created by chenqibiao on 2018/5/9.
 */
public class TrainTools {

    /**
     * 得到外采渠道名称
     *
     * @param channelID 渠道方ID
     * @return
     */
    public static String getChannelName(int channelID) {
        switch (channelID) {
            case 1:
                return "大拓";
            case 2:
                return "19E";
            case 7:
                return "同程";
            default:
                return channelID + "";
        }
    }

    /**
     * 得到车次类型
     *
     * @param trainTypeID 接口方火车类型ID
     * @return
     */
    public static String getTrainTypeName(String trainTypeID) {
        switch (trainTypeID) {
            case "G":
                return "高铁";
            case "C":
                return "城际";
            case "D":
                return "动车";
            case "Z":
                return "直达";
            case "T":
                return "特快";
            case "K":
                return "普快";
            case "0":
                ////数字0
                return "其他";
            default:
                return "其他";
        }
    }

    /**
     * 获取火车类型名称
     * 19e/同程 可共用
     *
     * @param trainTypeID 接口方火车类型ID
     * @return
     */
    public static String getTrainTypeNames(String trainTypeID) {
        switch (trainTypeID.trim()) {
            case "C":
                return "城际高速";
            case "D":
                return "动车组";
            case "KT":
                return "空调特快";
            case "KKS":
                return "空调快速";
            case "KPK":
                return "空调普快";
            case "KPM":
                return "空调普慢";
            case "KS":
                return "快速";
            case "PK":
                return "普快";
            case "PM":
                return "普慢";
            case "XGZ":
                return "香港直通车";
            case "Z":
                return "直达特快";
            default:
                return trainTypeID;
        }
    }

    /**
     * 座位类型组装转换
     * 本地(9:商务座； / P:特等座； / M:一等座； / O:二等座 / 6:高级软卧； / 4:软卧； / 3:硬卧； / 2:软座； / 1:硬座 / 10:无座)
     * 19E （0、商务座1、特等座2、一等座3、二等座4、高级软卧5、软卧6、硬卧7、软座8、硬座9、无座10、其他）
     *
     * @param seatType 接口方坐席ID
     * @return
     */
    public static String getSeatType(String seatType) {
        switch (seatType) {
            case "9":
                return "0";
            case "P":
                return "1";
            case "M":
                return "2";
            case "O":
                return "3";
            case "6":
                return "4";
            case "4":
                return "5";
            case "3":
                return "6";
            case "2":
                return "7";
            case "1":
                return "8";
            case "10":
                return "9";
            default:
                return seatType;
        }
    }

    /**
     * 座位类型组装转换
     * 本地(9:商务座； / P:特等座； / M:一等座； / O:二等座 / 6:高级软卧； / 4:软卧； / 3:硬卧； / 2:软座； / 1:硬座 / 10:无座)
     * 19E （0、商务座1、特等座2、一等座3、二等座4、高级软卧5、软卧6、硬卧7、软座8、硬座9、无座10、其他）
     *
     * @param seatType 接口方坐席ID
     * @return
     */
    public static String getSeatTypeRE(String seatType) {
        switch (seatType) {
            case "0":
                return "9";
            case "1":
                return "P";
            case "2":
                return "M";
            case "3":
                return "O";
            case "4":
                return "6";
            case "5":
                return "4";
            case "6":
                return "3";
            case "7":
                return "2";
            case "8":
                return "1";
            case "9":
                return "10";
            default:
                return seatType;
        }
    }

    /**
     * 证件类型转换（将我方的身份类型ID转换为19E的身份类型ID）
     * 19E 1、一代身份证、２、二代身份证、３、港澳通行证、４、台湾通行证、５、护照
     * 本地 1.身份证 2.护照  3.军人证，4：回乡证(对应19E港澳通行证)，5：台胞证，6：港澳通行证，7：其他
     *
     * @param idType 证件类型ID
     * @return
     */
    public static String getCardType(int idType) {
        switch (idType) {
            case 1:
                return "2";
            case 2:
                return "5";
            case 5:
                return "4";
            case 4:
                return "3";
            default:
                return "2";
        }
    }

    /**
     * 证件类型转换（根据证件Type获取Name，我方的身份类型ID）
     * 证件类型ID(1.身份证 / 2.护照 / 3.台胞证 / 4.港澳通行证)
     *
     * @param idType 证件类型ID
     * @return
     */
    public static String getCardTypeName(int idType) {
        switch (idType) {
            case 1:
                return "身份证";
            case 2:
                return "护照";
            case 4:
                return "回乡证";
            case 5:
                return "台胞证";
            default:
                return "身份证";
        }
    }

    /**
     * 根据退改类型获取描述
     *
     * @param typeID    退改类型
     * @param orderType 1：改签 2：退票
     * @return
     */
    public static String getRefundChangeTypeNameByID(int typeID, int orderType) {
        String typeName = "";
        if (orderType == 1) {
            // 改签
            switch (typeID) {
                case 1:
                    typeName = "部分改签";
                    break;
                case 2:
                    typeName = "全部改签";
                    break;
                default:
                    break;
            }
        }
        if (orderType == 2) {
            // 退票
            switch (typeID) {
                case 1:
                    typeName = "部分退票";
                    break;
                case 2:
                    typeName = "全部退票";
                    break;
                default:
                    break;
            }
        }

        return typeName;
    }

    /**
     * 01:差客E族 02:人达E家 03:空铁逸行
     *
     * @param partnerType 合作商ID
     * @return
     */
    public static String getpartnerName(String partnerType) {
        switch (partnerType) {
            case "01":
                return "差客合作商";
            case "04":
                return "易鑫合作商";
            case "02":
                return "人达合作商";
            case "03":
                return "空铁逸行合作商";
            default:
                return partnerType;
        }
    }

    /**
     * 01:差客E族 02:人达E家 03:空铁逸行
     *
     * @param partnerType 合作商名称
     * @return
     */
    public static String getpartnerLevel(String partnerType) {
        switch (partnerType) {
            case "04":
                return "1-01";
            default:
                return partnerType;
        }
    }

    /**
     * 根据列车类型+名称 查出ParamID
     *
     * @param productInfoType productInfoType
     * @param seatName        seatName
     * @return
     */
    public static String getTrainSeatParamID(String productInfoType, String seatName) {
        switch (productInfoType) {
            case "C":
            case "D":
            case "G":
                // 有当高铁/动车处理
                switch (seatName) {
                    case "高级软卧":
                        return "HC014";
                    case "软卧":
                        return "HC012";
                    case "商务座":
                    case "特等座":
                        return "HC004";
                    case "一等座":
                        return "HC005";
                    case "二等座":
                        return "HC006";
                    default:
                        return "HC006";
                }
            default:
                // 没有当普通列车处理
                switch (seatName) {
                    case "高级软卧":
                        return "HC007";
                    case "软卧":
                        return "HC008";
                    case "硬卧":
                        return "HC009";
                    case "软座":
                        return "HC013";
                    case "硬座":
                        return "HC010";
                    case "无座":
                        return "HC011";
                    default:
                        return "HC011";
                }
        }
    }
}
