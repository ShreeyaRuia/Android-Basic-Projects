package com.example.shopping;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Mainportal extends AppCompatActivity {
    int balanceamount=800;
    AlertDialog.Builder builder;
    Button shop,checkamount;
    ArrayList<Integer> totalamount=new ArrayList<>();
    ArrayList<Integer>  imageids=new ArrayList<>();
    ViewFlipper v_flipper;
    String CHANNEL_ID="newchannel";
    long count=900000;
    boolean checkactivty=true;
    boolean checkbacktointent=true;
    TextView counttime;
    Dialog cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainportal);
        cd= new Dialog(Mainportal.this);
        cd.setContentView(R.layout.mainpagecustomdialog);
        cd.show();
        v_flipper=findViewById(R.id.v_flipper);
        int images []={R.drawable.offer1,R.drawable.offer2,R.drawable.offer3,R.drawable.offer4,R.drawable.offer5};
        for (int i=0;i<images.length;i++){
            flipper(images[i]);
        }
        builder=new AlertDialog.Builder(this);
        shop=findViewById(R.id.shopby);
        checkamount=findViewById(R.id.checkamount);
        registerForContextMenu(shop);
        counttime=findViewById(R.id.counttime);
        new CountDownTimer(count,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                if(checkactivty) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = new Date(millisUntilFinished);
                    counttime.setText("OFFER ENDS IN:"+dateFormat.format(date));
                    count = millisUntilFinished;
                }
                else {
                    cancel();
                }
            }
            @Override
            public void onFinish() {
                counttime.setText("OFFER ENDED");
            }
        }.start();



        checkamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=0;
                int count=0;
                for(int i=0;i<totalamount.size();i++){
                    x=x+totalamount.get(i);
                    count++;
                }
                builder.setMessage("Total amount"+x+ "\n"+"No. of Items:"+count)
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
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu,menu);
        menu.setHeaderTitle("SHOP BY CATEGORY");
        menu.setHeaderIcon(R.drawable.shoppingbasketicon);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99 && data != null){
                Bundle bundle2 = data.getExtras();
                //bundle2 = data.getExtras();
                totalamount.addAll(bundle2.getIntegerArrayList("Cost"));
                imageids.addAll(bundle2.getIntegerArrayList("Imageselected"));
                count=bundle2.getLong("Counter");
                checkbacktointent=true;
                new CountDownTimer(count,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(checkbacktointent) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                            Date date = new Date(millisUntilFinished);
                            counttime.setText("OFFER FINISHES IN:"+dateFormat.format(date));
                            count = millisUntilFinished;
                        }
                        else {
                            cancel();
                        }
                    }
                    @Override
                    public void onFinish() {
                        counttime.setText("OFFER ENDED");
                    }
                }.start();
                createnotification();
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putLong("Counter", count);
        switch (item.getItemId()){
            case R.id.clothes:
                checkactivty=false;
                checkbacktointent=false;
                startActivityForResult(new Intent(getApplicationContext(),Clothes.class).putExtras(bundle),99);
                return true;
            case R.id.mobile:
                checkbacktointent=false;
                checkactivty=false;
                startActivityForResult(new Intent(getApplicationContext(),Phone.class).putExtras(bundle),99);
                return true;
            case R.id.shoes:
                checkbacktointent=false;
                checkactivty=false;
                startActivityForResult(new Intent(getApplicationContext(),Shoes.class).putExtras(bundle),99);
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater x=getMenuInflater();
        x.inflate(R.menu.optionmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.balance:
                builder.setMessage("BALANCE IN EWALLET=Rs."+balanceamount)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("BALANCE:");
                alert.show();
                return true;
            case R.id.cart:
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("Cost",totalamount);
                bundle.putIntegerArrayList("Imageselected",imageids);
                //mainlistview.setEnabled(false);
                Intent i = new Intent(getApplicationContext(),Cart.class);
                i.putExtras(bundle);
                startActivity(i);
                return true;
            case R.id.Loginhelp:
                Toast.makeText(Mainportal.this,"Try Reseting",Toast.LENGTH_LONG).show();
                return true;
            case R.id.rating:
                Customdialogbox customdialogbox=new Customdialogbox();
                customdialogbox.show(getSupportFragmentManager(),"dialog");
                return true;
            case R.id.share:
                Toast.makeText(Mainportal.this,"Shared with all contacts",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void flipper(int images){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(images);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        //v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
    }


    public void createnotification()
    {
        NotificationManager nm=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Example";
            String description = "This is a Demo";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            nm.createNotificationChannel(channel);
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this,CHANNEL_ID)
                            .setSmallIcon(R.drawable.carticon)
                            .setContentTitle("ITEMS ADDED")
                            .setContentText("CHECK YOUR CART");
            builder.setAutoCancel(true);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            nm.notify(0,builder.build());
        }
    }
}
