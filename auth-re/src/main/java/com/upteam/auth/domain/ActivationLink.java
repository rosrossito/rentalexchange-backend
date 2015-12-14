package com.upteam.auth.domain;

import javax.persistence.*;

/**
 * Created by opasichnyk on 12/4/2015.
 */
@NamedQuery(name="ActivationLink.findByUUID", query = "select u from ActivationLink u where u.linkUUID = :uuid")

@Entity
@Table(schema = "rental", name = "activationlink")
public class ActivationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    String linkUUID;

    @Column(name = "systemuser_id")
    long systemuser_id;

    public static enum Type {confirmRegistration, restorePassword};
    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

