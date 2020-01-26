package com.example.cricket;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static  int count;
    ArrayList<String> playernames = new ArrayList<>();
    ArrayList<Integer> imageselected=new ArrayList<>();
    ListView mainlistview;
    Integer imageid[]={R.drawable.india,R.drawable.windies,R.drawable.england};
    String country[]={"India","West Indies","England"};
    Button donebutton,clearbutton;
    AlertDialog.Builder builder;
    String CHANNEL_ID="newchannel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=0;
        final MyListAdapter adapter=new MyListAdapter(this,country,imageid);
        //ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_list_item_1,playernames);
        donebutton=(Button)findViewById(R.id.button2);
        builder=new AlertDialog.Builder(this);
        clearbutton=(Button)findViewById(R.id.button3);
        //ArrayAdapter adapter=new ArrayAdapter(this,R.layout.list ,mainlist);
        mainlistview =(ListView)findViewById(R.id.mylist);
        mainlistview.setAdapter(adapter);
        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String team = ((TextView) view).getText().toString();
                String team=adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("Count", count);
                if (count < 11) {
                    if (team.equals("India")) {
                        Intent i = new Intent(getApplicationContext(), Player.class);
                        i.putExtras(bundle);
                        startActivityForResult(i, 99);
                    } else if (team.equals("West Indies")) {
                        Intent i = new Intent(getApplicationContext(), Windies.class);
                        i.putExtras(bundle);
                        startActivityForResult(i, 99);
                    } else if (team.equals("England")) {
                        Intent i = new Intent(getApplicationContext(), England.class);
                        i.putExtras(bundle);
                        startActivityForResult(i, 99);
                    }
                }
                else
                {
                    //createnotification();
                    Toast.makeText(MainActivity.this,"Maximum reached press done" ,Toast.LENGTH_SHORT).show();
                        //Collections.copy(mainlist,playernames);

                }
            }
        });

        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<11)
                {
                    builder.setMessage("Total 11 players required: " + (11-count) + " left").setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Alert!");
                    alert.show();
            }
                else{
                    createnotification();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("List",playernames);
                    bundle.putIntegerArrayList("Imageselected",imageselected);
                    //mainlistview.setEnabled(false);
                    Intent i = new Intent(getApplicationContext(),Playerlist.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
        }
        });

        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playernames.clear();
                count=0;
                imageselected.clear();
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 99 && data != null) {
                Bundle bundle2 = new Bundle();
                bundle2 = data.getExtras();
                //ArrayList<String> temporary = new ArrayList<>();
                count = bundle2.getInt("Count2");
                playernames.addAll(bundle2.getStringArrayList("Selected"));
                imageselected.addAll(bundle2.getIntegerArrayList("Imageselected"));
                //System.out.println(playernames);
                if(count==11) {
                    createnotification();
                    /*Bundle bundle = new Bundle();
                    bundle.putStringArrayList("List",playernames);
                    bundle.putIntegerArrayList("Imageselected",imageselected);
                    mainlistview.setEnabled(false);
                    Intent i = new Intent(getApplicationContext(), Playerlist.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    }*/
                }
                }
            }
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
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("List",playernames);
                bundle.putIntegerArrayList("Imageselected",imageselected);
                Intent notificationIntent = new Intent(this,Playerlist.class);
                notificationIntent.putExtras(bundle);
                PendingIntent contentIntent1 = PendingIntent.getActivity(this,
                        0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(this,CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_notification)
                                .setContentTitle("CRICKET")
                                .setContentText("Final player list read tap to open");
                builder.setContentIntent(contentIntent1);
                builder.setAutoCancel(true);
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                nm.notify(0,builder.build());
            }
        }
    }




