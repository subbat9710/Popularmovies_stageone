package com.example.tularamsubba.popularmovieone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";

    private Movie mMovie;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView releaseDate;
    TextView description;
    TextView userRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(mMovie.getTitle());

        backdrop = (ImageView) findViewById(R.id.backdrop);
        title = (TextView) findViewById(R.id.movie_title);
        releaseDate = (TextView) findViewById(R.id.movie_releaseDate);
        description = (TextView) findViewById(R.id.movie_description);
        poster = (ImageView) findViewById(R.id.movie_poster);
        userRating = (TextView) findViewById(R.id.movie_userRating);


        title.setText(mMovie.getTitle());
        releaseDate.setText("Release Date: " + mMovie.getReleaseDate());
        description.setText("Description: \n" + mMovie.getDescription());
        userRating.setText("Rating: " + Double.toString(mMovie.getVoteAverage()));

        Picasso.with(this)
                .load(mMovie.getPoster())
                .into(poster);
        Picasso.with(this)
                .load(mMovie.getBackdrop())
                .into(backdrop);
    }
}
