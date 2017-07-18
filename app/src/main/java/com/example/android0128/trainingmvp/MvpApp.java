package com.example.android0128.trainingmvp;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.data.network.Services;
import com.example.android0128.trainingmvp.data.network.retrofit.RequestManager;
import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MvpApp extends Application {

    @Singleton
    @Component(modules = {ApplicationModule.class})
    public interface ApplicationComponent {

        @NonNull
        CharactersFragment.CharactersFragmentComponent plus(@NonNull CharactersFragment.CharactersFragmentModule charactersFragmentModule);

        void inject(@NonNull Activity activity);
    }

    @Inject
    ApplicationComponent applicationComponent;

    private static MvpApp instance;
    private Services services;

    public static MvpApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationComponent = prepareApplicationComponent().build();
        services = new RequestManager().getWebServices();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public Services getServices() {
        return services;
    }

    @NonNull
    protected DaggerMvpApp_ApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerMvpApp_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this));
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }
}
