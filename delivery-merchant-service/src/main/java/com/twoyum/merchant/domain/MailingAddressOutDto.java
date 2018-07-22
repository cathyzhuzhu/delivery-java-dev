package com.twoyum.merchant.domain;

import lombok.Data;

@Data
public class MailingAddressOutDto {
    private String email;//用户邮件地址
    private String typeCode;//语言类型
    private String countryId;//国家
    private String street;//街道
    private String roomNo;//公寓号
    private String city;//城市
    private String state;//州
    private String postCode;//邮编
    private int finishStatus;
}
