package com.lsxp.controller;

import com.lsxp.annotation.LogAnnotation;
import com.lsxp.pojo.Result;
import com.lsxp.utils.AliyunOSSOperator;
import com.lsxp.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.lsxp.utils.SystemConstants.LOCAL_FILE_POSITION;

@Slf4j
@RestController
public class UploadController {


    /*
    * 方法一：本地上传
    *
    * */
    //    @PostMapping("/upload")
    //    /*注意此处的参数名需要与前端表单中的name属性后的名称保持一致*/
    //    /*如果不一致，使用requestParam进行参数绑定即可*/
    //    public Result upload(String name,Integer age , MultipartFile file) throws IOException {
    //
    //        log.info("name:{},age:{},file:{}",name,age,file);
    //
    //        //获取原始文件名
    //        String originalFilename = file.getOriginalFilename();
    //        //修饰原始文件名
    //        //UUID可以随机生成一段
    //        String newName = String.valueOf(UUID.randomUUID());
    //        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    //        String newFileName = newName + extension;
    //        //将文件转存到磁盘文件
    //        file.transferTo(new File(LOCAL_FILE_POSITION+newFileName));
    //        return Result.success();
    //    }

    //注入OSS的工具类
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /*
    * 上传到阿里云的OSS云服务器
    *
    * */
    @PostMapping("/upload")
    public Result update(MultipartFile file) throws Exception {

        String originalFileName = file.getOriginalFilename();
        log.info("从前端获取到文件:{}", originalFileName);
        //获取输入流对象
        InputStream inputStream = file.getInputStream();
        String url = aliyunOSSOperator.uploadFile(inputStream,originalFileName);
        log.info("文件上传到OSS服务器后的地址:{}",url);
        return Result.success(url);
    }
}
