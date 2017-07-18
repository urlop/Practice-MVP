package com.example.android0128.trainingmvp.presentation.getCharacters;

import com.example.android0128.trainingmvp.RxImmediateSchedulerRule;
import com.example.android0128.trainingmvp.data.usecase.FavoriteCharacters;
import com.example.android0128.trainingmvp.data.usecase.GetCharacters;
import com.example.android0128.trainingmvp.presentation.PresenterConfiguration;
import com.example.android0128.trainingmvp.presentation.getCharacters.callbacks.GetCharactersContract;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CharactersPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private static List<Character> CHARACTERS_ALL = Lists.newArrayList(
            new Character(1, "Name1", "description1", new Character.Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg"), new Character.Options(), new Character.Options(), new Character.Options(), new Character.Options(), false),
            new Character(2, "Name2", "description2", new Character.Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg"), new Character.Options(), new Character.Options(), new Character.Options(), new Character.Options(), true),
            new Character(3, "Name3", "description3", new Character.Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg"), new Character.Options(), new Character.Options(), new Character.Options(), new Character.Options(), false),
            new Character(4, "Name4", "description4", new Character.Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg"), new Character.Options(), new Character.Options(), new Character.Options(), new Character.Options(), true)
    );
    private static List<Character> CHARACTERS_FIRST_PART = CHARACTERS_ALL.subList(0,2);
    private static List<Character> CHARACTERS_SECOND_PART = CHARACTERS_ALL.subList(2,4);

    private static List<Character> EMPTY_CHARACTERS = new ArrayList<>(0);

    private static String ERROR_MESSAGE = "ERROR DUMMY";

    private PresenterConfiguration presenterConfiguration;

    @Mock
    private GetCharactersContract.View view;
    @Mock
    private GetCharacters getPreviews;
    @Mock
    private FavoriteCharacters favoriteCharacters;

    private CharactersPresenter charactersPresenter;

    @Before
    public void setupPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        presenterConfiguration = PresenterConfiguration.builder()
                .ioScheduler(Schedulers.trampoline()) // We don't need async behavior in tests.
                .build();

        // Get a reference to the class under test
        charactersPresenter = new CharactersPresenter(presenterConfiguration, false, getPreviews, favoriteCharacters);
        charactersPresenter.bindView(view);
    }

    @Test
    public void requestCharacters_shouldResetDataWhenRequestTrue() {
        //Get first and second request
        setupFirstGroupOfData(false);
        setupSecondGroupOfData(false);

        //Reset data (Get only first again)
        setupFirstGroupOfData(true);
    }

    @Test
    public void onCharactersLoadSuccess_shouldSetTheExactSameData() {
        charactersPresenter.onCharactersLoadSuccess(CHARACTERS_FIRST_PART);
        assertThat(charactersPresenter.getData()).contains(CHARACTERS_FIRST_PART.get(0));

        verify(view).setCharactersView(CHARACTERS_FIRST_PART);
    }

    @Test
    public void onCharactersLoadFailure_shouldShowTheRightErrorMessage() {
        charactersPresenter.onCharactersLoadFailure(ERROR_MESSAGE);

        verify(view).setEmptyCharacters(ERROR_MESSAGE);

        //Can't check toast
        //ShadowHandler.idleMainLooper();
        //assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    public void updateItem_shouldChangeThatPosition() {
        setupFirstGroupOfData(false);

        Character character = CHARACTERS_ALL.get(0);
        character.setFavorite(true);
        charactersPresenter.updateItem(character, 0);
        assertThat(charactersPresenter.getData().get(0).isFavorite()).isEqualTo(true);
    }

    @Test
    public void updateItem_shouldChangeThatItem() {
        setupFirstGroupOfData(false);

        Character character = CHARACTERS_ALL.get(0);
        character.setFavorite(true);
        charactersPresenter.updateItem(character);
        assertThat(charactersPresenter.getData().get(0).isFavorite()).isEqualTo(true);
    }

    private void setupFirstGroupOfData(boolean reset){
        when(getPreviews.loadCharacters(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Observable.just(CHARACTERS_FIRST_PART));
        when(favoriteCharacters.loadCharacters()).thenReturn(Observable.just(CHARACTERS_FIRST_PART));
        charactersPresenter.requestCharacters(reset);
        assertThat(charactersPresenter.getData()).containsExactlyElementsOf(CHARACTERS_FIRST_PART);
    }

    private void setupSecondGroupOfData(boolean reset){
        when(getPreviews.loadCharacters(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Observable.just(CHARACTERS_SECOND_PART));
        when(favoriteCharacters.loadCharacters()).thenReturn(Observable.just(CHARACTERS_SECOND_PART));
        charactersPresenter.requestCharacters(reset);
        assertThat(charactersPresenter.getData()).containsExactlyElementsOf(CHARACTERS_ALL);
    }

}
