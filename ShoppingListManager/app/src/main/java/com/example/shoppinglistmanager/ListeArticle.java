package com.example.shoppinglistmanager;

public class ListeArticle {

    public String libelé;
    public int amount;
    public String date;
    public  int id;


    public String getLibelé ()
    {
        return libelé;
    }
    public void setLibelé (String libelé)
    {
        this.libelé=libelé;
    }
    public int getAmount ()
    {
        return amount;
    }
    public void setAmount (int amount)
    {
        this.amount=amount;
    }


    public String getDate ()
    {
        return date;
    }
    public void setDate (String date)
    {
        this.date=date;
    }
    public int getId(){return id ;}
    public void setId(int id){this.id= id; }

    public ListeArticle(String libelé, int amount, String date, int id)
    {
        this.libelé=libelé;
        this.amount=amount;
        this.date = date;
        this.id= id;
    }
}
