package com.codecool.robodog2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pedigree {
    @JsonIgnore
    long id;
    long momId;
    long dadId;
    long puppyId;

    public Pedigree(long momId, long dadId, long puppyId) {
        this.momId = momId;
        this.dadId = dadId;
        this.puppyId = puppyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMomId() {
        return momId;
    }

    public void setMomId(long momId) {
        this.momId = momId;
    }

    public long getDadId() {
        return dadId;
    }

    public void setDadId(long dadId) {
        this.dadId = dadId;
    }

    public long getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(long puppyId) {
        this.puppyId = puppyId;
    }
}
