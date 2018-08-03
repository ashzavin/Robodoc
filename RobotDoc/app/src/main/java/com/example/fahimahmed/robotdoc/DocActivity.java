package com.example.fahimahmed.robotdoc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DocActivity extends AppCompatActivity {

    String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null, s6 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        Intent i1 = getIntent();
        String daktar = i1.getStringExtra("key4");
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();
        Cursor cursor = db.query("Doctor", new String[]{"NAME", "ADDRESS", "ADDRESS2", "EMAIL", "PHONE", "MBBS"},
                "NAME = ?", new String[]{daktar}, null, null, null
        );

        if (cursor.moveToFirst()) {
            s1 = cursor.getString(0);
            s2 = cursor.getString(1);
            s3 = cursor.getString(2);
            s4 = cursor.getString(3);
            s5 = cursor.getString(4);
            s6 = cursor.getString(5);
        }

        TextView tt1, tt2, tt3, tt4, tt5, tt6;
        tt1 = (TextView) findViewById(R.id.docplatetext);
        tt1.setText(s1);
        tt3 = (TextView) findViewById(R.id.thikanaval);
        tt3.setText(s3);
        tt4 = (TextView) findViewById(R.id.chithival);
        tt4.setText(s4);
        tt5 = (TextView) findViewById(R.id.phoneval);
        tt5.setText(s5);
        tt6 = (TextView) findViewById(R.id.mbbsval);
        tt6.setText(s6);
    }

    public void onMapclick(View v) {
        String map = "http://maps.google.co.in/maps?q=" + s2;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Only if initiating from a Broadcast Receiver
        String mapsPackageName = "com.google.android.apps.maps";
        i.setClassName(mapsPackageName, "com.google.android.maps.MapsActivity");
        i.setPackage(mapsPackageName);
        getApplicationContext().startActivity(i);
    }

    public void onPhoneclick(View v) {
        String telmsg = "tel:" + s5;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(telmsg));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    public void onmsgclick(View V)
    {
        EditText mtxt = (EditText) findViewById(R.id.msgtext);
        String kotha = mtxt.getText().toString();
        String telmsg = "sms:" + s5;
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse(telmsg));
        sendIntent.putExtra("sms_body", kotha);
        startActivity(sendIntent);
    }

}
