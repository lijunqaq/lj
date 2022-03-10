package com.yizhu.lj.utils;

import java.util.List;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2020/5/7
 */
public class ListUtils {

    public ListUtils() {

    }

    /**
     * 输出
     * @param list 将List转为数组并输出, out: 2, 3, 4
     * @return
     */
    public static String listToString(List list){

        StringBuffer content = new StringBuffer();
        for(int i = 0; i < list.size(); i++){
            content.append(list.get(i));
            if(i < list.size() - 1){
                content.append(",");
            }
        }
        return content.toString();
    }

    /**
     * 在list中查找是否有str
     * @param list
     * @param str
     * @return
     */
    public static boolean find(List<String> list, String str){
        boolean flag = false;
        for(String lis : list){
            if(lis.equals(str)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 在List中查找是否有String,如果有返回下标, 否则返回 -1
     * @param list
     * @param str
     * @return
     */
    public static int findIndex(List<String> list, String str) {

        int index = 0;
        for(String lis : list) {
            if(lis.equals(str)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static boolean clockJudge(String[] clock, int index) {
        if(clock[index].equals("0")) {
            return true;
        }
        return false;
    }
    /**
     *
     * @param index 下标
     * @param clock 时钟
     * @param range 当前使用内存块
     * @return
     */
    public static int findZero(int index, String[] clock, int range) {

        while(true) {

            if(clock[index].equals("0")) {
                break;
            }else {
                clock[index] = "0";
                index++;
                if(index > range-1) {
                    index = Math.abs(range - index);
                }
            }
        }
        return index;
    }

    /**
     * 在数组中查找是否存在该字符串
     * @param obj
     * @param str
     * @return
     */
    public static boolean strJudge(Object[] obj, String str) {
        boolean flag = false;
        if(obj == null) {
            return flag;
        }
        for(int i = 0; i < obj.length; i++) {
            if(str.equals(obj[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 获取二维数组中同一列的行的长度
     * @param str 数据
     * @param length 二维数组的列
     * @param memory 内存块
     * @return
     *
     */

    public static int findNull(Object[][] str, int length, int memory) {

        int index = 0;
        if(str == null) {
            return -1;
        }
        for(int i = 0; i < memory; i++) {
            if(str[i][length] != null) {
                index = i;
            }
        }
        return index;
    }

}
