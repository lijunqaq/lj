package com.yizhu.lj.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yizhu.lj.dao.entity.Picture;
import com.yizhu.lj.dao.mapper.PictureMapper;
import com.yizhu.lj.dto.ResponseBean;
import com.yizhu.lj.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijun
 * @since 2019-09-05
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {


    @Override
    public ResponseBean<List<Picture>> findAllPictures() {
        List<Picture> pictures = selectList(new EntityWrapper<Picture>()
        .eq("state",1));
        if(CollectionUtils.isEmpty(pictures)){
            return new ResponseBean<>(0,"没有数据");
        }
        return new ResponseBean<>(1,"",pictures);
    }
}
