package com.tacheyourself.mymovie.Model;

public class Movie {

    private int mId;
    private String mTitle;
    private String mDescription;
    private String mLanguage;
    private String mLinkMovie;
    private String mLinkImage;
    private int mYear;


    public Movie(){

    }

    public Movie(int id, String title, String description, String language, String linkMovie, String linkImage, int year) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mLanguage = language;
        mLinkMovie = linkMovie;
        mLinkImage = linkImage;
        mYear = year;
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
}