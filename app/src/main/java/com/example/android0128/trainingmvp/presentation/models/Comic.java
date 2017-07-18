package com.example.android0128.trainingmvp.presentation.models;

import java.io.Serializable;
import java.util.List;


public class Comic implements Serializable {
    int id;
    String name;
    String description;
    Thumbnail thumbnail;
    Options creators;
    Options characters;

    boolean isFavorite;

    public static class Thumbnail implements Serializable {
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

    public static class Options implements Serializable {
        List<Item> items;

        public static class Item implements Serializable {
            String resourceURI; //http://gateway.marvel.com/v1/public/creators/121
            String name; //Peter David
            String role; //writer

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
