package com.upteam.auth.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by opasichnyk on 12/4/2015.
 */
@Entity
@Table(name = "activationlink")
@NamedQueries({
        @NamedQuery(name = "ActivationLink.findByUUID", query = "select u from ActivationLink u where u.uuid = :uuid"),
        @NamedQuery(name = "ActivationLink.findBySystemUserID", query = "select c from ActivationLink c where c.systemuserId = :systemuser_id")
})
public class ActivationLink {

    @Id
    @SequenceGenerator(name = "activationlinkseq", sequenceName = "activationlink_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activationlinkseq")
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "systemuser_id")
    private Long systemuserId;

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

    public Long getSystemuserId() {
        return systemuserId;
    }

    public void setSystemuserId(Long systemuserId) {
        this.systemuserId = systemuserId;
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

