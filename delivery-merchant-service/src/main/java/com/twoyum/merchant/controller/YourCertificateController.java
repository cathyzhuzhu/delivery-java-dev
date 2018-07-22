package com.twoyum.merchant.controller;

import com.twoyum.merchant.domain.*;
import com.twoyum.merchant.response.Response;
import com.twoyum.merchant.service.YourCertificateService;
import com.twoyum.merchant.utils.EnumUtil;
import com.twoyum.merchant.utils.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发布引导页控制层：您的证件
 */
@RestController
@RequestMapping(value = "/yourCertificate")
public class YourCertificateController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(YourCertificateController.class);
    @Autowired
    private YourCertificateService yourCertificateService;

    @Autowired
    private EnumUtil enumUtil;

    @Autowired
    private JWTUtil jWTUtil;

    /**
     * 更新证件照片
     * @param identificationPhotoInDto 证件照片对象
     * @return 返回结果
     */
    @RequestMapping(value = "/identificationPhoto/update")
    public Response updateIdentificationPhoto(@RequestBody IdentificationPhotoInDto identificationPhotoInDto,HttpServletRequest request) {
        String typeCode = identificationPhotoInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(identificationPhotoInDto.getToken());
            identificationPhotoInDto.setEmail(email);
            yourCertificateService.updateIdentificationPhoto(identificationPhotoInDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    /**
     * 查询证件照片
     * @param identificationPhotoInDto 证件照片对象
     * @return 返回结果
     */
    @RequestMapping(value = "/identificationPhoto/get")
    public Response getIdentificationPhoto(@RequestBody IdentificationPhotoInDto identificationPhotoInDto) {
        String typeCode = identificationPhotoInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(identificationPhotoInDto.getToken());
            identificationPhotoInDto.setEmail(email);
            IdentificationPhotoOutDto identificationPhotoOutDto=yourCertificateService.getIdentificationPhoto(identificationPhotoInDto);
            response.setData(identificationPhotoOutDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }
    /**
     * 纳税人ID号
     */
    @RequestMapping(value = "/taxpayerId/update")
    public Response updateTaxpayerId(@RequestBody TaxpayerIdInDto taxpayerIdInDto,HttpServletRequest request) {
        String typeCode = taxpayerIdInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(taxpayerIdInDto.getToken());
            taxpayerIdInDto.setEmail(email);
            yourCertificateService.updateTaxpayerId(taxpayerIdInDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    @RequestMapping(value = "/taxpayerId/get")
    public Response getTaxpayerId(@RequestBody TaxpayerIdInDto taxpayerIdInDto) {
        String typeCode = taxpayerIdInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(taxpayerIdInDto.getToken());
            taxpayerIdInDto.setEmail(email);
            TaxpayerIdOutDto  taxpayerIdOutDto = yourCertificateService.getTaxpayerId(taxpayerIdInDto);
            response.setData(taxpayerIdOutDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    /**
     * 经理人
     */
    @RequestMapping(value = "/manager/update")
    public Response updateManager(@RequestBody ManagerInDto managerInDto,HttpServletRequest request) {
        String typeCode = managerInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(managerInDto.getToken());
            managerInDto.setEmail(email);
            yourCertificateService.updateManager(managerInDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    /**
     * 经理人
     */
    @RequestMapping(value = "/manager/get")
    public Response getManager(@RequestBody ManagerInDto managerInDto) {
        String typeCode = managerInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(managerInDto.getToken());
            managerInDto.setEmail(email);
            ManagerOutDto  managerOutDto = yourCertificateService.getManager(managerInDto);
            response.setData(managerOutDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }


    /**
     * 邮寄地址
     */
    @RequestMapping(value = "/mailingAddress/update" ,method = RequestMethod.POST)
    public Response updateMailingAddress(@RequestBody MailingAddressInDto mailingAddressInDto,HttpServletRequest request) {
        addLogInfo(mailingAddressInDto.getEmail(),request);
        String typeCode = mailingAddressInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(mailingAddressInDto.getToken());
            mailingAddressInDto.setEmail(email);
            yourCertificateService.updateMailingAddress(mailingAddressInDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    /**
     * 邮寄地址
     */
    @RequestMapping(value = "/mailingAddress/get" ,method = RequestMethod.POST)
    public Response getMailingAddress(@RequestBody MailingAddressInDto mailingAddressInDto) {
        String typeCode = mailingAddressInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            String email = jWTUtil.getUsername(mailingAddressInDto.getToken());
            mailingAddressInDto.setEmail(email);
            MailingAddressOutDto mailingAddressOutDto = yourCertificateService.getMailingAddress(mailingAddressInDto);
            response.setData(mailingAddressOutDto);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }

    /**
     * 国家列表
     */
    @RequestMapping(value = "/countryList" ,method = RequestMethod.POST)
    public Response getCountryList(@RequestBody CountryInDto countryInDto) {
        String typeCode = countryInDto.getTypeCode();
        Response  response = new Response();
        try{
            if(StringUtils.isEmpty(typeCode)){
                response.setStatus(0);
                response.setMessage(enumUtil.getTipMsg("zh-cn",3));
                return response;
            }
            List<CountryOutDto> countryInDtoList =  yourCertificateService.getCountryList();
            response.setData(countryInDtoList);
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,1));
            response.setData(countryInDtoList);
        }catch(Exception e){
            response.setStatus(-1);
            response.setMessage(enumUtil.getTipMsg(typeCode,2));
            response.setError(e.getMessage());
            response.setErrorCode("");
            logger.error("操作异常：",e);
        }
        return response;
    }
}
