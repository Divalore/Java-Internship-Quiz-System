package com.example.quizsystem.controller;

import com.example.quizsystem.model.Result;
import com.example.quizsystem.utils.FileHandler;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ViewResultsController {
    @FXML private TableView<Result> resultsTable;
    @FXML private TableColumn<Result, String> colEmail;
    @FXML private TableColumn<Result, String> colScore;
    @FXML private TableColumn<Result, String> colTotal;
    @FXML
    public void initialize(){
        colEmail.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().studentEmail));
        colScore.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().score)));
        colTotal.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().totalQuestions)));

        List<Result> loadedResults = FileHandler.loadResults();
        if(loadedResults!=null){
            ObservableList<Result> resultList= FXCollections.observableArrayList(loadedResults);
            resultsTable.setItems(resultList);
        }
    }

}
