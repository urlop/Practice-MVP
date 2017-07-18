package com.example.android0128.trainingmvp.presentation.getEvents.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;


public interface GetEventsContract {
    interface View {
        void setEventsView(ArrayList<Event> events);

        void setupEventsView(ArrayList<Event> events);

        void setEmptyEvents();

        void openDetail(Event event, int position);

        void setItem(Event event, int position);

        void removeItem(int position);

        void addItem(Event event);

        void askToUpdateFavorite(Event event);

        void askToUpdateAll(Event event);
    }

    interface UserActionsListener {
        void requestEvents(boolean reset);

        void openEvents(Event event, int position);

        void updateItem(Event event, int position);

        void updateItem(Event event);
    }
}
