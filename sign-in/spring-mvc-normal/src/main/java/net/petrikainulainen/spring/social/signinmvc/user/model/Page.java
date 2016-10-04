package net.petrikainulainen.spring.social.signinmvc.user.model;

import javax.persistence.*;


@Entity
@Table(name = "page")
public class Page {

    @Id
    @Column(name = "PAGE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long page_id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false,unique = true)
    private String url_to_html;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID", nullable = false)
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
        return page_id;
    }

    public void setPageid(Long pageid) {
        this.page_id = pageid;
    }
}
