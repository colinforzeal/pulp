package com.pulp.user.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by user on 09.10.2016.
 */
public class PageForm {
    @NotEmpty
    @NotNull
    private String pageName;

    public PageForm() {
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
