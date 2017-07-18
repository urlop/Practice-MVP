package com.example.android0128.trainingmvp.data.usecase.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */
public interface ComicsUseCaseCallback {
    void onComicsSuccess(ArrayList<Comic> characters);

    void onComicsFailure(String message);
}
