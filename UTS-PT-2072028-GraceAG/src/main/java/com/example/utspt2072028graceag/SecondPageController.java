package com.example.utspt2072028graceag;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SecondPageController {

    public TextField inputUsername;
    public PasswordField inputPassword;
    public Button btnSubmit;
    String userNameDiinput;
    String userPasswordDiinput;

    public String getUserNameDiinput() {
        return userNameDiinput;
    }

    public void setUserNameDiinput(String userNameDiinput) {
        this.userNameDiinput = userNameDiinput;
    }

    public String getUserPasswordDiinput() {
        return userPasswordDiinput;
    }

    public void setUserPasswordDiinput(String userPasswordDiinput) {
        this.userPasswordDiinput = userPasswordDiinput;
    }

    public void submit(ActionEvent actionEvent) {
        setUserNameDiinput(inputUsername.getText());
        setUserPasswordDiinput(inputPassword.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Data successfully added");
        alert.setTitle("Message");
        alert.showAndWait();
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
