package com.aayushsinha.android.providerwebinar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(MainActivity.this, "This is a Toast", Toast.LENGTH_SHORT).show();
        String[] permissions = new String[] {Manifest.permission.READ_CONTACTS};
        int r_code = 1;
        ActivityCompat.requestPermissions(MainActivity.this, permissions, r_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts();
                } else {
                    //Permission Denied
                }
                break;
        }
    }

    public void getContacts() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if(cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    String[] args= new String[] {id};
                    Cursor cur_1 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", args, null);
                    while (cur_1 != null && cur_1.moveToNext()) {
                        String phone = cur_1.getString(cur_1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.d("CONTACTS_WEBINAR_TEST", "ID: " + id + " Name: " + name + " Number: " + phone);
                    }

                    if(cur_1 != null) {
                        cur_1.close();
                    }
                }
            }
        }

        if(cur != null) {
            cur.close();
        }
    }
}
