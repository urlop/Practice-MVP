package com.example.android0128.trainingmvp.data.network.models;

import java.util.List;


public class ComicsResponse {
    Data data;

    public class Data extends GeneralData{
        List<ComicResult> results;
    }

    public List<ComicResult> getComics() {
        return data.results;
    }
}
