package com.example.gaoc10.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gaoc10.coolweather.model.City;
import com.example.gaoc10.coolweather.model.County;
import com.example.gaoc10.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoc10 on 2016/3/15 0015.
 */
public class CoolWeatherDB {
    public static final String DB_NAME="cool_weather";
    public static final int VERSION=1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    public CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getReadableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB==null){
            coolWeatherDB=new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province){
        ContentValues values=new ContentValues();
        values.put("province_name",province.getProvinceName());
        values.put("province_code",province.getProvinceCode());
        db.insert("Province",null,values);
    }

    public List<Province> loadProvinces(){
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        List<Province> result=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                result.add(province);

            }while(cursor.moveToNext());
        }
        return result;
    }

    public void saveCity(City city){
        ContentValues values=new ContentValues();
        values.put("city_name",city.getCityName());
        values.put("city_code", city.getCityCode());
        values.put("province_Id",city.getProvinceId());
        db.insert("City",null,values);
    }

    public List<City> loadCities(int provinceId){
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null);
        List<City> result=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                result.add(city);

            }while(cursor.moveToNext());
        }
        return result;
    }

    public void saveCounty(County county){
        ContentValues values=new ContentValues();
        values.put("county_name",county.getCountyName());
        values.put("county_code",county.getCountyCode());
        values.put("city_id",county.getCityId());
        db.insert("county",null,values);
    }

    public List<County> loadCounties(int cityId){
        Cursor cursor=db.query("county",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        List<County> result=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                result.add(county);

            }while(cursor.moveToNext());
        }
        return result;
    }
}
