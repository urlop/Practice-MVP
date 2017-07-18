package com.example.android0128.trainingmvp.presentation.getCharacters;

import android.support.annotation.VisibleForTesting;

import com.example.android0128.trainingmvp.data.usecase.FavoriteCharacters;
import com.example.android0128.trainingmvp.data.usecase.GetCharacters;
import com.example.android0128.trainingmvp.presentation.Presenter;
import com.example.android0128.trainingmvp.presentation.PresenterConfiguration;
import com.example.android0128.trainingmvp.presentation.getCharacters.callbacks.GetCharactersContract;
import com.example.android0128.trainingmvp.presentation.models.Character;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CharactersPresenter extends Presenter implements GetCharactersContract.UserActionsListener { //GetCharactersDataResponse,

    @android.support.annotation.NonNull
    private PresenterConfiguration presenterConfiguration;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private GetCharactersContract.View view;
    private GetCharacters getCharacters;
    private FavoriteCharacters favoriteCharacters;
    private boolean onlyFavorites;
    private List<Character> data = new ArrayList<>();

    private final static int LIMIT = 12;
    private int offset = 0;
    private boolean lastPageReached = false;

    private boolean resetRequested = false;

    public CharactersPresenter(@android.support.annotation.NonNull PresenterConfiguration presenterConfiguration,
                        boolean onlyFavorites,
                        GetCharacters getCharacters, FavoriteCharacters favoriteCharacters) {
        this.presenterConfiguration = presenterConfiguration;
        this.onlyFavorites = onlyFavorites;
        this.getCharacters = getCharacters;
        this.favoriteCharacters = favoriteCharacters;
    }

    @Override
    public void requestCharacters(boolean reset) {
        if (reset) {
            offset = 0;
            lastPageReached = false;
            resetRequested = true;
            data.clear();
        }
        if (!lastPageReached) {
            if (onlyFavorites) {
                favoriteCharacters.loadCharacters()
                        .subscribeOn(Schedulers.io())//presenterConfiguration.ioScheduler())//Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Observer<List<Character>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull List<Character> characters) {
                                        onCharactersLoadSuccess(characters);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        onCharactersLoadFailure(e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        );
            } else {
                getCharacters.loadCharacters(offset, LIMIT)
                        .subscribeOn(Schedulers.io())//(presenterConfiguration.ioScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Observer<List<Character>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull List<Character> characters) {
                                        onCharactersLoadSuccess(characters);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        onCharactersLoadFailure(e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        );
            }
        } else {
            //To notify view and stop loader
            view.setCharactersView(new ArrayList<>());
        }
    }

    @Override
    public void openCharacters(Character character, int position) {
        view.openDetail(character, position);
    }

    /**
     * Updates specific item inside view when you know its position in the list;
     *
     * @param character item shown
     * @param position  position of the item
     */
    @Override
    public void updateItem(Character character, int position) {
        if (onlyFavorites) {
            if (!character.isFavorite()) {
                view.removeItem(position);
                data.remove(position);
                view.askToUpdateAll(character);
            }
        } else {
            view.setItem(character, position);
            data.set(position, character);
            view.askToUpdateFavorite(character);
        }
    }

    /**
     * Updates specific item inside view when you don't know its position in the list;
     *
     * @param character item shown
     */
    @Override
    public void updateItem(Character character) {
        int position = getItemPosition(character);
        if (onlyFavorites) {
            if (!character.isFavorite()) {
                if (position >= 0) {
                    view.removeItem(position);
                    data.remove(position);
                }
            } else {
                if (position < 0) {
                    view.addItem(character);
                    data.add(character);
                }
            }
        } else {
            if (position >= 0) {
                view.setItem(character, position);
                data.set(position, character);
            }
        }
    }

    private int getItemPosition(Character character) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == character.getId()) {
                return i;
            }
        }
        return -1;
    }

    //@Override
    public void onCharactersLoadSuccess(List<Character> characters) {
        offset += LIMIT;
        data.addAll(characters);
        if (resetRequested) {
            view.setupCharactersView(characters);
            resetRequested = false;
        } else {
            view.setCharactersView(characters);
        }
        if (onlyFavorites || characters.size() < 1) {
            lastPageReached = true;
        }
    }

    //@Override
    public void onCharactersLoadFailure(String error) {
        if (error != null && !error.isEmpty()) {
            view.setEmptyCharacters(error);
        } else {
            view.setEmptyCharacters();
        }
    }


    @VisibleForTesting
    public List<Character> getData() {
        return data;
    }

    @VisibleForTesting
    public int getOffset() {
        return offset;
    }

    @VisibleForTesting
    public boolean isLastPageReached() {
        return lastPageReached;
    }

    @VisibleForTesting
    public boolean isResetRequested() {
        return resetRequested;
    }

    @VisibleForTesting
    public void setGetCharacters(GetCharacters getCharacters) {
        this.getCharacters = getCharacters;
    }

    @VisibleForTesting
    public void setFavoriteCharacters(FavoriteCharacters favoriteCharacters) {
        this.favoriteCharacters = favoriteCharacters;
    }

    private void clearSubscriptions() {
        compositeDisposable.clear();
    }

    public void onStop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void bindView(@android.support.annotation.NonNull Object view) {
        super.bindView(view);
        this.view = (GetCharactersContract.View) view();
    }
}
