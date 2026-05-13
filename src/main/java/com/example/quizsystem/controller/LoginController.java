package com.example.quizsystem.controller;

import com.example.quizsystem.utils.FileHandler;
import com.example.quizsystem.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class LoginController {
    public static String loggedInUser="Admin";
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        if(user==null||user.trim().isEmpty()||pass==null||pass.trim().isEmpty()){
            javafx.scene.control.Alert alert= new javafx.scene.control.Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter both username and password!");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader;
            Scene scene;
            Stage stage = (Stage) usernameField.getScene().getWindow();
            if (user.equals("admin") && pass.equals("1234")) {
                loader = new FXMLLoader(getClass().
                        getResource("/com/example/quizsystem/view/admin_dashboard.fxml"));
            } else if (user.equals("student") && pass.equals("1234")) {
                loader = new FXMLLoader(getClass().
                        getResource("/com/example/quizsystem/view/student_dashboard.fxml"));
            } else {
                boolean validStudent=false;
                List<Student>students= FileHandler.loadStudents();
                for (Student s : students){
                    if(s.email.equals(user) && s.password.equals(pass)){
                        validStudent= true;
                        break;
                    }
                }
                if (validStudent) {
                    loggedInUser=user;
                    loader = new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/student_dashboard.fxml"));
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid Email or Password!");
                    alert.showAndWait();
                    return;
                }

            }
            scene = new Scene(loader.load(),1000,700);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
