package ua.lemekh.model;

public enum RoleEnum {
    ADMIN("ADMIN"),
    STUDENT("STUDENT");

    private String role;
    RoleEnum(String s) {
        this.role = s;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
