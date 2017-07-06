package com.example.webwerks1.myapp.tvseries;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by webwerks1 on 1/6/16.
 */
public class TvResults implements Parcelable
{
    private String poster_path;
    private double popularity;
    private int id;
    private String backdrop_path;
    private double vote_average;
    private String overview;
    private String first_air_date;
    private String original_language;
    private Double vote_count;
    private String name;
    private String original_name;
    private List<String> origin_country;
    private List<Integer> genre_ids;

    public TvResults(){

    }

    protected TvResults(Parcel in) {
        poster_path = in.readString();
        popularity = in.readDouble();
        id = in.readInt();
        backdrop_path = in.readString();
        vote_average = in.readDouble();
        overview = in.readString();
        first_air_date = in.readString();
        original_language = in.readString();
        vote_count = in.readDouble();
        name = in.readString();
        original_name = in.readString();
        origin_country = in.createStringArrayList();
    }

    public static final Creator<TvResults> CREATOR = new Creator<TvResults>() {
        @Override
        public TvResults createFromParcel(Parcel in) {
            return new TvResults(in);
        }

        @Override
        public TvResults[] newArray(int size) {
            return new TvResults[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Double getVote_count() {
        return vote_count;
    }

    public void setVote_count(Double vote_count) {
        this.vote_count = vote_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeDouble(popularity);
        dest.writeInt(id);
        dest.writeString(backdrop_path);
        dest.writeDouble(vote_average);
        dest.writeString(overview);
        dest.writeString(first_air_date);
        dest.writeString(original_language);
        dest.writeDouble(vote_count);
        dest.writeString(name);
        dest.writeString(original_name);
        dest.writeStringList(origin_country);
    }
}
