package com.pulp.user.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SiteForm {

    @Size(max = 30)
    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String pageName;

    public String getName() {
        return name;
    }

    public SiteForm() {
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
