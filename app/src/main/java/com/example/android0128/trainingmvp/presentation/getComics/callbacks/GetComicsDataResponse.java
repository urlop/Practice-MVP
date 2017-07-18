package com.example.android0128.trainingmvp.presentation.getComics.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public interface GetComicsDataResponse {
    void onComicsLoadSuccess(ArrayList<Comic> characters);

    void onComicsLoadFailure(String error);
}
