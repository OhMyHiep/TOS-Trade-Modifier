package com.tos.Domain;

import org.apache.poi.ss.usermodel.*;

import com.tos.util.FileManager;


public class Main {
    public static void main(String[] args) {
        // formatTrades();
        insertImage();
        
    }

    public static void insertImage(){
        FileManager fm=new FileManager();
        fm.open();
        Sheet sheet= fm.getWorkbook().getSheetAt(2);
        Container container= new Container();
        container.processImageName(sheet);
        fm.readImage("null");
        fm.close();
    }


    public static void formatTrades(){
        FileManager fm=new FileManager();
        fm.open();
        Sheet sheet= fm.getWorkbook().getSheetAt(0);

        Container container= new Container();
        container.processOrders(sheet);
        container.processTrades();
        container.printTrades();
        fm.writeTrades(container.getTrades());
        // container.printOrders();
        fm.close();
    }
}