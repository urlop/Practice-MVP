package com.example.android0128.trainingmvp.presentation.getCharacters.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;


public interface GetCharactersDataResponse {
    void onCharactersLoadSuccess(ArrayList<Character> characters);

    void onCharactersLoadFailure(String error);
}
