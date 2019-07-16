package com.example.lj.controller.user;


import com.example.lj.dto.EchartBarVo;
import com.example.lj.dto.IpNumOutDTO;
import com.example.lj.service.LoginLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 〈
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/24
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */
@RestController
@RequestMapping("/v1/chart/onlineInfoSale")
@Api(tags = "表格",description = "表格相关api接口")
public class OnlineInfoCtrl {

    @Autowired
    private LoginLogService loginLogService;




    @GetMapping("/IpNums")
    public EchartBarVo IpNums() {
        //柱状图视图类
        EchartBarVo result = new EchartBarVo();
        List<IpNumOutDTO> factorylist = loginLogService.findIpNums();

        //将查询的数据转成echarts需要的格式
        for (IpNumOutDTO obj : factorylist) {
            String title = obj.getIp();
            String value = BigDecimal.valueOf(obj.getNum()).toString();
            result.getTitles().add(title);
            result.getValues().add(value);

        }
        return result;
    }


}
