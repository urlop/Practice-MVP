package com.example.android0128.trainingmvp.presentation.getCharacters.callbacks;

import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.List;


public interface GetCharactersContract {
    interface View {
        void setCharactersView(List<Character> characters);

        void setupCharactersView(List<Character> characters);

        void setEmptyCharacters(String message);

        void setEmptyCharacters();

        void openDetail(Character character, int position);

        void setItem(Character character, int position);

        void removeItem(int position);

        void addItem(Character character);

        void askToUpdateFavorite(Character character);

        void askToUpdateAll(Character character);
    }

    interface UserActionsListener {
        void requestCharacters(boolean reset);

        void openCharacters(Character character, int position);

        void updateItem(Character character, int position);

        void updateItem(Character character);
    }
}
