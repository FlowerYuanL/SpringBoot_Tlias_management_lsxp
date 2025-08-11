package com.lsxp.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class AliyunOSSOperator {

    @Autowired
    private AliyunOSSPropertoes aliyunOSSPropertoes;

    public String uploadFile(InputStream inputStream, String originalFileName) throws Exception {
        String endpoint = aliyunOSSPropertoes.getEndpoint();
        String bucketName = aliyunOSSPropertoes.getBucketName();
        String region = aliyunOSSPropertoes.getRegion();

        //从环境变量中获取访问凭证。即AccessKey和AccessKeySecret
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        //填写Object完整路径，例如2025/08/UUID.img.Object完整路径不能包含Bucket
        //获取当前系统日期的字符串，格式为yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/"));
        //使用UUID生成一个新的文件名（不包含扩展名的部分）
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = dir + newFileName;
        log.info("新的文件名为:{}",objectName);
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        //创建OSSClient实例
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .region(region)
                .clientConfiguration(clientBuilderConfiguration)
                .credentialsProvider(credentialsProvider)
                .build();
        try{
            //创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            //创建PutObject请求
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        }finally {
            if(ossClient!=null){
                ossClient.shutdown();
            }
        }
        /*返回文件最终被上传的网址*/
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;

    }
}
