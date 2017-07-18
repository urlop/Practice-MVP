package com.example.android0128.trainingmvp.data.repositories.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */

public interface ComicsRepositoryCallback {
    void loadComics(int offset, int limit);
    void loadSavedComics();
    void saveComic(Comic character);
    boolean isComicSaved(int id);
    void deleteComic(int id);
}
