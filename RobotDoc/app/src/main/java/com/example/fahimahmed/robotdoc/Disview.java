package com.example.fahimahmed.robotdoc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Disview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disview);
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();

        Cursor cursor = db.query("Diseases", new String[] {"NAME"},null, null,null,null,"NAME");

        ArrayList<String> res  = new ArrayList<String>();
        if(cursor.moveToFirst())res.add(cursor.getString(0));

        while(cursor.moveToNext())
        {
            res.add(cursor.getString(0));
        }

        if(cursor != null) cursor.close();

        listshow(res);
    }
    public void listshow(ArrayList<String> sortedres)
    {
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();


        int ln2 = sortedres.size();
        ArrayList<Dis_holder> arr2 = new ArrayList<Dis_holder>();

        for(int ii = 0; ii < ln2; ii++)
        {
            String s1 = sortedres.get(ii);
            // getting the original disease name from the map of query result
            String srch_string = s1;

            //Gathering all the symptoms of that disease
            Cursor cursor = db.query("SYMPTOM", new String[] {"Symps"}, "NAME = ?", new String[] {srch_string},null,null,"Symps");

            StringBuilder sb = new StringBuilder();

            if(cursor.moveToFirst())
            {
                sb.append(cursor.getString(0));
                sb.append("\n");
            }

            while(cursor.moveToNext())
            {
                sb.append(cursor.getString(0));
                sb.append("\n");
            }

            String s2 = sb.toString();
            if(cursor != null)cursor.close();

            cursor = db.query("Diseases", new String[] {"TREATMENT"}, "NAME = ?",new String[] {srch_string}, null, null, null);
            String s4 = "";
            if(cursor.moveToFirst())
            {
                s4=cursor.getString(0);
            }

            Dis_holder dis_temp = new Dis_holder(s1,s2,s4);
            arr2.add(dis_temp);
            if(cursor != null)cursor.close();
        }

        DisAdapter dataadapter2 = new DisAdapter(this, R.layout.symtomresult, arr2);
        ListView listView = (ListView) findViewById(R.id.dislistview2);
        //converting physical pixel unit to display pixel unit
        Resources r = getResources(); //feeding r the details about display resources of a device
        int height_pxl = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 150, r.getDisplayMetrics()
                //converting 150 display matrix to universal display unit
        );

        int Divider_height_pixel = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,24,r.getDisplayMetrics()
        );

        //Setting divider height with display pixel unit
        listView.setDividerHeight(Divider_height_pixel);

        //Setting minimum height of the listview
        listView.setMinimumHeight(height_pxl);
        listView.setAdapter(dataadapter2);

    }
    public class DisAdapter extends ArrayAdapter<Dis_holder>
    {
        private ArrayList<Dis_holder> arr3; //an arraylist to keep the argument arraylist
        private int layoutid;   //a layoutid, that will reserve the layout id of the custom class's layout

        //A viewholder class that will hold the view's for later usage
        private class Viewholder{
            TextView t1,t2,t3,t4;
            Button b1;
        }

        //Constructor to the Custom adapter
        public  DisAdapter(Context context, int textresourceViewid, ArrayList<Dis_holder> datalist)
        {
            super(context, textresourceViewid, datalist);
            layoutid=textresourceViewid;
            arr3 = new ArrayList<Dis_holder>();
            arr3.addAll(datalist);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //Getting a instance to the item of that position, the item itself is an instance of the custom class
            final Dis_holder var1 = arr3.get(position);

            //Creating an instance/variable of a viewholder
            // where we would keep the reference to the view that is sent as argument
            final Viewholder viewholder;

            //If the view is not yet created
            if(convertView == null)
            {
                viewholder = new Viewholder(); //Constructing a new viewholder

                LayoutInflater inflater = LayoutInflater.from(getContext()); //Inflating it from the context

                convertView = inflater.inflate(layoutid, parent, false);
                //initializing the convertView into a valid view,
                //layoutid means the layout for the custom class,
                //parent means the parent layout on which layout id would be placed
                //false means it will not be permanently attached to the parent layout

                //Below we're creating reference for each of the View elements in the customclass list3data
                viewholder.t1 = (TextView) convertView.findViewById(R.id.resulthead);
                viewholder.t2 = (TextView) convertView.findViewById(R.id.res_symp);
                viewholder.t3 = (TextView) convertView.findViewById(R.id.treat_head);
                viewholder.t4 = (TextView) convertView.findViewById(R.id.treats);
                viewholder.b1 = (Button) convertView.findViewById(R.id.doc_find_1);

                //The newly initialized convertview would have the tag of the viewholder used to create that View
                convertView.setTag(viewholder);


                //setting onClicklistener for doctor khujun button :)
                viewholder.b1.setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(Disview.this, SearchDocFound.class);
                                String src_string = (String) (String)var1.getT1();
                                intent.putExtra("key3",src_string);
                                startActivity(intent);
                            }

                        }
                );
            }
            else{
                //if the convertView View has already been initialized,
                // we'll simply get the tag of that and put it inside the viewholder
                viewholder = (Viewholder) convertView.getTag();
            }

            //the viewholder, means that convertview will be set the texts of var1,
            //where var1 is actually arr.get(position)

            viewholder.t1.setText(var1.getT1());
            viewholder.t2.setText(var1.getT2());
            viewholder.t3.setText(var1.getT3());
            viewholder.t4.setText(var1.getT4());
            viewholder.b1.setText(var1.getT5());

            //returning the convertView after setting the values
            return convertView;
        }
    }

    public String getconditions(String[] qur)
    {
        String retval = "Symps = ?";
        int len = qur.length;
        for(int i = 1; i < len; i++)
        {
            retval = retval + " OR Symps = ?";
        }
        /*String text = retval;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(DisDB.this, text, duration); //MainActivity.this gives the context of this activity on which the toast should be shown
        toast.show();
        */
        return retval;
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
