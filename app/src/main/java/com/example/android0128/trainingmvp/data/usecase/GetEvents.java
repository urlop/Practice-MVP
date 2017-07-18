package com.example.android0128.trainingmvp.data.usecase;

import com.example.android0128.trainingmvp.data.repositories.EventsRepository;
import com.example.android0128.trainingmvp.data.repositories.callbacks.EventsRepositoryCallback;
import com.example.android0128.trainingmvp.data.usecase.callbacks.EventsUseCaseCallback;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;


public class GetEvents implements EventsUseCaseCallback {

    private GetEventsDataResponse getEventsDataResponse;
    private EventsRepositoryCallback eventsRepositoryCallback;

    public GetEvents(GetEventsDataResponse getEventsDataResponse) {
        this.getEventsDataResponse = getEventsDataResponse;
        eventsRepositoryCallback = new EventsRepository(this);
    }

    @Override
    public void onEventsSuccess(ArrayList<Event> events) {
        getEventsDataResponse.onEventsLoadSuccess(events);
    }

    @Override
    public void onEventsFailure(String message) {
        getEventsDataResponse.onEventsLoadFailure(message);
    }

    public void loadEvents(int offset, int limit) {
        eventsRepositoryCallback.loadEvents(offset, limit);
    }

}
