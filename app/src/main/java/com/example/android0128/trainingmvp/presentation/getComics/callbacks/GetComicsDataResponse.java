package com.example.android0128.trainingmvp.presentation.getComics.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;


public interface GetComicsDataResponse {
    void onComicsLoadSuccess(ArrayList<Comic> characters);

    void onComicsLoadFailure(String error);
}
