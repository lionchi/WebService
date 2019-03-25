package com.gavrilov.common;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class UserDetails {
    private String login;
    private Integer enable;
    private Set<String> roleNames = new HashSet<>();

    public UserDetails() {
    }

    public boolean userIsAuthorized() {
        return login != null  && enable != null && roleNames.size() > 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
