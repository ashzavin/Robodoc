package com.example.fahimahmed.robotdoc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Typeface siam = Typeface.createFromAsset(getAssets(),"font/Siyamrupali_1_01.ttf");
        TextView h1 = (TextView) findViewById(R.id.head3);

        h1.setText("রোবডক এ স্বাগতম");
        h1.setTextColor(Color.WHITE);

        TextView t2,t3,t4;
        t2 = (TextView) findViewById(R.id.disanalysis);
        t3 = (TextView) findViewById(R.id.doclist);
        t4 = (TextView) findViewById(R.id.dislist);
        t2.setText("রোগের উপসর্গ শনাক্ত করুন");
        t3.setText("বিশেষজ্ঞ ডাক্তারের তালিকা দেখুন");
        t4.setText("রোগের বিস্তারিত দেখুন");
    }

    public void onanalysis(View v)
    {
        Intent intent = new Intent(this, DiseaseAnalysis.class);
        startActivity(intent);
    }
    public void ondata(View v)
    {
        Intent intent = new Intent(this, Disview.class);
        startActivity(intent);
    }
    public void ondoc(View v)
    {
        Intent intent = new Intent(this, Doclistview.class);
        startActivity(intent);
    }
}
