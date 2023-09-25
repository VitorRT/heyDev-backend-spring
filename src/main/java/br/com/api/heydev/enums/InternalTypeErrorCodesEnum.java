package br.com.api.heydev.enums;

public enum InternalTypeErrorCodesEnum {
    E500000("500.000", "Internal error without mapped cause."),
    E410000("410.000","user not found."),
    E410001("410.001", "username already exists."),
    E410002("410.002", "email already exists."),
    E410003("410.003","validation error type."),
    E410004("410.004","please enter a username for the account."),
    E410005("410.005", "please enter a email for the account."),
    E410006("410.006","please enter a password for the account."),
    E410007("410.007", "please enter a valid email."),
    E410008("410.008", "username must be a minimum of 3 characters and a maximum of 18 characters."),
    E410009("410.009", "password must be at least 8 characters long."),
    E410010("410.010","please enter a social name for the profile."),
    E410011("410.011", "social name must be a minimum of 4 characters and a maximum of 40 characters."),
    E410012("410.012", "please send an image."),
    E410013("410.013", "an error occurred while uploading the image."),
    E410014("410.014", "profile not found."),
    E410015("410.015", "please send a file in the parameter called \"file\".")
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
