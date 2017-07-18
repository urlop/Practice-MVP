package com.example.android0128.trainingmvp.data.repositories.callbacks;

import com.example.android0128.trainingmvp.data.network.models.CharactersResponse;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */

public interface CharactersRepositoryCallback {
    Observable<List<Character>> loadCharacters(int offset, int limit);
    Observable<List<Character>> loadSavedCharacters();
    void saveCharacter(Character character);
    boolean isCharacterSaved(int id);
    void deleteCharacter(int id);
    void deleteAllCharacters();
}
