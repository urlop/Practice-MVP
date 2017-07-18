package com.example.android0128.trainingmvp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    public static final String MAIN_THREAD_HANDLER = "main_thread_handler";

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    @Singleton
    public Application provideQualityMattersApp() {
        return application;
    }

    @Provides
    @NonNull
    @Singleton
    public Gson provideGson(TypeAdapterFactory typeAdapterFactory) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    @Provides
    @NonNull
    @Named(MAIN_THREAD_HANDLER) @Singleton
    public Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /*@Provides
    @NonNull
    @Singleton
    public Picasso providePicasso(@NonNull Application qualityMattersApp, @NonNull OkHttpClient okHttpClient) {
        return new Picasso.Builder(qualityMattersApp)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }*/

    /*@Provides
    @NonNull
    @Singleton
    public QualityMattersImageLoader provideImageLoader(@NonNull Picasso picasso) {
        return new PicassoImageLoader(picasso);
    }*/
}
