package com.example.android0128.trainingmvp.presentation.getEventDetail.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;


public interface GetEventDetailContract {
    interface View {
        void setEventView(Event character);

        void showMessage(String message);

        void changeFavorite(boolean isFavorite);
    }

    interface UserActionsListener {
        void requestEvent();

        void setFavorite();

        Event getResult();
    }
}
