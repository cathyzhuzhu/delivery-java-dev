package com.twoyum.commons.enums;

/**
 * 中文简体所有提示信息
 */
public enum ZHCNEnum{
    SUCCESSFULOPERATION("操作成功", 1),
    OPERATIONFAILED("操作失败",2),
    LANGUAGETYPESARENOTEMPTY("语言类型不为空",3),
    UPLOADPERSONALPHOTOSUCCESS("上传个人照片成功",4),
    UPLOADINGPERSONALPHOTOSFAILED("上传个人照片失败",5),
    UPLOADINGPERSONALPHOTOSNOTEMPTY("上传个人照片不为空",6);
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
