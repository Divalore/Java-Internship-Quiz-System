package com.example.quizsystem.controller;

import com.example.quizsystem.utils.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.example.quizsystem.model.Student;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import java.io.File;

public class AdminController {
    @FXML
    private void openCreateExam() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/create_exam.fxml"));
            Scene scene = new Scene(loader.load(),1000,700);
            Stage stage = new Stage();
            stage.setTitle("Create Exam");
            stage.setScene(scene);
            stage.centerOnScreen();
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
    private void registerNewStudent(){
        TextInputDialog emailDialog= new TextInputDialog();
        emailDialog.setTitle("Register Student");
        emailDialog.setHeaderText("Enter the student's Gmail address:");
        emailDialog.showAndWait().ifPresent(email ->{
            TextInputDialog passDialog=new TextInputDialog();
            passDialog.setTitle("Register Student");
            passDialog.setHeaderText("Enter a password for "+ email + ": ");
            passDialog.showAndWait().ifPresent(password->{
                Student newStudent= new Student(email,password);
                FileHandler.saveStudent(newStudent);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Student registered successfully! They can now log in.");
                alert.showAndWait();
            });
        });
    }
    @FXML
    private void openManageQuestions(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/manage_questions.fxml"));
            Scene scene= new Scene(loader.load(),1000,700);
            Stage stage=new Stage();
            stage.setTitle("Manage Questions");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
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
