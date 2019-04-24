package com.ezops.assignments.titanic.entity;

import com.ezops.assignments.titanic.utils.JSONUtils;

import java.util.List;

public class BarChartSeriesEntity<T> {


    private String name;
    private List<T> data;

    public BarChartSeriesEntity(String name, List<T> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
