package com.example.karolis.whatsinmyfridge.Dagger2;

import com.example.karolis.whatsinmyfridge.FridgeApplication;
import com.example.karolis.whatsinmyfridge.ItemsListFragment;
import com.example.karolis.whatsinmyfridge.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);

    void inject(FridgeApplication fridgeApplication);

    void inject(ItemsListFragment itemsListFragment);
}
