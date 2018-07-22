package com.twoyum.commons.enums;


/**
 * 中文繁体所有提示信息
 */
public enum ZHHKEnum {
    SUCCESSFULOPERATION("操作成功", 1),
    OPERATIONFAILED("操作失敗",2),
    LANGUAGETYPESARENOTEMPTY("語言類型不為空",3),
    UPLOADPERSONALPHOTOSUCCESS("上傳個人照片成功",4),
    UPLOADINGPERSONALPHOTOSFAILED("上傳個人照片失敗",5),
    UPLOADINGPERSONALPHOTOSNOTEMPTY("上傳個人照片不為空",6);
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
