package com.example.lj.controller.user;

import com.example.lj.service.impl.AliServiceImpl;
import com.example.lj.service.impl.QiNiuServiceImpl;
import com.example.lj.utils.AliOssUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/v1")
@Api(tags = "七牛云",description = "七牛云相关api接口")
public class QiniuController {
	@Autowired
	private QiNiuServiceImpl qiniuService;
    @Autowired
    private AliServiceImpl aliService;
    private static String fpath="http://pon8smknt.bkt.clouddn.com/";
    private static String Alipath="https://lijunqaq.oss-cn-shenzhen.aliyuncs.com/";


//	 /**
//     * 上传文件到七牛云存储
//     * @param multipartFile
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/img/qiniu")
//    @ResponseBody
//    public String uploadImgQiniu(@RequestParam("qiniuimg") MultipartFile multipartFile) throws IOException {
//        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
//       // String  path1="曹操"+"_"+UUID.randomUUID();
//
//        byte[] byt = new byte[inputStream.available()];
//
//        inputStream.read(byt);
//        String path = UUID.randomUUID()+"";
//        String path1 = qiniuService.uploadObject(byt,path);
//
//        //   aliService.getUptoken(byt,"sssa");
//
////        empService.saveUser(user);
////		model.addAttribute("info","恭喜您，添加员工成功");
//		return fpath+path1;
//    }


    /**
     * 上传文件到阿里云oss
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("/img/qiniu")
    @ResponseBody
    public String uploadImgAliOSS(@RequestParam("qiniuimg") MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();

        byte[] byt = new byte[inputStream.available()];



        //url += "http://你的BucketName.你的Endpoint/自定义路径/" + fileName;

        String path = UUID.randomUUID()+".jpg";
        AliOssUtil.uploadByte(inputStream,path);

        return Alipath+path;
    }


}
