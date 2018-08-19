package com.example.tularamsubba.popularmovieone;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie implements Parcelable {
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie() {}

    protected Movie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        voteAverage = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return "http://image.tmdb.org/t/p/w500" + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return "http://image.tmdb.org/t/p/w500" + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage){
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeDouble(voteAverage);
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
