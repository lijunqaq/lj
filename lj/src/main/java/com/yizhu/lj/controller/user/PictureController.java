package com.yizhu.lj.controller.user;


import com.yizhu.lj.dao.entity.Picture;
import com.yizhu.lj.dto.ResponseBean;
import com.yizhu.lj.service.PictureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijun
 * @since 2019-09-05
 */
@Controller
@RequestMapping("/v1/picture")
@Api(tags = "picture",description = "图片相关api接口")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping
    @ResponseBody
    public ResponseBean<List<Picture>> getAllPicture(){
        return pictureService.findAllPictures();
    }
}

