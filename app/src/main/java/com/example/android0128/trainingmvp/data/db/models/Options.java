package com.example.android0128.trainingmvp.data.db.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Options extends RealmObject {
    RealmList<Item> items;

    public Options() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = (RealmList<Item>) items;
    }
}