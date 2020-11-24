package com.example.onlinequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import static java.lang.Thread.sleep;


public class SplashActivity extends AppCompatActivity {
    TextView quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        quiz=(TextView)findViewById(R.id.quiz);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.pacifico);
        quiz.setTypeface(typeface);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity( intent);



            }
        }).start();

    }
}
