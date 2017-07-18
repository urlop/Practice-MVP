package com.example.android0128.trainingmvp.presentation.getGeneralEvent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.presentation.callbacks.ChildenFragmentCallback;
import com.example.android0128.trainingmvp.presentation.getEvents.EventsFragment;
import com.example.android0128.trainingmvp.presentation.models.Event;
import com.example.android0128.trainingmvp.utils.TabAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralEventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralEventsFragment extends Fragment implements ChildenFragmentCallback {

    private OnFragmentInteractionListener mListener;
    EventsFragment allFragment, favoriteFragment;

    public GeneralEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GeneralEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralEventsFragment newInstance() {
        GeneralEventsFragment fragment = new GeneralEventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_characters, container, false);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        allFragment = EventsFragment.newInstance(1, false);
        favoriteFragment = EventsFragment.newInstance(1, true);
        adapter.addFragment(allFragment, "All");
        adapter.addFragment(favoriteFragment, "Favorites");
        viewPager.setAdapter(adapter);
    }

    public void updateChildFragmentFavorites(Event event){
        favoriteFragment.getPresenter().updateItem(event);
    }

    public void updateChildFragmentAll(Event event){
        allFragment.getPresenter().updateItem(event);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnFragmentChange(Object item, boolean changeFavorite) {
        if (changeFavorite) {
            updateChildFragmentFavorites((Event) item);
        } else {
            updateChildFragmentAll((Event) item);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
