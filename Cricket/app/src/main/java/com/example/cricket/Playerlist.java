package com.example.cricket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Playerlist extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);
        Intent i = getIntent();
        Bundle b=i.getExtras();
        ArrayList<String> players= (b.getStringArrayList("List"));
        ArrayList<Integer> images=(b.getIntegerArrayList("Imageselected"));
        String [] playername=new String[players.size()];
        Integer[] imageid=new Integer[images.size()];
        for(int j=0;j<players.size();j++)
        {
            playername[j]=players.get(j);
            imageid[j]=images.get(j);
        }
        final ProgressDialog prdialog = new ProgressDialog(Playerlist.this);
        prdialog.setMax(100);
        prdialog.setMessage("Generating Final Player List");
        prdialog.setTitle("Please wait!");
        prdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prdialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (prdialog.getProgress() <= prdialog.getMax()) {
                        Thread.sleep(500);
                        prdialog.incrementProgressBy(5);
                        if (prdialog.getProgress() == prdialog.getMax()) {
                            prdialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        MyListAdapter adapter=new MyListAdapter(this,playername,imageid);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list,players);
        ListView playerslist=(ListView)findViewById(R.id.myfinallist);
        playerslist.setAdapter(adapter);
     }
}
