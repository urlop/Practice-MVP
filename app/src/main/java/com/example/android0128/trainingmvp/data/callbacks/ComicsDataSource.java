package com.example.android0128.trainingmvp.data.callbacks;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public interface ComicsDataSource {

    interface GetComicsCallback {

        void onComicsLoaded(ArrayList<Comic> actor);

        void onDataNotAvailable(String message);
    }

    void loadComics(@NonNull GetComicsCallback callback, int offset, int limit);

    void deleteComic(@NonNull int id);

    void saveComic(@NonNull Comic comic);

    boolean isComicSaved(@NonNull int id);

}
