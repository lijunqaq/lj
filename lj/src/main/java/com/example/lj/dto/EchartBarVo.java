package com.example.lj.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/24
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */
public class EchartBarVo {
    //标题
    private List<String> titles= new ArrayList<>();

    //数据
    private  List<String> values=new ArrayList<>();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
