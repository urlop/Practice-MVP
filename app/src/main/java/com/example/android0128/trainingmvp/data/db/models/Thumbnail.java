package com.example.android0128.trainingmvp.data.db.models;

import io.realm.RealmObject;


public class Thumbnail extends RealmObject {
    String path; //"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
    String extension; //"jpg"

    public Thumbnail() {
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
