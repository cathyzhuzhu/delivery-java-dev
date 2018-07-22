package com.twoyum.login.enums;

/**
 * 中文简体所有提示信息
 */
public enum ZHCNEnum{
    REGISTERSUCCESSFUL("注册成功", 1),
    REGISTERFAILED("注册失败", 2),
    ACCOUNTALREADYEXISTS("该账号已存在",3),
    LOGINSUCCESSFUL("登录成功",4),
    WRONGEMAILORPASSWORD("邮件或密码错误",5),
    SUBMITTEDSUCCESSFULLY("提交成功",6),
    SUBMISSIONFAILED("提交失败",7),
    EMAILNOTNULL("邮件不为空",8),
    EMAILISWRONG("邮件格式错误",9),
    PASSWORDNOTNULL("密码不为空",10),
    incorrectpassword("密码不正确",11),
    YOURAVATAR("您的头像，谢谢。",12),
    FORGETPASSWORDMAILTEXT("您好，请点击以下链接重设密码。如果不是您本人，请忽略此邮件。该链接将在1小时后过期。",13),
    LINKHASEXPIREDRESUBMIT("链接已失效，请重新提交。",14),
    ACCOUNTNOTEXIST("该账号不存在",15),
    RESETPASSWORDTITLE("2Yum重设密码",16),
    RESETPASSWORDBUTTON("重设密码",17),
    PASSWORDSDONOTMATCH("密码不匹配",18),
    RESETPASSWORDSUCCESSFULLY("重设密码成功！",19);

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
