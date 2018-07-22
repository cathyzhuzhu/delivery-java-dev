package com.twoyum.enums;


/**
 * 中文繁体所有提示信息
 */
public enum ZHHKEnum {
    REGISTERSUCCESSFUL("注册成功", 1),
    REGISTERFAILED("注册失敗", 2),
    EMAILHASBEENUSED("該郵寄地址已被使用",3),
    LOGINSUCCESSFUL("登入成功",4),
    LOGINFAILED("登入失敗",5),
    SENDEMAILSUCCESSFUL("發送郵件成功",6),
    SENDEMAILFAILED("發送郵件失敗",7),
    EMAILNOTNULL("郵件不為空",8),
    EMAILISWRONG("郵件格式錯誤",9),
    PASSWORDNOTNULL("密碼不為空",10),
    PASSWORDISWRONG("密碼格式錯誤",11),
    UPLOADPHOTOSNOTEMPTY("上傳照片不為空",12),
    FORGETPASSWORDMAILTEXT("您好，請點擊以下連結重設密碼。如果不是您本人，請忽略此郵件。該連結將在12小時後過期。",13),
    LINKHASEXPIREDRESUBMIT("連結已失效，請重新提交。",14),
    ACCOUNTNOTEXIST("該帳號不存在",15);

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
