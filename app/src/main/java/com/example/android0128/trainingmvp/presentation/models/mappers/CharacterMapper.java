package com.example.android0128.trainingmvp.presentation.models.mappers;

import com.example.android0128.trainingmvp.data.db.models.CharacterDB;
import com.example.android0128.trainingmvp.data.db.models.Item;
import com.example.android0128.trainingmvp.data.db.models.Options;
import com.example.android0128.trainingmvp.data.db.models.Thumbnail;
import com.example.android0128.trainingmvp.data.network.models.CharacterResult;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android0128 on 2/2/17.
 */

public class CharacterMapper {

    public static CharacterDB characterToCharacterDB(Character character) {
        CharacterDB characterResult = new CharacterDB();
        characterResult.setDescription(character.getDescription());
        characterResult.setIdValue(character.getId());
        characterResult.setName(character.getName());
        characterResult.setThumbnail(characterThumbnailToCharacterDBThumbnail(character.getThumbnail()));
        return characterResult;
    }

    public static Options optionsToOptionDB(Character.Options character) {
        Options characterResult = new Options();
        List<Item> characterArrayList = new ArrayList<>();
        for (Character.Options.Item item : character.getItems()) {
            Item itemDB = new Item();
            itemDB.setName(item.getName());
            itemDB.setResourceURI(item.getResourceURI());
            characterArrayList.add(itemDB);
        }
        characterResult.setItems(characterArrayList);
        return characterResult;
    }

    public static ArrayList<Character> characterResultsToCharacters(List<CharacterResult> characterResults) {
        ArrayList<Character> characterArrayList = new ArrayList<>();
        for (CharacterResult characterResult : characterResults) {
            Character character = new Character();
            character.setDescription(characterResult.getDescription());
            character.setId(characterResult.getIdValue());
            character.setName(characterResult.getName());
            character.setThumbnail(characterResultThumbnailToCharacterThumbnail(characterResult.getThumbnail()));
            character.setComics(optionsNWToOptions(characterResult.getComics()));
            character.setSeries(optionsNWToOptions(characterResult.getSeries()));
            character.setStories(optionsNWToOptions(characterResult.getStories()));
            character.setEvents(optionsNWToOptions(characterResult.getEvents()));
            characterArrayList.add(character);
        }
        return characterArrayList;
    }

    public static ArrayList<Character> characterDBsToCharacters(List<CharacterDB> characterResults) {
        ArrayList<Character> characterArrayList = new ArrayList<>();
        for (CharacterDB characterResult : characterResults) {
            Character character = new Character();
            character.setDescription(characterResult.getDescription());
            character.setId(characterResult.getIdValue());
            character.setName(characterResult.getName());
            character.setThumbnail(characterDBThumbnailToCharacterThumbnail(characterResult.getThumbnail()));
            character.setComics(optionsDBToOptions(characterResult.getComics()));
            character.setSeries(optionsDBToOptions(characterResult.getSeries()));
            character.setStories(optionsDBToOptions(characterResult.getStories()));
            character.setEvents(optionsDBToOptions(characterResult.getEvents()));
            characterArrayList.add(character);
        }
        return characterArrayList;
    }

    public static Character.Options optionsNWToOptions(CharacterResult.Options character) {
        Character.Options characterResult = new Character.Options();
        List<Character.Options.Item> characterArrayList = new ArrayList<>();
        try {
            for (CharacterResult.Options.Item item : character.getItems()) {
                Character.Options.Item itemDB = new Character.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                characterArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        characterResult.setItems(characterArrayList);
        return characterResult;
    }

    public static Character.Options optionsDBToOptions(Options character) {
        Character.Options characterResult = new Character.Options();
        List<Character.Options.Item> characterArrayList = new ArrayList<>();
        try {
            for (Item item : character.getItems()) {
                Character.Options.Item itemDB = new Character.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                characterArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        characterResult.setItems(characterArrayList);
        return characterResult;
    }

    public static Character.Thumbnail characterResultThumbnailToCharacterThumbnail(CharacterResult.Thumbnail thumbnailResult) {
        Character.Thumbnail thumbnail = new Character.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static CharacterResult.Thumbnail characterThumbnailToCharacterResultThumbnail(Character.Thumbnail thumbnail) {
        CharacterResult.Thumbnail thumbnailResult = new CharacterResult.Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

    public static Character.Thumbnail characterDBThumbnailToCharacterThumbnail(Thumbnail thumbnailResult) {
        Character.Thumbnail thumbnail = new Character.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static Thumbnail characterThumbnailToCharacterDBThumbnail(Character.Thumbnail thumbnail) {
        Thumbnail thumbnailResult = new Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

}
