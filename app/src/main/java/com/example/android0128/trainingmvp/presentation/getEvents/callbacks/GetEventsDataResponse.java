package com.example.android0128.trainingmvp.presentation.getEvents.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public interface GetEventsDataResponse {
    void onEventsLoadSuccess(ArrayList<Event> characters);

    void onEventsLoadFailure(String error);
}
