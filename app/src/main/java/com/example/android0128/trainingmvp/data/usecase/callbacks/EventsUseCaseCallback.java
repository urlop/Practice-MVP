package com.example.android0128.trainingmvp.data.usecase.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;


public interface EventsUseCaseCallback {
    void onEventsSuccess(ArrayList<Event> characters);

    void onEventsFailure(String message);
}
