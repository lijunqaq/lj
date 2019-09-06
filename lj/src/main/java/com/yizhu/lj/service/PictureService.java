package com.yizhu.lj.service;


import com.baomidou.mybatisplus.service.IService;
import com.yizhu.lj.dao.entity.Picture;
import com.yizhu.lj.dto.ResponseBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijun
 * @since 2019-09-05
 */
public interface PictureService extends IService<Picture> {

    /**
     * 查询所有照片
     * @return
     */

    ResponseBean<List<Picture>> findAllPictures();
}
