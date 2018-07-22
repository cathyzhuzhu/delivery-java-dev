package com.twoyum.login.enums;


/**
 * 英文所有提示信息
 */
public enum ENEnum {
    REGISTERSUCCESSFUL("Register successful", 1),
    REGISTERFAILED("Register failed", 2),
    ACCOUNTALREADYEXISTS("Account already exists",3),
    LOGINSUCCESSFUL("Login successful",4),
    WRONGEMAILORPASSWORD("Wrong email or password",5),
    SUBMITTEDSUCCESSFULLY("Submitted Successfully",6),
    SUBMISSIONFAILED("Submission Failed",7),
    EMAILNOTNULL("Mail is not empty",8),
    EMAILISWRONG("Mail format error",9),
    PASSWORDNOTNULL("Password is not empty",10),
    INCORRECTPASSWORD("Incorrect Password",11),
    YOURAVATAR("Your avatar, thank you.",12),
    FORGETPASSWORDMAILTEXT("Hello, please click the link below to reset your password. If it is not you, please ignore this email.The link will expire in 1 hours.",13),
    LINKHASEXPIREDRESUBMIT("Link has expired，Please resubmit.",14),
    ACCOUNTNOTEXIST("The account does not exist",15),
    RESETPASSWORDTITLE("2Yum Reset Password",16),
    RESETPASSWORDBUTTON("Reset Password",17),
    PASSWORDSDONOTMATCH("Passwords do not match",18),
    RESETPASSWORDSUCCESSFULLY("Reset password successfully！",19);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ENEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ENEnum c : ENEnum.values()) {
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
