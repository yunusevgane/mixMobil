package com.financemoney.yoga.helper;

import java.io.Serializable;

public class Youga_helper implements Serializable {
    String id;
    String instruction;
    String yougaimage;
    String youganame;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getYougaimage() {
        return yougaimage;
    }

    public void setYougaimage(String yougaimage) {
        this.yougaimage = yougaimage;
    }

    public String getYouganame() {
        return youganame;
    }

    public void setYouganame(String youganame) {
        this.youganame = youganame;
    }
}
