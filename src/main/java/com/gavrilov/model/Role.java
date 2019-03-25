package com.gavrilov.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Role extends BaseEntity {
    @NotEmpty
    private String name;
    @NotNull
    private Long userId;

    public Role() {
    }

    public Role(@NotEmpty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
