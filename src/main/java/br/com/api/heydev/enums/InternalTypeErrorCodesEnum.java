package br.com.api.heydev.enums;

public enum InternalTypeErrorCodesEnum {
    E500000("500.000", "Internal error without mapped cause."),
    E100000("410.000","user not found."),
    E410001("410.001", "username already exists."),
    E410002("410.002", "email already exists."),
    E410003("410.003","validation error type."),
    E410004("410.004","please enter a username for the account."),
    E410005("410.005", "please enter a email for the account."),
    E410006("410.006","please enter a password for the account."),
    E410007("410.007", "please enter a valid email."),
    E410008("410.008", "username must be a minimum of 3 characters and a maximum of 18 characters."),
    E410009("410.009", "password must be at least 8 characters long.")
    ;

    private final String code;
    private final String message;

    InternalTypeErrorCodesEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getValue() {
        return this.name();
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("Fault code: %s = %s.", getMessage());
    }
}
