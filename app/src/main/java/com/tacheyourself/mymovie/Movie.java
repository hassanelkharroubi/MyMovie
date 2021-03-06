package com.tacheyourself.mymovie;

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

    public Movie(int mId, String mTitle, String mDescription, String mLanguage, String mLinkMovie, String mLinkImage, int mYear) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mLanguage = mLanguage;
        this.mLinkMovie = mLinkMovie;
        this.mLinkImage = mLinkImage;
        this.mYear = mYear;
    }

    public int getmId() {
        return mId;
    }

    public int getmYear() {
        return mYear;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public String getmLinkImage() {
        return mLinkImage;
    }

    public String getmLinkMovie() {
        return mLinkMovie;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public void setmLinkImage(String mLinkImage) {
        this.mLinkImage = mLinkImage;
    }

    public void setmLinkMovie(String mLinkMovie) {
        this.mLinkMovie = mLinkMovie;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }
}
