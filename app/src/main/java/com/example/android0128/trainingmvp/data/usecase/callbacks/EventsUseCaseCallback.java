package com.example.android0128.trainingmvp.data.usecase.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */
public interface EventsUseCaseCallback {
    void onEventsSuccess(ArrayList<Event> characters);

    void onEventsFailure(String message);
}
