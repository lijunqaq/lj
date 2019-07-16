//package com.example.lj.utils.utils;
//
//import com.shanglv51.trainservice.dao.bean.AnnotatedBeanPublic;
//import com.shanglv51.trainservice.service.impl.single.SearchTrain.SearchTrainService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by dengxia on 2018/5/9.
// */
//@Component
//public class SystemParamHelper {
//    /**
//     * 日志
//     */
//    private static Logger logger = LoggerFactory.getLogger(SearchTrainService.class);
//
//    @Autowired
//    AnnotatedBeanPublic annotatedBeanPublic;
//
//    /**
//     * 获取渠道1.大托 2.19e 7.同程
//     * @param paramID
//     * @param defaultValue
//     * @return
//     */
//    public String getChannelID(String paramID, String defaultValue) {
//        String channelID = "7";
//        try {
//            SimpleDateFormat df = new SimpleDateFormat("HH");
//            int hour = Convert.ToInt(df.format(new Date()));
//            if (hour >= 6 && hour <= 22) {
//                channelID = annotatedBeanPublic.getChannelID();
//            } else {
//                channelID = "2";
//            }
//        } catch (Exception ex) {
//            // 获取失败  默认同程
//            channelID = "7";
//            logger.error(ex.getMessage());
//        }
//        return channelID;
//    }
//}
