package com.example.android0128.trainingmvp.presentation.models.mappers;

import com.example.android0128.trainingmvp.data.db.models.EventDB;
import com.example.android0128.trainingmvp.data.db.models.Item;
import com.example.android0128.trainingmvp.data.db.models.Options;
import com.example.android0128.trainingmvp.data.db.models.Thumbnail;
import com.example.android0128.trainingmvp.data.network.models.EventResult;
import com.example.android0128.trainingmvp.presentation.models.Event;

import java.util.ArrayList;
import java.util.List;


public class EventMapper {

    public static EventDB eventToEventDB(Event event) {
        EventDB eventResult = new EventDB();
        eventResult.setDescription(event.getDescription());
        eventResult.setIdValue(event.getId());
        eventResult.setName(event.getName());
        eventResult.setThumbnail(eventThumbnailToEventDBThumbnail(event.getThumbnail()));
        return eventResult;
    }

    public static Options optionsToOptionDB(Event.Options event) {
        Options eventResult = new Options();
        List<Item> eventArrayList = new ArrayList<>();
        for (Event.Options.Item item : event.getItems()) {
            Item itemDB = new Item();
            itemDB.setName(item.getName());
            itemDB.setResourceURI(item.getResourceURI());
            eventArrayList.add(itemDB);
        }
        eventResult.setItems(eventArrayList);
        return eventResult;
    }

    public static ArrayList<Event> eventResultsToEvents(List<EventResult> eventResults) {
        ArrayList<Event> eventArrayList = new ArrayList<>();
        for (EventResult eventResult : eventResults) {
            Event event = new Event();
            event.setDescription(eventResult.getDescription());
            event.setId(eventResult.getIdValue());
            event.setName(eventResult.getName());
            event.setThumbnail(eventResultThumbnailToEventThumbnail(eventResult.getThumbnail()));
            event.setCharacters(optionsNWToOptions(eventResult.getCharacters()));
            event.setCreators(optionsNWToOptions(eventResult.getCreators()));
            eventArrayList.add(event);
        }
        return eventArrayList;
    }

    public static ArrayList<Event> eventDBsToEvents(List<EventDB> eventResults) {
        ArrayList<Event> eventArrayList = new ArrayList<>();
        for (EventDB eventResult : eventResults) {
            Event event = new Event();
            event.setDescription(eventResult.getDescription());
            event.setId(eventResult.getIdValue());
            event.setName(eventResult.getName());
            event.setThumbnail(eventDBThumbnailToEventThumbnail(eventResult.getThumbnail()));
            event.setCharacters(optionsDBToOptions(eventResult.getCharacters()));
            event.setCreators(optionsDBToOptions(eventResult.getCreators()));
            eventArrayList.add(event);
        }
        return eventArrayList;
    }

    public static Event.Options optionsNWToOptions(EventResult.Options event) {
        Event.Options eventResult = new Event.Options();
        List<Event.Options.Item> eventArrayList = new ArrayList<>();
        try {
            for (EventResult.Options.Item item : event.getItems()) {
                Event.Options.Item itemDB = new Event.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                eventArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        eventResult.setItems(eventArrayList);
        return eventResult;
    }

    public static Event.Options optionsDBToOptions(Options event) {
        Event.Options eventResult = new Event.Options();
        List<Event.Options.Item> eventArrayList = new ArrayList<>();
        try {
            for (Item item : event.getItems()) {
                Event.Options.Item itemDB = new Event.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                eventArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        eventResult.setItems(eventArrayList);
        return eventResult;
    }

    public static Event.Thumbnail eventResultThumbnailToEventThumbnail(EventResult.Thumbnail thumbnailResult) {
        Event.Thumbnail thumbnail = new Event.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static EventResult.Thumbnail eventThumbnailToEventResultThumbnail(Event.Thumbnail thumbnail) {
        EventResult.Thumbnail thumbnailResult = new EventResult.Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

    public static Event.Thumbnail eventDBThumbnailToEventThumbnail(Thumbnail thumbnailResult) {
        Event.Thumbnail thumbnail = new Event.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static Thumbnail eventThumbnailToEventDBThumbnail(Event.Thumbnail thumbnail) {
        Thumbnail thumbnailResult = new Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

}
