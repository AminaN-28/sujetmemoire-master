package com.example.shoppinlist;

class ArticleClass {
    private String nameA;
    private  int qte;
    private int price;
    private byte [] img;

    public String getNameA(){return nameA; }
    public void setNameA(String nameA){this.nameA=nameA; }

    public int getQte(){return qte ;}
    public void setQte(int qte){this.qte=qte;}

    public int getPrice(){return price;}

    public void setPrice(int price) { this.price = price; }

    public byte[] getImg() { return img; }
     public void setImg(byte[] img){this.img= img;}

    public ArticleClass (String nameA, int qte ,int price ,byte [] img)
    {
        this.nameA=nameA;
        this.qte=qte;
        this.price = price;
        this.img =img;
    }


}
