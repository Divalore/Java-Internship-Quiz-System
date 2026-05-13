package com.example.quizsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private void openExam() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/take_exam.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Take Exam");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleLogout(ActionEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource
                    ("/com/example/quizsystem/view/login.fxml"));

            Scene scene= new Scene(loader.load(),800,600);
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Exam System Login");
            stage.setScene(scene);
            stage.centerOnScreen();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void openViewResults(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/view_results.fxml"));
            Scene scene= new Scene(loader.load(),800,600);
            Stage stage=new Stage();
            stage.setTitle("Exam Results");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
