package com.example.utspt2072028graceag.dao;

import com.example.utspt2072028graceag.model.User;
import com.example.utspt2072028graceag.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements DaoInterface<User> {

    @Override
    public ObservableList<User> getData() {
        ObservableList<User> uList;
        uList = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kalimat_sql = "select * from user";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kalimat_sql);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                User u = new User();
                u.setIdUser(result.getInt("iduser"));
                u.setUserName(result.getString("username"));
                u.setUserPassword(result.getString("userpassword"));
                uList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uList;
    }

    @Override
    public int addData(User data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "INSERT INTO user(username, userpassword) VALUES(?, ?)";
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(kalimat_sql);
                ps.setString(1, data.getUserName());
                ps.setString(2, data.getUserPassword());
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
    public int delData(User data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "DELETE FROM user WHERE iduser = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(kalimat_sql);
                ps.setInt(1, data.getIdUser());
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
    public int updateData(User data) {
        Connection conn = MyConnection.getConnection();
        int hasil = 0;
        try {
            conn.setAutoCommit(false);
            String kalimat_sql = "UPDATE user SET username = ?, userpassword = ?WHERE iduser = ?";
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(kalimat_sql);
                ps.setString(1, data.getUserName());
                ps.setString(2, data.getUserPassword());
                ps.setInt(3, data.getIdUser());
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
}
