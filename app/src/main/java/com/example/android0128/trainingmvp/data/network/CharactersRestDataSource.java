package com.example.android0128.trainingmvp.data.network;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.MvpApp;
import com.example.android0128.trainingmvp.data.callbacks.CharactersDataSource;
import com.example.android0128.trainingmvp.data.network.models.CharactersResponse;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.mappers.CharacterMapper;
import com.example.android0128.trainingmvp.utils.Constants;
import com.example.android0128.trainingmvp.utils.Utils;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public class CharactersRestDataSource implements CharactersDataSource {

    private Services services;

    public CharactersRestDataSource() {
        services = MvpApp.getInstance().getServices();
    }

    @Override
    public Observable<List<Character>> loadCharacters(int offset, int limit) {
        long timestamp = new Date().getTime();
        String hash = Utils.md5(timestamp + Constants.API_PRIVATE_KEY + Constants.API_KEY);
        /*services.getCharacters(timestamp, Constants.API_KEY, hash, limit, offset).enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if (response.isSuccessful()) {
                    callback.onCharactersLoaded(CharacterMapper.characterResultsToCharacters(response.body().getCharacters()));
                } else {
                    callback.onDataNotAvailable(response.message());
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });*/
        return services.getCharactersRx(timestamp, Constants.API_KEY, hash, limit, offset)
                //.map((Function<CharactersResponse, List<Character>>) charactersResponse -> CharacterMapper.characterResultsToCharacters(charactersResponse.getCharacters())).toObservable();
                .map(charactersResponse -> CharacterMapper.characterResultsToCharacters(charactersResponse.getCharacters()));
                /*.flatMap(new Function<CharactersResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@io.reactivex.annotations.NonNull CharactersResponse charactersResponse) throws Exception {
                        return CharacterMapper.characterResultsToCharacters(charactersResponse.getCharacters());
                    }
                });*/
                /*.flatMapIterable(charactersResponse -> {
                    List<Character> list = CharacterMapper.characterResultsToCharacters(charactersResponse.getCharacters());
                    return (Iterable<? extends List<Character>>) list;
                });*/
    }

    @Override
    public void deleteCharacter(@NonNull int id) {
        //Not implemented here
    }

    @Override
    public void deleteAllCharacters() {
        //Not implemented here
    }

    @Override
    public void saveCharacter(@NonNull Character character) {
        //Not implemented here
    }

    @Override
    public boolean isCharacterSaved(@NonNull int id) {
        //Not implemented here
        return false;
    }

}
