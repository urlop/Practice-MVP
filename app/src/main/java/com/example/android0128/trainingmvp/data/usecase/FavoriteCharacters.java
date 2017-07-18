package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.CharactersRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.CharactersRepositoryCallback;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.List;

import io.reactivex.Observable;


public class FavoriteCharacters {//implements CharactersUseCaseCallback {

    //private GetCharactersDataResponse getCharactersDataResponse;
    private CharactersRepositoryCallback charactersRepositoryCallback;

    public FavoriteCharacters() {//GetCharactersDataResponse getCharactersDataResponse) {
        //this.getCharactersDataResponse = getCharactersDataResponse;
        charactersRepositoryCallback = new CharactersRepository();
    }

    public void saveCharacter(Character character) {
        charactersRepositoryCallback.saveCharacter(character);
    }

    public boolean isCharacterSaved(int id) {
        return charactersRepositoryCallback.isCharacterSaved(id);
    }

    public void deleteCharacter(int id) {
        charactersRepositoryCallback.deleteCharacter(id);
    }

    public void deleteAllCharacters() {
        charactersRepositoryCallback.deleteAllCharacters();
    }


    /*@Override
    public void onCharactersSuccess(ArrayList<Character> characters) {
        getCharactersDataResponse.onCharactersLoadSuccess(characters);
    }

    @Override
    public void onCharactersFailure(String message) {
        getCharactersDataResponse.onCharactersLoadFailure(message);
    }*/

    public Observable<List<Character>> loadCharacters() {
        return charactersRepositoryCallback.loadSavedCharacters();
    }
}
