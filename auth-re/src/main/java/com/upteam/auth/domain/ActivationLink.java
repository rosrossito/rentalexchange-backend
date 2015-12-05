package com.upteam.auth.domain;

import javax.persistence.*;

/**
 * Created by opasichnyk on 12/4/2015.
 */
//Entity
public class ActivationLink {

    @Id
    @GeneratedValue
    private Long id;

    String linkUUID;
    long systemuser_id;
    enum type {confirmRegistration, restorePassword};

    public ActivationLink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkUUID() {
        return linkUUID;
    }

    public void setLinkUUID(String linkUUID) {
        this.linkUUID = linkUUID;
    }

    public long getSystemuser_id() {
        return systemuser_id;
    }

    public void setSystemuser_id(long systemuser_id) {
        this.systemuser_id = systemuser_id;
    }
}
