package com.example.shoppinglistmanager;

public class ArticleCLASS  {

    private String nameA;
    private  int qte;
    private String price;
    private byte [] img;
    private int id;

    public String getNameA(){return nameA; }
    public void setNameA(String nameA){this.nameA=nameA; }

    public int getQte(){return qte ;}
    public void setQte(int qte){this.qte=qte;}

    public String getPrice(){return price;}

    public void setPrice(String price) { this.price = price; }

    public byte[] getImg() { return img; }
    public void setImg(byte[] img){this.img= img;}

    public ArticleCLASS (String nameA , String price , int qte, byte [] img, int id)
    {
        this.nameA=nameA;
        this.qte=qte;
        this.price = price;
        this.img =img;
        this.id = id;
    }

}
