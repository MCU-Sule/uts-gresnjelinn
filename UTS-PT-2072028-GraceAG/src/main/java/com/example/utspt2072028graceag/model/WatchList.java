package com.example.utspt2072028graceag.model;

public class WatchList {
    private int idWatchList;
    private int lastWatch;

    public String getLastWatchString() {
        return String.valueOf(getLastWatch()) + '/';
    }

    public void setLastWatchString(String lastWatchString) {
        this.lastWatchString = lastWatchString;
    }

    public boolean isFavorite() {
        return favorite;
    }

    private String lastWatchString;
    private boolean favorite;
    private Movie movie;
    private User user;

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public int getLastWatch() {
        return lastWatch;
    }

    public void setLastWatch(int lastWatch) {
        this.lastWatch = lastWatch;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WatchList() {

    }
}