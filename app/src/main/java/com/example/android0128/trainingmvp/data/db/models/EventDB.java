package com.example.android0128.trainingmvp.data.db.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class EventDB extends RealmObject {
    @PrimaryKey
    int idValue;
    String name;
    String description;
    Thumbnail thumbnail;
    Options creators;
    Options characters;

    public EventDB() {
    }

    public int getIdValue() {
        return idValue;
    }

    public void setIdValue(int idValue) {
        this.idValue = idValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Options getCreators() {
        return creators;
    }

    public void setCreators(Options creators) {
        this.creators = creators;
    }

    public Options getCharacters() {
        return characters;
    }

    public void setCharacters(Options characters) {
        this.characters = characters;
    }
}
