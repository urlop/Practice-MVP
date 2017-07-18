package com.example.android0128.trainingmvp.presentation.getCharacters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.android0128.trainingmvp.MvpApp;
import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.data.usecase.FavoriteCharacters;
import com.example.android0128.trainingmvp.data.usecase.GetCharacters;
import com.example.android0128.trainingmvp.presentation.NavigationActivity;
import com.example.android0128.trainingmvp.presentation.PresenterConfiguration;
import com.example.android0128.trainingmvp.presentation.callbacks.ChildenFragmentCallback;
import com.example.android0128.trainingmvp.presentation.getCharacterDetail.CharacterDetailActivity;
import com.example.android0128.trainingmvp.presentation.getCharacters.callbacks.GetCharactersContract;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import io.reactivex.schedulers.Schedulers;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CharactersFragment extends Fragment implements GetCharactersContract.View {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @BindView(R.id.refresh)
    public MaterialRefreshLayout materialRefreshLayout;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ONLY_FAVORITES = "only-favorites";
    private int mColumnCount = 3;
    private boolean mOnlyFavorites;
    private OnListFragmentInteractionListener mListener;
    private ChildenFragmentCallback mChildrenListener;
    MyCharactersRecyclerViewAdapter adapter;

    @Inject
    CharactersPresenter presenter; //GetCharactersContract.UserActionsListener presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharactersFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CharactersFragment newInstance(int columnCount, boolean onlyFavorites) {
        CharactersFragment fragment = new CharactersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putBoolean(ARG_ONLY_FAVORITES, onlyFavorites);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mOnlyFavorites = getArguments().getBoolean(ARG_ONLY_FAVORITES);
        }

        ((MvpApp) getActivity().getApplication()).applicationComponent().plus(
                new CharactersFragmentModule(mOnlyFavorites, new GetCharacters(), new FavoriteCharacters())).inject(this);

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void OnListFragmentInteraction(Character item, int position) {
                presenter.openCharacters(item, position);
            }
        };
        mChildrenListener = (ChildenFragmentCallback) getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_list, container, false);
        ButterKnife.bind(this, view);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter = new MyCharactersRecyclerViewAdapter(getActivity().getLayoutInflater(), getContext(), new ArrayList<>(), mListener);
        recyclerView.setAdapter(adapter);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestCharacters(true);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestCharacters(false);
            }
        });

        //presenter = new CharactersPresenter(this, mOnlyFavorites, new GetCharacters(), new FavoriteCharacters());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.bindView(this);
        presenter.requestCharacters(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setCharactersView(List<Character> characters) {
        adapter.addData(characters);
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void setupCharactersView(List<Character> characters) {
        adapter = new MyCharactersRecyclerViewAdapter(getActivity().getLayoutInflater(), getContext(), characters, mListener);
        recyclerView.setAdapter(adapter);
        materialRefreshLayout.finishRefresh();
    }

    @Override
    public void setEmptyCharacters(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void setEmptyCharacters() {
        Toast.makeText(getContext(), getString(R.string.error_no_data), Toast.LENGTH_SHORT).show();
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void openDetail(Character character, int position) {
        Intent intent = new Intent(getContext(), CharacterDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CHARACTER, character);
        intent.putExtra(Constants.EXTRA_POSITION, position);
        startActivityForResult(intent, Constants.CODE_DETAIL_CHARACTER);
    }

    @Override
    public void setItem(Character character, int position) {
        adapter.setItem(character, position);
    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void addItem(Character character) {
        adapter.addItem(character);
    }

    @Override
    public void askToUpdateFavorite(Character character) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(character, true);
        }
    }

    @Override
    public void askToUpdateAll(Character character) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(character, false);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void OnListFragmentInteraction(Character item, int position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.CODE_DETAIL_CHARACTER) {
                Character character = (Character) data.getExtras().getSerializable(Constants.EXTRA_CHARACTER);
                int position = data.getExtras().getInt(Constants.EXTRA_POSITION);
                presenter.updateItem(character, position);
            }
        }
    }

    public GetCharactersContract.UserActionsListener getPresenter() {
        return presenter;
    }

    @Subcomponent(modules = CharactersFragmentModule.class)
    public interface CharactersFragmentComponent {
        void inject(@NonNull CharactersFragment fragment);
    }

    @Module
    public static class CharactersFragmentModule {

        private boolean onlyFavorites;
        private GetCharacters getCharacters;
        private FavoriteCharacters favoriteCharacters;

        public CharactersFragmentModule(boolean onlyFavorites,
                             @NonNull GetCharacters getCharacters,
                             @NonNull FavoriteCharacters favoriteCharacters) {
            this.onlyFavorites = onlyFavorites;
            this.getCharacters = getCharacters;
            this.favoriteCharacters = favoriteCharacters;
        }

        @Provides
        @NonNull
        public CharactersPresenter provideCharactersPresenter() {
            return new CharactersPresenter(
                    PresenterConfiguration.builder().ioScheduler(Schedulers.io()).build(),
                    onlyFavorites,
                    getCharacters,
                    favoriteCharacters
            );
        }
    }
}
