package com.twoyum.merchant.domain;

import lombok.Data;

@Data
public class TaxpayerIdOutDto {
    private String email;//用户邮件地址
    private String typeCode;//语言类型
    private String taxpayerId;//纳税人ID
    private int taxpayerIdType;//纳税人ID类型
    private int finishStatus;//状态

}
