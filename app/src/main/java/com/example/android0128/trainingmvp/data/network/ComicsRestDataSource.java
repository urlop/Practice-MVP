package com.example.android0128.trainingmvp.data.network;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.MvpApp;
import com.example.android0128.trainingmvp.data.callbacks.ComicsDataSource;
import com.example.android0128.trainingmvp.data.network.models.ComicsResponse;
import com.example.android0128.trainingmvp.presentation.models.Comic;
import com.example.android0128.trainingmvp.presentation.models.mappers.ComicMapper;
import com.example.android0128.trainingmvp.utils.Constants;
import com.example.android0128.trainingmvp.utils.Utils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComicsRestDataSource implements ComicsDataSource {

    private Services services;

    public ComicsRestDataSource() {
        services = MvpApp.getInstance().getServices();
    }

    @Override
    public void loadComics(@NonNull final GetComicsCallback callback, int offset, int limit) {
        long timestamp = new Date().getTime();
        String hash = Utils.md5(timestamp + Constants.API_PRIVATE_KEY + Constants.API_KEY);
        services.getComics(timestamp, Constants.API_KEY, hash, limit, offset).enqueue(new Callback<ComicsResponse>() {
            @Override
            public void onResponse(Call<ComicsResponse> call, Response<ComicsResponse> response) {
                if (response.isSuccessful()) {
                    callback.onComicsLoaded(ComicMapper.comicResultsToComics(response.body().getComics()));
                } else {
                    callback.onDataNotAvailable(response.message());
                }
            }

            @Override
            public void onFailure(Call<ComicsResponse> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void deleteComic(@NonNull int id) {
        //Not implemented here
    }

    @Override
    public void saveComic(@NonNull Comic comic) {
        //Not implemented here
    }

    @Override
    public boolean isComicSaved(@NonNull int id) {
        //Not implemented here
        return false;
    }

}
