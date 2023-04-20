package sample;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<String> txtRead;
    private ArrayList<Product> productList;

    public Menu(File newFile){
        productList = new ArrayList<Product>();
        txtRead = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(newFile);
            while (myReader.hasNextLine()) {
                txtRead.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error to open the file.");
            e.printStackTrace();
        }
        makeProductList();
    }
    public void makeProductList()
    {
        ArrayList<Product> fisrtP = new ArrayList<Product>();
        ArrayList<Product> secP= new ArrayList<Product>();
        ArrayList<Product> drinksP= new ArrayList<Product>();

        int countFirst = 0;
        int countSecond = 0;
        int countDrinks = 0;
        for (int i = 0; i<txtRead.size();i++)
        {
            if (txtRead.get(i).equals("first")){
                fisrtP.add(new Product(txtRead.get(i),txtRead.get(i+1),Integer.parseInt(txtRead.get(i+2))));
                i+=2;
            }
            else if (txtRead.get(i).equals("main")){
                secP.add(new Product(txtRead.get(i),txtRead.get(i+1),Integer.parseInt(txtRead.get(i+2))));
                i+=2;
            }
            else if (txtRead.get(i).equals("drink")){
                drinksP.add(new Product(txtRead.get(i),txtRead.get(i+1),Integer.parseInt(txtRead.get(i+2))));
                i+=2;
            }
        }
        System.out.println(fisrtP.size());
        newProductList(fisrtP,secP,drinksP);
    }
    public void newProductList(ArrayList<Product> fisrtP,ArrayList<Product> secP,ArrayList<Product> drinksP)
    {
        for (int f = 0;f<fisrtP.size();f++) {
            productList.add(fisrtP.get(f));
        }
        for (int m = 0;m<fisrtP.size();m++)
            productList.add(secP.get(m));
        for (int d = 0;d<fisrtP.size();d++)
            productList.add(drinksP.get(d));
        //System.out.println(productList);// check
    }
    public ArrayList<Product> getProductList()
    {
        return this.productList;
    }
}
