package com.example.android0128.trainingmvp.presentation.getEvents;

import com.example.android0128.trainingmvp.data.usecase.FavoriteEvents;
import com.example.android0128.trainingmvp.data.usecase.GetEvents;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsContract;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;
import java.util.List;


public class EventsPresenter implements GetEventsDataResponse, GetEventsContract.UserActionsListener {

    private GetEventsContract.View view;
    private GetEvents getPreviews;
    private FavoriteEvents favoriteEvents;
    private boolean onlyFavorites;
    private List<Event> data = new ArrayList<>();

    final static int LIMIT = 12;
    int offset = 0;
    boolean lastPageReached = false;

    boolean resetRequested = false;

    EventsPresenter(GetEventsContract.View homeView, boolean onlyFavorites) {
        this.view = homeView;
        this.onlyFavorites = onlyFavorites;
        getPreviews = new GetEvents(this);
        favoriteEvents = new FavoriteEvents(this);
    }

    @Override
    public void requestEvents(boolean reset) {
        if (reset) {
            offset = 0;
            lastPageReached = false;
            resetRequested = true;
        }
        if (!lastPageReached) {
            if (onlyFavorites) {
                favoriteEvents.loadEvents();
            } else {
                getPreviews.loadEvents(offset, LIMIT);
            }
        } else {
            //To notify view and stop loader
            view.setEventsView(new ArrayList<Event>());
        }
    }

    @Override
    public void openEvents(Event event, int position) {
        view.openDetail(event, position);
    }

    /**
     * Updates specific item inside view when you know its position in the list;
     *
     * @param event    item shown
     * @param position position of the item
     */
    @Override
    public void updateItem(Event event, int position) {
        if (onlyFavorites) {
            if (!event.isFavorite()) {
                view.removeItem(position);
                data.remove(position);
                view.askToUpdateAll(event);
            }
        } else {
            view.setItem(event, position);
            data.set(position, event);
            view.askToUpdateFavorite(event);
        }
    }

    /**
     * Updates specific item inside view when you don't know its position in the list;
     *
     * @param event item shown
     */
    @Override
    public void updateItem(Event event) {
        int position = getItemPosition(event);
        if (onlyFavorites) {
            if (!event.isFavorite()) {
                if (position >= 0) {
                    view.removeItem(position);
                    data.remove(position);
                }
            } else {
                if (position < 0) {
                    view.addItem(event);
                    data.add(event);
                }
            }
        } else {
            if (position >= 0) {
                view.setItem(event, position);
                data.set(position, event);
            }
        }
    }

    private int getItemPosition(Event event) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == event.getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onEventsLoadSuccess(ArrayList<Event> events) {
        offset += LIMIT;
        data.addAll(events);
        if (resetRequested) {
            view.setupEventsView(events);
            resetRequested = false;
        } else {
            view.setEventsView(events);
        }
        if (onlyFavorites || events.size() < 1) {
            lastPageReached = true;
        }

    }

    @Override
    public void onEventsLoadFailure(String error) {
        view.setEmptyEvents();
    }


}
