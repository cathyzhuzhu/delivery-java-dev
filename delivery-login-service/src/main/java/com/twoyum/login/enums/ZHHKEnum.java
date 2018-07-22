package com.twoyum.login.enums;


/**
 * 中文繁体所有提示信息
 */
public enum ZHHKEnum {
    REGISTERSUCCESSFUL("注册成功", 1),
    REGISTERFAILED("注册失敗", 2),
    ACCOUNTALREADYEXISTS("該帳號已存在",3),
    LOGINSUCCESSFUL("登入成功",4),
    WRONGEMAILORPASSWORD("郵件或密碼錯誤",5),
    SUBMITTEDSUCCESSFULLY("提交成功",6),
    SUBMISSIONFAILED("提交失敗",7),
    EMAILNOTNULL("郵件不為空",8),
    EMAILISWRONG("郵件格式錯誤",9),
    PASSWORDNOTNULL("密碼不為空",10),
    INCORRECTPASSWORD("密碼不正確",11),
    YOURAVATAR("您的頭像，謝謝。",12),
    FORGETPASSWORDMAILTEXT("您好，請點擊以下連結重設密碼。如果不是您本人，請忽略此郵件。該連結將在1小時後過期。",13),
    LINKHASEXPIREDRESUBMIT("連結已失效，請重新提交。",14),
    ACCOUNTNOTEXIST("該帳號不存在",15),
    RESETPASSWORDTITLE("2Yum重設密碼",16),
    RESETPASSWORDBUTTON("重設密碼",17),
    PASSWORDSDONOTMATCH("密碼不匹配",18),
    RESETPASSWORDSUCCESSFULLY("重設密碼成功！",19);

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ZHHKEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ZHHKEnum c : ZHHKEnum.values()) {
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
