package com.gavrilov.model;

import javax.validation.constraints.NotEmpty;

public class Url extends BaseEntity {
    @NotEmpty
    private String description;
    @NotEmpty
    private String value;

    public Url() {
        super();
    }

    public Url(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }
}
