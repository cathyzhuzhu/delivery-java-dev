package com.twoyum.commons.enums;


/**
 * 英文所有提示信息
 */
public enum ENEnum {
    SUCCESSFULOPERATION("Successful operation", 1),
    OPERATIONFAILED("operation failed", 2),
    LANGUAGETYPESARENOTEMPTY("Language types are not empty",3),
    UPLOADPERSONALPHOTOSUCCESS("Upload personal photo success",4),
    UPLOADINGPERSONALPHOTOSFAILED("Uploading personal photos failed",5),
    UPLOADINGPERSONALPHOTOSNOTEMPTY("Uploading personal photos is not empty",6);

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
