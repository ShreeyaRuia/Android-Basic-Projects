package com.example.mathgame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {
    ImageView left,right;
     TextView t,equation,scoreview,name;
     EditText ans;
     int a,b,x;
     int score=0,sum;
     String list[]={"+","-","*"};
     Button check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.textanimation);
        left.setAnimation(animation);
        right.setAnimation(animation);
        name=findViewById(R.id.name);
        name.setAnimation(animation);
        ans = findViewById(R.id.answer);
        equation=findViewById(R.id.equation);
        scoreview=findViewById(R.id.score);
        t = findViewById(R.id.timer1);
        check=findViewById(R.id.button);
        Timer newtimer = new Timer(45000, Level1.this, this, R.id.timer1, Level2.class);
        newtimer.execute();
        getnext();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    x = Integer.parseInt("" + ans.getText());
                    if (x == sum) {
                        score = score + 10;
                        scoreview.setText("SCORE:"+score);
                        ans.setTextColor(Color.parseColor("#4BE3AC"));
                    }
                    else{
                        ans.setTextColor(Color.RED);
                        //left.setColorFilter(Color.RED);
                        //right.setColorFilter(Color.RED);
                    }
                    getnext();
                }
                catch (Exception e){
                }

            }
        });

    }

    public void getnext(){
        if (t.getText() != "Timer Ended") {
            a = (int) (Math.random() * (11));
            b = (int) (Math.random() * (11));
            int i = (int) (Math.random() * (3));
            if (i == 0) {
                equation.setText(""+a+"+"+ b);
                sum = a+b;
            }
            if (i == 1) {
                equation.setText(""+ a +"-"+ b);
                sum = a-b;

            }
            if (i == 2) {
                equation.setText(""+a +"*"+ b);
                sum = a*b;
            }
            //left.setColorFilter(Color.parseColor("#4BE3AC"));
            //right.setColorFilter(Color.parseColor("#4BE3AC"));
        }

    }

    public int getScore(){
        return score;
    }

}
