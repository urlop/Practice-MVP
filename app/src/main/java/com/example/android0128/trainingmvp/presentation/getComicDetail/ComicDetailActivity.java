package com.example.android0128.trainingmvp.presentation.getComicDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.presentation.getComicDetail.callbacks.GetComicDetailContract;
import com.example.android0128.trainingmvp.presentation.models.Comic;
import com.example.android0128.trainingmvp.utils.Constants;
import com.example.android0128.trainingmvp.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicDetailActivity extends AppCompatActivity implements GetComicDetailContract.View {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_thumbnail)
    ImageView iv_thumbnail;
    @BindView(R.id.iv_favorite)
    ImageView iv_favorite;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.tv_creators)
    TextView tv_creators;
    @BindView(R.id.tv_characters)
    TextView tv_characters;

    private GetComicDetailContract.UserActionsListener presenter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        ButterKnife.bind(this);

        presenter = new ComicPresenter(this,
                (Comic) getIntent().getExtras().getSerializable(Constants.EXTRA_CHARACTER));
        position = getIntent().getExtras().getInt(Constants.EXTRA_POSITION);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iv_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setFavorite();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void setComicView(Comic comic) {
        tv_name.setText(comic.getName());
        Picasso.with(this).load(Utils.getComicImagePath(comic)).into(iv_thumbnail);
        iv_favorite.setImageResource(comic.isFavorite() ? R.drawable.ic_action_star_10 : R.drawable.ic_action_star_0);
        tv_description.setText(comic.getDescription());
        tv_creators.setText(Utils.getListTextComic(comic.getCreators().getItems()));
        tv_characters.setText(Utils.getListTextComic(comic.getCharacters().getItems()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeFavorite(boolean isFavorite) {
        iv_favorite.setImageResource(isFavorite ? R.drawable.ic_action_star_10 : R.drawable.ic_action_star_0);
        showMessage(isFavorite ? "Favorited" : "Removed");
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Constants.EXTRA_CHARACTER, presenter.getResult());
        resultIntent.putExtra(Constants.EXTRA_POSITION, position);
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
}
