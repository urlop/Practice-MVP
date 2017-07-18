package com.example.android0128.trainingmvp.data.network;

import android.support.annotation.NonNull;

import com.example.android0128.trainingmvp.MvpApp;
import com.example.android0128.trainingmvp.data.callbacks.EventsDataSource;
import com.example.android0128.trainingmvp.data.network.models.EventsResponse;
import com.example.android0128.trainingmvp.presentation.models.Event;
import com.example.android0128.trainingmvp.presentation.models.mappers.EventMapper;
import com.example.android0128.trainingmvp.utils.Constants;
import com.example.android0128.trainingmvp.utils.Utils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RubyMobile-1 on 23/01/2017.
 */

public class EventsRestDataSource implements EventsDataSource {

    private Services services;

    public EventsRestDataSource() {
        services = MvpApp.getInstance().getServices();
    }

    @Override
    public void loadEvents(@NonNull final GetEventsCallback callback, int offset, int limit) {
        long timestamp = new Date().getTime();
        String hash = Utils.md5(timestamp + Constants.API_PRIVATE_KEY + Constants.API_KEY);
        services.getEvents(timestamp, Constants.API_KEY, hash, limit, offset).enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                if (response.isSuccessful()) {
                    callback.onEventsLoaded(EventMapper.eventResultsToEvents(response.body().getEvents()));
                } else {
                    callback.onDataNotAvailable(response.message());
                }
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void deleteEvent(@NonNull int id) {
        //Not implemented here
    }

    @Override
    public void saveEvent(@NonNull Event event) {
        //Not implemented here
    }

    @Override
    public boolean isEventSaved(@NonNull int id) {
        //Not implemented here
        return false;
    }

}
