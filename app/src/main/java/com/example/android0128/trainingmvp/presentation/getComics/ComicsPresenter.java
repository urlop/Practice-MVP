package com.example.android0128.trainingmvp.presentation.getComics;

import com.example.android0128.trainingmvp.data.usecase.FavoriteComics;
import com.example.android0128.trainingmvp.data.usecase.GetComics;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsContract;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsDataResponse;
import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android0128 on 2/2/17.
 */

public class ComicsPresenter implements GetComicsDataResponse, GetComicsContract.UserActionsListener {

    private GetComicsContract.View view;
    private GetComics getPreviews;
    private FavoriteComics favoriteComics;
    private boolean onlyFavorites;
    private List<Comic> data = new ArrayList<>();

    final static int LIMIT = 12;
    int offset = 0;
    boolean lastPageReached = false;

    boolean resetRequested = false;

    ComicsPresenter(GetComicsContract.View homeView, boolean onlyFavorites) {
        this.view = homeView;
        this.onlyFavorites = onlyFavorites;
        getPreviews = new GetComics(this);
        favoriteComics = new FavoriteComics(this);
    }

    @Override
    public void requestComics(boolean reset) {
        if (reset) {
            offset = 0;
            lastPageReached = false;
            resetRequested = true;
        }
        if (!lastPageReached) {
            if (onlyFavorites) {
                favoriteComics.loadComics();
            } else {
                getPreviews.loadComics(offset, LIMIT);
            }
        } else {
            //To notify view and stop loader
            view.setComicsView(new ArrayList<Comic>());
        }
    }

    @Override
    public void openComics(Comic comic, int position) {
        view.openDetail(comic, position);
    }

    /**
     * Updates specific item inside view when you know its position in the list;
     *
     * @param comic    item shown
     * @param position position of the item
     */
    @Override
    public void updateItem(Comic comic, int position) {
        if (onlyFavorites) {
            if (!comic.isFavorite()) {
                view.removeItem(position);
                data.remove(position);
                view.askToUpdateAll(comic);
            }
        } else {
            view.setItem(comic, position);
            data.set(position, comic);
            view.askToUpdateFavorite(comic);
        }
    }

    /**
     * Updates specific item inside view when you don't know its position in the list;
     *
     * @param comic item shown
     */
    @Override
    public void updateItem(Comic comic) {
        int position = getItemPosition(comic);
        if (onlyFavorites) {
            if (!comic.isFavorite()) {
                if (position >= 0) {
                    view.removeItem(position);
                    data.remove(position);
                }
            } else {
                if (position < 0) {
                    view.addItem(comic);
                    data.add(comic);
                }
            }
        } else {
            if (position >= 0) {
                view.setItem(comic, position);
                data.set(position, comic);
            }
        }
    }

    private int getItemPosition(Comic comic) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == comic.getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onComicsLoadSuccess(ArrayList<Comic> comics) {
        offset += LIMIT;
        data.addAll(comics);
        if (resetRequested) {
            view.setupComicsView(comics);
            resetRequested = false;
        } else {
            view.setComicsView(comics);
        }
        if (onlyFavorites || comics.size() < 1) {
            lastPageReached = true;
        }

    }

    @Override
    public void onComicsLoadFailure(String error) {
        view.setEmptyComics();
    }


}
