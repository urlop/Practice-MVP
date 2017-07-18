package com.example.android0128.trainingmvp.data.repositories;

import com.example.android0128.trainingmvp.data.callbacks.ComicsDataSource;
import com.example.android0128.trainingmvp.data.db.ComicLocalDataSource;
import com.example.android0128.trainingmvp.data.network.ComicsRestDataSource;
import com.example.android0128.trainingmvp.data.repositories.callbacks.ComicsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.ComicsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public class ComicsRepository implements ComicsRepositoryCallback {

    private ComicsDataSource comicsLocalDataSource;
    private ComicsDataSource comicsRestDataSource;
    private ComicsUseCaseCallback comicsUseCaseCallback;

    public ComicsRepository(ComicsUseCaseCallback comicsUseCaseCallback) {
        this.comicsUseCaseCallback = comicsUseCaseCallback;
        this.comicsLocalDataSource = new ComicLocalDataSource();
        this.comicsRestDataSource = new ComicsRestDataSource();
    }

    @Override
    public void loadComics(int offset, int limit) {
        getComicsFromRemote(offset, limit);
    }

    @Override
    public void loadSavedComics() {
        getLocalComics();
    }

    @Override
    public void saveComic(Comic comic) {
        comicsLocalDataSource.saveComic(comic);
    }

    @Override
    public boolean isComicSaved(int id) {
        return comicsLocalDataSource.isComicSaved(id);
    }

    @Override
    public void deleteComic(int id) {
        comicsLocalDataSource.deleteComic(id);
    }

    private void getLocalComics() {
        comicsLocalDataSource.loadComics(new ComicsDataSource.GetComicsCallback() {
            @Override
            public void onComicsLoaded(ArrayList<Comic> comics) {
                //Set property isFavorite
                for (Comic comic : comics) {
                    comic.setFavorite(true);
                }
                comicsUseCaseCallback.onComicsSuccess(comics);
            }

            @Override
            public void onDataNotAvailable(String message) {
                comicsUseCaseCallback.onComicsFailure(message);
            }
        }, 0, 0);
    }

    private void getComicsFromRemote(int offset, int limit) {
        comicsRestDataSource.loadComics(new ComicsDataSource.GetComicsCallback() {
            @Override
            public void onComicsLoaded(final ArrayList<Comic> comics) {
                comicsLocalDataSource.loadComics(new ComicsDataSource.GetComicsCallback() {
                    @Override
                    public void onComicsLoaded(ArrayList<Comic> comicsLocal) {
                        //Set property isFavorite
                        for (Comic comic : comics) {
                            for (Comic comicLocal : comicsLocal) {
                                if (comic.getId() == comicLocal.getId()) {
                                    comic.setFavorite(true);
                                }
                            }
                        }
                        comicsUseCaseCallback.onComicsSuccess(comics);
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        comicsUseCaseCallback.onComicsFailure(message);
                    }
                }, 0, 0);
            }

            @Override
            public void onDataNotAvailable(String message) {
                comicsUseCaseCallback.onComicsFailure(message);
            }
        }, offset, limit);
    }

}
