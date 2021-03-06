package com.upteam.auth.domain;

import com.upteam.auth.domain.domainenum.SystemUserStatus;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Entity
@Table(name = "systemuser")
public class SystemUser {

    @JsonProperty("id")
    @Id
    @SequenceGenerator(name = "systemuserseq", sequenceName = "systemuser_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "systemuserseq")
    private long id;

    @JsonProperty("login")
    String login;

    @JsonProperty("email")
    String email;

    String image;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private SystemUserStatus status;

    String password;

    public SystemUser() {
    }

    public long getId() {
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

    public SystemUserStatus getStatus() {
        return status;
    }

    public void setStatus(SystemUserStatus status) {
        this.status = status;
    }
}
