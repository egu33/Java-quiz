package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
public class QuizApiController {
    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();

    @GetMapping("/quiz")
    public Quiz quiz(){
        int index = new Random().nextInt(quizzes.size());

        return quizzes.get(index);
    }


    @GetMapping("/show")
    public List<Quiz> show(){
        return quizzes;
    }

    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer) {
        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);
    }

    //checkメソット
    @GetMapping("/check")
    public String check(String question, boolean answer){
        //回答が正しいかチェックする
        //指定された質問を登録済みのクイズから検索する。
        //クイズが見つかったら、クイズの正解と回答を比較して、一致している場合正解
        //一致していなければ不正解で返す
        //クイズが見つからなかった場合はクイズがありませんとかえす
        for(Quiz quiz:quizzes){
            if(quiz.getQuestion().equals(question)){
                if(quiz.isAnswer() == answer){
                    return "正解";
                }
                else{
                    return "不正解";
                }
            }

        }
        return "もんだいがありません";
    }
    @PostMapping("/save")
    public String save(){
        try {
            quizFileDao.write(quizzes);
            return "ファイルに保存しました";
        } catch (IOException e) {
            e.printStackTrace();
            return "ファイルの保存に失敗しました";
        }

    }
    @GetMapping("/load")
    public String load(){
        try {
            quizzes = quizFileDao.read();
            return "ファイルを読み込みました";
        } catch (IOException e) {
            e.printStackTrace();
            return "読み込みに失敗しました";

        }
    }
}
