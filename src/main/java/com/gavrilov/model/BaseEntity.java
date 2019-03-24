package com.gavrilov.model;

public abstract class BaseEntity {
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
