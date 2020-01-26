package com.example.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Clothes extends AppCompatActivity implements View.OnClickListener{

    AlertDialog.Builder builder;
    Button donebutton;
    int newitemamount;
    ArrayList<Integer> totalamount=new ArrayList<>();
    ArrayList<Integer>  imageids=new ArrayList<>();
    ImageButton phone1,phone2,phone3,phone4,phone5,phone6;
    int x;
    long count;
    boolean checktimer=true;
    int offeramount;
    String offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i= getIntent();
        Bundle bundle= i.getExtras();
        count=bundle.getLong("Counter");
        setContentView(R.layout.activity_clothes);
        builder=new AlertDialog.Builder(this);
        phone1=findViewById(R.id.blueshirt);
        phone2=findViewById(R.id.blackshirt);
        phone3=findViewById(R.id.greyshirt);
        phone4=findViewById(R.id.pinkshirt);
        phone5=findViewById(R.id.yellowshirt);
        phone6=findViewById(R.id.maroonshirt);
        phone1.setOnClickListener(this);
        phone2.setOnClickListener(this);
        phone3.setOnClickListener(this);
        phone4.setOnClickListener(this);
        phone5.setOnClickListener(this);
        phone6.setOnClickListener(this);
        donebutton=findViewById(R.id.done);
        donebutton.setOnClickListener(this);
        final TextView counttime=findViewById(R.id.counttime);
        new CountDownTimer(count,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(checktimer) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = new Date(millisUntilFinished);
                    counttime.setText(dateFormat.format(date));
                    count = millisUntilFinished;
                }
                else
                {
                    cancel();
                }
            }
            @Override
            public void onFinish() {
                counttime.setText("OFFER ENDED");
            }
        }.start();

       // https://www.youtube.com/watch?v=fn5OlqQuOCk
       // https://www.youtube.com/watch?v=eX-TdY6bLdg

    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.done) {
            Intent i2 = new Intent();
            Bundle bundle2 = new Bundle();
            bundle2.putIntegerArrayList("Cost", totalamount);
            bundle2.putIntegerArrayList("Imageselected", imageids);
            bundle2.putLong("Counter",count);
            checktimer=false;
            i2.putExtras(bundle2);
            setResult(RESULT_OK, i2);
            finish();
        } else {
            switch (v.getId()) {
                case R.id.blueshirt:
                    x=R.drawable.blueshirt;
                    newitemamount = 1000;
                    break;
                case R.id.blackshirt:
                    x=R.drawable.blackshirt;
                    newitemamount = 900;
                    break;
                case R.id.greyshirt:
                    x=R.drawable.greyshirt;
                    newitemamount = 800;
                    break;
                case R.id.pinkshirt:
                    x=R.drawable.pinkshirt;
                    newitemamount = 700;
                    break;
                case R.id.yellowshirt:
                    x=R.drawable.yellowshirt;
                    newitemamount = 600;
                    break;
                case R.id.maroonshirt:
                    x=R.drawable.maroonshirt;
                    newitemamount = 500;
                    break;
            }
            if(count!=0) {
                System.out.println(newitemamount+"ffffffffff");
                 offeramount =(int)(newitemamount*(0.95));
                offer = "OFFER AMOUNT=Rs." + offeramount;
            }
            else{
                offer="OFFER ENDED";
            }
            builder.setMessage("Amount item is=Rs." + newitemamount  + "\n"+offer+"\n"+
                    "Do you want to add?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(count!=0) {
                                totalamount.add(offeramount);
                            }
                            else
                                totalamount.add(newitemamount);
                            imageids.add(x);
                            Toast.makeText(getApplicationContext(), "ITEM ADDED", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("COST DETAILS");
            alert.show();
        }
    }

}
