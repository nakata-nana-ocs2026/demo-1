// package com.example.demo.service;

// import com.example.demo.model.Question;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// @Service
// public class QuestionService {

//     private final Random random = new Random();

//     private final List<Question> questions = new ArrayList<>();

//     public QuestionService() {

//         questions.add(new Question("北海道",4,"晴れ型",9.5,30,180,"晴れの日が多い"));
//         questions.add(new Question("北海道",3,"変動型",2.1,60,120,"寒暖差が大きい"));
//         questions.add(new Question("北海道",10,"雨型",11.4,140,70,"雨の日が続く"));

//         questions.add(new Question("東北",3,"変動型",7.2,70,110,"寒暖差がある"));
//         questions.add(new Question("東北",4,"晴れ型",13.0,40,170,"晴天が多い"));
//         questions.add(new Question("東北",9,"雨型",20.1,150,60,"雨が多い"));

//         questions.add(new Question("関東",3,"変動型",13.1,85,120,"気温変化が激しい"));
//         questions.add(new Question("関東",4,"晴れ型",18.5,45,190,"春らしい晴天"));
//         questions.add(new Question("関東",9,"荒天型",25.0,230,35,"強風あり"));
//         questions.add(new Question("関東",10,"雨型",18.2,140,65,"秋雨前線の影響"));

//         questions.add(new Question("中部",3,"変動型",11.5,80,100,"朝晩が冷える"));
//         questions.add(new Question("中部",4,"晴れ型",17.0,50,185,"晴れが続く"));
//         questions.add(new Question("中部",9,"荒天型",24.2,220,42,"大雨注意"));
//         questions.add(new Question("中部",10,"雨型",16.8,135,75,"曇りが多い"));

//         questions.add(new Question("関西",3,"変動型",12.8,75,115,"春の嵐あり"));
//         questions.add(new Question("関西",4,"晴れ型",19.1,35,200,"暖かく晴天"));
//         questions.add(new Question("関西",9,"荒天型",26.0,250,28,"台風接近"));
//         questions.add(new Question("関西",10,"雨型",19.0,145,70,"長雨傾向"));

//         questions.add(new Question("九州",3,"変動型",14.0,90,95,"気温差が大きい"));
//         questions.add(new Question("九州",9,"荒天型",27.1,300,20,"非常に強い雨"));
//     }

//     public Question getRandomQuestion() {
//         return questions.get(random.nextInt(questions.size()));
//     }
// }