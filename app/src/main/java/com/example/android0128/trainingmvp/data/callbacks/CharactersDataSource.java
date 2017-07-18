package com.example.android0128.trainingmvp.data.callbacks;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public interface CharactersDataSource {

    interface GetCharactersCallback {

        void onCharactersLoaded(ArrayList<Character> actor);

        void onDataNotAvailable(String message);
    }

    Observable<List<Character>> loadCharacters(int offset, int limit); //@NonNull GetCharactersCallback callback, int offset, int limit);

    void deleteCharacter(@NonNull int id);

    void deleteAllCharacters();

    void saveCharacter(@NonNull Character character);

    boolean isCharacterSaved(@NonNull int id);

}
