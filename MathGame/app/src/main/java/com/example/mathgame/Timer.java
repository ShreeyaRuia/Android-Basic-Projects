package com.example.mathgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Timer extends AsyncTask<Void,Void,Void> {
    long start;
    TextView t,x;
    Activity currentactivity;
    Class nextactivity;
    Context c;
    AlertDialog.Builder b;

    public Timer(int start,Activity v,Context c, int id,Class next) {
        currentactivity=v;
        nextactivity=next;
        t=(TextView)v.findViewById(id);
        this.start=start;
        this.c=c;
    }

    @Override
    protected void onPreExecute() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(start);
        t.setText(//dateFormat.format(date));
                "start");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        x=currentactivity.findViewById(R.id.score);
        t.setText("Timer Ended");
        b=new AlertDialog.Builder(c);
        if(nextactivity!=null){
        b.setMessage("TIME OVER GOTO NEXT LEVEL?"+"\nScore:"+x.getText());}
        else{
            b.setMessage("LAST LEVEL GAME COMPLETED"+"\nScore:"+x.getText());
        }
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //try{
                if(nextactivity!=null) {
                    Intent i = new Intent(c, nextactivity);
                    c.startActivity(i);
                }
                else{
                    dialog.dismiss();
                }
                //}
                //catch (Exception e){
                  //  b.setMessage("LAST LEVEL GAME COMPLETED");
                //}
            }
        });
        b.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = b.create();
        alert.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(start);
        t.setText(dateFormat.format(date));
        if(start<=10000){
            t.setTextColor(Color.RED);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while(start!=0){
            countdown();
            publishProgress();
        }
        return null;
    }

    public void countdown(){
        try {
            Thread.sleep(1000);
            start=start-1000;
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
