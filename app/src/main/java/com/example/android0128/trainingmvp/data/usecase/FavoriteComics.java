package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.ComicsRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.ComicsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.ComicsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by Ruby on 08/02/2017.
 */

public class FavoriteComics implements ComicsUseCaseCallback {

    private GetComicsDataResponse getComicsDataResponse;
    private ComicsRepositoryCallback comicsRepositoryCallback;

    public FavoriteComics(GetComicsDataResponse getComicsDataResponse) {
        this.getComicsDataResponse = getComicsDataResponse;
        comicsRepositoryCallback = new ComicsRepository(this);
    }

    public void saveComic(Comic comic) {
        comicsRepositoryCallback.saveComic(comic);
    }
    public boolean isComicSaved(int id) {
        return comicsRepositoryCallback.isComicSaved(id);
    }
    public void deleteComic(int id) {
        comicsRepositoryCallback.deleteComic(id);
    }

    @Override
    public void onComicsSuccess(ArrayList<Comic> comics) {
        getComicsDataResponse.onComicsLoadSuccess(comics);
    }

    @Override
    public void onComicsFailure(String message) {
        getComicsDataResponse.onComicsLoadFailure(message);
    }

    public void loadComics() {
        comicsRepositoryCallback.loadSavedComics();
    }
}
