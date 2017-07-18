package com.example.android0128.trainingmvp.data.network.models;

/**
 * Created by Ruby on 11/02/2017.
 */

public class GeneralThumbnail {
    String path; //"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
    String extension; //"jpg"

    public GeneralThumbnail() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
