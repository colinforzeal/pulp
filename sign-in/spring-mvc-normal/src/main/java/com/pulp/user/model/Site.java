package com.pulp.user.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "site")
public class Site {

    @Id
    @Column(name="site_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long site_id;


    @Column(nullable = false,unique = true)
    private String name;
    @Column(name = "modification_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Site(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @OneToMany(mappedBy = "site",cascade = CascadeType.REMOVE)
    private List<Page> pages;

    public Site(){}


//
    public Site(String name, List<Page> pages) {
        this.name = name;
        this.pages = pages;
    }

    public Site(String name) {
        this.name = name;
    }

    public Long getId() {
        return site_id;
    }

    public void setId(Long id) {
        this.site_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Site[id=%d, name='%s']%n",
                site_id, name);
        if (pages != null) {
            for(Page page : pages) {
                result += String.format(
                        "Page[id=%d, name='%s']%n",
                        page.getPageid(), page.getName());
            }
        }

        return result;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
