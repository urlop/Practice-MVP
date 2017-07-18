package com.example.android0128.trainingmvp.presentation.getComics.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Comic;

import java.util.ArrayList;


public interface GetComicsContract {
    interface View {
        void setComicsView(ArrayList<Comic> characters);

        void setupComicsView(ArrayList<Comic> characters);

        void setEmptyComics();

        void openDetail(Comic character, int position);

        void setItem(Comic character, int position);

        void removeItem(int position);

        void addItem(Comic character);

        void askToUpdateFavorite(Comic character);

        void askToUpdateAll(Comic character);
    }

    interface UserActionsListener {
        void requestComics(boolean reset);

        void openComics(Comic character, int position);

        void updateItem(Comic character, int position);

        void updateItem(Comic character);
    }
}
