package com.example.shoppinlist;

class ListeArticle  {
    public String libelé;
    public int amount;
    public String date;


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
    public ListeArticle (String libelé  , int amount, String date)
    {
        this.libelé=libelé;
        this.amount=amount;
        this.date = date;
    }
}
