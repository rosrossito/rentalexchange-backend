package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by olegls2000 on 2/15/2016.
 */
public class TestVO {

    @JsonProperty("users")
    private List<UserInfoVO> users;

    public List<UserInfoVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfoVO> users) {
        this.users = users;
    }
}
