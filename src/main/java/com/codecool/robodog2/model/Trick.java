package com.codecool.robodog2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Trick {
    @JsonIgnore
    private long id;
    private String name;

    public Trick(){

    }

    public Trick(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
