package com.pulp.user.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SiteEditForm {

    @Size(max = 30)
    @NotEmpty
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public SiteEditForm() {
    }

    public void setName(String name) {

        this.name = name;
    }


}

