package com.example.android0128.trainingmvp.data.repositories;

import com.example.android0128.trainingmvp.data.callbacks.EventsDataSource;
import com.example.android0128.trainingmvp.data.db.EventLocalDataSource;
import com.example.android0128.trainingmvp.data.network.EventsRestDataSource;
import com.example.android0128.trainingmvp.data.repositories.callbacks.EventsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.EventsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;


public class EventsRepository implements EventsRepositoryCallback {

    private EventsDataSource eventsLocalDataSource;
    private EventsDataSource eventsRestDataSource;
    private EventsUseCaseCallback eventsUseCaseCallback;

    public EventsRepository(EventsUseCaseCallback eventsUseCaseCallback) {
        this.eventsUseCaseCallback = eventsUseCaseCallback;
        this.eventsLocalDataSource = new EventLocalDataSource();
        this.eventsRestDataSource = new EventsRestDataSource();
    }

    @Override
    public void loadEvents(int offset, int limit) {
        getEventsFromRemote(offset, limit);
    }

    @Override
    public void loadSavedEvents() {
        getLocalEvents();
    }

    @Override
    public void saveEvent(Event event) {
        eventsLocalDataSource.saveEvent(event);
    }

    @Override
    public boolean isEventSaved(int id) {
        return eventsLocalDataSource.isEventSaved(id);
    }

    @Override
    public void deleteEvent(int id) {
        eventsLocalDataSource.deleteEvent(id);
    }

    private void getLocalEvents() {
        eventsLocalDataSource.loadEvents(new EventsDataSource.GetEventsCallback() {
            @Override
            public void onEventsLoaded(ArrayList<Event> events) {
                //Set property isFavorite
                for (Event event : events) {
                    event.setFavorite(true);
                }
                eventsUseCaseCallback.onEventsSuccess(events);
            }

            @Override
            public void onDataNotAvailable(String message) {
                eventsUseCaseCallback.onEventsFailure(message);
            }
        }, 0, 0);
    }

    private void getEventsFromRemote(int offset, int limit) {
        eventsRestDataSource.loadEvents(new EventsDataSource.GetEventsCallback() {
            @Override
            public void onEventsLoaded(final ArrayList<Event> events) {
                eventsLocalDataSource.loadEvents(new EventsDataSource.GetEventsCallback() {
                    @Override
                    public void onEventsLoaded(ArrayList<Event> eventsLocal) {
                        //Set property isFavorite
                        for (Event event : events) {
                            for (Event eventLocal : eventsLocal) {
                                if (event.getId() == eventLocal.getId()) {
                                    event.setFavorite(true);
                                }
                            }
                        }
                        eventsUseCaseCallback.onEventsSuccess(events);
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        eventsUseCaseCallback.onEventsFailure(message);
                    }
                }, 0, 0);
            }

            @Override
            public void onDataNotAvailable(String message) {
                eventsUseCaseCallback.onEventsFailure(message);
            }
        }, offset, limit);
    }

}
