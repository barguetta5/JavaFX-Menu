package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

import javafx.scene.text.Text;

import javax.swing.*;

public class menuController {

    @FXML
    private GridPane mainGrid;

    @FXML
    private GridPane secGrid;

    @FXML
    private GridPane drinksGrid;

    private ComboBox check;
    private final int SIZE = 3;
    private File menuFile = new File("menu.TXT");
    private Menu m= new Menu(menuFile);
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    private ArrayList<ComboBox> comboBoxes = new ArrayList<ComboBox>();
    private ArrayList<Product> pList = new ArrayList<Product>(m.getProductList());
    private int countCheckBox = 0;
    private int countCombokBox = 0;
    private Order o = new Order();

    public void initialize()
    {
        //mainGrid.add(check,SIZE, SIZE);

        mainGrid.add(new Text("Menu"),0, 0);
        mainGrid.add(new Text("Name                                                 Price    Quantity"),0, 1);
        mainGrid.add(new Text("First meals"),0, SIZE);
        secGrid.add(new Text("Seconds meals"),0, 0);
        drinksGrid.add(new Text("Drinks meals"),0, 0);
        int first = 0, sec =0, drinks = 0;

        for (int i = 0;i<pList.size();i++)
        {
            if (pList.get(i).getType().equals("first")){
                check = new ComboBox();
                check.getItems().add(1);
                check.getItems().add(2);
                check.getItems().add(3);
                check.getItems().add(4);
                check.getSelectionModel().selectFirst();
                comboBoxes.add(check);
                checkBoxes.add(new CheckBox("$"+pList.get(i).getPrice()));
                mainGrid.add(comboBoxes.get(countCombokBox),SIZE, (SIZE+1)*(first+1));
                mainGrid.add(checkBoxes.get(countCheckBox),SIZE-2, (SIZE+1)*(first+1));
                mainGrid.add(new Text(pList.get(i).getName()),0, (SIZE+1)*(first+1));
                countCheckBox++;
                countCombokBox++;
                first++;

            }
            else if (pList.get(i).getType().equals("main")){
                    check = new ComboBox();
                    check.getItems().add(1);
                    check.getItems().add(2);
                    check.getItems().add(3);
                    check.getItems().add(4);
                    check.getSelectionModel().selectFirst();
                    comboBoxes.add(check);
                    checkBoxes.add(new CheckBox("$"+pList.get(i).getPrice()));
                    secGrid.add(comboBoxes.get(countCombokBox),SIZE, SIZE*(sec+1));
                    secGrid.add(checkBoxes.get(countCheckBox),(SIZE-2), SIZE*(sec+1));
                    secGrid.add(new Text(pList.get(i).getName()),0, (SIZE)*(sec+1));
                    countCheckBox++;
                    countCombokBox++;
                    sec++;
            }
            else{
                    check = new ComboBox();
                    check.getItems().add(1);
                    check.getItems().add(2);
                    check.getItems().add(3);
                    check.getItems().add(4);
                    check.getSelectionModel().selectFirst();
                    comboBoxes.add(check);
                    checkBoxes.add(new CheckBox("$"+pList.get(i).getPrice()));
                    drinksGrid.add(comboBoxes.get(countCombokBox),SIZE, SIZE*(drinks+1));
                    drinksGrid.add(checkBoxes.get(countCheckBox),SIZE-1, SIZE*(drinks+1));
                    drinksGrid.add(new Text(pList.get(i).getName()),0, (SIZE)*(drinks+1));
                    countCheckBox++;
                    countCombokBox++;
                    drinks++;
            }
        }
    }

    @FXML
    void orderByPress(ActionEvent event) {
    }

    public void orderPress(javafx.event.ActionEvent event) {
        dialogUser();
    }
    public void dialogUser()
    {
        String[] options = {"אישור הזמנה", "ביטול הזמנה", "עדכון הזמנה"};
        int x = JOptionPane.showOptionDialog(null, "בחר את אחת האפשרויות בבקשה",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (x == 0){
            userCreateFile();
        }//אישור הזמנה
        else if (x==1){
            for (int i =0;i< checkBoxes.size();i++)
                checkBoxes.get(i).setSelected(false);
        }

    }
    public void userCreateFile() {
        String str = JOptionPane.showInputDialog("Write your name and id");
        if (str.equals("")) {
            System.out.println("text empty");
            return;
        }
        try {
            File myObj = new File(str+".txt");
            if (myObj.createNewFile())
                System.out.println("File created: " + myObj.getName());
            else
                System.out.println("File already exists.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); }
        userPayed(str);
    }
    public void userPayed(String str)
    {
        createOrder();
        String sum = orderList();
        try {
            FileWriter myWriter = new FileWriter(str+".txt");
            for (int i = 0;i<o.getPrdcList().size();i++)
                myWriter.write(o.getPrdcList().get(i).toString());
            myWriter.write(sum);
            JOptionPane.showMessageDialog(null,o.getPrdcList()+"\nyour paymet is:"+sum);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void createOrder()
    {
        String nameP;
        String typeP;
        int priceP;
        int amount;
        String comValue;

        for (int i =0;i< checkBoxes.size();i++){
            comValue = String.valueOf(comboBoxes.get(i).getValue());
            nameP = pList.get(i).getName();
            typeP = pList.get(i).getType();
            priceP = pList.get(i).getPrice();
            amount =Integer.parseInt(comValue) ;
            if (checkBoxes.get(i).isSelected()){
                o.getPrdcList().add(new Product(typeP,nameP,priceP,amount));
            }
        }
    }
    public String orderList()
    {
        int amount = 0;
        String comValue,checkValue;
        int price = 0;
        int sum = 0;

        for (int i =0;i< checkBoxes.size();i++)
        {
            if (checkBoxes.get(i).isSelected())
            {

                comValue = String.valueOf(comboBoxes.get(i).getValue());
                amount =Integer.parseInt(comValue) ;
                checkValue =(String) checkBoxes.get(i).getText();
                checkValue = checkValue.replace("$","");
                price = Integer.parseInt(checkValue);
                sum+= (amount*price);
            }
        }
        return "$"+sum;
    }

}
//this is





