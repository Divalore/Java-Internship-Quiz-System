package com.example.quizsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class CreateExamController {
    @FXML
    private TextField examTitleField;
    @FXML
    private TextField questionField;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private TextField option3;
    @FXML
    private TextField option4;
    @FXML
    private  ComboBox<String> correctAnswerDropdown;

    @FXML
    public void initialize(){
        correctAnswerDropdown.getItems().addAll("Option A", "Option B","Option C","Option D");
    }

    @FXML
    private void handleSave() {
        String examTitle = examTitleField.getText();
        String question = questionField.getText();

        String opt1 = option1.getText();
        String opt2 = option2.getText();
        String opt3 = option3.getText();
        String opt4 = option4.getText();
        String selectedDropdown=correctAnswerDropdown.getValue();
        String correct="";
        if(selectedDropdown!=null){
            if(selectedDropdown.equals("Option A")) correct=opt1;
            else if(selectedDropdown.equals("Option B")) correct= opt2;
            else if(selectedDropdown.equals("Option C")) correct=opt3;
            else if(selectedDropdown.equals("Option D")) correct=opt4;
        }

        if(examTitle.trim().isEmpty()|| question.trim().isEmpty()||
        opt1.trim().isEmpty()||opt2.trim().isEmpty()||
        opt3.trim().isEmpty()||opt4.trim().isEmpty() || correct.isEmpty()){
            javafx.scene.control.Alert alert= new javafx.scene.control.Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled out before saving the question.");
            alert.showAndWait();
            return;
        }

        com.example.quizsystem.model.Question q = new com.example.quizsystem.model.Question(examTitle, question, opt1, opt2, opt3, opt4, correct);

        com.example.quizsystem.utils.FileHandler.saveQuestion(q);

        examTitleField.clear();
        questionField.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
        correctAnswerDropdown.getSelectionModel().clearSelection();
//        System.out.println("Saved to file successfully!");//        System.out.println("Exam: " + examTitle);
//        System.out.println("Q:"+ question);
//        System.out.println(opt1 +", "+ opt2 +", "+ opt3 +", " + opt4);
//        System.out.println("Correct: "+correct);
//        System.out.println("Saved Successfully!");
    }
}
