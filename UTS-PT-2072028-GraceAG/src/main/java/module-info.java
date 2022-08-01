module com.example.utspt2072028graceag {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;

    opens com.example.utspt2072028graceag to javafx.fxml;
    exports com.example.utspt2072028graceag;
    exports com.example.utspt2072028graceag.util;
    exports com.example.utspt2072028graceag.dao;
    exports com.example.utspt2072028graceag.model;
}