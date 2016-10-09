package com.pulp.user.model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

    @Column(name = "data",length = 10000000)
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;
    @Column(name = "modification_time", nullable = false)

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    public Page(String name, Site site) {
        this.name = name;
        this.site=site;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @PrePersist
    public void prePersist() {
        this.modificationTime = DateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = DateTime.now();
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(DateTime modificationTime) {
        this.modificationTime = modificationTime;
    }
}
