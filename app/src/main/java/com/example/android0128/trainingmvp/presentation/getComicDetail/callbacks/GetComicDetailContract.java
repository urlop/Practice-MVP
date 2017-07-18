package com.example.android0128.trainingmvp.presentation.getComicDetail.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;


public interface GetComicDetailContract {
    interface View {
        void setComicView(Comic character);

        void showMessage(String message);

        void changeFavorite(boolean isFavorite);
    }

    interface UserActionsListener {
        void requestComic();

        void setFavorite();

        Comic getResult();
    }
}
