package com.example.tularamsubba.popularmovieone;

import retrofit.Callback;
import retrofit.http.GET;

public interface MoviesApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movie.MovieResult> cb);

    @GET("/movie/top_rated")
    void getTopRatedMovies(Callback<Movie.MovieResult> cb);
}
