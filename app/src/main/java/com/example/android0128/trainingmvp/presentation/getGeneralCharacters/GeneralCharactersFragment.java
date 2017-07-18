package com.example.android0128.trainingmvp.presentation.getGeneralCharacters;

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
import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersFragment;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.presentation.models.Event;
import com.example.android0128.trainingmvp.utils.TabAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralCharactersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralCharactersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralCharactersFragment extends Fragment implements ChildenFragmentCallback {

    private OnFragmentInteractionListener mListener;
    CharactersFragment allFragment, favoriteFragment;

    public GeneralCharactersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GeneralCharactersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralCharactersFragment newInstance() {
        GeneralCharactersFragment fragment = new GeneralCharactersFragment();
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
        allFragment = CharactersFragment.newInstance(3, false);
        favoriteFragment = CharactersFragment.newInstance(3, true);
        adapter.addFragment(allFragment, "All");
        adapter.addFragment(favoriteFragment, "Favorites");
        viewPager.setAdapter(adapter);
    }

    public void updateChildFragmentFavorites(Character character){
        favoriteFragment.getPresenter().updateItem(character);
    }

    public void updateChildFragmentAll(Character character){
        allFragment.getPresenter().updateItem(character);
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
            updateChildFragmentFavorites((Character) item);
        } else {
            updateChildFragmentAll((Character) item);
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
