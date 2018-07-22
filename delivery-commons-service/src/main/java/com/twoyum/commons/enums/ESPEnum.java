package com.twoyum.commons.enums;

/**
 *  西班牙文所有提示信息
 */
public enum ESPEnum {
    SUCCESSFULOPERATION("El éxito de la operación", 1),
    OPERATIONFAILED("El fracaso de la operación",2),
    LANGUAGETYPESARENOTEMPTY("Tipo de lenguaje no es nulo",3),
    UPLOADPERSONALPHOTOSUCCESS("Subir fotos personales de éxito",4),
    UPLOADINGPERSONALPHOTOSFAILED("El fracaso de subir fotos personales",5),
    UPLOADINGPERSONALPHOTOSNOTEMPTY("Subir fotos personales no vacía",6);

    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private ESPEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (ESPEnum c : ESPEnum.values()) {
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
