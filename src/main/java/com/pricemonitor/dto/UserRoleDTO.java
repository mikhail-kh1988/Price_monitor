package com.pricemonitor.dto;

public class UserRoleDTO {

    private String login;
    private String roleName;

    public UserRoleDTO(String login, String roleName) {
        this.login = login;
        this.roleName = roleName;
    }

    public UserRoleDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
