package com.example.android0128.trainingmvp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.data.network.Services;
import com.example.android0128.trainingmvp.data.network.retrofit.RequestManager;

import org.robolectric.RuntimeEnvironment;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.RealmCore;
import io.realm.log.RealmLog;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QualityMattersUnitTestApp extends MvpApp {

    public static MvpApp getInstance(){
        return (MvpApp)RuntimeEnvironment.application;
    }

    //@Override
    //public void onCreate() {
        /*
        // Setup Realm to be mocked. The order of these matters
        mock(RealmCore.class);
        mock(RealmLog.class);
        mock(Realm.class);
        mock(RealmConfiguration.class);
        Realm.init(RuntimeEnvironment.application);

        // Create the mock
        final Realm mockRealm = mock(Realm.class);
        final RealmConfiguration mockRealmConfig = mock(RealmConfiguration.class);

        // TODO: Better solution would be just mock the RealmConfiguration.Builder class. But it seems there is some
        // problems for powermock to mock it (static inner class). We just mock the RealmCore.loadLibrary(Context) which
        // will be called by RealmConfiguration.Builder's constructor.
        doNothing().when(RealmCore.class);
        RealmCore.loadLibrary(any(Context.class));


        // TODO: Mock the RealmConfiguration's constructor. If the RealmConfiguration.Builder.build can be mocked, this
        // is not necessary anymore.
        //when(RealmConfiguration.class).thenReturn(mockRealmConfig);

        // Anytime getInstance is called with any configuration, then return the mockRealm
        when(Realm.getDefaultInstance()).thenReturn(mockRealm);
        */
    //}

    @Override
    public Services getServices() {
        Services services = mock(Services.class);
        when(services.getCharactersRx(anyLong(), anyString(), anyString(), anyLong(), anyLong()));
        return services;
    }
}
