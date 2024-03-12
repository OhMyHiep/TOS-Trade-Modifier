package com.tos;

import org.apache.poi.ss.usermodel.*;

public class Main {
    public static void main(String[] args) {
        FileManager fm=new FileManager();
        Sheet sheet=fm.open();

        Container container= new Container();
        container.processOrders(sheet);
        container.processTrades();
        // container.printTrades();
        fm.writeTrades(container.getTrades());
        // container.printOrders();
        fm.close();
    }
}