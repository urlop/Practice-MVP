package com.example.android0128.trainingmvp.presentation.getGeneralComics;

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
import com.example.android0128.trainingmvp.presentation.getComics.ComicsFragment;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.utils.TabAdapter;
import com.example.android0128.trainingmvp.presentation.models.Comic;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralComicsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralComicsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralComicsFragment extends Fragment implements ChildenFragmentCallback {

    private OnFragmentInteractionListener mListener;
    ComicsFragment allFragment, favoriteFragment;

    public GeneralComicsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GeneralComicsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralComicsFragment newInstance() {
        GeneralComicsFragment fragment = new GeneralComicsFragment();
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
        allFragment = ComicsFragment.newInstance(2, false);
        favoriteFragment = ComicsFragment.newInstance(2, true);
        adapter.addFragment(allFragment, "All");
        adapter.addFragment(favoriteFragment, "Favorites");
        viewPager.setAdapter(adapter);
    }

    public void updateChildFragmentFavorites(Comic comic){
        favoriteFragment.getPresenter().updateItem(comic);
    }

    public void updateChildFragmentAll(Comic comic){
        allFragment.getPresenter().updateItem(comic);
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
            updateChildFragmentFavorites((Comic) item);
        } else {
            updateChildFragmentAll((Comic) item);
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
