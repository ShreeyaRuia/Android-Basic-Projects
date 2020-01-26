package com.example.filestudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText textmsg;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textmsg=(EditText)findViewById(R.id.editText1);
        t=findViewById(R.id.textView);
    }

    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("student.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("Shreeya 89\nRaghav 99\nPreha 80\nRishita 88\nRam 40\nShyam 60");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("student.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            String s="",inputString;
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader inputReader = new BufferedReader(InputRead);
            int i=0,count=0;
            String names[]=new String[100];
            int marks[]=new int[100];
            while ((inputString = inputReader.readLine()) != null) {
                System.out.println("inputstringgggggggggg"+inputString);
                String[] data=inputString.split(" ");
                System.out.println("daaaaaaaaataaaaaaaaa"+data);
                names[i]=data[0];
                marks[i]=Integer.parseInt(data[1]);
                i++;
                count=i;
                stringBuffer.append(inputString + "\n");
            }
            InputRead.close();
            textmsg.setText(stringBuffer);
            int sum=0,max=0,maxindex=0,min=marks[0],minindex=0;
            for(i=0;i<count;i++){
                sum=sum+marks[i];
                if(marks[i]>max) {
                    max = marks[i];
                    maxindex=i;
                }
                if(marks[i]<min) {
                    min = marks[i];
                    minindex=i;
                }
            }
            System.out.println("ssssssssssssuuuuuuuuummmmmm"+sum +marks.length+"minindex"+minindex);
            double average=sum/(count);
            t.setText("Average:"+average+"\nMax score:"+names[maxindex]+" "+marks[maxindex]+
                    "\nMin score:"+names[minindex]+" "+marks[minindex]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
