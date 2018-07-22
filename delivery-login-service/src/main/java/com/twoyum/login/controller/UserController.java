package com.twoyum.login.controller;

import com.twoyum.login.commons.Response;
import com.twoyum.login.domain.UserInDto;
import com.twoyum.login.domain.UserOutDto;
import com.twoyum.login.domain.ValidateInfoInDto;
import com.twoyum.login.domain.ValidateInfoOutDto;
import com.twoyum.login.service.IUserService;
import com.twoyum.login.service.ValidateInfoService;
import com.twoyum.login.utils.EnumUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理Controller
 *
 * @author huangjuan
 * Created on 2018/04/01
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private EnumUtil enumUtil;

    @Value("${mail.from}")
    protected String from;

    @Value("${mail.basePath}")
    protected String basePath;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ValidateInfoService validateInfoService;

    /**
     * 用户登录
     * @param userInDto 输入对象
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @RequestMapping("/login")
    public Response login(@RequestBody UserInDto userInDto,HttpServletRequest request){
        addLogInfo(userInDto.getEmail(),request);
        Response response = new Response();
        String typeCode = userInDto.getTypeCode();
        try{
            String email = userInDto.getEmail();
            String password = userInDto.getPassword();
            if(StringUtils.isEmpty(email)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//邮件不为空
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(!validateEmail(email)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//邮件格式错误
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(StringUtils.isEmpty(password)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//密码不为空
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(password.length()<8 || password.length()>20){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//密码不正确
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            Map<String,Object> map =  userService.login(email, password);
            response.setData(map);
            response.setStatus(1);
            response.setMessage(enumUtil.getTipMsg(typeCode,6));//提交成功
            logger.info("登录成功");
        }catch (Exception e){
            logger.error("登录失败异常：",e);
            response.setError(e.getMessage());
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,5));//提交失败
            response.setData("");
        }
        return response;
    }

    private static boolean validateEmail(String email){
        boolean ret = false;
        String format ="[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";//"\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
        if(email.matches(format)){
            ret = true;
        }
        return ret;
    }

    /**
     * 用户注册
     *
     * @param userInDto 用户信息
     * @return 操作结果
     */
    @RequestMapping("/register")
    public Response register(@RequestBody  UserInDto userInDto,HttpServletRequest request){
        addLogInfo(userInDto.getEmail(),request);
        Response response = new Response();
        String typeCode = userInDto.getTypeCode();
        try{
            String url = userInDto.getUrl();
            String email = userInDto.getEmail();
            String password = userInDto.getPassword();

            if(StringUtils.isEmpty(email)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//邮件不为空
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(!validateEmail(email)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//邮件格式错误
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(StringUtils.isEmpty(password)){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//密码不为空
                response.setStatus(0);
                response.setData("");
                return  response;
            }
            if(password.length()<8 || password.length()>20){
                response.setMessage(enumUtil.getTipMsg(typeCode,5));//密码不正确
                response.setStatus(0);
                response.setData("");
                return  response;
            }

            if(userService.findByEmailCount(userInDto)){
                response.setMessage(enumUtil.getTipMsg(typeCode,3));//该账号已被存在
                response.setStatus(0);//注册失败
                response.setData("");
            }else{
                if(StringUtils.isEmpty(url)){
                    response.setMessage(enumUtil.getTipMsg(typeCode,12));//您的头像，谢谢。
                    response.setStatus(0);
                    response.setData("");
                    return  response;
                }else{
                    userService.addRegisterUser(userInDto);
                    response.setMessage(enumUtil.getTipMsg(typeCode,6));//提交成功
                    response.setStatus(1);//注册成功
                    response.setData("");
                }
            }

        }catch (Exception e){
            response.setMessage(enumUtil.getTipMsg(typeCode,7));//提交失败
            response.setError(e.getMessage());
            response.setStatus(0);//注册失败
            response.setData("");
        }
        return response;
    }

    /**
     * 忘记密码发送邮件
     * @param userInDto
     * @return
     */
    @RequestMapping("/iForgetPasswordMail")
    public Response iForgetPasswordMail(@RequestBody  UserInDto userInDto,HttpServletRequest request){
        addLogInfo(userInDto.getEmail(),request);
        Response response = new Response();
        String typeCode = userInDto.getTypeCode();
        String email = userInDto.getEmail();

//        if(StringUtils.isEmpty(email)){
//            response.setStatus(0);
//            response.setData("");
//            response.setMessage(enumUtil.getTipMsg(typeCode,8));//邮箱不为空
//            return response;
//        }
//        if(!validateEmail(email)){
//            response.setStatus(0);
//            response.setData("");
//            response.setMessage(enumUtil.getTipMsg(typeCode,9));//邮件格式错误
//            return response;
//        }
        UserOutDto user = userService.findUserByEmail(email);
        if(user==null){
            response.setStatus(0);
            response.setData("");
            response.setMessage(enumUtil.getTipMsg(typeCode,15));//该账号不存在
            return response;
        }
        try {
            String secretKey= UUID.randomUUID().toString();  //密钥
            Timestamp outDate = new Timestamp(System.currentTimeMillis()+1*60*60*1000);//1小时后过期

//            users.setValidateCode(secretKey);
//            users.setRegisterDate(outDate);

           // long date = users.getRegisterDate().getTime()/1000*1000;                  //忽略毫秒数
            String key = user.getEmail()+"$"+secretKey;
            String digitalSignature = MD5Encode(key); //数字签名
//            users.setSign(digitalSignature);
            UserInDto inDto = new UserInDto();
            inDto.setEmail(email);
            inDto.setSign(digitalSignature);
            inDto.setValidateCode(secretKey);
            inDto.setRegisterDate(outDate);
//            BeanUtils.copyProperties(users,inDto);

            userService.updateUserInfo(inDto);

            String resetPassHref =  basePath+"login-service/user/resetPassword?sid="+ URLEncoder.encode(digitalSignature)+"&email="+ URLEncoder.encode(user.getEmail())+"&typeCode="+URLEncoder.encode(typeCode);
            sendTemplateMail(from,email,resetPassHref,typeCode);
            response.setStatus(1);
            response.setMessage(enumUtil.getTipMsg(typeCode,6));
            response.setData("");
        }catch(Exception e){
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,7));
            response.setData("");
            response.setError(e.getMessage());
        }
        return response;
    }

    public String MD5Encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");//确定计算方法
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));//加密后的字符串
        return newstr;
    }
