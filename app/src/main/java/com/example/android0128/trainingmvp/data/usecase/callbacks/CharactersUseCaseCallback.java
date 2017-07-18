package com.example.android0128.trainingmvp.data.usecase.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;


public interface CharactersUseCaseCallback {
    void onCharactersSuccess(ArrayList<Character> characters);

    void onCharactersFailure(String message);
}
