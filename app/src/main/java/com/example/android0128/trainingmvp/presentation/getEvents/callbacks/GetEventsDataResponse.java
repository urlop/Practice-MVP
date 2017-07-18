package com.example.android0128.trainingmvp.presentation.getEvents.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;


public interface GetEventsDataResponse {
    void onEventsLoadSuccess(ArrayList<Event> characters);

    void onEventsLoadFailure(String error);
}
