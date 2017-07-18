package com.example.android0128.trainingmvp.presentation.getCharacterDetail;

import com.example.android0128.trainingmvp.data.usecase.FavoriteCharacters;
import com.example.android0128.trainingmvp.presentation.getCharacterDetail.callbacks.GetCharacterDetailContract;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;


public class CharacterPresenter implements GetCharacterDetailContract.UserActionsListener { //GetCharactersDataResponse

    private GetCharacterDetailContract.View view;
    private FavoriteCharacters favoriteCharacters;
    private Character character;

    CharacterPresenter(GetCharacterDetailContract.View view, Character extraCharacter) {
        this.view = view;
        favoriteCharacters = new FavoriteCharacters();//(this);
        checkExtra(extraCharacter);
    }

    private void checkExtra(Character extraCharacter) {
        if (extraCharacter != null) {
            Character.Options defaultOptions = new Character.Options();
            defaultOptions.setItems(new ArrayList<Character.Options.Item>());
            extraCharacter.setComics(extraCharacter.getComics() != null ? extraCharacter.getComics() : defaultOptions);
            extraCharacter.setSeries(extraCharacter.getSeries() != null ? extraCharacter.getSeries() : defaultOptions);
            extraCharacter.setStories(extraCharacter.getStories() != null ? extraCharacter.getStories() : defaultOptions);
            extraCharacter.setEvents(extraCharacter.getEvents() != null ? extraCharacter.getEvents() : defaultOptions);
            character = extraCharacter;
            view.setCharacterView(extraCharacter);
        }
    }

    @Override
    public void requestCharacter() {

    }

    @Override
    public void setFavorite() {
        if (favoriteCharacters.isCharacterSaved(character.getId())) {
            favoriteCharacters.deleteCharacter(character.getId());
            character.setFavorite(false);
            view.changeFavorite(false);
        } else {
            favoriteCharacters.saveCharacter(character);
            character.setFavorite(true);
            view.changeFavorite(true);
        }
    }

    @Override
    public Character getResult() {
        return character;
    }

    /*@Override
    public void onCharactersLoadSuccess(ArrayList<Character> characters) {

    }

    @Override
    public void onCharactersLoadFailure(String error) {

    }*/
}
