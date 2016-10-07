package com.pulp.user.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "site")
public class Site {

    @Id
    @Column(name="SITE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long site_id;


    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Site(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @OneToMany(mappedBy = "site",cascade = CascadeType.ALL)
    private Set<Page> pages;

    public Site(){}


//
    public Site(String name, Set<Page> pages) {
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

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
