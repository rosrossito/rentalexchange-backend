package com.upteam.auth.domain;

import javax.persistence.*;

/**
 * Created by opasichnyk on 11/25/2015.
 */
//Entity

@Entity
public class SystemUser {

@Id
@GeneratedValue
private Long id;

String login;
String email;
String image;
enum status {temporary, active, blocked, delete};
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
}
