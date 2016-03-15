package com.example.gaoc10.coolweather.model;

/**
 * Created by gaoc10 on 2016/3/15 0015.
 */
public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
