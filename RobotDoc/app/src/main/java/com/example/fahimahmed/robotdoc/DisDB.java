package com.example.fahimahmed.robotdoc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DisDB extends AppCompatActivity {
    String[] queries = null;
    public TreeMap symcount = new TreeMap();
    public TreeMap mp2 = new TreeMap();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        symcount.put("ডায়রিয়া",4.00);
        symcount.put("আমাশয়",6.00);
        symcount.put("কলেরা",6.00);
        symcount.put("জ্বর", 5.00);
        symcount.put("অ্যাপেন্ডিসাইটিস",4.00);
        symcount.put("স্ট্রোক", 5.00);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_db);
        Typeface siam = Typeface.createFromAsset(getAssets(),"font/Siyamrupali_1_01.ttf");
        TextView t14 = (TextView) findViewById(R.id.head14);
        t14.setText("উপসর্গ পর্যালোচনার ফলাফল");


        Intent i = getIntent();
        Bundle b = i.getExtras();
        queries=b.getStringArray("key");
        SQLiteOpenHelper tempdb = new Dbhelper(this);
        SQLiteDatabase db = tempdb.getReadableDatabase();

        Cursor cursor = db.query("SYMPTOM", new String[] {"NAME"}, getconditions(queries), queries,null,null,"NAME");

        ArrayList<String> res  = new ArrayList<String>();
        if(cursor.moveToFirst())res.add(cursor.getString(0));

        while(cursor.moveToNext())
        {
            res.add(cursor.getString(0));
        }

        if(cursor != null) cursor.close();
        TreeMap mp = new TreeMap();
        int ln = res.size();
        Collections.sort(res);
        int fln = queries.length;
        for(int k = 0; k < ln ;k++)
        {
            String tmp = res.get(k);
            double counter = 0;
            int j;
            for( j = k; j < ln; j++)
            {
                if(res.get(j).equals(tmp))
                {
                    counter = counter + 1.0;
                }
                else
                {
                    j--;
                    break;
                }
            }
            double prcnt = counter/(double)symcount.get(tmp);
            prcnt*=100.00;
            prcnt = Math.round(prcnt*100);
            prcnt/=100;
            Double value = new Double(prcnt);
            mp.put(tmp,value);
            k = j;
        }
        Set set = mp.entrySet();
        Iterator it = set.iterator();


        ArrayList<String> sortedres = new ArrayList<String>();
        while(it.hasNext())
        {
            Map.Entry me = (Map.Entry)it.next();
            Double val = (Double) me.getValue();
            String tmp = (String) me.getKey();

            String fin = val.toString()+"% মিলে গেছে "+tmp+" রোগের সাথে";
            char[] temparray = fin.toCharArray();
            for(int ii = 0; ii < 6; ii++)
            {
                if(temparray[ii] == '0')temparray[ii] = '০';
                else if(temparray[ii] == '1') temparray[ii] = '১';
                else if(temparray[ii] == '2') temparray[ii] = '২';
                else if(temparray[ii] == '3') temparray[ii] = '৩';
                else if(temparray[ii] == '4') temparray[ii] = '৪';
                else if(temparray[ii] == '5') temparray[ii] = '৫';
                else if(temparray[ii] == '6') temparray[ii] = '৬';
                else if(temparray[ii] == '7') temparray[ii] = '৭';
                else if(temparray[ii] == '8') temparray[ii] = '৮';
                else if(temparray[ii] == '9') temparray[ii] = '৯';
            }
            fin=String.valueOf(temparray);
            mp2.put(fin, tmp);
            sortedres.add(fin);
        }

        Collections.sort(sortedres);
        int lntt = sortedres.size();
        while(true)
        {
            int ctr=0;
            for(int ii = 0; ii < lntt; ii++)
            {
                String tmpp = sortedres.get(ii);

                if(tmpp.charAt(3) == '.')ctr++;
                else break;
            }
            if(ctr == lntt)break;
            String tm = sortedres.get(0);
            if(tm.charAt(0) == '১' && tm.charAt(3) == '.')
            {
                tm=sortedres.remove(0);
                sortedres.add(tm);
            }
            else break;
        }
        Collections.reverse(sortedres);
        listshow(sortedres);
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
            String srch_string = (String) mp2.get(s1);

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
        ListView listView = (ListView) findViewById(R.id.resview);
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
                                Intent intent = new Intent(DisDB.this, SearchDocFound.class);
                                String src_string = (String) mp2.get((String)var1.getT1());
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
}
