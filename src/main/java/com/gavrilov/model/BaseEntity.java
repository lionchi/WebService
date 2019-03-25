package com.gavrilov.model;

import javax.validation.constraints.NotNull;

public abstract class BaseEntity {
    @NotNull
    private Long id;

    BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
