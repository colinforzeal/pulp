package com.pulp.user.model;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.persistence.*;


@Entity
@Table(name = "page")
public class Page {

    @Id
    @Column(name = "pageid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pageid;

    @Column(nullable = false)
    private String name;
    @Column
    private boolean isMainPage;

    @Column(name = "data")
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    public Page(String name, Site site) {
        this.name = name;

    }

    public Page(){}




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getPageid() {
        return pageid;
    }

    public void setPageid(Long pageid) {
        this.pageid = pageid;
    }

    public boolean isMainPage() {
        return isMainPage;
    }

    public void setMainPage(boolean mainPage) {
        isMainPage = mainPage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
