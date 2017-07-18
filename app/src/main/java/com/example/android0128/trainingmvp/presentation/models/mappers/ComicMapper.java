package com.example.android0128.trainingmvp.presentation.models.mappers;

import com.example.android0128.trainingmvp.data.db.models.ComicDB;
import com.example.android0128.trainingmvp.data.db.models.Item;
import com.example.android0128.trainingmvp.data.db.models.Options;
import com.example.android0128.trainingmvp.data.db.models.Thumbnail;
import com.example.android0128.trainingmvp.data.network.models.ComicResult;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android0128 on 2/2/17.
 */

public class ComicMapper {

    public static ComicDB comicToComicDB(Comic comic) {
        ComicDB comicResult = new ComicDB();
        comicResult.setDescription(comic.getDescription());
        comicResult.setIdValue(comic.getId());
        comicResult.setName(comic.getName());
        comicResult.setThumbnail(comicThumbnailToComicDBThumbnail(comic.getThumbnail()));
        return comicResult;
    }

    public static Options optionsToOptionDB(Comic.Options comic) {
        Options comicResult = new Options();
        List<Item> comicArrayList = new ArrayList<>();
        for (Comic.Options.Item item : comic.getItems()) {
            Item itemDB = new Item();
            itemDB.setName(item.getName());
            itemDB.setResourceURI(item.getResourceURI());
            comicArrayList.add(itemDB);
        }
        comicResult.setItems(comicArrayList);
        return comicResult;
    }

    public static ArrayList<Comic> comicResultsToComics(List<ComicResult> comicResults) {
        ArrayList<Comic> comicArrayList = new ArrayList<>();
        for (ComicResult comicResult : comicResults) {
            Comic comic = new Comic();
            comic.setDescription(comicResult.getDescription());
            comic.setId(comicResult.getIdValue());
            comic.setName(comicResult.getName());
            comic.setThumbnail(comicResultThumbnailToComicThumbnail(comicResult.getThumbnail()));
            comic.setCharacters(optionsNWToOptions(comicResult.getCharacters()));
            comic.setCreators(optionsNWToOptions(comicResult.getCreators()));
            comicArrayList.add(comic);
        }
        return comicArrayList;
    }

    public static ArrayList<Comic> comicDBsToComics(List<ComicDB> comicResults) {
        ArrayList<Comic> comicArrayList = new ArrayList<>();
        for (ComicDB comicResult : comicResults) {
            Comic comic = new Comic();
            comic.setDescription(comicResult.getDescription());
            comic.setId(comicResult.getIdValue());
            comic.setName(comicResult.getName());
            comic.setThumbnail(comicDBThumbnailToComicThumbnail(comicResult.getThumbnail()));
            comic.setCharacters(optionsDBToOptions(comicResult.getCharacters()));
            comic.setCreators(optionsDBToOptions(comicResult.getCreators()));
            comicArrayList.add(comic);
        }
        return comicArrayList;
    }

    public static Comic.Options optionsNWToOptions(ComicResult.Options comic) {
        Comic.Options comicResult = new Comic.Options();
        List<Comic.Options.Item> comicArrayList = new ArrayList<>();
        try {
            for (ComicResult.Options.Item item : comic.getItems()) {
                Comic.Options.Item itemDB = new Comic.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                comicArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        comicResult.setItems(comicArrayList);
        return comicResult;
    }

    public static Comic.Options optionsDBToOptions(Options comic) {
        Comic.Options comicResult = new Comic.Options();
        List<Comic.Options.Item> comicArrayList = new ArrayList<>();
        try {
            for (Item item : comic.getItems()) {
                Comic.Options.Item itemDB = new Comic.Options.Item();
                itemDB.setName(item.getName());
                itemDB.setResourceURI(item.getResourceURI());
                comicArrayList.add(itemDB);
            }
        }catch (Exception e){

        }
        comicResult.setItems(comicArrayList);
        return comicResult;
    }

    public static Comic.Thumbnail comicResultThumbnailToComicThumbnail(ComicResult.Thumbnail thumbnailResult) {
        Comic.Thumbnail thumbnail = new Comic.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static ComicResult.Thumbnail comicThumbnailToComicResultThumbnail(Comic.Thumbnail thumbnail) {
        ComicResult.Thumbnail thumbnailResult = new ComicResult.Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

    public static Comic.Thumbnail comicDBThumbnailToComicThumbnail(Thumbnail thumbnailResult) {
        Comic.Thumbnail thumbnail = new Comic.Thumbnail();
        thumbnail.setExtension(thumbnailResult.getExtension());
        thumbnail.setPath(thumbnailResult.getPath());
        return thumbnail;
    }

    public static Thumbnail comicThumbnailToComicDBThumbnail(Comic.Thumbnail thumbnail) {
        Thumbnail thumbnailResult = new Thumbnail();
        thumbnailResult.setExtension(thumbnail.getExtension());
        thumbnailResult.setPath(thumbnail.getPath());
        return thumbnailResult;
    }

}
