package com.example.android0128.trainingmvp;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersFragment;

import javax.inject.Singleton;

import dagger.Component;

//@Singleton
//@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent2 {

    @NonNull
    CharactersFragment.CharactersFragmentComponent plus(@NonNull CharactersFragment.CharactersFragmentModule itemsFragmentModule);

    void inject(@NonNull Activity activity);
}
