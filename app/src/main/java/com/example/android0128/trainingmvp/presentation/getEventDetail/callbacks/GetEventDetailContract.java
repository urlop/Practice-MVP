package com.example.android0128.trainingmvp.presentation.getEventDetail.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

/**
 * Created by android0128 on 2/2/17.
 */

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
