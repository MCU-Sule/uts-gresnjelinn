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

public class WatchListDao implements DaoInterface<WatchList> {

    @Override
    public ObservableList<WatchList> getData() {
        ObservableList<WatchList> wList;
        wList = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "SELECT W.idwatchlist, w.lastwatch, w.favorite, w.movie_idmovie AS movie_id, w.user_iduser AS user_id, m.title, m.durasi, u.username, CONCAT(lastwatch, '/', durasi) AS durasi_watch FROM user u JOIN watchlist w ON u.iduser = w.user_iduser JOIN movie m ON w.movie_idmovie = m.idmovie";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                User u = new User();
                u.setIdUser(result.getInt("user_id"));
                u.setUserPassword(result.getString("username"));

                Movie m = new Movie();
                m.setIdMovie(result.getInt("movie_id"));
                m.setTitle(result.getString("title"));
                m.setDurasi(result.getInt("durasi"));

                WatchList w = new WatchList();
                w.setIdWatchList(result.getInt("idwatchlist"));
                w.setLastWatch(result.getInt("lastwatch"));
                w.setFavorite(result.getBoolean("favorite"));
                w.setMovie(m);
                w.setUser(u);
                wList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;
    }

    @Override
    public int addData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "INSERT INTO watchlist (lastwatch, favorite, movie_idmovie, user_iduser) VALUES(?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ps.setInt(1, data.getLastWatch());
            ps.setInt(2,data.getFavorite());
            ps.setInt(3,data.getMovie().getIdMovie());
            ps.setInt(4, data.getUser().getIdUser());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "DELETE FROM watchlist WHERE idwatchlist = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(kalimat_sql);
                ps.setInt(1, data.getIdWatchList());
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
    public int updateData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "UPDATE watchlist SET lastwatch = ?, favorite = ?, movie_idmovie = ?, user_iduser = ? WHERE iduser = ?";
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(kalimat_sql);
                ps.setInt(1, data.getLastWatch());
                ps.setInt(2,data.getFavorite());
                ps.setInt(3,data.getMovie().getIdMovie());
                ps.setInt(4, data.getUser().getIdUser());
                ps.setInt(5, data.getIdWatchList());
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

    public ObservableList<WatchList> getFilteredData(int id) {
        ObservableList<WatchList> wfList;
        wfList = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "SELECT W.idwatchlist, w.lastwatch, w.favorite, w.movie_idmovie AS movie_id, w.user_iduser AS user_id, m.title, m.durasi, u.username, CONCAT(lastwatch, '/', durasi) AS durasi_watch FROM user u JOIN watchlist w ON u.iduser = w.user_iduser JOIN movie m ON w.movie_idmovie = m.idmovie WHERE u.iduser = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();
            while(result.next()) {
                User u = new User();
                u.setIdUser(result.getInt("user_id"));
                u.setUserPassword(result.getString("username"));

                Movie m = new Movie();
                m.setIdMovie(result.getInt("movie_id"));
                m.setTitle(result.getString("title"));
                m.setDurasi(result.getInt("durasi"));

                WatchList w = new WatchList();
                w.setIdWatchList(result.getInt("idwatchlist"));
                w.setLastWatch(result.getInt("lastwatch"));
                w.setFavorite(result.getBoolean("favorite"));
                w.setMovie(m);
                w.setUser(u);
                wfList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wfList;
    }
}
