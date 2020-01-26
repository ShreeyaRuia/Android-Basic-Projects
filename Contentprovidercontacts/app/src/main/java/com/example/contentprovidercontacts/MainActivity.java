package com.example.contentprovidercontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity  extends ListActivity {
    public static int PERMISSIONS_REQUEST_READ_CONTACTS=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);


        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]
                {ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER};
        Cursor c = getContentResolver().query(allContacts, projection, null, null, null);
        startManagingCursor(c);

        String[] columns = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,};
        int[] views = new int[]{R.id.contactID, R.id.contactName};

        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, R.layout.activity_main, c, columns, views, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.setListAdapter(adapter);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }



