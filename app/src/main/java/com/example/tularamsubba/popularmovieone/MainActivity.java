package com.example.tularamsubba.popularmovieone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getPopularMovies();
        getTopRatedMovies();
    }

    private void getPopularMovies(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "da2f2f7cb30a2624ad72a8eaa02dfea7");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.getPopularMovies(new Callback<Movie.MovieResult>() {
            @Override
            public void success(Movie.MovieResult movieResult, Response response) {
                mAdapter.setmMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    private void getTopRatedMovies(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "da2f2f7cb30a2624ad72a8eaa02dfea7");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.getTopRatedMovies(new Callback<Movie.MovieResult>() {
            @Override
            public void success(Movie.MovieResult movieResult, Response response) {
                mAdapter.setmMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public MovieViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public static class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>{
        private List<Movie> mMovieList;
        private LayoutInflater mInflater;
        private Context mContext;

        public MoviesAdapter(Context context){
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
            View view = mInflater.inflate(R.layout.row_movie, parent, false);
            final MovieViewHolder viewHolder = new MovieViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = viewHolder.getAdapterPosition();
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, mMovieList.get(position));
                    mContext.startActivity(intent);

                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position){
            Movie movie = mMovieList.get(position);

            //This is how we use Picasso to load images from the internet.
            Picasso.with(mContext)
                    .load(movie.getPoster())
                    .placeholder(R.color.colorAccent)
                    .into(holder.imageView);
        }
        @Override
        public int getItemCount(){
            return (mMovieList == null) ? 0 : mMovieList.size();
        }

        public void setmMovieList(List<Movie> movieList){
            this.mMovieList = new ArrayList<>();
            this.mMovieList.addAll(movieList);

            // The adapter needs to know that the data has changed. If we don't call this, app will crash.
            notifyDataSetChanged();
        }
    }

}
