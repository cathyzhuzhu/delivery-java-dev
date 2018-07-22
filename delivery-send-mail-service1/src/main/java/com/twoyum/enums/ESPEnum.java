package com.twoyum.enums;

/**
 *  西班牙文所有提示信息
 */
public enum ESPEnum {
    REGISTERSUCCESSFUL("Registro de éxito", 1),
    REGISTERFAILED("No de registro", 2),
    EMAILHASBEENUSED("La Dirección de correo electrónico ya está en uso",3),
    LOGINSUCCESSFUL("Registro de éxito",4),
    LOGINFAILED("La sesión fracasó",5),
    SENDEMAILSUCCESSFUL("Enviar un mensaje de éxito",6),
    SENDEMAILFAILED("No enviar el mensaje",7),
    EMAILNOTNULL("El correo no vacía",8),
    EMAILISWRONG("Formato de mensaje de error",9),
    PASSWORDNOTNULL("La contraseña no es nulo",10),
    PASSWORDISWRONG("La contraseña es incorrecta",11),
    UPLOADPHOTOSNOTEMPTY("Subir fotos no vacía",12),
    FORGETPASSWORDMAILTEXT("Hola, haz clic en el siguiente enlace para restablecer tu contraseña. Si no es usted, ignore este correo electrónico.El enlace caducará en 12 horas.",13),
    LINKHASEXPIREDRESUBMIT("El enlace ha expirado. Vuelva a enviarlo.",14),
    ACCOUNTNOTEXIST("La cuenta no existe",15);


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
