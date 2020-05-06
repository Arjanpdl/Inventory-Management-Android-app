package com.example.admin.augscan;

public class Items {
    private String itemname;
    private String itemcategory;
    private String itemprice;
    private String itembarcode;


public Items() {

}

public Items(String itemname,String itemcategory,String itemprice,String itembarcode){

    this.itemname=itemname;
    this.itemcategory=itemcategory;
    this.itemprice=itemprice;
    this.itembarcode= itembarcode;
}

    public String getItemname() {
        return itemname;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public String getItemprice() {
        return itemprice;
    }

    public String getItembarcode() {
        return itembarcode;
    }
}