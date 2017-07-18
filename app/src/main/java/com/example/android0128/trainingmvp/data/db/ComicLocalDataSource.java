package com.example.android0128.trainingmvp.data.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android0128.trainingmvp.data.callbacks.ComicsDataSource;
import com.example.android0128.trainingmvp.data.db.models.ComicDB;
import com.example.android0128.trainingmvp.presentation.models.Comic;
import com.example.android0128.trainingmvp.presentation.models.mappers.ComicMapper;

import io.realm.Realm;
import io.realm.RealmResults;


public class ComicLocalDataSource implements ComicsDataSource {

    @Override
    public void loadComics(@NonNull GetComicsCallback callback, @Nullable int offset, @Nullable int limit) {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults<ComicDB> comicResults = realm.where(ComicDB.class).findAll();
        callback.onComicsLoaded(ComicMapper.comicDBsToComics(comicResults));
    }

    @Override
    public void deleteComic(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<ComicDB> results = realm.where(ComicDB.class).equalTo("idValue", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void saveComic(@NonNull Comic comic) {
        final ComicDB comicDB = ComicMapper.comicToComicDB(comic);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(comicDB);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
            }
        });
    }

    @Override
    public boolean isComicSaved(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(ComicDB.class).equalTo("idValue", id).count() > 0;
    }

}
