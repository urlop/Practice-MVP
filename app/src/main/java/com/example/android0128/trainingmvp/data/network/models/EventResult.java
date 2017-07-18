package com.example.android0128.trainingmvp.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EventResult {
    @SerializedName("id")
    int idValue;
    @SerializedName("title")
    String name;
    String description;
    Thumbnail thumbnail;
    EventResult.Options creators;
    EventResult.Options characters;

    public EventResult() {
    }

    public static class Thumbnail extends GeneralThumbnail {
        public Thumbnail() {
        }
    }

    public static class Options {
        List<EventResult.Options.Item> items;

        public Options() {
        }

        public static class Item extends GeneralItem {
            //String resourceURI; //"http://gateway.marvel.com/v1/public/creators/2707"
            //String name; //"Jeff Albrecht"
            String role; //"inker"

            public Item() {
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
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
