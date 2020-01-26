package com.example.databasewithhelper;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, surname, marks, editid;
    DatabaseHelperClass myDB;
    Button btnAdd, btnViewall, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelperClass(this);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        marks = (EditText) findViewById(R.id.marks);
        editid = (EditText) findViewById(R.id.editId);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewall = (Button) findViewById(R.id.btnViewall);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(name.getText().toString(), surname.getText().toString(), marks.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = myDB.getAllData();
                if (result.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("ID :" + result.getString(0) + "\n");
                    buffer.append("NAME :" + result.getString(1) + "\n");
                    buffer.append("SURNAME :" + result.getString(2) + "\n");
                    buffer.append("MARKS :" + result.getString(3) + "\n\n\n");
                }
                showMessage("DATA", buffer.toString());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(editid.getText().toString(), name.getText().toString(), surname.getText().toString(), marks.getText().toString());
                if (isUpdated == true) {
                    Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
