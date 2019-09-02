package com.yizhu.lj.utils.utils;

import java.io.Serializable;

/**
 * Created by dengxia on 2018/5/10.
 * 计算数据总的有几页 供分页使用
 */
public class CalculatePageData implements Serializable {
    /**
     * 计算数据可以分为几页
     *
     * @param allDataNum     总的数据
     * @param onePageDataNum 每一页的数据条数
     * @return 一共有几页
     */
    public static Integer GetPageNum(Integer allDataNum, Integer onePageDataNum) {
        Integer pageNum = 0;
        try
        {
            if (allDataNum % onePageDataNum == 0) {
                pageNum = allDataNum / onePageDataNum;
            } else {
                pageNum = allDataNum / onePageDataNum + 1;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return pageNum;
    }
}
