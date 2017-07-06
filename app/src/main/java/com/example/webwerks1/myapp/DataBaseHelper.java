package com.example.webwerks1.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.webwerks1.myapp.movies.Results;
import com.example.webwerks1.myapp.tvseries.TvResults;

import java.util.ArrayList;

/**
 * Created by webwerks1 on 17/5/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "userManager";


    public static final String MOVIE_TABLE = "movies";

    // Movies Table Columns names
    private static final String ID = "id";
    private static final String MOVIE_ID="movie_id";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW="overview";
    private static final String RELEASE_DATE="release_date";
    private static final String ORIGINAL_LANGUAGE="original_language";
    private static final String TITLE="title";
    private static final String BACKDROP_PATH="backdrop_path";
    private static final String POPULARITY="popularity";
    private static final String VOTE_COUNT="vote_count";
    private static final String VOTE_AVG="vote_average";


    public static final String TV_TABLE = "tv";

    // Tv Table Columns names
    private static final String TV_ID = "tv_id";
    private static final String TV_POSTER_PATH = "tv_poster_path";
    private static final String TV_POPULARITY="tv_popularity";
    private static final String TV_BACKDROP_PATH="tv_backdrop_path";
    private static final String TV_VOTE_AVG="tv_vote_average";
    private static final String TV_OVERVIEW="tv_overview";
    private static final String TV_FIRST_AIR="tv_first_air";
    private static final String TV_ORIGINAL_LANGUAGE="tv_original_language";
    private static final String TV_VOTE_COUNT="tv_vote_count";
    private static final String TV_NAME="tv_name";
    private static final String TV_ORIGINAL_NAME="tv_original_name";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + MOVIE_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MOVIE_ID +" INTEGER ,"
                + ORIGINAL_TITLE + " TEXT,"
                + RELEASE_DATE + " TEXT,"
                + POSTER_PATH +" TEXT ,"
                + ORIGINAL_LANGUAGE+" TEXT,"
                + ADULT +" TEXT, "
                + OVERVIEW +" TEXT, "
                + TITLE +" TEXT, "
                + BACKDROP_PATH +" TEXT, "
                + POPULARITY+" REAL,"
                + VOTE_COUNT+" INTEGER,"
                + VOTE_AVG+" REAL"+ ")";

        String CREATE_TV_TABLE="CREATE TABLE "+TV_TABLE +" ("
                +ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TV_ID +" INTEGER ,"
                +TV_ORIGINAL_NAME +" TEXT ,"
                +TV_FIRST_AIR +" TEXT ,"
                +TV_POSTER_PATH +" TEXT ,"
                +TV_ORIGINAL_LANGUAGE +" TEXT ,"
                +TV_OVERVIEW +" TEXT ,"
                +TV_NAME+" TEXT ,"
                +TV_BACKDROP_PATH+" TEXT ,"
                +TV_POPULARITY+" REAL ,"
                +TV_VOTE_COUNT+" REAL ,"
                +TV_VOTE_AVG+" REAL "+")";


        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_TV_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

  /*  public void addUser(User user)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_NAME,user.getName());
        values.put(KEY_PASSWORD,user.getPassword());
        values.put(KEY_SESSION_ID,user.getSession_id());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }*/


    public void addMovie(Results results){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MOVIE_ID,results.getId());
        values.put(ORIGINAL_TITLE,results.getOriginal_title());
        values.put(RELEASE_DATE,results.getRelease_date());
        values.put(POSTER_PATH,results.getPoster_path());
        values.put(ORIGINAL_LANGUAGE,results.getOriginal_language());
        values.put(ADULT,results.isAdult());
        values.put(OVERVIEW,results.getOverview());
        values.put(TITLE,results.getTitle());
        values.put(BACKDROP_PATH,results.getBackdrop_path());
        values.put(POPULARITY,results.getPopularity());
        values.put(VOTE_COUNT,results.getVote_count());
        values.put(VOTE_AVG,results.getVote_average());
        database.insert(MOVIE_TABLE,null,values);
        database.close();
    }


    public void addTv(TvResults tvResults){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(TV_ID,tvResults.getId());
        values.put(TV_ORIGINAL_NAME,tvResults.getOriginal_name());
        values.put(TV_FIRST_AIR,tvResults.getFirst_air_date());
        values.put(TV_POSTER_PATH,tvResults.getPoster_path());
        values.put(TV_ORIGINAL_LANGUAGE,tvResults.getOriginal_language());
        values.put(TV_OVERVIEW,tvResults.getOverview());
        values.put(TV_NAME,tvResults.getName());
        values.put(TV_BACKDROP_PATH,tvResults.getBackdrop_path());
        values.put(TV_POPULARITY,tvResults.getPopularity());
        values.put(TV_VOTE_COUNT,tvResults.getVote_count());
        values.put(TV_VOTE_AVG,tvResults.getVote_average());

        database.insert(TV_TABLE,null,values);
        database.close();
    }

    public boolean isMovieInDatabase(Results results){
        Log.e("isMOvie>>",results.getOriginal_title());
        String isMovieExistsQuery="SELECT  * FROM " + MOVIE_TABLE +" where "+MOVIE_ID+" = "+results.getId()+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(isMovieExistsQuery, null);
        Log.e("Cursor",cursor.getCount()+"");
        if(cursor.getCount() > 0)
        {
            cursor.close();
            db.close();
            return true;
        }
        else {
            // Does not exist!
            cursor.close();
            db.close();
            return false;
        }
    }


    public boolean isTVInDatabase(TvResults tvResults){
        Log.e("isMOvie>>",tvResults.getOriginal_name());
        String isMovieExistsQuery="SELECT  * FROM " + TV_TABLE +" where "+TV_ID+" = "+tvResults.getId()+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(isMovieExistsQuery, null);
        Log.e("Cursor",cursor.getCount()+"");
        if(cursor.getCount() > 0)
        {
            cursor.close();
            db.close();
            return true;
        }
        else {
            // Does not exist!
            cursor.close();
            db.close();
            return false;
        }
    }


    public ArrayList<Results> getAllMovies(){

        SQLiteDatabase database=getReadableDatabase();
        String query="SELECT * FROM "+MOVIE_TABLE;
        Cursor cursor=database.rawQuery(query,null);
        Log.d("****",cursor.getCount()+"");
        ArrayList<Results> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Results results=new Results();
                results.setId(Integer.parseInt(cursor.getString(1)));
                results.setOriginal_title(cursor.getString(2));
                results.setRelease_date(cursor.getString(3));
                results.setPoster_path(cursor.getString(4));
                results.setOriginal_language(cursor.getString(5));
                results.setAdult((cursor.getString(6)=="true")?true:false);
                results.setOverview(cursor.getString(7));
                results.setTitle(cursor.getString(8));
                results.setBackdrop_path(cursor.getString(9));
                results.setPopularity(Double.parseDouble(cursor.getString(10)));
                results.setVote_count(Integer.parseInt(cursor.getString(11)));
                results.setVote_average(Double.parseDouble(cursor.getString(12)));
                // Adding contact to list
                list.add(results);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }



    public ArrayList<TvResults> getAllTV(){

        SQLiteDatabase database=getReadableDatabase();
        String query="SELECT * FROM "+TV_TABLE;
        Cursor cursor=database.rawQuery(query,null);
        Log.d("****",cursor.getCount()+"");
        ArrayList<TvResults> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                TvResults results=new TvResults();
                results.setId(Integer.parseInt(cursor.getString(1)));
                results.setOriginal_name(cursor.getString(2));
                results.setFirst_air_date(cursor.getString(3));
                results.setPoster_path(cursor.getString(4));
                results.setOriginal_language(cursor.getString(5));
                results.setOverview(cursor.getString(7));
                results.setName(cursor.getString(8));
                results.setBackdrop_path(cursor.getString(9));
                results.setPopularity(Double.parseDouble(cursor.getString(10)));
                results.setVote_count(Double.parseDouble(cursor.getString(11)));
                // Adding contact to list
                list.add(results);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


    public void deleteMovie(String id){
        String deleteAllContactsQuery= "DELETE FROM "+MOVIE_TABLE+" where "+MOVIE_ID+" = "+id+" ;";
        Log.e("deleteMovie","gere");
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(deleteAllContactsQuery);
        db.close();
    }

    public void deleteTv(String id){
        String deleteAllContactsQuery= "DELETE FROM "+TV_TABLE+" where "+TV_ID+" = "+id+" ;";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(deleteAllContactsQuery);
        db.close();
    }
}
