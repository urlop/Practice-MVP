package com.example.android0128.trainingmvp.data.db.models;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by android0128 on 2/2/17.
 */

public class CharacterDB extends RealmObject {
    @PrimaryKey
    int idValue;
    String name;
    String description;
    Thumbnail thumbnail;
    Options comics;
    Options series;
    Options stories;
    Options events;

    public CharacterDB() {
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

    public Options getComics() {
        return comics;
    }

    public void setComics(Options comics) {
        this.comics = comics;
    }

    public Options getSeries() {
        return series;
    }

    public void setSeries(Options series) {
        this.series = series;
    }

    public Options getStories() {
        return stories;
    }

    public void setStories(Options stories) {
        this.stories = stories;
    }

    public Options getEvents() {
        return events;
    }

    public void setEvents(Options events) {
        this.events = events;
    }
}
