package com.example.quizsystem.controller;

import com.example.quizsystem.model.Question;
import com.example.quizsystem.utils.FileHandler;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ManageQuestionsController {
    @FXML private TableView<Question> questionsTable;
    @FXML private TableColumn<Question, String> colExam;
    @FXML private TableColumn<Question, String> colQuestion;
    @FXML private TableColumn<Question,String> colCorrect;
    private ObservableList<Question> questionList;

    @FXML
    public void initialize(){
        colExam.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().examTitle));
        colQuestion.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().question));
        colCorrect.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().correctAnswer));

        List<Question> loadedQuestions= FileHandler.loadQuestion();
        if(loadedQuestions !=null){
            questionList= FXCollections.observableArrayList(loadedQuestions);
            questionsTable.setItems(questionList);
        }
    }
    @FXML
    private void deleteQuestion(){
        Question selected= questionsTable.getSelectionModel().getSelectedItem();

        if(selected ==null){
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please click on a question in the table first!");
            alert.showAndWait();
            return;
        }
        questionList.remove(selected);
        FileHandler.saveAllQuestions(new ArrayList<>(questionList));
        Alert success= new Alert(Alert.AlertType.INFORMATION);
        success.setContentText("Question deleted successfully!");
        success.showAndWait();
    }
    @FXML
    private void editQuestion(){
        Question selected=questionsTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please Select a question to edit!");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Edit Question");
        dialog.setHeaderText("Update the Question details:");

        TextField titleField= new TextField(selected.examTitle);
        TextField qField= new TextField(selected.question);
        TextField opt1Field=new TextField(selected.option1);
        TextField opt2Field=new TextField(selected.option2);
        TextField opt3Field=new TextField(selected.option3);
        TextField opt4Field=new TextField(selected.option4);
        TextField correctField=new TextField(selected.correctAnswer);

        VBox vbox=new VBox(5);
        vbox.getChildren().addAll(
                new Label("Exam Ttile:"), titleField,
                new Label("Question:"),qField,
                new Label("Option A:"),opt1Field,
                new Label("Option B:"),opt2Field,
                new Label("Option C:"),opt3Field,
                new Label("Option D:"),opt4Field,
                new Label("Correct Answer:"),correctField
        );

        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(response->{
            if(response==ButtonType.OK){
                selected.examTitle=titleField.getText();
                selected.question=qField.getText();
                selected.option1=opt1Field.getText();
                selected.option2=opt2Field.getText();
                selected.option3=opt3Field.getText();
                selected.option4=opt4Field.getText();
                selected.correctAnswer=correctField.getText();

                questionsTable.refresh();
                FileHandler.saveAllQuestions(new ArrayList<>(questionList));
                Alert success= new Alert(Alert.AlertType.INFORMATION);
                success.setContentText("Question updated successfully!");
                success.showAndWait();
            }
        });

    }
}