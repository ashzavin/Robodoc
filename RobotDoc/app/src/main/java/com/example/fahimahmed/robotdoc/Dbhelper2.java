package com.example.fahimahmed.robotdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fahim Ahmed on 4/16/2016.
 */
public class Dbhelper2 extends SQLiteOpenHelper {
    private static final String DB_NAME = "Robo2";
    private static final int DB_VERSION = 1;
    Dbhelper2(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE SYMPTOM (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NAME TEXT, "
                +"Symps TEXT); ");

        db.execSQL("CREATE TABLE Diseases (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NAME TEXT, "
                +"TREATMENT TEXT); ");

        db.execSQL("CREATE TABLE Doc_Symp (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NAME TEXT, "
                +"Symps TEXT); ");

        db.execSQL("CREATE TABLE Doctor (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NAME TEXT, "
                +"ADDRESS TEXT, "
                +"ADDRESS2 TEXT, "
                +"EMAIL TEXT, "
                +"PHONE TEXT, "
                +"MBBS TEXT); ");

        insertSymp(db, "ডায়রিয়া","পাতলা পায়খানা");
        insertSymp(db, "ডায়রিয়া","বমিবমি ভাব");
        insertSymp(db, "ডায়রিয়া","নিস্তেজতা");
        insertSymp(db, "ডায়রিয়া","পেট ব্যাথা");
        insertSymp(db, "আমাশয়","পায়খানার সাথে মিউকাস পড়া");
        insertSymp(db, "আমাশয়","বমিবমি ভাব");
        insertSymp(db, "আমাশয়","তলপেট ব্যাথা");
        insertSymp(db, "আমাশয়","তাপমাত্রা ১০০-১০২");
        insertSymp(db, "আমাশয়","অতিমাত্রায় গ্যাস");
        insertSymp(db, "আমাশয়","রক্তবাহী পায়খানা");
        insertSymp(db, "কলেরা","পাতলা পায়খানা");
        insertSymp(db, "কলেরা","পেট ব্যাথা");
        insertSymp(db, "কলেরা","পানিশূন্যতা");
        insertSymp(db, "কলেরা","দ্রুত হৃদস্পন্দন");
        insertSymp(db, "কলেরা","মাংসপেশীতে চাপাব্যাথা");
        insertSymp(db, "কলেরা","নিম্ন রক্তচাপ");
        insertSymp(db, "জ্বর","গা ব্যাথা");
        insertSymp(db, "জ্বর","দুর্বলতা");
        insertSymp(db, "জ্বর","খাদ্যে অরুচি");
        insertSymp(db, "জ্বর","তাপমাত্রা ১০০-১০২");
        insertSymp(db, "জ্বর","মাথাব্যাথা");
        insertSymp(db, "অ্যাপেন্ডিসাইটিস","নাভির ডানে ফোলা");
        insertSymp(db, "অ্যাপেন্ডিসাইটিস","বমিবমি ভাব");
        insertSymp(db, "অ্যাপেন্ডিসাইটিস","ক্ষুধামন্দা");
        insertSymp(db, "অ্যাপেন্ডিসাইটিস","তাপমাত্রা ১০০-১০২");
        insertSymp(db, "স্ট্রোক","মুখমন্ডলে অবশভাব");
        insertSymp(db, "স্ট্রোক","হাতে অবশভাব");
        insertSymp(db, "স্ট্রোক","কথায় জড়তা");
        insertSymp(db, "স্ট্রোক","বুকের বামপাশে চাপা ব্যাথা");
        insertSymp(db, "স্ট্রোক","শ্বাসকষ্ট");
        insertDis(db,"ডায়রিয়া","ডায়রিয়া এর ফলে দেহ হতে প্রচুর পরিমাণ এ পানি নিঃসরণ ঘটে। তাই এসময় রোগীকে যথেষ্ট পরিমাণ এ পানি, খাবার স্যালাইন খেতে দিতে হবে।\n"
                +"রোগী সুস্থ না হওয়া পর্যন্ত সকল ধরনের ভারি খাবার বর্জন করতে হবে।\n"
                +"রোগী সকল ধরনের স্বাভাবিক খাবার যেমন সাদা ভাত, কলা সিদ্ধ, অল্প মসলায় রান্না করা মুরগির মাংস, মাছ, সব্জি খেতে পারবে।\n");
        insertDis(db,"আমাশয়","আমাশয়ের চিকিৎসার মূল লক্ষ্য দুইটিঃ\n"+"বার বার পাতলা পায়খানার ফলে পানি শূন্যতার হাত থেকে রোগীকে রক্ষা করতে হবে \n"+"বিশেষ করে ডি-হাইড্রেশন প্রতিরোধ করে রাখা সবচেয়ে বেশি জরুরী ( ডায়রিয়ার মত )\n"
                +"এবং  সাথে জীবাণু নাশে ঔষধের ব্যবহার করে যাবেন চিকিৎসকের পরামর্শ অনুসারে\n"+
                "সেই সাথে পায়খানা বেশি হয় বা তরল হয় এমন ধরনের খাবার এবং ঔষধ অবশ্যই কিছু দিনের জন্য বন্ধ রাখা ভাল: যেমন এন্টাসিড বা তৈল জাতীয় খাবার\n"+
                "মেডিকেশনঃ ড্রাগস ব্যাবহার করার সময় অবশ্যই খেয়াল রাখবেন শরিরের ডি হাইড্রেশনের দিকে এবং সে জন্য খাওয়ার স্যালাইন বা আইভি স্যালাইন দিয়ে রাখতে হবে\n");
        insertDis(db,"জ্বর","তাপমাত্রা ১০০ এর উপরে অবস্থান করলে নিয়মিত ৮ ঘন্টা অন্তর প্যারাসিটামল ট্যাবলেট দিয়ে যেতে হবে\n"+
                "তাপমাত্রা না কমলে রোগীর মাথায় পানি দিতে হবে ৪৫ মিনিট অন্তরর\n"+"রোগীর অরুচি থাকা স্বত্বেও স্বাভাবিক খাবার দিয়ে যেতে হবে\n"+
                "জ্বর ৩ দিন এর বেশি স্থায়ী হলে ডাক্তারের শরণাপন্ন হয়ে রক্ত পরীক্ষা করাতে হবে\n");
        insertDis(db,"কলেরা","কলেরা আক্রান্ত রোগীর দ্রুত পানিশূন্যতা ঘটে তাই আক্রান্ত রোগীকে অতি দ্রুত ইলেক্ট্রোলাইট স্যালাইন প্রদান করতে হবে, এবং অতি দ্রুত ডাক্তারের শরণাপন্ন হতে হবে\n");
        insertDis(db,"অ্যাপেন্ডিসাইটিস","এটি প্রকৃতপক্ষে এক প্রকার উপসর্গ যা নির্দেশ করে যে আক্রান্ত রোগীর দ্রুত অ্যাপেন্ডিসাইটিস অপারেশন করতে হবে, এই জন্য দ্রুত ডাক্তারের শরণাপন্ন হতে হবে\n");
        insertDis(db,"স্ট্রোক","এটি রোগীর জরুরী অবস্থা নির্দেশ করছে। অতি দ্রুত রোগীকে একদিকে কাত করে শুইয়ে দিয়ে হাস্পাতালের হৃদরোগ বিভাগে স্থানান্তর করুন\n");
        insertDocSymp(db, "ফাহিম আহমেদ", "জ্বর");
        insertDocSymp(db, "তানভির মাহমুদ", "জ্বর");
        insertDocSymp(db, "জুবায়ের হাসান","স্ট্রোক");
        insertDocSymp(db, "আসলাম হোসেন","স্ট্রোক");
        insertDocSymp(db, "আনোয়ারা খান", "কলেরা");
        insertDocSymp(db, "আনোয়ারা খান", "ডায়রিয়া");
        insertDocSymp(db, "আসিফ হাসান মেহেদি","ডায়রিয়া");
        insertDocSymp(db, "আসিফ হাসান মেহেদি","আমাশয়");
        insertDocSymp(db, "ফারহাত তাবাসসুম", "অ্যাপেন্ডিসাইটিস");
        insertDocSymp(db, "মামুন আহমেদ", "অ্যাপেন্ডিসাইটিস");
        insertDoc(db,"ফাহিম আহমেদ","Ibrahim Cardiac Hospital & Research Institute, Dhaka, Bangladesh","বারডেম হাস্পাতাল, শাহবাগ, ঢাকা", "fahim.avalon@gmail.com","+8801782869957", "ইব্রাহিম মেডিকেল কলেজ");
        insertDoc(db,"তানভির মাহমুদ","Popular Diagnostic Centre, Road No. 2, Dhaka, Dhaka Division, Bangladesh","পপুলার ডায়গনস্টিক সেন্টার, ধানমন্ডি, ঢাকা" ,"fahim.avalon@gmail.com","+8801782869957", "ঢাকা মেডিকেল কলেজ");
        insertDoc(db,"জুবায়ের হাসান","Bangabandhu Sheikh Mujib Medical University","বঙ্গবন্ধু মেডিকেল কলেজ, শাহবাগ, ঢাকা" ,"fahim.avalon@gmail.com","+8801782869957", "ঢাকা মেডিকেল কলেজ");
        insertDoc(db,"আসলাম হোসেন","Bangabandhu Sheikh Mujib Medical University","বঙ্গবন্ধু মেডিকেল কলেজ, শাহবাগ, ঢাকা" ,"fahim.avalon@gmail.com","+8801782869957", "রাজশাহী মেডিকেল কলেজ");
        insertDoc(db,"আনোয়ারা খান","Al Helal Specialized Hospital, Begum Rokeya Avenue, Dhaka, Dhaka Division, Bangladesh","আল হেলাল স্পেশালাইজড হাসপাতাল, বেগম রোকেয়া স্মরনী, মিরপুর-১০, ঢাকা" ,"fahim.avalon@gmail.com","+8801782869957", "চট্টগ্রাম মেডিকেল কলেজ");
        insertDoc(db,"আসিফ হাসান মেহেদি", "Uttara Cresent Hospital, Dhaka, Dhaka Division, Bangladesh","উত্তরা ক্রিসেন্ট হাস্পাতাল, উত্তরা, সেক্টর -৩, ঢাকা" ,"fahim.avalon@gmail.com","+8801782869957", "ঢাকা মেডিকেল কলেজ");
        insertDoc(db,"ফারহাত তাবাসসুম","Ibrahim Cardiac Hospital & Research Institute, Dhaka, Bangladesh", "বারডেম হাস্পাতাল, শাহবাগ, ঢাকা","fahim.avalon@gmail.com","+8801782869957", "ইব্রাহিম মেডিকেল কলেজ");
        insertDoc(db,"মামুন আহমেদ","Al Helal Specialized Hospital, Begum Rokeya Avenue, Dhaka, Dhaka Division, Bangladesh","আল হেলাল স্পেশালাইজড হাসপাতাল, বেগম রোকেয়া স্মরনী, মিরপুর-১০, ঢাকা","fahim.avalon@gmail.com","+8801782869957", "ঢাকা মেডিকেল কলেজ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    private static void insertSymp(SQLiteDatabase db, String name, String symp)
    {
        ContentValues sympval = new ContentValues();
        sympval.put("NAME", name);
        sympval.put("Symps", symp);
        db.insert("SYMPTOM", null, sympval);
    }


    private static void insertDis(SQLiteDatabase db, String name, String treat)
    {
        ContentValues disval = new ContentValues();
        disval.put("NAME", name);
        disval.put("TREATMENT", treat);
        db.insert("Diseases", null, disval);
    }

    private static void insertDocSymp(SQLiteDatabase db, String name, String symp)
    {
        ContentValues sympval = new ContentValues();
        sympval.put("NAME", name);
        sympval.put("Symps", symp);
        db.insert("Doc_Symp", null, sympval);
    }


    private static void insertDoc(SQLiteDatabase db, String name, String address, String addr2, String mail, String phone, String mbbs)
    {
        ContentValues docdata = new ContentValues();
        docdata.put("NAME",name);
        docdata.put("ADDRESS",address);
        docdata.put("ADDRESS2", addr2);
        docdata.put("EMAIL", mail);
        docdata.put("PHONE", phone);
        docdata.put("MBBS", mbbs);
        db.insert("Doctor", null, docdata);
    }


}
