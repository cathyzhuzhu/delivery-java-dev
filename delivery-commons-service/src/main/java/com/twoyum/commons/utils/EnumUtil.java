package com.twoyum.commons.utils;


import com.twoyum.commons.enums.ENEnum;
import com.twoyum.commons.enums.ESPEnum;
import com.twoyum.commons.enums.ZHCNEnum;
import com.twoyum.commons.enums.ZHHKEnum;
import org.springframework.stereotype.Component;

@Component
public class EnumUtil {
    public String getTipMsg(String type,int code){
        String msg  = "";
        if("zh-cn".equalsIgnoreCase(type)){
            msg = ZHCNEnum.getName(code);
        }else if("zh-hk".equalsIgnoreCase(type)){
            msg = ZHHKEnum.getName(code);
        }else if("en".equalsIgnoreCase(type)){
            msg = ENEnum.getName(code);
        }else if("esp".equalsIgnoreCase(type)){
            msg = ESPEnum.getName(code);
        }
        return msg;
    }
}
