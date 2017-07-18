package com.example.android0128.trainingmvp.data.repositories;

import com.example.android0128.trainingmvp.data.callbacks.CharactersDataSource;
import com.example.android0128.trainingmvp.data.db.CharacterLocalDataSource;
import com.example.android0128.trainingmvp.data.network.CharactersRestDataSource;
import com.example.android0128.trainingmvp.data.network.models.CharactersResponse;
import com.example.android0128.trainingmvp.data.repositories.callbacks.CharactersRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.CharactersUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.mappers.CharacterMapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import kotlin.jvm.functions.Function1;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public class CharactersRepository implements CharactersRepositoryCallback {

    private CharactersDataSource charactersLocalDataSource;
    private CharactersDataSource charactersRestDataSource;
    //private CharactersUseCaseCallback charactersUseCaseCallback;

    public CharactersRepository(){//CharactersUseCaseCallback charactersUseCaseCallback) {
        //this.charactersUseCaseCallback = charactersUseCaseCallback;
        this.charactersLocalDataSource = new CharacterLocalDataSource();
        this.charactersRestDataSource = new CharactersRestDataSource();
    }

    @Override
    public Observable<List<Character>> loadCharacters(int offset, int limit) {
        return getCharactersFromRemote(offset, limit);
    }

    @Override
    public Observable<List<Character>> loadSavedCharacters() {
        return getLocalCharacters();
    }

    @Override
    public void saveCharacter(Character character) {
        charactersLocalDataSource.saveCharacter(character);
    }

    @Override
    public boolean isCharacterSaved(int id) {
        return charactersLocalDataSource.isCharacterSaved(id);
    }

    @Override
    public void deleteCharacter(int id) {
        charactersLocalDataSource.deleteCharacter(id);
    }

    @Override
    public void deleteAllCharacters() {
        charactersLocalDataSource.deleteAllCharacters();
    }

    private Observable<List<Character>> getLocalCharacters() {
        return charactersLocalDataSource.loadCharacters(0, 0)
                .map(characters -> {
                    for (Character character : characters) {
                        character.setFavorite(true);
                    }
                    return characters;
                });
        /*charactersLocalDataSource.loadCharacters(new CharactersDataSource.GetCharactersCallback() {
            @Override
            public void onCharactersLoaded(ArrayList<Character> characters) {
                //Set property isFavorite
                for (Character character : characters) {
                    character.setFavorite(true);
                }
                charactersUseCaseCallback.onCharactersSuccess(characters);
            }

            @Override
            public void onDataNotAvailable(String message) {
                charactersUseCaseCallback.onCharactersFailure(message);
            }
        }, 0, 0);*/
    }

    private Observable<List<Character>> getCharactersFromRemote(int offset, int limit) {
        return Observable.combineLatest(charactersRestDataSource.loadCharacters(offset, limit), charactersLocalDataSource.loadCharacters(0, 0),
                new BiFunction<List<Character>, List<Character>, List<Character>>() {
                    @Override
                    public List<Character> apply(@NonNull List<Character> characters, @NonNull List<Character> charactersLocal) throws Exception {
                        //Set property isFavorite
                        for (Character character : characters) {
                            for (Character characterLocal : charactersLocal) {
                                if (character.getId() == characterLocal.getId()) {
                                    character.setFavorite(true);
                                }
                            }
                        }
                        return characters;
                    }
                });

        //return charactersRestDataSource.loadCharacters(offset, limit);
        /*charactersRestDataSource.loadCharacters(new CharactersDataSource.GetCharactersCallback() {
            @Override
            public void onCharactersLoaded(final ArrayList<Character> characters) {
                charactersLocalDataSource.loadCharacters(new CharactersDataSource.GetCharactersCallback() {
                    @Override
                    public void onCharactersLoaded(ArrayList<Character> charactersLocal) {
                        //Set property isFavorite
                        for (Character character : characters) {
                            for (Character characterLocal : charactersLocal) {
                                if (character.getId() == characterLocal.getId()) {
                                    character.setFavorite(true);
                                }
                            }
                        }
                        charactersUseCaseCallback.onCharactersSuccess(characters);
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        charactersUseCaseCallback.onCharactersFailure("Problem obtaining favorites from DB");
                    }
                }, 0, 0);
            }

            @Override
            public void onDataNotAvailable(String message) {
                charactersUseCaseCallback.onCharactersFailure(message);
            }
        }, offset, limit);*/
    }

}
