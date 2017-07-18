package com.example.android0128.trainingmvp.data.usecase.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */
public interface CharactersUseCaseCallback {
    void onCharactersSuccess(ArrayList<Character> characters);

    void onCharactersFailure(String message);
}
