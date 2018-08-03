package com.example.fahimahmed.robotdoc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowDis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdis);
        Typeface siam = Typeface.createFromAsset(getAssets(),"font/Siyamrupali_1_01.ttf");
        Intent cntct = getIntent();
        String name = cntct.getStringExtra("k2");
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();

        Cursor cursor = db.query("Diseases", new String[] {"NAME","TREATMENT"}, "NAME = ?", new String[] {name}, null, null, null);
        String treat = null;
        if(cursor.moveToFirst())
        {
            treat = cursor.getString(1);
        }

        TextView t1,t2,t3,t4,t5;
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);

        t1.setText("রোগ সম্পর্কিত তথ্য");
        t2.setText("রোগের নামঃ ");
        t3.setText(name);
        t4.setText("তথ্য ও প্রাথমিক চিকিৎসা:");
        t5.setText(treat);

    }
}
