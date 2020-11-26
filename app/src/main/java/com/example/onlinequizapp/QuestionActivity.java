package com.example.onlinequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question,qcount,timer;
    private Button option1,option2,option3,option4;
    List<QuestionModel>questionModelList;
    private int quesNum;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question=findViewById(R.id.question);
        qcount=findViewById(R.id.question_no);
        timer=findViewById(R.id.timer);

        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        
        getQuestionList();

    }

    private void getQuestionList() {
        questionModelList=new ArrayList<>();
        questionModelList.add(new QuestionModel("Question 1","A","B","C","D",2));
        questionModelList.add(new QuestionModel("Question 2","A","B","C","D",3));
        questionModelList.add(new QuestionModel("Question 3","A","B","C","D",4));
        questionModelList.add(new QuestionModel("Question 4","A","B","C","D",1));
        questionModelList.add(new QuestionModel("Question 5","A","B","C","D",2));

        setQuestion();

    }

    private void setQuestion() {
        timer.setText(valueOf(10));

        question.setText(questionModelList.get(0).getQuestion());
        option1.setText(questionModelList.get(0).optionA);
        option2.setText(questionModelList.get(0).optionB);
        option3.setText(questionModelList.get(0).optionC);
        option4.setText(questionModelList.get(0).optionD);

        qcount.setText(valueOf(1)+"/"+ valueOf(questionModelList.size()));

        startTimer();
        quesNum=0;

    }

    private void startTimer() {
        countDownTimer=new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished < 10000)
                {
                    timer.setText(String.valueOf(millisUntilFinished/1000));
                }

            }

            @Override
            public void onFinish() {
                changequestion();

            }
        };
        countDownTimer.start();
    }

    @Override
    public void onClick(View v) {

        int selectedOption=0;

        switch (v.getId())
        {
            case R.id.option1:
                selectedOption=1;
                break;

            case R.id.option2:
                selectedOption=2;
                break;

            case R.id.option3:
                selectedOption=3;
                break;

            case R.id.option4:
                selectedOption=4;
                break;

            default:
        }

       countDownTimer.cancel();
        checkAnswer(selectedOption,v);
    }

    private void checkAnswer(int selectedOption,View view) {
        if (selectedOption ==questionModelList.get(quesNum).getCorrectAns())
        {
            //right ans
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else
        {
            //wrong
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch(questionModelList.get(quesNum).getCorrectAns())
            {
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

            }
        }

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changequestion();
            }
        },2000);


    }


    private void changequestion() {
        if (quesNum < questionModelList.size() -1)
        {
            quesNum++;
            playAnim(question,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);
            playAnim(option4,0,4);

            qcount.setText((quesNum + 1) + "/" + questionModelList.size());
            timer.setText(valueOf(10));
            startTimer();
        }
        else
        {
            //go to score activity
            Intent intent=new Intent(QuestionActivity.this,ScoreActivity.class);
            startActivity(intent);
            QuestionActivity.this.finish();
        }
    }

    private void playAnim(final View view, final int value, final int viewno) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value==0)
                        {
                            switch (viewno)
                            {
                                case 0:
                                    ((TextView)view).setText(questionModelList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                ((Button)view).setText(questionModelList.get(quesNum).getOptionA());
                                break;

                                case 2:
                                    ((Button)view).setText(questionModelList.get(quesNum).getOptionB());
                                    break;

                                case 3:
                                    ((Button)view).setText(questionModelList.get(quesNum).getOptionC());
                                    break;

                                case 4:
                                    ((Button)view).setText(questionModelList.get(quesNum).getOptionD());
                                    break;


                            }
                            if (viewno != 0)
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e99c03")));
                            playAnim(view,1,viewno);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
}
