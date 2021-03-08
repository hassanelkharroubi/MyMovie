package com.tacheyourself.mymovie.Model;

import java.io.Serializable;

public class Movie implements Serializable {

    private int mId;
    private String mTitle;
    private String mDescription;
    private String mLanguage;
    private String mLinkMovie;
    private String mLinkImage;
    private String mImdbID;
    private int mYear;


    public String getImdbID() {
        return mImdbID;
    }

    public void setImdbID(String imdbID) {
        mImdbID = imdbID;
    }

    public Movie(){

    }

    public Movie(int id, String title, String description, String language, String linkMovie, String linkImage, int year, String imdbID) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mLanguage = language;
        mLinkMovie = linkMovie;
        mLinkImage = linkImage;
        mYear = year;
        mImdbID=imdbID;
    }

    public Movie(int id, String title, String description, String language, String linkImage, int year, String imdbID) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mLanguage = language;
        mLinkImage = linkImage;
        mYear = year;
        mImdbID=imdbID;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getLinkMovie() {
        return mLinkMovie;
    }

    public void setLinkMovie(String linkMovie) {
        mLinkMovie = linkMovie;
    }

    public String getLinkImage() {
        return mLinkImage;
    }

    public void setLinkImage(String linkImage) {
        mLinkImage = linkImage;
    }

    public int getYear() {
        return mYear;
    }
    public void setYear(int year) {
        mYear = year;
    }


    /**
     * to retreive a query parameters to use them in url
     * @ query parameters
     */
    public String getQueryParameters(){
        String url="?title="+mTitle+"&description="+mDescription+"&language="
                +mLanguage+"&linkMovie="+mLinkMovie+"&linkImage="+mLinkImage+"&year="+mYear;

        return url;
    }



}
