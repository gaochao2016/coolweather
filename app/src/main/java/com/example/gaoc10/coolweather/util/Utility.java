package com.example.gaoc10.coolweather.util;

import android.text.TextUtils;

import com.example.gaoc10.coolweather.db.CoolWeatherDB;
import com.example.gaoc10.coolweather.model.City;
import com.example.gaoc10.coolweather.model.County;
import com.example.gaoc10.coolweather.model.Province;

/**
 * Created by gaoc10 on 2016/3/17 0017.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB weatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String []allProvinces=response.split(",");
            if(allProvinces!=null&&allProvinces.length>0){
                for(String p:allProvinces){
                    String [] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    weatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCitiesResponse(CoolWeatherDB weatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if(allCities!=null&&allCities.length>0){
                for(String p:allCities){
                    String[] array=p.split("\\|");
                    City city=new City();
                    city.setProvinceId(provinceId);
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    weatherDB.saveCity(city);

                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCountiesResponse(CoolWeatherDB weatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties=response.split(",");
            if(allCounties!=null&&allCounties.length>0){
                for(String p:allCounties){
                    String[] array=p.split("\\|");
                    County county =new County();
                    county.setCityId(cityId);
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    weatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
