package com.example.databasewithouthelper;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnAdd,btnDelete,btnUpdate,btnView,btnViewall;
    EditText editRollno,editName,editMarks;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editRollno=(EditText)findViewById(R.id.editRollno);
        editName=(EditText)findViewById(R.id.editName);
        editMarks=(EditText)findViewById(R.id.editMarks);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=findViewById(R.id.btnView);
        btnViewall=findViewById(R.id.btnViewall);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO student VALUES('"+editRollno.getText()+"','"+
                        editName.getText()+"','"+editMarks.getText()+"');");

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM student WHERE rollno='"+editRollno.getText()+"'");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE student SET name='"+editName.getText()+"',marks='"+
                        editMarks.getText()+"' WHERE rollno='"+editRollno.getText()+"'");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
                if(c.moveToFirst())
                {
                    editName.setText(c.getString(1));
                    editMarks.setText(c.getString(2));
                }
            }
        });

        btnViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Rollno: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());

            }
        });





    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
