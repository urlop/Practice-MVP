package com.example.android0128.trainingmvp.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CharacterResult {
    @SerializedName("id")
    int idValue;
    String name;
    String description;
    Thumbnail thumbnail;
    CharacterResult.Options comics;
    CharacterResult.Options series;
    CharacterResult.Options stories;
    CharacterResult.Options events;

    public CharacterResult() {
    }

    public static class Thumbnail extends GeneralThumbnail {
        public Thumbnail() {
        }
    }

    public static class Options {
        List<CharacterResult.Options.Item> items;

        public Options() {
        }

        public static class Item extends GeneralItem {
            public Item() {
            }
        }

        public List<CharacterResult.Options.Item> getItems() {
            return items;
        }

        public void setItems(List<CharacterResult.Options.Item> items) {
            this.items = items;
        }
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

    public CharacterResult.Options getComics() {
        return comics;
    }

    public void setComics(CharacterResult.Options comics) {
        this.comics = comics;
    }

    public CharacterResult.Options getSeries() {
        return series;
    }

    public void setSeries(CharacterResult.Options series) {
        this.series = series;
    }

    public CharacterResult.Options getStories() {
        return stories;
    }

    public void setStories(CharacterResult.Options stories) {
        this.stories = stories;
    }

    public CharacterResult.Options getEvents() {
        return events;
    }

    public void setEvents(CharacterResult.Options events) {
        this.events = events;
    }
}
