package com.example.fahimahmed.robotdoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiseaseAnalysis extends Activity {
    SymAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_analysis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Typeface siam = Typeface.createFromAsset(getAssets(),"font/Siyamrupali_1_01.ttf");
        Button ganal = (Button) findViewById(R.id.goanal);
        //ganal.setText("খুঁজুন");
       // ganal.setTypeface(siam);
        //ganal.setTextColor(Color.WHITE);
        TextView et11 = (TextView) findViewById( R.id.head11);
        et11.setText("নিম্নের তালিকা হতে আপনার উপসর্গগুলো নিরবাচন করুন");
        et11.setTextColor(Color.WHITE);



        symlistshow();

        checkButtonClick();

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
    public void symlistshow()
    {
        ArrayList<Symholder1> symlist = new ArrayList<Symholder1>();
        Symholder1 s1 = new Symholder1("পাতলা পায়খানা"); symlist.add(s1);
        s1 = new Symholder1("নিস্তেজতা"); symlist.add(s1);
        s1 = new Symholder1("বমিবমি ভাব"); symlist.add(s1);
        s1 = new Symholder1("পেট ব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("তলপেট ব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("পায়খানার সাথে মিউকাস পড়া"); symlist.add(s1);
        s1 = new Symholder1("তাপমাত্রা ১০০-১০২"); symlist.add(s1);
        s1 = new Symholder1("অতিমাত্রায় গ্যাস"); symlist.add(s1);
        s1 = new Symholder1("রক্তবাহী পায়খানা"); symlist.add(s1);
        s1 = new Symholder1("পানিশূন্যতা"); symlist.add(s1);
        s1 = new Symholder1("দ্রুত হৃদস্পন্দন"); symlist.add(s1);
        s1 = new Symholder1("মাংসপেশীতে চাপাব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("নিম্ন রক্তচাপ"); symlist.add(s1);
        s1 = new Symholder1("গা ব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("দুর্বলতা"); symlist.add(s1);
        s1 = new Symholder1("খাদ্যে অরুচি"); symlist.add(s1);
        s1 = new Symholder1("মাথাব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("নাভির ডানে ফোলা"); symlist.add(s1);
        s1 = new Symholder1("ক্ষুধামন্দা"); symlist.add(s1);
        s1 = new Symholder1("মুখমন্ডলে অবশভাব"); symlist.add(s1);
        s1 = new Symholder1("হাতে অবশভাব"); symlist.add(s1);
        s1 = new Symholder1("কথায় জড়তা"); symlist.add(s1);
        s1 = new Symholder1("বুকের বামপাশে চাপা ব্যাথা"); symlist.add(s1);
        s1 = new Symholder1("শ্বাসকষ্ট"); symlist.add(s1);
        dataAdapter = new SymAdapter(this,R.layout.symlayout, symlist);
        ListView listView = (ListView) findViewById(R.id.cklist);
        listView.setAdapter((ListAdapter) dataAdapter);

    }
    public class SymAdapter extends ArrayAdapter<Symholder1> {

        private ArrayList<Symholder1> symlist1;

        public SymAdapter(Context context, int textViewResourceId, ArrayList<Symholder1> smlist)
        {
            super(context, textViewResourceId, smlist);
            this.symlist1 = new ArrayList<Symholder1>();
            this.symlist1.addAll(smlist);
        }


        private class ViewHolder {
            TextView code;
            CheckBox name;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            if(convertView == null)
            {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView=vi.inflate(R.layout.symlayout,null);

                holder = new ViewHolder();
                holder.code = (TextView)convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(
                        new View.OnClickListener(){
                            public void onClick(View v)
                            {
                                CheckBox cb = (CheckBox)v;
                                Symholder1 sm = (Symholder1)cb.getTag();
                                sm.setcheck(cb.isChecked());
                            }
                        }
                );
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }

            Symholder1 sm = symlist1.get(position);
            holder.code.setText(sm.getName());
            Typeface siam = Typeface.createFromAsset(getAssets(),"font/Siyamrupali_1_01.ttf");

            holder.code.setTextColor(Color.WHITE);
            holder.name.setChecked(sm.getcheck());
            holder.name.setTag(sm);
            return convertView;
        }


    }

    private void checkButtonClick(){

        Button myButton = (Button) findViewById(R.id.goanal);
        myButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ArrayList<String> qr = new ArrayList<String>();
                                            ArrayList<Symholder1> sm = dataAdapter.symlist1;
                                            int ln = sm.size();
                                            for(int i = 0; i < ln ; i++)
                                            {
                                                Symholder1 temp = sm.get(i);
                                                if(temp.getcheck() == true)
                                                {
                                                    qr.add(temp.getName());
                                                }
                                            }

                                            ln = qr.size();
                                            String[] arr = new String[ln];
                                            for(int i = 0; i < ln ;i++)
                                            {
                                                arr[i] = qr.get(i);
                                            }
                                            Bundle b=new Bundle();
                                            b.putStringArray("key", arr);
                                            Intent i=new Intent(DiseaseAnalysis.this, DisDB.class);
                                            i.putExtras(b);
                                            startActivity(i);
                                        }
                                    }


        );
    }

}

