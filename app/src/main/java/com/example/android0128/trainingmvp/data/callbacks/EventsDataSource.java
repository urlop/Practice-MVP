package com.example.android0128.trainingmvp.data.callbacks;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public interface EventsDataSource {

    interface GetEventsCallback {

        void onEventsLoaded(ArrayList<Event> event);

        void onDataNotAvailable(String message);
    }

    void loadEvents(@NonNull GetEventsCallback callback, int offset, int limit);

    void deleteEvent(@NonNull int id);

    void saveEvent(@NonNull Event event);

    boolean isEventSaved(@NonNull int id);

}
