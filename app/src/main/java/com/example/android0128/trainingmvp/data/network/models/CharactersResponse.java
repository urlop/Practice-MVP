package com.example.android0128.trainingmvp.data.network.models;

import java.util.List;

/**
 * Created by android0128 on 2/2/17.
 */

public class CharactersResponse {
    Data data;

    public class Data extends GeneralData{
        List<CharacterResult> results;
    }

    public List<CharacterResult> getCharacters() {
        return data.results;
    }
}
