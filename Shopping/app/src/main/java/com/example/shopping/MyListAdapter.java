package com.example.shopping;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.ArrayAdapter;

import static com.example.shopping.Cart.setcost;

public class MyListAdapter extends ArrayAdapter {
    private final Activity context;
    int cost[];
    private final String[] country;
    private final int[] imageid;
    int[] number;
    public MyListAdapter( Activity context, String[] country,int[] imageid,int[] number,int cost[]) {
        super(context, R.layout.listmain,country);
        this.context = context;
        this.country=country;
        this.imageid=imageid;
        this.number=number;
        this.cost=cost;
    }

    public View getView(final int position, View view, final ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listmain, null,true);
        final holder hold=new holder();
        hold.titleText = (TextView) rowView.findViewById(R.id.title);
        hold.quantitytext = (TextView) rowView.findViewById(R.id.quantity);
        hold.plus=(Button)rowView.findViewById(R.id.plus);
        hold.minus=(Button)rowView.findViewById(R.id.minus);
        hold.imageView = (ImageView) rowView.findViewById(R.id.icon);
        rowView.setTag(hold);
        hold.titleText.setText(country[position]);
        hold.quantitytext.setText(""+number[position]);
        hold.imageView.setImageResource(imageid[position]);
        hold.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.parseInt((String) hold.quantitytext.getText());
                x++;
                hold.quantitytext.setText(""+x);
                number[position]=x;
                hold.titleText.setText("COST:"+cost[position]*x);
                int finalcost=0;
                for(int i=0;i<cost.length;i++){
                    finalcost=finalcost+cost[i]*number[i];
                    setcost(finalcost);
                }

                //((ListView) parent).performItemClick(v, position, 0);
            }
        });
        hold.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=Integer.parseInt((String) hold.quantitytext.getText());
                if(x>0) {
                    x--;
                    number[position]=x;
                    hold.quantitytext.setText("" + x);
                    hold.titleText.setText("COST:"+cost[position]*x);
                    int finalcost=0;
                    for(int i=0;i<cost.length;i++){
                        finalcost=finalcost+cost[i]*number[i];
                        setcost(finalcost);
                    }
                }
            }
        });
        return rowView;
    }

    static class holder{
        Button plus;
        Button minus;
        TextView quantitytext;
        TextView titleText;
        ImageView imageView;

    }

    public int[] getnumber(){
        return number;
    }

}
