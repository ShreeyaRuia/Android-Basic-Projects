package com.example.shopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    Button button;
    int[] number;
    AlertDialog.Builder builder;
    static TextView finalamount;
    //GridView gridview;
    DatePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        builder=new AlertDialog.Builder(this);
        //gridview=(GridView)findViewById(R.id.gridview);
        Intent i = getIntent();
        Bundle b=i.getExtras();
        ArrayList<Integer> total=(b.getIntegerArrayList("Cost"));
        ArrayList<Integer> images=(b.getIntegerArrayList("Imageselected"));
        int cost[]=new int[images.size()];
        String [] totalamount=new String[images.size()];
        int[] imageid=new int[images.size()];
        picker=(DatePicker)findViewById(R.id.picker);
        number=new int[total.size()];
        int amount=0;
        for(int j=0;j<total.size();j++)
        {
            cost[j]=total.get(j);
            totalamount[j]="Cost:"+total.get(j);
            imageid[j]=images.get(j);
            number[j]=1;
            amount=amount+cost[j];

        }
        //GridAdapter gridAdapter=new GridAdapter(this,totalamount,imageid);
        //gridview.setAdapter(gridAdapter);
        final MyListAdapter adapter = new MyListAdapter(this, totalamount, imageid,number,cost);
        final ListView Cartlistview = (ListView) findViewById(R.id.mylist);
        Cartlistview.setAdapter(adapter);
        button=findViewById(R.id.donecart);
        finalamount=findViewById(R.id.finalamount);
        finalamount.setText("Final Amount: Rs."+amount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=getdate();
                builder.setMessage("THANK YOU FOR ORDERING!"+ "\n"+"Your order will be delivered by:"+x)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("CART AMOUNT");
                alert.show();
            }


                //number=adapter.getnumber();
                //for(int i=0;i<number.length;i++)
                  //  System.out.println("ddddddddddddd"+i +"ggg" +number[i]);



        });

    }

    public String getdate(){
        StringBuilder builder=new StringBuilder();
        builder.append((picker.getDayOfMonth()+3)+"/");
        builder.append((picker.getMonth()+1)+"/");
        builder.append(picker.getYear());
        return builder.toString();

    }


    public static void setcost(int amount){
     finalamount.setText("Final Amount: Rs."+amount);
    }
}
