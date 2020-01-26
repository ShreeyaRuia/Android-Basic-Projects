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

public class Shoes extends AppCompatActivity implements View.OnClickListener{
    AlertDialog.Builder builder;
    Button donebutton;
    int newitemamount;
    ArrayList<Integer> totalamount=new ArrayList<>();
    ArrayList<Integer>  imageids=new ArrayList<>();
    ImageButton shoes1,shoes2,shoes3,shoes4,shoes5,shoes6;
    int x;
    long count;
    boolean checktimer=true;
    int offeramount;
    String offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        Intent i= getIntent();
        Bundle bundle= i.getExtras();
        count=bundle.getLong("Counter");
        builder=new AlertDialog.Builder(this);
        shoes1=findViewById(R.id.shoes1);
        shoes2=findViewById(R.id.shoes2);
        shoes3=findViewById(R.id.shoes3);
        shoes4=findViewById(R.id.shoes4);
        shoes5=findViewById(R.id.shoes5);
        shoes6=findViewById(R.id.shoes6);
        shoes1.setOnClickListener(this);
        shoes2.setOnClickListener(this);
        shoes3.setOnClickListener(this);
        shoes4.setOnClickListener(this);
        shoes5.setOnClickListener(this);
        shoes6.setOnClickListener(this);
        donebutton=findViewById(R.id.done2);
        donebutton.setOnClickListener(this);
        final TextView counttime=findViewById(R.id.counttime1);
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
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.done2) {
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
                case R.id.shoes1:
                    x=R.drawable.shoes1;
                    newitemamount = 1000;
                    break;
                case R.id.shoes2:
                    x=R.drawable.shoes2;
                    newitemamount = 900;
                    break;
                case R.id.shoes3:
                    x=R.drawable.shoes3;
                    newitemamount = 800;
                    break;
                case R.id.shoes4:
                    x=R.drawable.shoes4;
                    newitemamount = 700;
                    break;
                case R.id.shoes5:
                    x=R.drawable.shoes5;
                    newitemamount = 600;
                    break;
                case R.id.shoes6:
                    x=R.drawable.shoes6;
                    newitemamount = 500;
                    break;
            }
            if(count!=0) {
                offeramount =(int)(newitemamount*(0.95));
                offer = "OFFER AMOUNT=Rs." + offeramount;
            }
            else{
                offer="OFFER ENDED";
            }
            builder.setMessage("Amount item is=Rs." + newitemamount + "\n"+offer+"\n"+
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
