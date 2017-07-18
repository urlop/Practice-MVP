package com.example.android0128.trainingmvp.data.db;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.data.callbacks.CharactersDataSource;
import com.example.android0128.trainingmvp.data.db.models.CharacterDB;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.mappers.CharacterMapper;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;


public class CharacterLocalDataSource implements CharactersDataSource {

    @Override
    public Observable<List<Character>> loadCharacters(int offset, int limit) {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults<CharacterDB> characterResults = realm.where(CharacterDB.class).findAll();
        return Observable.just(CharacterMapper.characterDBsToCharacters(characterResults));
    }

    @Override
    public void deleteCharacter(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<CharacterDB> results = realm.where(CharacterDB.class).equalTo("idValue", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void saveCharacter(@NonNull Character character) {
        final CharacterDB characterDB = CharacterMapper.characterToCharacterDB(character);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(characterDB);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
            }
        });
    }

    @Override
    public boolean isCharacterSaved(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(CharacterDB.class).equalTo("idValue", id).count() > 0;
    }

    public void deleteAllCharacters() {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<CharacterDB> results = realm.where(CharacterDB.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

}
