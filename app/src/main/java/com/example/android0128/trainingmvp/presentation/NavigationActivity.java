package com.example.android0128.trainingmvp.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android0128.trainingmvp.MvpApp;
import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.data.usecase.FavoriteCharacters;
import com.example.android0128.trainingmvp.data.usecase.GetCharacters;
import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersFragment;
import com.example.android0128.trainingmvp.presentation.getCharacters.CharactersPresenter;
import com.example.android0128.trainingmvp.presentation.getCharacters.callbacks.GetCharactersContract;
import com.example.android0128.trainingmvp.presentation.getGeneralCharacters.GeneralCharactersFragment;
import com.example.android0128.trainingmvp.presentation.getGeneralComics.GeneralComicsFragment;
import com.example.android0128.trainingmvp.presentation.getGeneralEvent.GeneralEventsFragment;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import io.reactivex.schedulers.Schedulers;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;

    final int FRAGMENT_CHARACTERS = 0;
    final int FRAGMENT_COMICS = 1;
    final int FRAGMENT_EVENTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpApp.getInstance().applicationComponent().inject(this);

        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        goToFragment(FRAGMENT_CHARACTERS);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_characters) {
            goToFragment(FRAGMENT_CHARACTERS);
        } else if (id == R.id.nav_comics) {
            goToFragment(FRAGMENT_COMICS);
        } else if (id == R.id.nav_events) {
            goToFragment(FRAGMENT_EVENTS);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToFragment(int fragmentStep){
        Fragment fragment;
        int titleInt;
        switch (fragmentStep) {
            case 1:
                fragment = GeneralComicsFragment.newInstance();
                titleInt = R.string.title_comics;
                break;
            case 2:
                fragment = GeneralEventsFragment.newInstance();
                titleInt = R.string.title_events;
                break;
            default:
                fragment = GeneralCharactersFragment.newInstance();
                titleInt = R.string.title_characters;
                break;
        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
        toolbar.setTitle(titleInt);
    }
}
