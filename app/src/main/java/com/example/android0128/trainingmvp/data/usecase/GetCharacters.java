package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.CharactersRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.CharactersRepositoryCallback;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.List;

import io.reactivex.Observable;


public class GetCharacters { //implements CharactersUseCaseCallback {

    //private GetCharactersDataResponse getCharactersDataResponse;
    private CharactersRepositoryCallback charactersRepositoryCallback;

    public GetCharacters() {//GetCharactersDataResponse getCharactersDataResponse) {
        //this.getCharactersDataResponse = getCharactersDataResponse;
        charactersRepositoryCallback = new CharactersRepository();//(this);
    }

    /*@Override
    public void onCharactersSuccess(ArrayList<Character> characters) {
        getCharactersDataResponse.onCharactersLoadSuccess(characters);
    }

    @Override
    public void onCharactersFailure(String message) {
        getCharactersDataResponse.onCharactersLoadFailure(message);
    }*/

    public Observable<List<Character>> loadCharacters(int offset, int limit) {
        return charactersRepositoryCallback.loadCharacters(offset, limit);
    }

}
