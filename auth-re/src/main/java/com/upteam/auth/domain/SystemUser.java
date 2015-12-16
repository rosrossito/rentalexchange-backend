package com.upteam.auth.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Entity
@Table(name = "systemuser")
@NamedQuery(name="systemUser.findByMail", query = "select u from SystemUser u where u.email = :email")
public class SystemUser {

    @SequenceGenerator(name = "systemuserseq", sequenceName = "systemuser_seq", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "systemuserseq")
    private Long id;

    String login;

    String email;

    String image;

    @Enumerated(EnumType.STRING)
    private Status status;

    String password;

    public SystemUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
