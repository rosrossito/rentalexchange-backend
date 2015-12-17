package com.upteam.auth.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by opasichnyk on 12/4/2015.
 */
@NamedQuery(name = "ActivationLink.findByUUID", query = "select u from ActivationLink u where u.uuid = :uuid")

@Entity
@Table(name = "activationlink")
public class ActivationLink {

    @Id
    @SequenceGenerator(name = "activationlinkseq", sequenceName = "activationlink_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activationlinkseq")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    String uuid;

    @Column(name = "systemuser_id")
    long systemuser_id;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LinkType type;

    @Column(name = "effectivedate")
    private LocalDateTime effectiveDate;


    public ActivationLink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getSystemuser_id() {
        return systemuser_id;
    }

    public void setSystemuser_id(long systemuser_id) {
        this.systemuser_id = systemuser_id;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public LinkType getType() {
        return type;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

