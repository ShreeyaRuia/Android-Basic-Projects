package com.example.cricket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Player extends AppCompatActivity {
    int icount=0;
    ArrayList<String> selected = new ArrayList<>();
    ArrayList<Integer> imageselected = new ArrayList<>();
    ListView Indialistview;
    String [] Indialist={"Virat Kohli","Mahendra Singh Dhoni","Hardik Pandya","Rohit Sharma",
            "Rishab Pant","Dinesh Karthik","Shikhar Dhawan", "Jasprit Bumrah","Yuzvendra Chahal",
            "Mohd Shami","Kuldeep Yadav"};
    Integer imageid[]={R.drawable.virat,R.drawable.dhoni,R.drawable.hardik,R.drawable.rohit,R.drawable.rishab,
            R.drawable.dinesh,R.drawable.shikhar,R.drawable.bumrah,R.drawable.chahal,
            R.drawable.shami,R.drawable.kuldeep};
    //private static final String Tag=Player.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list ,Indialist);
        final MyListAdapter adapter=new MyListAdapter(this,Indialist,imageid);
        Indialistview =(ListView)findViewById(R.id.myIndialist);
        Indialistview.setAdapter(adapter);
        Intent i= getIntent();
        final Bundle bundle= i.getExtras();
        icount= bundle.getInt("Count");
        //System.out.println("&&&&&&&&&&&&"+icount);
        Button bdone= (Button)findViewById(R.id.button1);
        bdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent();
                Bundle bundle2= new Bundle();
                bundle2.putInt("Count2",icount);
                bundle2.putStringArrayList("Selected",selected);
                bundle2.putIntegerArrayList("Imageselected",imageselected);
                i2.putExtras(bundle2);
                setResult(RESULT_OK,i2);
                finish();
                //startActivity(i2);
            }
        });
        Indialistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(icount<11)
                {
                String value=adapter.getItem(position);
                if(selected.contains(value))
                {
                    Toast.makeText(Player.this,"Player already selected" ,Toast.LENGTH_SHORT).show();
                }
                //int imagevalue=(int)(adapter.getItem(position));
                 else {   //System.out.println("#########"+position);
                    selected.add(value);
                    imageselected.add(imageid[position]);
                    icount++;
                }
            }
                else{
                    Toast.makeText(Player.this,"Maximum reached press done" ,Toast.LENGTH_SHORT).show();
                }
            }

        });



    }
}
