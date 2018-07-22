package com.twoyum.commons.utils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AWSS3Utils {
    private static Logger logger = Logger.getLogger(AWSS3Utils.class);


//    /**
//     * 以理解为一个很大的磁储硬盘，其容量以T为单位
//     */
//    public static String bucketName = "personal-upload";

//    /**
//     * 上传文件地址
//     */
//    public static String localPath = "C:\\webpage\\red-btn.png";

//            /**
//             * 存储新对象的键
//             */
//            public static String s3Path = "amazonS3";

    private AmazonS3 s3Client;

    public void loadAWSS3Object(String accessKeyID,String secretKey,String endPoint){
        AWSCredentials awsCredentials=new BasicAWSCredentials(accessKeyID, secretKey); //创建Amazon S3对象
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        s3Client = new AmazonS3Client(awsCredentials, clientConfig);
        s3Client.setEndpoint(endPoint);
    }
    /**
     * 查看所有可用的bucket
     */
    public void getAllBucket(){
        List<Bucket> buckets = s3Client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println("Bucket: " + bucket.getName());
        }
    }

    /**
     * 查看bucket下所有的object
     //             * @param bucketName 存储桶
     */
    public void getAllBucketObject(String bucketName){
        ObjectListing objects = s3Client.listObjects(bucketName);
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                System.out.println("Object: " + objectSummary.getKey());
            }
            objects = s3Client.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
    }

    /**
     * amazonS3文件上传
     * @param bucketName 保存到某个存储桶
     * @param inputStream 上传文件流
     */
    public String amazonS3Upload(String accessKeyID,String secretKey,String endPoint,String bucketName,InputStream inputStream,String fileName,String contentType){
        loadAWSS3Object(accessKeyID,secretKey,endPoint);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String key = sdf.format(new Date())+"/"+fileName;//保存文件的key （以key-value形式保存）
        ObjectMetadata  metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        s3Client.putObject(new PutObjectRequest(bucketName, key, inputStream,metadata));
        //获取一个request
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                bucketName, key);
        Date expirationDate = null;
        try {
            expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置过期时间
        urlRequest.setExpiration(expirationDate);
        //生成公用的url
        URL url = s3Client.generatePresignedUrl(urlRequest);
        System.out.println(url);
        return url.toString();

//                http://personal-upload.s3.amazonaws.com/amazonS3/1.jpg?AWSAccessKeyId=AKIAISJERYZGVWD66R4A&Expires=1523957510&Signature=QO%2BaTIIb2GjYyjXrQzp1DK37tlQ%3D
//                InputStream inputStream = null;
//                try {
//                    inputStream = new FileInputStream(file);
//                    PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, null);
//                    request.setCannedAcl(CannedAccessControlList.PublicRead);
//                    s3Client.putObject(request);
//                    System.out.println("PutObjectResult: "+result.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
    }

    /**
     * amazonS3文件下载
     * @param bucketName 下载某个存储桶的数据
     * @param key 下载文件的key
     * @param targetFilePath 下载文件保存地址
     */
    public void amazonS3Downloading(String bucketName,String key,String targetFilePath){
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName,key));
        if(object!=null){
            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
            InputStream input = null;
            FileOutputStream fileOutputStream = null;
            byte[] data = null;
            try {
                //获取文件流
                input=object.getObjectContent();
                data = new byte[input.available()];
                int len = 0;
                fileOutputStream = new FileOutputStream(targetFilePath);
                while ((len = input.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(input!=null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 文件删除
     * @param s3Client
     * @param bucketName 删除文件所在存储桶
     * @param key 删除文件key
     */
    public static void amazonS3DeleteObject(AmazonS3 s3Client,String bucketName,String key){
        s3Client.deleteObject(bucketName, key);
    }

    /**
     * 删除存储桶
     * @param s3Client
     * @param bucketName 需要删除的存储桶
     */
    public static void amazonS3DeleteBucket(AmazonS3 s3Client,String bucketName){
        s3Client.deleteBucket(bucketName);
    }
}
