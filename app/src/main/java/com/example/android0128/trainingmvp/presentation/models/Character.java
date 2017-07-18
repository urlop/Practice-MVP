package com.example.android0128.trainingmvp.presentation.models;

import java.io.Serializable;
import java.util.List;


public class Character implements Serializable {
    int id;
    String name;
    String description;
    Thumbnail thumbnail;
    Options comics;
    Options series;
    Options stories;
    Options events;

    boolean isFavorite;

    public Character() {
    }

    public Character(int id, String name, String description, Thumbnail thumbnail, Options comics, Options series, Options stories, Options events, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.comics = comics;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.isFavorite = isFavorite;
    }

    public static class Thumbnail implements Serializable {
        String path; //"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        String extension; //"jpg"

        public Thumbnail() {
        }

        public Thumbnail(String path, String extension) {
            this.path = path;
            this.extension = extension;
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

    public static class Options implements Serializable {
        List<Item> items;

        public static class Item implements Serializable {
            String resourceURI; //http://gateway.marvel.com/v1/public/comics/21366
            String name; //Avengers: The Initiative (2007) #14

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

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    //TODO Check if this should be here
    public String getImagePath() {
        return thumbnail.path + "." + thumbnail.extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
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
