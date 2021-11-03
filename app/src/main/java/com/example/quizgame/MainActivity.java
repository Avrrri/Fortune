package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String settings1;
    String settings2;
    String rAnswer;
    int n, n1,n2;
    ImageView wheel;
    TextView question;
    EditText answer;

    private int last;
    private boolean spinning = false;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.qwestion);
        answer = findViewById(R.id.answer);
        wheel = findViewById(R.id.wheelGame);

        //Прием данных о сложности
        Bundle argument = getIntent().getExtras();
        if(argument!=null)
        {
            settings1 = argument.getString("settingsDifficult");
        }
        //Прием данных о скине колеса
        Bundle argument1 = getIntent().getExtras();
        if(argument1!=null)
        {
            settings2 = argument1.getString("settingsWheel");
        }

        switch (settings1){
            case "Легкая":
                QuestionEasy();
                break;

            case "Нормальная":
                QuestionNormal();
                break;

            case "Сложная":
                QuestionHard();
                break;
        }
        //Реализация выбора колеса
        switch (settings2){
            case "Обычный":
                Resources res = getResources();
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.wheel3, null);
                wheel.setImageDrawable(drawable);
                break;

            case "Странный":
                Resources res1 = getResources();
                Drawable drawable1 = ResourcesCompat.getDrawable(res1, R.drawable.wheel2, null);
                wheel.setImageDrawable(drawable1);
                break;

            case "Цветной":
                Resources res2 = getResources();
                Drawable drawable2 = ResourcesCompat.getDrawable(res2, R.drawable.wheel1, null);
                wheel.setImageDrawable(drawable2);
                break;
        }

    }
    //Генерация легких вопросов
    public void QuestionEasy (){
        // генерация вопроса и ответа к нему
        String[] difficult_ease = getResources().getStringArray(R.array.difficult_1);
        Random random = new Random();
        n = random.nextInt(difficult_ease.length - 0)+0;
        question.setText(difficult_ease[n]);
    }
    //Генерация средних вопросов
    public void QuestionNormal (){
        // генерация вопроса и ответа к нему
        String[] difficult_ease = getResources().getStringArray(R.array.difficult_2);
        Random random = new Random();
        n1 = random.nextInt(difficult_ease.length - 0)+0;
        question.setText(difficult_ease[n1]);
    }
    //Генирация Саня как ты до этого додумалась?!
    public void QuestionHard (){
        // генерация вопроса и ответа к нему
        String[] difficult_ease = getResources().getStringArray(R.array.difficult_3);
        Random random = new Random();
        n2 = random.nextInt(difficult_ease.length - 0)+0;
        question.setText(difficult_ease[n2]);
    }

    //Кнопка поворота колеса, принятия иисуса
    public void speenWheel(View view) {
        if (!spinning) {
            Random random = new Random();
            int new_rand = random.nextInt(2160);
            float pointWidth = wheel.getWidth() / 2;
            float pointHeight = wheel.getHeight() / 2;
            Animation rotation = new RotateAnimation(last, new_rand, pointWidth, pointHeight);
            rotation.setDuration(2700);
            rotation.setFillAfter(true);
            rotation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            last = new_rand;
            wheel.startAnimation(rotation);
        }
        switch (settings1) {
            case "Легкая":
                rAnswer =  answer.getText().toString();//считывание текста
                String[] answer_ease = getResources().getStringArray(R.array.answer_1);
                int nAnswer = n;
                if (rAnswer.equals(answer_ease[nAnswer])){
                    Toast.makeText(getApplicationContext(),"Правильно!",Toast.LENGTH_SHORT).show();
                    QuestionEasy();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Не верно!",Toast.LENGTH_SHORT).show();
                }
                break;

            case "Нормальная":
                rAnswer =  answer.getText().toString();
                String[] answer_normal = getResources().getStringArray(R.array.answer_2);
                int nAnswer1 = n1;
                if (rAnswer.equals(answer_normal[nAnswer1])){
                    Toast.makeText(getApplicationContext(),"Правильно!",Toast.LENGTH_SHORT).show();
                    QuestionNormal();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Не верно!",Toast.LENGTH_SHORT).show();
                }
                break;

            case "Сложная":
                rAnswer =  answer.getText().toString();
                String[] answer_hard = getResources().getStringArray(R.array.answer_3);
                int nAnswer2 = n2;
                if (rAnswer.equals(answer_hard[nAnswer2])){
                    Toast.makeText(getApplicationContext(),"Правильно!",Toast.LENGTH_SHORT).show();
                    QuestionHard();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Не верно!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

