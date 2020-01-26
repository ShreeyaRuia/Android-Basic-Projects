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

public class Windies extends AppCompatActivity {
    int icount;
    ArrayList<String> selected = new ArrayList<>();
    ArrayList<Integer> imageselected = new ArrayList<>();
    String [] Windieslist={"Evin Lewis","Sunil Narine","Nicholas Pooran","Shimron Hetmyer",
            "R. Campbell","Kieron Pollard","Andre Russel","Carlos Braithwate","Keemo Paul",
            "Kemar Roach","Oshane Thomas"};
    Integer[] imageid={R.drawable.evin,R.drawable.sunil,R.drawable.nicolas,R.drawable.shimron,
            R.drawable.campbell,R.drawable.kieron,R.drawable.andre,R.drawable.carlos,
            R.drawable.keemo,R.drawable.kemar,R.drawable.oshane};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windies);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list ,Windieslist);
        final MyListAdapter adapter=new MyListAdapter(this,Windieslist,imageid);
        final ListView Windieslistview =(ListView)findViewById(R.id.myWindieslist);
        Windieslistview.setAdapter(adapter);
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
        Windieslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(icount<11)
                {
                    String value=adapter.getItem(position);
                    if(selected.contains(value))
                    {
                        Toast.makeText(Windies.this,"Player already selected" ,Toast.LENGTH_SHORT).show();
                    }
                    //int imagevalue=(int)(adapter.getItem(position));
                    else {   //System.out.println("#########"+position);
                        selected.add(value);
                        imageselected.add(imageid[position]);
                        icount++;
                    }
                }
                else{
                    //Windieslistview.setEnabled(false);
                    Toast.makeText(Windies.this,"Maximum reached press done" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
