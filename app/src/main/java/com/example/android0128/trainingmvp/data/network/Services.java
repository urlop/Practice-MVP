package com.example.android0128.trainingmvp.data.network;

import com.example.android0128.trainingmvp.data.network.models.CharactersResponse;
import com.example.android0128.trainingmvp.data.network.models.ComicsResponse;
import com.example.android0128.trainingmvp.data.network.models.EventsResponse;
import com.example.android0128.trainingmvp.utils.Constants;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Services {
    @GET(Constants.CHARACTERS)
    Call<CharactersResponse> getCharacters(@Query("ts") long timestamp, @Query("apikey") String apikey, @Query("hash") String hash,
                                           @Query("limit") long limit, @Query("offset") long offset);

    @GET(Constants.COMICS)
    Call<ComicsResponse> getComics(@Query("ts") long timestamp, @Query("apikey") String apikey, @Query("hash") String hash,
                                   @Query("limit") long limit, @Query("offset") long offset);

    @GET(Constants.EVENTS)
    Call<EventsResponse> getEvents(@Query("ts") long timestamp, @Query("apikey") String apikey, @Query("hash") String hash,
                                   @Query("limit") long limit, @Query("offset") long offset);


    //RxJava
    @GET(Constants.CHARACTERS)
    Observable<CharactersResponse> getCharactersRx(@Query("ts") long timestamp, @Query("apikey") String apikey, @Query("hash") String hash,
                                                   @Query("limit") long limit, @Query("offset") long offset);
}
