package sample;

import javafx.scene.Node;

public class Product {
    private int price;
    private int amount;
    private String name;
    private String type;
    public Product(){
        this.amount=0;
        this.price = 0;
        this.name = null;
        this.type = null;
    }

    public Product(String type,String name,int price)
    {
        this.amount=0;
        this.price = price;
        this.name = name;
        this.type = type;
    }
    public Product(String type,String name,int price,int amount)
    {
        this.amount=amount;
        this.price = price;
        this.name = name;
        this.type = type;
    }
    public int getPrice()
    {
        return this.price;
    }
    public int getAmount()
    {
        return this.amount;
    }
    public String getName()
    {
        return this.name;
    }
    public String getType()
    {
        return this.type;
    }
    public void setPrice(int price){this.price = price;}
    public void setType(String type){this.type = type;}
    public void setName(String name){this.name = name;}
    public String toString()
    {
        return "Type="+this.type+"\r\n"+
                "Name="+this.name+"\r\n"+
                "Price="+this.price+"\r\n"+
                "Amount="+this.amount+"\r\n\r\n";
    }


}
