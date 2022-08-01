package com.example.utspt2072028graceag.model;

public class Movie {
    private int idMovie;
    private String title;
    private String genre;
    private int durasi;

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    @Override
    public String toString() {
        return title;
    }

    public Movie() {

    }
}
