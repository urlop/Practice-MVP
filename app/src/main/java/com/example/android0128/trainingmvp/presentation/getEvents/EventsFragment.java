package com.example.android0128.trainingmvp.presentation.getEvents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
import com.example.android0128.trainingmvp.presentation.getEventDetail.EventDetailActivity;
import com.example.android0128.trainingmvp.presentation.getEvents.callbacks.GetEventsContract;
import com.example.android0128.trainingmvp.presentation.callbacks.ChildenFragmentCallback;
import com.example.android0128.trainingmvp.presentation.models.Event;
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
public class EventsFragment extends Fragment implements GetEventsContract.View {

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
    MyEventsRecyclerViewAdapter adapter;

    private GetEventsContract.UserActionsListener presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventsFragment newInstance(int columnCount, boolean onlyFavorites) {
        EventsFragment fragment = new EventsFragment();
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
            public void onListFragmentInteraction(Event item, int position) {
                presenter.openEvents(item, position);
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
        adapter = new MyEventsRecyclerViewAdapter(getContext(), new ArrayList<Event>(), mListener);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestEvents(true);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                presenter.requestEvents(false);
            }
        });

        presenter = new EventsPresenter(this, mOnlyFavorites);
        presenter.requestEvents(true);

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
    public void setEventsView(ArrayList<Event> events) {
        adapter.addData(events);
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void setupEventsView(ArrayList<Event> events) {
        adapter = new MyEventsRecyclerViewAdapter(getContext(), events, mListener);
        recyclerView.setAdapter(adapter);
        materialRefreshLayout.finishRefresh();
    }

    @Override
    public void setEmptyEvents() {
        Toast.makeText(getContext(), getString(R.string.error_no_data), Toast.LENGTH_SHORT).show();
        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void openDetail(Event event, int position) {
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CHARACTER, event);
        intent.putExtra(Constants.EXTRA_POSITION, position);
        startActivityForResult(intent, Constants.CODE_DETAIL_CHARACTER);
    }

    @Override
    public void setItem(Event event, int position) {
        adapter.setItem(event, position);
    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void addItem(Event event) {
        adapter.addItem(event);
    }

    @Override
    public void askToUpdateFavorite(Event event) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(event, true);
        }
        //((GeneralEventsFragment) getParentFragment()).updateChildFragmentFavorites(event);
    }

    @Override
    public void askToUpdateAll(Event event) {
        if (null != mListener) {
            mChildrenListener.OnFragmentChange(event, false);
        }
        //((GeneralEventsFragment) getParentFragment()).updateChildFragmentAll(event);
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
        // TODO: Update argument type and name
        void onListFragmentInteraction(Event item, int position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.CODE_DETAIL_CHARACTER) {
                Event event = (Event) data.getExtras().getSerializable(Constants.EXTRA_CHARACTER);
                int position = data.getExtras().getInt(Constants.EXTRA_POSITION);
                presenter.updateItem(event, position);
            }
        }
    }

    public GetEventsContract.UserActionsListener getPresenter() {
        return presenter;
    }
}
