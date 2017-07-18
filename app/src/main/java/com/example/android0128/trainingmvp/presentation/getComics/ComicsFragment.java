package com.example.android0128.trainingmvp.presentation.getComics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.presentation.callbacks.ChildenFragmentCallback;
import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersFragment;
import com.example.android0128.trainingmvp.presentation.getComicDetail.ComicDetailActivity;
import com.example.android0128.trainingmvp.presentation.getComics.ComicsPresenter;
import com.example.android0128.trainingmvp.presentation.getComics.MyComicsRecyclerViewAdapter;
import com.example.android0128.trainingmvp.presentation.getComics.callbacks.GetComicsContract;
import com.example.android0128.trainingmvp.presentation.getGeneralComics.GeneralComicsFragment;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.Comic;
import com.example.android0128.trainingmvp.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ComicsFragment extends Fragment implements GetComicsContract.View {

    @BindView(R.id.refresh)
    MaterialRefreshLayout materialRefreshLayout;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ONLY_FAVORITES = "only-favorites";
    private int mColumnCount = 3;
    private boolean mOnlyFavorites;
    private OnListFragmentInteractionListener mListener;
    private ChildenFragmentCallback mChildrenListener;
    MyComicsRecyclerViewAdapter adapter;

    private GetComicsContract.UserActionsListener presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ComicsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ComicsFragment newInstance(int columnCount, boolean onlyFavorites) {
        ComicsFragment fragment = new ComicsFragment();
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

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Comic item, int position) {
                presenter.openComics(item, position);
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
        adapter = new MyComicsRecyclerViewAdapter(getContext(), new ArrayList<Comic>(), mListener);
        recyclerView.setAdapter(adapter);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestComics(true);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestComics(false);
            }
        });

        presenter = new ComicsPresenter(this, mOnlyFavorites);
        presenter.requestComics(true);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setComicsView(ArrayList<Comic> comics) {
        adapter.addData(comics);
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void setupComicsView(ArrayList<Comic> comics) {
        adapter = new MyComicsRecyclerViewAdapter(getContext(), comics, mListener);
        recyclerView.setAdapter(adapter);
        materialRefreshLayout.finishRefresh();
    }

    @Override
    public void setEmptyComics() {
        Toast.makeText(getContext(), getString(R.string.error_no_data), Toast.LENGTH_SHORT).show();
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void openDetail(Comic comic, int position) {
        Intent intent = new Intent(getContext(), ComicDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CHARACTER, comic);
        intent.putExtra(Constants.EXTRA_POSITION, position);
        startActivityForResult(intent, Constants.CODE_DETAIL_CHARACTER);
    }

    @Override
    public void setItem(Comic comic, int position) {
        adapter.setItem(comic, position);
    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void addItem(Comic comic) {
        adapter.addItem(comic);
    }

    @Override
    public void askToUpdateFavorite(Comic comic) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(comic, true);
        }
        //((GeneralComicsFragment) getParentFragment()).updateChildFragmentFavorites(comic);
    }

    @Override
    public void askToUpdateAll(Comic comic) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(comic, false);
        }
        //((GeneralComicsFragment) getParentFragment()).updateChildFragmentAll(comic);
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
        void onListFragmentInteraction(Comic item, int position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.CODE_DETAIL_CHARACTER) {
                Comic comic = (Comic) data.getExtras().getSerializable(Constants.EXTRA_CHARACTER);
                int position = data.getExtras().getInt(Constants.EXTRA_POSITION);
                presenter.updateItem(comic, position);
            }
        }
    }

    public GetComicsContract.UserActionsListener getPresenter() {
        return presenter;
    }
}
