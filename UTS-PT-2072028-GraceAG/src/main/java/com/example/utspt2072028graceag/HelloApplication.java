// UTS Pemrograman Terapan 1 Agustus 2022
// Grace Angelina Gunawan - 2072028

package com.example.utspt2072028graceag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSFirstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1086, 485);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}