package com.twoyum.merchant.domain;

import lombok.Data;

@Data
public class ManagerOutDto {
    private String email;//用户邮件地址
    private String typeCode;//语言类型
    private int managerSex;//管理员性别
    private String managerName;//管理员姓名
    private String managerPhone;//管理员电话
    private String managerEmail;//管理员email
    private int finishStatus;//状态


}
