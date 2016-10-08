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
    @Column(nullable = false)
    private String url;
    @Column(nullable = false,unique = true)
    private String url_to_html;
    @Column
    private boolean isMainPage;

    @Column(name = "data")
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    public Page(String name, String url, String url_to_html) {
        this.name = name;
        this.url = url;
        this.url_to_html = url_to_html;
    }

    public Page(){}

    public Page(String name, String url_to_html, Site site) {
        this.name = name;
        this.url_to_html = url_to_html;
        this.site = site;
    }

    public Page(String name, String url, String url_to_html, Site site) {

        this.name = name;
        this.url = url;
        this.url_to_html = url_to_html;
        this.site = site;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_to_html() {
        return url_to_html;
    }

    public void setUrl_to_html(String url_to_html) {
        this.url_to_html = url_to_html;
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
