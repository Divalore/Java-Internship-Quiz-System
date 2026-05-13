package com.example.quizsystem.controller;


import com.example.quizsystem.model.Question;
import com.example.quizsystem.utils.FileHandler;
import com.example.quizsystem.model.Result;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.tree.ExpandVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TakeExamController {
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton opt1;
    @FXML
    private RadioButton opt2;
    @FXML
    private RadioButton opt3;
    @FXML
    private RadioButton opt4;

    private final ToggleGroup group = new ToggleGroup();
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    @FXML
    public void initialize() {
        System.out.println("INITIALISE RUNNING...");

        opt1.setToggleGroup(group);
        opt2.setToggleGroup(group);
        opt3.setToggleGroup(group);
        opt4.setToggleGroup(group);

        questions = FileHandler.loadQuestion();
        if (questions == null) {
            questions = new ArrayList<>();
        }

        System.out.println("Loaded questions: " + questions.size());

        if (questions.isEmpty()) {
            questionLabel.setText("No Questions available.");
            return;
        }
        System.out.println("Questions loaded: " + questions);
        loadQuestion();

    }

    //    public static List<Question>loadQuestion(){
//        try {
//            File file = new File("questions.json");
//            System.out.println("Path: "+file.getAbsolutePath());
//            if (!file.exists()) {
//                System.out.println("FILE NOT FOUND!");
//                return new ArrayList<>();
//            }
//            Gson gson= new Gson();
//            Reader reader= new FileReader(file);
//            List<Question>list=gson.fromJson(reader,
//                    new TypeToken<List<Question>>(){}.getType());
//            reader.close();
//
//            if(list==null){
//                return  new ArrayList<>();
//            }
//            return list;
//        }catch (Exception e){
//        e.printStackTrace();
//        return new ArrayList<>();}
//    }
    private void loadQuestion() {
        if (questions == null || questions.isEmpty()) {
            questionLabel.setText("No Questions available.");
            return;
        }
        if (currentIndex >= questions.size()) {
            questionLabel.setText("End of Questions");
            return;
        }

        Question q = questions.get(currentIndex);
        questionLabel.setText(q.question);
        opt1.setText(q.option1);
        opt2.setText(q.option2);
        opt3.setText(q.option3);
        opt4.setText(q.option4);
    }

    @FXML
    private void handleNext() {
        if (currentIndex >= questions.size()) return;
        RadioButton selected = (RadioButton) group.getSelectedToggle();

        if (selected != null) {
            String answer = selected.getText();
            if (answer.equals(questions.get(currentIndex).correctAnswer)) {
                score++;
            }
        }
        currentIndex++;
        group.selectToggle(null);

        if (currentIndex < questions.size()) {
            loadQuestion();
        } else {
            questionLabel.setText("End of Exam! Please Click Submit.");
            opt1.setVisible(false);
            opt2.setVisible(false);
            opt3.setVisible(false);
            opt4.setVisible(false);
        }
    }

    @FXML
    private void handleSubmit() {
        if(currentIndex<questions.size()-1) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setHeaderText("Incomplete Exam!");
            confirm.setContentText("You haven;t finished all the questions!. Do you really want to submit early?");
            Optional<ButtonType> response = confirm.showAndWait();
            if (response.isEmpty() || response.get() != ButtonType.OK) {
                return;
            }
        }
            Result finalResult= new Result(LoginController.loggedInUser,score, questions.size());
            FileHandler.saveResult(finalResult);
            Alert finalScore= new Alert(Alert.AlertType.INFORMATION);
            finalScore.setHeaderText("Exam Finished!");
            finalScore.setContentText("Your Final Score is: "+score+"/"+questions.size());
            finalScore.showAndWait();

            try{
                Node source=(Node) questionLabel;
                Stage stage= (Stage) source.getScene().getWindow();
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/example/quizsystem/view/student_dashboard.fxml"));
                stage.setScene(new Scene(loader.load(),800,600));
            }
            catch (Exception e){
                e.printStackTrace();

        }
        questionLabel.setText("Exam Submitted! Your Score: "+ score+"/"+ questions.size());}
}
