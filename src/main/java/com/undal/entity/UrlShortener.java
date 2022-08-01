package com.undal.entity;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URI;

@Entity
public class UrlShortener {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String alias;
    private URI url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UrlShortener(String alias, URI url) {
        this.alias = alias;
        this.url = url;
    }


    public UrlShortener() {
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }
}
