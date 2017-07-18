package com.example.android0128.trainingmvp.data.repositories.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;

/**
 * Created by RubyMobile-1 on 24/01/2017.
 */

public interface EventsRepositoryCallback {
    void loadEvents(int offset, int limit);
    void loadSavedEvents();
    void saveEvent(Event character);
    boolean isEventSaved(int id);
    void deleteEvent(int id);
}
