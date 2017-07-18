package com.example.android0128.trainingmvp.presentation.getEventDetail;

import com.example.android0128.trainingmvp.data.usecase.FavoriteEvents;
import com.example.android0128.trainingmvp.presentation.getEventDetail.callbacks.GetEventDetailContract;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;

/**
 * Created by android0128 on 2/2/17.
 */

public class EventPresenter implements GetEventsDataResponse, GetEventDetailContract.UserActionsListener {

    private GetEventDetailContract.View view;
    private FavoriteEvents favoriteEvents;
    private Event event;

    EventPresenter(GetEventDetailContract.View view, Event extraEvent) {
        this.view = view;
        favoriteEvents = new FavoriteEvents(this);
        checkExtra(extraEvent);
    }

    private void checkExtra(Event extraEvent){
        if (extraEvent != null){
            Event.Options defaultOptions = new Event.Options();
            defaultOptions.setItems(new ArrayList<Event.Options.Item>());
            extraEvent.setCreators(extraEvent.getCreators() != null ? extraEvent.getCreators() : defaultOptions);
            extraEvent.setCharacters(extraEvent.getCharacters() != null ? extraEvent.getCharacters() : defaultOptions);
            event = extraEvent;
            view.setEventView(extraEvent);
        }
    }

    @Override
    public void requestEvent() {

    }

    @Override
    public void setFavorite() {
        if (favoriteEvents.isEventSaved(event.getId())){
            favoriteEvents.deleteEvent(event.getId());
            event.setFavorite(false);
            view.changeFavorite(false);
        } else {
            favoriteEvents.saveEvent(event);
            event.setFavorite(true);
            view.changeFavorite(true);
        }
    }

    @Override
    public Event getResult() {
        return event;
    }

    @Override
    public void onEventsLoadSuccess(ArrayList<Event> events) {

    }

    @Override
    public void onEventsLoadFailure(String error) {

    }
}
