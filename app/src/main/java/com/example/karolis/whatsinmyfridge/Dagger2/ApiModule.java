package com.example.karolis.whatsinmyfridge.Dagger2;


import com.example.karolis.whatsinmyfridge.Managers.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ApiModule {


    @Provides
    @Singleton
    DatabaseManager provideDatabaseManager(){
        return new DatabaseManager(Realm.getDefaultInstance());
    }


}
