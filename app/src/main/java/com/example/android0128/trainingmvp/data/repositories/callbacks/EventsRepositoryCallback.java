package com.example.android0128.trainingmvp.data.repositories.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Event;


public interface EventsRepositoryCallback {
    void loadEvents(int offset, int limit);
    void loadSavedEvents();
    void saveEvent(Event character);
    boolean isEventSaved(int id);
    void deleteEvent(int id);
}
