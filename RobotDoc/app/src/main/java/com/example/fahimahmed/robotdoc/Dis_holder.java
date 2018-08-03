package com.example.fahimahmed.robotdoc;

/**
 * Created by Fahim Ahmed on 4/22/2016.
 */
public class Dis_holder {
    public String head,symptoms,head2, treats;
    public String button_text = "ডাক্তার খুঁজুন";
    Dis_holder(String s1, String s2, String s4)
    {
        head = s1;
        symptoms = s2;
        head2 = "বিস্তারিত";
        treats = s4;
    }

    public String getT1(){return head;}
    public String getT2(){return symptoms;}
    public String getT3(){return head2;}
    public String getT4(){return treats;}
    public String getT5(){return button_text;}

}
