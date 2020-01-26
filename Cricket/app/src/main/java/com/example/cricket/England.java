package com.example.cricket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class England extends AppCompatActivity {
    int icount;
    ArrayList<String> selected = new ArrayList<>();
    ArrayList<Integer> imageselected = new ArrayList<>();
    Integer imageid[]={R.drawable.johnny,R.drawable.jason,R.drawable.morgan,R.drawable.buttler
    ,R.drawable.ben,R.drawable.chris,R.drawable.jofra,R.drawable.ali,R.drawable.adil
    ,R.drawable.mark,R.drawable.liam};
    String [] Englandlist={"Jonny Bairstow","Jason Roy","Eoin Morgan","Jos Buttler",
            "Ben Stokes","Chris Woakes","Jofra Archer","Moeen Ali","Adil Rashid",
            "Mark Wood","Liam Plunkett"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_england);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list ,Englandlist);
        final MyListAdapter adapter=new MyListAdapter(this,Englandlist,imageid);
        final ListView Englandlistview =(ListView)findViewById(R.id.myEnglandlist);
        Englandlistview.setAdapter(adapter);
        Intent i= getIntent();
        final Bundle bundle= i.getExtras();
        icount= bundle.getInt("Count");
        System.out.println("&&&&&&&&&&&&"+icount);
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
        Englandlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(icount<11)
                {
                    String value=adapter.getItem(position);
                    if(selected.contains(value))
                    {
                        Toast.makeText(England.this,"Player already selected" ,Toast.LENGTH_SHORT).show();
                    }
                    //int imagevalue=(int)(adapter.getItem(position));
                    else {   //System.out.println("#########"+position);
                        selected.add(value);
                        imageselected.add(imageid[position]);
                        icount++;
                    }
                }
                else{
                    //Englandlistview.setEnabled(false);
                    Toast.makeText(England.this,"Maximum reached press done" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
