package ru.martha.autotesting.enums;

public enum RoleCode {
    ADMINISTRATOR(1),
    TESTER(2),
    JUNIOR_TESTER(3),
    SENIOR_TESTER(4);

    private final int code;

    RoleCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RoleCode getByCode(int code) {
        for (RoleCode c : values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return RoleCode.TESTER;
    }

    public String role() {
        return Integer.toString(code);
    }
}
