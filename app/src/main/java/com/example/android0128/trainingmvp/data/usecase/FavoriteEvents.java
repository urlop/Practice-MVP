package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.EventsRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.EventsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.EventsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;

/**
 * Created by Ruby on 08/02/2017.
 */

public class FavoriteEvents implements EventsUseCaseCallback {

    private GetEventsDataResponse getEventsDataResponse;
    private EventsRepositoryCallback eventsRepositoryCallback;

    public FavoriteEvents(GetEventsDataResponse getEventsDataResponse) {
        this.getEventsDataResponse = getEventsDataResponse;
        eventsRepositoryCallback = new EventsRepository(this);
    }

    public void saveEvent(Event event) {
        eventsRepositoryCallback.saveEvent(event);
    }
    public boolean isEventSaved(int id) {
        return eventsRepositoryCallback.isEventSaved(id);
    }
    public void deleteEvent(int id) {
        eventsRepositoryCallback.deleteEvent(id);
    }

    @Override
    public void onEventsSuccess(ArrayList<Event> events) {
        getEventsDataResponse.onEventsLoadSuccess(events);
    }

    @Override
    public void onEventsFailure(String message) {
        getEventsDataResponse.onEventsLoadFailure(message);
    }

    public void loadEvents() {
        eventsRepositoryCallback.loadSavedEvents();
    }
}
