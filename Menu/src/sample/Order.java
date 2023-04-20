package sample;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> prdcList;
    public Order()
    {
        this.prdcList = new ArrayList<Product>();
    }
    public ArrayList<Product> getPrdcList()
    {
        return this.prdcList;
    }
}
