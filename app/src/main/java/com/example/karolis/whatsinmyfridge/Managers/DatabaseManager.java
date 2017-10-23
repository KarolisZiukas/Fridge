package com.example.karolis.whatsinmyfridge.Managers;


import com.example.karolis.whatsinmyfridge.FoodItemModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class DatabaseManager {

    private final Realm realm;

    public Realm getRealm() {
        return realm;
    }
    public DatabaseManager(Realm realm){
        this.realm = realm;
    }

    public RealmResults<FoodItemModel> getAllItems(){
        return Realm.getDefaultInstance().where(FoodItemModel.class).findAll();
    }

    public void insertNewItem(FoodItemModel foodItemModel){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(foodItemModel);
        realm.commitTransaction();
    }

    public void removeItem(String name){
        final RealmResults<FoodItemModel> foundItems = realm.where(FoodItemModel.class).equalTo("name", name).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                foundItems.deleteFirstFromRealm();
            }
        });
    }



}
