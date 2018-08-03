package com.example.fahimahmed.robotdoc;

/**
 * Created by Fahim Ahmed on 4/17/2016.
 */
public class Symholder1 {
    String name = "";
    Boolean chkval = false;

    public Symholder1(String nm)
    {
        super();
        this.name=nm;
    }
    public String getName(){
        return this.name;
    }
    public Boolean getcheck()
    {
        return this.chkval;
    }
    public void setcheck(Boolean val)
    {
        this.chkval=val;
    }
}
