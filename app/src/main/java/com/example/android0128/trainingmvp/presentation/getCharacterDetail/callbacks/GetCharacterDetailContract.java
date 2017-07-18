package com.example.android0128.trainingmvp.presentation.getCharacterDetail.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Character;


public interface GetCharacterDetailContract {
    interface View {
        void setCharacterView(Character character);

        void showMessage(String message);

        void changeFavorite(boolean isFavorite);
    }

    interface UserActionsListener {
        void requestCharacter();

        void setFavorite();

        Character getResult();
    }
}
