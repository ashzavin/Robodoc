package com.example.fahimahmed.robotdoc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Doclistview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doclistview);
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();
        Cursor cursor = db.query("Doctor", new String[] {"NAME"}, null,null, null, null, "NAME");
        ArrayList<String> arr1 = new ArrayList<String>();
        if(cursor.moveToFirst())arr1.add((String)cursor.getString(0));
        while(cursor.moveToNext())arr1.add((String)cursor.getString(0));
        cursor.close();

        ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1);
        ListView buckysListView = (ListView) findViewById(R.id.doclist22);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String daktar = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(Doclistview.this, DocActivity.class);
                        intent.putExtra("key4",daktar);
                        startActivity(intent);
                    }
                }
        );
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
