package com.example.android0128.trainingmvp.data.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android0128.trainingmvp.data.callbacks.EventsDataSource;
import com.example.android0128.trainingmvp.data.db.models.EventDB;
import com.example.android0128.trainingmvp.presentation.models.Event;
import com.example.android0128.trainingmvp.presentation.models.mappers.EventMapper;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by RubyMobile-1 on 25/01/2017.
 */

public class EventLocalDataSource implements EventsDataSource {

    @Override
    public void loadEvents(@NonNull GetEventsCallback callback, @Nullable int offset, @Nullable int limit) {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults<EventDB> eventResults = realm.where(EventDB.class).findAll();
        callback.onEventsLoaded(EventMapper.eventDBsToEvents(eventResults));
    }

    @Override
    public void deleteEvent(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<EventDB> results = realm.where(EventDB.class).equalTo("idValue", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void saveEvent(@NonNull Event event) {
        final EventDB eventDB = EventMapper.eventToEventDB(event);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(eventDB);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
            }
        });
    }

    @Override
    public boolean isEventSaved(@NonNull int id) {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(EventDB.class).equalTo("idValue", id).count() > 0;
    }

}
