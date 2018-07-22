package com.twoyum.enums;


/**
 * 中文简体所有提示信息
 */
public enum ZHCNEnum{
    REGISTERSUCCESSFUL("注册成功", 1),
    REGISTERFAILED("注册失败", 2),
    EMAILHASBEENUSED("该邮件地址已被使用",3),
    LOGINSUCCESSFUL("登录成功",4),
    LOGINFAILED("登录失败",5),
    SENDEMAILSUCCESSFUL("发送邮件成功",6),
    SENDEMAILFAILED("发送邮件失败",7),
    EMAILNOTNULL("邮件不为空",8),
    EMAILISWRONG("邮件格式错误",9),
    PASSWORDNOTNULL("密码不为空",10),
    PASSWORDISWRONG("密碼格式错误",11),
    UPLOADPHOTOSNOTEMPTY("上传照片不为空",12),
    FORGETPASSWORDMAILTEXT("您好，请点击以下链接重设密码。如果不是您本人，请忽略此邮件。该链接将在12小时后过期。",13),
    LINKHASEXPIREDRESUBMIT("链接已失效，请重新提交",14),
    ACCOUNTNOTEXIST("该账号不存在",15);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ZHCNEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ZHCNEnum c : ZHCNEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
