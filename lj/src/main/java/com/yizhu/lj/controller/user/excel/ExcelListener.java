package com.yizhu.lj.controller.user.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yizhu.lj.dao.entity.UserHospital;
import com.yizhu.lj.dao.mapper.UserHospitalMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    private  UserHospitalMapper mapper;
    public ExcelListener(UserHospitalMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(o);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口
        Test obj=(Test)object;
        UserHospital userHospital = new UserHospital();
        BeanUtils.copyProperties(obj,userHospital);
        System.out.println(object);
        mapper.insert(userHospital);
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }
}
