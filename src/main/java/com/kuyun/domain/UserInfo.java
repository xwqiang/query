package com.kuyun.domain;

/**
 * Created by xuwuqiang on 2019/1/29.
 */
public class UserInfo {

    private Long id;
    private String name;

    public UserInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
