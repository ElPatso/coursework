package ua.lemekh.model;

public enum RoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_LECTURER("ROLE_LECTURER"),
    ROLE_STUDENT("ROLE_STUDENT");

    private String role;
    RoleEnum(String s) {
        this.role = s;
    }

    public String getRole() {
        return role;
    }

}
