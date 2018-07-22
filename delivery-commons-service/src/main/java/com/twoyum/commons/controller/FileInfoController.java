package com.twoyum.commons.controller;

import com.twoyum.commons.commons.Response;
import com.twoyum.commons.domain.FileInfoInDto;
import com.twoyum.commons.domain.FileInfoOutDto;
import com.twoyum.commons.service.fileInfo.FileInfoService;
import com.twoyum.commons.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 文件控制层
 */
@RestController
@RequestMapping(value = "/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private AWSS3Utils aWSS3Utils;

    @Autowired
    private EnumUtil enumUtil;

    @Value("${file.uploadPath}")
    private String uploadPath;

    @Value("${s3.personalBucketName}")
    private String personalBucketName;//个人照片

    @Value("${s3.dishesBucketName}")
    private String dishesBucketName;//菜品招聘

    @Value("${s3.picBucketName}")
    private String picBucketName;//应用照片

    @Value("${s3.idphotoBucketName}")
    private String idphotoBucketName;//证件照片

    /**
     * keyID
     */
    @Value("${s3.accessKeyID}")
    private String accessKeyID ;

    /**
     * key
     */
    @Value("${s3.secretKey}")
    private String secretKey;


    @Value("${s3.endPoint}")
    private String endPoint;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private MutipartFileUtils mutipartFileUtils;

    private static final Logger logger = LoggerFactory.getLogger(FileInfoController.class);



    /**
     * 文件上传照片
     * @param fileInfoInDto 上传文件对象
     * @return 返回上传结果
     */
    @RequestMapping(value = "/uploadPic")
    public Response uploadPic(FileInfoInDto fileInfoInDto) {
        logger.info("进入上传照片接口");
        Response response = new Response();
        InputStream inputStream = null;
        String typeCode = fileInfoInDto.getTypeCode();

        try {
            if(!typeCode.equalsIgnoreCase("en")
                    && !typeCode.equalsIgnoreCase("esp")
                    && !typeCode.equalsIgnoreCase("zh-cn")
                    && !typeCode.equalsIgnoreCase("zh-hk")){
                response.setMessage(enumUtil.getTipMsg("zh-cn",7));
                response.setStatus(0);
                response.setData("");
                return response;
            }
            if(StringUtils.isEmpty(typeCode)){
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                response.setStatus(0);
                response.setData("");
                return response;
            }
            MultipartFile file = fileInfoInDto.getUploadFile();
            if(file == null){
                response.setMessage(enumUtil.getTipMsg(typeCode,6));
                response.setStatus(0);
                response.setData("");
                return response;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String keyValue = sdf.format(new Date());
            String fileName = System.getProperty("java.io.tmpdir")  + keyValue+"_"+file.getOriginalFilename();
            String newFileName = System.getProperty("java.io.tmpdir") + keyValue+"_"+"thumb_"+file.getOriginalFilename();
            File oldFile = new File(fileName);
            File newFile = new File(newFileName);
            if(oldFile.length() <= 5*1024*1024){
                inputStream = file.getInputStream();
            }else{
                file.transferTo(oldFile);
                logger.info(fileName);
                imageUtil.thumbnailImage(fileName,400,600);
                logger.info(file.getOriginalFilename());
                logger.info(file.getContentType());
                inputStream = new FileInputStream(newFile);
            }
            String bucketName = "";
            int picType = fileInfoInDto.getPicType();
            if(picType == 1){
                bucketName = personalBucketName;//个人照片存储
            }
            else if(picType == 2){
                bucketName = idphotoBucketName;//证件存储
            }
            else if(picType == 3){
                bucketName = picBucketName;//应用存储
            }
            else if(picType == 4){
                bucketName = dishesBucketName;//菜品存储
            }
            String url = aWSS3Utils.amazonS3Upload(accessKeyID,secretKey,endPoint,bucketName, inputStream,  keyValue +"_"+ file.getOriginalFilename(),file.getContentType());
            response.setData(url);
            response.setMessage(enumUtil.getTipMsg(typeCode,4));//上传个人照片成功
            response.setStatus(1);
        }catch (Exception e){
            logger.error("操作异常："+e);
            response.setError(e.getMessage());
            response.setMessage(enumUtil.getTipMsg(typeCode,5));//上传图片照片失败
            response.setStatus(0);
            response.setData("");
        }
          return response;
    }


//    /**
//     * 文件上传应用图片
//     * @param fileInfoInDto 上传文件对象
//     * @return 返回上传结果
//     */
//    @RequestMapping(value = "/uploadPic")
//    public Response uploadPic(FileInfoInDto fileInfoInDto) {
//        logger.info("进入上传接口");
//        Response response = new Response();
//        InputStream inputStream = null;
//        String typeCode = fileInfoInDto.getTypeCode();
//
//        try {
//            if(!typeCode.equalsIgnoreCase("en")
//                    && !typeCode.equalsIgnoreCase("esp")
//                    && !typeCode.equalsIgnoreCase("zh-cn")
//                    && !typeCode.equalsIgnoreCase("zh-hk")){
//                response.setMessage(enumUtil.getTipMsg("zh-cn",7));
//                response.setStatus(0);
//                response.setData("");
//                return response;
//            }
//            if(StringUtils.isEmpty(typeCode)){
//                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
//                response.setStatus(0);
//                response.setData("");
//                return response;
//            }
//            MultipartFile file = fileInfoInDto.getUploadFile();
//            if(file==null){
//                response.setMessage(enumUtil.getTipMsg(typeCode,6));
//                response.setStatus(0);
//                response.setData("");
//                return response;
//            }
//            logger.info(file.getOriginalFilename());
//            logger.info(file.getContentType());
//            inputStream = file.getInputStream();
//            String url = aWSS3Utils.amazonS3Upload(accessKeyID,secretKey,endPoint,picBucketName, inputStream,  file.getOriginalFilename(),file.getContentType());
//            response.setData(url);
//            response.setMessage(enumUtil.getTipMsg(typeCode,4));//上传个人照片成功
//            response.setStatus(1);
//        }catch (Exception e){
//            logger.error("操作异常："+e);
//            response.setError(e.getMessage());
//            response.setMessage(enumUtil.getTipMsg(typeCode,5));//上传图片照片失败
//            response.setStatus(0);
//            response.setData("");
//        }
//        return response;
//    }

        /**
         * 文件上传
         * @param file 上传文件对象
         * @return 返回上传结果
         */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Response uploadFile(@RequestParam("uploadFile") MultipartFile file) {//The field uploadFile exceeds its maximum permitted size of 1048576 bytes
        ServiceInstance instance = client.getLocalServiceInstance();
        String serviceId = instance.getServiceId();
        FileInfoInDto fileInfoInDto = new FileInfoInDto();
        Response response = new Response();
        if (file.isEmpty()) {
            response.setMessage("文件为空");
            response.setStatus(0);
            return  response;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        //String filePath = "C://Users//admin//IdeaProjects//eams//upload//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        String keyValue = (UUID.randomUUID()+"").replaceAll("-","");
        File dest = new File(uploadPath + keyValue + suffixName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        fileInfoInDto.setServiceId(serviceId);
        fileInfoInDto.setOrgFileName(fileName);
        fileInfoInDto.setCurFileName(keyValue + suffixName);
        fileInfoInDto.setKeyValue(keyValue);
        fileInfoInDto.setSuffixName(suffixName);
        fileInfoService.insertFileInfo(fileInfoInDto);
        try {
            file.transferTo(dest);
            response.setStatus(1);
            response.setData(keyValue);
            response.setMessage("上传成功");
            logger.info("上传成功");
        } catch (Exception e) {
            logger.error("上传失败异常：",e);
            response.setError(e.getMessage());
            response.setStatus(0);
            response.setMessage("上传失败");
        }
        return response;
    }

    /**
     * 文件下载
//     * @param fileInfoInDto 输入对象(keyValue)
     * @param request
     * @param response
     * @return 返回下载结果
     */
    @RequestMapping("/download")
    public Response downloadFile(FileInfoInDto fileInfoInDto,HttpServletRequest request, HttpServletResponse response){
        Response responseVo = new Response();
        try {
                FileInfoOutDto fileInfoOutDto = fileInfoService.getFileInfo(fileInfoInDto);
                if (fileInfoInDto != null) {
                    String orgFileName = fileInfoOutDto.getOrgFileName();
                    String curFileName = fileInfoOutDto.getCurFileName();
                    download(curFileName,orgFileName,request,response);
                }
                responseVo.setStatus(1);
                responseVo.setMessage("下载成功");
                logger.info("下载成功");
            }catch (Exception e){
                logger.error("下载失败异常：",e);
                responseVo.setError(e.getMessage());
                responseVo.setStatus(0);
                responseVo.setMessage("下载失败");
            }
        return responseVo;
    }

    /**
     * 批量打包文件下载
//     * @param fileNameList 文件名+后缀
     * @param request
     * @param response
     * @return 返回下载结果
     */
    @RequestMapping("/batchDownload")
    public Response batchDownloadFile(FileInfoInDto fileInfoInDto,HttpServletRequest request, HttpServletResponse response){
        Response responseVo = new Response();
        try{
                List<FileInfoOutDto> fileInfoInDtos = fileInfoService.getFileInfoList(fileInfoInDto);
                String currentDate =  FileUtils.copyFileListToDir(fileInfoInDtos,uploadPath,uploadPath );
                String zipPath = uploadPath;
                String dir = uploadPath+File.separator+currentDate;

                FileUtils.zip(dir,zipPath,currentDate+".zip");
                download(currentDate+".zip",currentDate+".zip",request,response);
                DeleteFileUtils.delete(uploadPath+File.separator+currentDate);
                DeleteFileUtils.delete(uploadPath+File.separator+currentDate+".zip");
                responseVo.setStatus(1);
                responseVo.setMessage("下载成功");
                logger.info("下载成功");
            } catch (Exception e){
                logger.error("下载失败异常",e);
                responseVo.setError(e.getMessage());
                responseVo.setStatus(0);
                responseVo.setMessage("下载失败");
            }
        return responseVo;
    }

    /**
     * 操作下载
     * @param curFileName 当前文件名
     * @param orgFileName 原始文件名
     * @param request
     * @param response
     * @throws Exception
     */
    public void download(String curFileName,String orgFileName,HttpServletRequest request, HttpServletResponse response)throws Exception{
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        File file = new File(uploadPath,curFileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" +  orgFileName);// 设置文件名
            byte[] buffer = new byte[1024];
            try{
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }
}
