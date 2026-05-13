package com.example.quizsystem.utils;

import com.example.quizsystem.model.Question;
import com.example.quizsystem.model.Student;
import com.example.quizsystem.model.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.plaf.FileChooserUI;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH="questions.json";
    private static final String STUDENT_FILE="student.json";
    private static final String RESULT_FILE="results.json";
    public static  void saveQuestion(Question question){
        Gson gson= new Gson();
        List<Question> questions=loadQuestion();
        if(questions==null){
            questions=new ArrayList<>();
        }
        questions.add(question);

        try(Writer writer=new FileWriter(FILE_PATH)){
            gson.toJson(questions,writer);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static List<Question> loadQuestion(){
        Gson gson= new Gson();
        try(Reader reader= new FileReader(FILE_PATH)){
            Type listType= new TypeToken<List<Question>>(){}.getType();
            return gson.fromJson(reader,listType);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public static void saveStudent(Student student){
        Gson gson=new Gson();
        List<Student> students=loadStudents();
        if(students ==null) students=new ArrayList<>();
        students.add(student);
        try(Writer writer=new FileWriter(STUDENT_FILE)){
            gson.toJson(students,writer);
        }catch (Exception e){
            e.printStackTrace();
        }

        }
        public static List<Student> loadStudents(){
        Gson gson =new Gson();
        try(Reader reader = new FileReader(STUDENT_FILE)){
            Type listType=new TypeToken<List<Student>>(){}.getType();
            return gson.fromJson(reader,listType);
        }catch (Exception e){
            return new ArrayList<>();
        }
        }
        public static void saveAllQuestions(List<com.example.quizsystem.model.Question> questions){
        Gson gson=new Gson();
        try(Writer writer= new FileWriter(FILE_PATH)){
            gson.toJson(questions,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        public static void saveResult(Result result){
        Gson gson= new Gson();
        List<Result> results= loadResults();
        if(results==null) results=new ArrayList<>();
        results.add(result);
        try(Writer writer= new FileWriter(RESULT_FILE)){
            gson.toJson(results,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        public static List<Result>loadResults(){
        Gson gson= new Gson();
        try(Reader reader = new FileReader(RESULT_FILE)){
            Type listType= new TypeToken<List<Result>>(){}.getType();
            return gson.fromJson(reader,listType);
        }
        catch (Exception e){
            return new ArrayList<>();
        }
        }

}

