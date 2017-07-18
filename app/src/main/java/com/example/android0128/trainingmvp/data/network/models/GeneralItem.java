package com.example.android0128.trainingmvp.data.network.models;

/**
 * Created by Ruby on 11/02/2017.
 */

public class GeneralItem {
    String resourceURI; //http://gateway.marvel.com/v1/public/comics/21366
    String name; //Avengers: The Initiative (2007) #14

    public GeneralItem() {
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
