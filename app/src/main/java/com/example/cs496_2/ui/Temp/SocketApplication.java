package com.example.cs496_2.ui.Temp;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;


public class SocketApplication extends Application {
    private static SocketApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, "fea2bbbf76a6c2fc180a859de172c406");
    }
}