package com.example.android0128.trainingmvp.presentation.getComicDetail;

import com.example.android0128.trainingmvp.data.usecase.FavoriteComics;
import com.example.android0128.trainingmvp.presentation.getComicDetail.callbacks.GetComicDetailContract;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;


public class ComicPresenter implements GetComicsDataResponse, GetComicDetailContract.UserActionsListener {

    private GetComicDetailContract.View view;
    private FavoriteComics favoriteComics;
    private Comic comic;

    ComicPresenter(GetComicDetailContract.View view, Comic extraComic) {
        this.view = view;
        favoriteComics = new FavoriteComics(this);
        checkExtra(extraComic);
    }

    private void checkExtra(Comic extraComic){
        if (extraComic != null){
            Comic.Options defaultOptions = new Comic.Options();
            defaultOptions.setItems(new ArrayList<Comic.Options.Item>());
            extraComic.setCreators(extraComic.getCreators() != null ? extraComic.getCreators() : defaultOptions);
            extraComic.setCharacters(extraComic.getCharacters() != null ? extraComic.getCharacters() : defaultOptions);
            comic = extraComic;
            view.setComicView(extraComic);
        }
    }

    @Override
    public void requestComic() {

    }

    @Override
    public void setFavorite() {
        if (favoriteComics.isComicSaved(comic.getId())){
            favoriteComics.deleteComic(comic.getId());
            comic.setFavorite(false);
            view.changeFavorite(false);
        } else {
            favoriteComics.saveComic(comic);
            comic.setFavorite(true);
            view.changeFavorite(true);
        }
    }

    @Override
    public Comic getResult() {
        return comic;
    }

    @Override
    public void onComicsLoadSuccess(ArrayList<Comic> comics) {

    }

    @Override
    public void onComicsLoadFailure(String error) {

    }
}
