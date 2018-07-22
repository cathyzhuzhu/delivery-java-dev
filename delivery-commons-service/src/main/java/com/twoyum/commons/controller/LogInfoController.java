package com.twoyum.commons.controller;

import com.twoyum.commons.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import com.twoyum.commons.domain.LogInfoInDto;
import com.twoyum.commons.service.logInfo.LogInfoService;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志控制层
 */
@RestController
@RequestMapping(value = "/logInfo")
public class LogInfoController {
    private static final Logger logger = LoggerFactory.getLogger(LogInfoController.class);
    @Autowired
    private LogInfoService logInfoService;


    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public Response saveLoggerInfo(HttpServletRequest request, @RequestBody LogInfoInDto logInfoInDto) {
        Response  response = new Response();
        try{
            ServiceInstance instance = client.getLocalServiceInstance();
            String serviceId = instance.getServiceId();
            logInfoInDto.setIpAddress(getIpAddr(request));
            logInfoInDto.setServiceId(serviceId);
            logInfoService.addLogInfo(logInfoInDto);
            logger.info("/logInfo/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            response.setStatus(1);
            response.setMessage("记录日志成功");
        }catch(Exception e){
            response.setStatus(0);
            response.setMessage("记录日志失败");
            response.setError(e.getMessage());
            logger.error("记录日志异常：",e);
        }
        return response;
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
//
//    @ResponseBody
//    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//    public int addUser(User fileInfo){
//        return userService.addUser(fileInfo);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//
//        return userService.findAllUser(pageNum,pageSize);
//    }
}
