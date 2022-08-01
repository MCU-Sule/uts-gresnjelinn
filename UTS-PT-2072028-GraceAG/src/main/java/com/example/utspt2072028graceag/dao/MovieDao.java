package com.example.utspt2072028graceag.dao;

import com.example.utspt2072028graceag.model.Movie;
import com.example.utspt2072028graceag.model.User;
import com.example.utspt2072028graceag.model.WatchList;
import com.example.utspt2072028graceag.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDao implements DaoInterface<Movie> {

    @Override
    public ObservableList<Movie> getData() {
        ObservableList<Movie> mList;
        mList = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "select * from movie";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                Movie m = new Movie();
                m.setIdMovie(result.getInt("idmovie"));
                m.setTitle(result.getString("title"));
                m.setGenre(result.getString("genre"));
                m.setDurasi(result.getInt("durasi"));
                mList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mList;
    }

    @Override
    public int addData(Movie data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "INSERT INTO movie(title, genre, durasi) VALUES(?, ?, ?)";
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(kalimat_sql);
                ps.setString(1, data.getTitle());
                ps.setString(2, data.getGenre());
                ps.setInt(3, data.getDurasi());
                hasil = ps.executeUpdate();
                if (hasil > 0) {
                    System.out.println("Data berhasil di insert");
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int delData(Movie data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "DELETE FROM movie WHERE idmovie = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(kalimat_sql);
                ps.setInt(1, data.getIdMovie());
                hasil = ps.executeUpdate();
                if (hasil > 0) {
                    System.out.println("berhasil delete data");
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    @Override
    public int updateData(Movie data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "UPDATE movie SET title = ?, genre = ?, durasi = ? WHERE idmovie = ?";
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(kalimat_sql);
                ps.setString(1, data.getTitle());
                ps.setString(2, data.getGenre());
                ps.setInt(3, data.getDurasi());
                ps.setInt(4, data.getIdMovie());
                hasil = ps.executeUpdate();
                if (hasil > 0) {
                    System.out.println("Update berhasil dilakukan");
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasil;
    }

    public ObservableList<Movie> getFilteredData(String genre) {
        ObservableList<Movie> mfList;
        mfList = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "SELECT * FROM movie WHERE genre LIKE ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ps.setString(1, genre);

            ResultSet result = ps.executeQuery();
            while(result.next()) {
                Movie m = new Movie();
                m.setIdMovie(result.getInt("idmovie"));
                m.setTitle(result.getString("title"));
                m.setGenre(result.getString("genre"));
                m.setDurasi(result.getInt("durasi"));
                mfList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mfList;
    }
}
