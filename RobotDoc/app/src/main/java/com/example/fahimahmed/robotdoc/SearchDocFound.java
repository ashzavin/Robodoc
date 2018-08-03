package com.example.fahimahmed.robotdoc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchDocFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doc_found);

        Intent i = getIntent();
        String disname = i.getStringExtra("key3");
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();
        TextView tt = (TextView) findViewById(R.id.dochead1);
        String setval = disname + " এর জন্য বিশেষজ্ঞ ডাক্তার এর তালিকা";
        tt.setText(setval);
        Cursor cursor = db.query("Doc_Symp", new String[] {"NAME"}, "Symps = ?", new String[] {disname}, null, null, "NAME");
        ArrayList<String> arr1 = new ArrayList<String>();
        if(cursor.moveToFirst())arr1.add((String)cursor.getString(0));
        while(cursor.moveToNext())arr1.add((String)cursor.getString(0));

        cursor.close();

        ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1);
        ListView buckysListView = (ListView) findViewById(R.id.docname1);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String daktar = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(SearchDocFound.this, DocActivity.class);
                        intent.putExtra("key4",daktar);
                        startActivity(intent);
                    }
                }
        );
    }

}
