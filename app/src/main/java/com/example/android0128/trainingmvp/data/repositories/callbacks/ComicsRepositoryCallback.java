package com.example.android0128.trainingmvp.data.repositories.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;


public interface ComicsRepositoryCallback {
    void loadComics(int offset, int limit);
    void loadSavedComics();
    void saveComic(Comic character);
    boolean isComicSaved(int id);
    void deleteComic(int id);
}
