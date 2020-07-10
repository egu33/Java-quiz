package com.example.quizapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuizFileDao {
    public static final String FILE_PATH = "quizzes.tex";
    public void write(List<Quiz> quizzes) throws IOException {
        List<String> lines = new ArrayList<>();
        for(Quiz quiz: quizzes){
            lines.add(quiz.toString());
        }
        Path path = Paths.get(FILE_PATH);
        Files.write(path, lines);
    }
    public List<Quiz> read() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path); //例外が発生すると　throw 例外を投げる。

        List<Quiz> quizzes = new ArrayList<>();
        for (String line: lines){
            quizzes.add(Quiz.fromString(line));
        }
        return quizzes;
    }
}
