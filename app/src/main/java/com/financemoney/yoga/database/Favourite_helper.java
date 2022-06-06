package com.financemoney.yoga.database;

public class Favourite_helper {
    String id;
    String isfav;
    String position;

    public Favourite_helper(String id, String isfav, String position) {
        this.id = id;
        this.isfav = isfav;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsfav() {
        return isfav;
    }

    public void setIsfav(String isfav) {
        this.isfav = isfav;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
