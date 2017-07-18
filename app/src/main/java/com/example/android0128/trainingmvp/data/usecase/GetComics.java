package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.ComicsRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.ComicsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.ComicsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public class GetComics implements ComicsUseCaseCallback {

    private GetComicsDataResponse getComicsDataResponse;
    private ComicsRepositoryCallback comicsRepositoryCallback;

    public GetComics(GetComicsDataResponse getComicsDataResponse) {
        this.getComicsDataResponse = getComicsDataResponse;
        comicsRepositoryCallback = new ComicsRepository(this);
    }

    @Override
    public void onComicsSuccess(ArrayList<Comic> comics) {
        getComicsDataResponse.onComicsLoadSuccess(comics);
    }

    @Override
    public void onComicsFailure(String message) {
        getComicsDataResponse.onComicsLoadFailure(message);
    }

    public void loadComics(int offset, int limit) {
        comicsRepositoryCallback.loadComics(offset, limit);
    }

}