//
    /**
     * 重置密码按钮点击链接接口
     * @param userInDto
     * @param response
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @RequestMapping("/resetPassword")//String sid,String email,String typeCode
    public Response checkResetLink(UserInDto userInDto,HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        addLogInfo(userInDto.getEmail(),request);
        Response  res = new Response();
        String sid =  userInDto.getSid();
        String email = userInDto.getEmail();
        String typeCode = userInDto.getTypeCode();
        sid = sid.replaceAll(" ", "+");
        String msg = "";
        if("zh-cn".equals(typeCode)||"zh-hk".equals(typeCode)){
            msg = URLEncoder.encode(enumUtil.getTipMsg(typeCode,14));
        }else{
            msg = enumUtil.getTipMsg(typeCode,14);
        }
        if(StringUtils.isEmpty(sid) || StringUtils.isEmpty(email)){
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
            String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }
        UserOutDto outDto = userService.findUserByEmail(email);
         if(outDto == null){
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
             String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }

         if(outDto.getEnabled()==1){
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
             String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }
        ValidateInfoInDto validateInfoInDto = new ValidateInfoInDto();
        validateInfoInDto.setEmail(email);
        validateInfoInDto.setSign(sid);
        ValidateInfoOutDto  validateInfoOutDto=validateInfoService.findValidateInfoByEmail(validateInfoInDto);
         if(validateInfoOutDto==null){
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
             String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }
        Timestamp outDate = validateInfoOutDto.getRegisterDate();
         if(outDate.getTime() <= System.currentTimeMillis()){//表示已经过期
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
             String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }
        String key = validateInfoOutDto.getEmail()+"$"+validateInfoOutDto.getValidateCode();          //数字签名
        String digitalSignature = MD5Encode(key);
        if(!digitalSignature.equals(sid)) {
            res.setStatus(0);
            res.setMessage(msg);
            res.setData("");
            String pageUrl = basePath+"resetResult.html?typeCode="+typeCode+"&message="+msg;
            response.sendRedirect(pageUrl);
            return res;
        }
            String pageUrl = basePath+"resetPassword.html?email="+URLEncoder.encode(email)+"&typeCode="+URLEncoder.encode(typeCode);
            response.sendRedirect(pageUrl);

            msg=enumUtil.getTipMsg(typeCode,6);
            res.setStatus(1);
            res.setMessage(msg);
            res.setData("");
        return res;
    }

    @RequestMapping("/updatePassword")
    public Response updatePassword(UserInDto userInDto,HttpServletRequest request){
        addLogInfo(userInDto.getEmail(),request);
        Response response = new Response();
        String decEmail = URLDecoder.decode(userInDto.getEmail());
        userInDto.setEmail(decEmail);
        String password = userInDto.getPassword();
        String decPassword =  URLDecoder.decode(password);
        String confirmPassword = userInDto.getConfirmPassword();
        String decConfirmPassword =  URLDecoder.decode(confirmPassword);
        String typeCode = userInDto.getTypeCode();
         if(!decPassword.equals(decConfirmPassword)){
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,18));
            response.setData("");
            return response;
        }
        try {
            String retPassword = encoder.encode(decPassword);
            userInDto.setPassword(retPassword);
            int ret = userService.updatePassword(userInDto);
            response.setStatus(1);
            response.setMessage(enumUtil.getTipMsg(typeCode,19));
            response.setData("");
            return response;
        }catch(Exception e){
            response.setStatus(0);
            response.setMessage(enumUtil.getTipMsg(typeCode,7));
            response.setData("");
            response.setError(e.getMessage());
            logger.error("异常信息："+e);
        }
        return response;
    }

}