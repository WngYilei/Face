package com.xl.entity;

import android.content.Intent;

import javax.xml.transform.Result;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class Status {
    private Boolean Result;
    private String sta;

    public Status(Boolean result, String sta) {
        Result = result;
        this.sta = sta;
    }
    public Status() {

    }

    public Boolean getResult() {
        return Result;
    }

    public void setResult(Boolean result) {
        Result = result;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }
}
