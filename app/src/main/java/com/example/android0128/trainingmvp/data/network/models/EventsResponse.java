package com.example.android0128.trainingmvp.data.network.models;

import java.util.List;


public class EventsResponse {
    Data data;

    public class Data extends GeneralData{
        List<EventResult> results;
    }

    public List<EventResult> getEvents() {
        return data.results;
    }
}
