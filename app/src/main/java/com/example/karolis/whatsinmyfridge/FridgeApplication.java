package com.example.karolis.whatsinmyfridge;


import android.app.Application;

import com.example.karolis.whatsinmyfridge.Dagger2.ApiModule;
import com.example.karolis.whatsinmyfridge.Dagger2.AppModule;
import com.example.karolis.whatsinmyfridge.Dagger2.DaggerMainComponent;
import com.example.karolis.whatsinmyfridge.Dagger2.MainComponent;

import io.realm.Realm;

public class FridgeApplication extends Application{

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .apiModule(new ApiModule())
                .build();
        mainComponent.inject(this);
        Realm.init(this);
    }

    public MainComponent getMainComponent(){
        return mainComponent;
    }
}
