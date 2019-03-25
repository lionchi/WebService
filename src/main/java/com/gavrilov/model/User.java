package com.gavrilov.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotNull
    private Integer enable;
    private Set<String> roleNames = new HashSet<>();

    public User() {
    }

    public User(String login, String password, Integer enable) {
        this.login = login;
        this.password = password;
        this.enable = enable;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }
}
