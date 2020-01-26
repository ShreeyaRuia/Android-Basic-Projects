package com.example.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    Context context;
    private final int[] values;
    private final int[] images;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context,int[] values, int[] images) {
        this.values = values;
        this.images = images;
        this.context=context;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       if(convertView==null){
           view=new View(context);
           view=layoutInflater.inflate(R.layout.single_item,null);
           ImageView imageView=(ImageView)view.findViewById(R.id.imageview);
           TextView textview=(TextView)view.findViewById(R.id.textview);
            imageView.setImageResource(images[position]);
            textview.setText("COST:Rs."+values[position]);
       }
       return view;
    }
}
