package com.example.gaoc10.coolweather.util;

/**
 * Created by gaoc10 on 2016/3/17 0017.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
