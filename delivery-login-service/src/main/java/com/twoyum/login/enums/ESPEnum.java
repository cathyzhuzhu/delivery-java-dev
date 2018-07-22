package com.twoyum.login.enums;

/**
 *  西班牙文所有提示信息
 */
public enum ESPEnum {
    REGISTERSUCCESSFUL("Registro de éxito", 1),
    REGISTERFAILED("No de registro", 2),
    ACCOUNTALREADYEXISTS("la cuenta ya existe",3),
    LOGINSUCCESSFUL("Registro de éxito",4),
    WRONGEMAILORPASSWORD("Correo o contraseña equivocada",5),
    SUBMITTEDSUCCESSFULLY("Enviado con éxito",6),
    SUBMISSIONFAILED("Error al enviar",7),
    EMAILNOTNULL("El correo no vacía",8),
    EMAILISWRONG("Formato de mensaje de error",9),
    PASSWORDNOTNULL("La contraseña no es nulo",10),
    INCORRECTPASSWORD("Contraseña Incorrecta",11),
    YOURAVATAR("Tu avatar, gracias.",12),
    FORGETPASSWORDMAILTEXT("Hola, haz clic en el siguiente enlace para restablecer tu contraseña. Si no es usted, ignore este correo electrónico.El enlace caducará en 1 horas.",13),
    LINKHASEXPIREDRESUBMIT("El enlace ha expirado，Vuelva a enviarlo.",14),
    ACCOUNTNOTEXIST("La cuenta no existe",15),
    RESETPASSWORDTITLE("2Yum Restablecer Contraseña",16),
    RESETPASSWORDBUTTON("Restablecer Contraseña",17),
    PASSWORDSDONOTMATCH("Las contraseñas no coinciden",18),
    RESETPASSWORDSUCCESSFULLY("Restablecer contraseña exitosamente！",19);


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
