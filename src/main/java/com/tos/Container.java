package com.tos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import lombok.Getter;

@Getter
public class Container {
   
    private HashMap<String,ArrayList<Order>> map= new HashMap<>(); 
    private ArrayList<Trade> trades;



    public void processOrders(Sheet sheet){

            // Create a DataFormatter to format cell values
            DataFormatter dataFormatter = new DataFormatter();

            // Iterate over rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                // Use DataFormatter to get formatted cell values
                String execTime = dataFormatter.formatCellValue(row.getCell(0));
                int quantity = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(3)));
                String symbol = dataFormatter.formatCellValue(row.getCell(5));
                double netPrice = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(10)));

                
                if(map.getOrDefault(symbol, null)==null){
                    ArrayList<Order> orders= new ArrayList<>();  
                    map.put(symbol, orders);
                }
                map.get(symbol).add( 
                    Order.builder()
                    .execTime(execTime)
                    .quantity(quantity)
                    .symbol(symbol)
                    .price(netPrice)
                    .build());
             }

    }



        public void processTrades(){
            trades= new ArrayList<>();
            for (String k: map.keySet()){
                ArrayList<Order> orl= map.get(k);
                Collections.sort(orl,Comparator.comparing(Order::getExecTimeAsDate)); 
                double tempAvgEntryPrice=0.0;
                double tempEntryQty=0.0;
                double tempExitQty=0.0;
                double tempExitPrice=0.0;
                String entryDate="";
                String entryTime="";

                for(Order o: orl){
                    if(o.getQuantity()>0){
                        tempAvgEntryPrice+=o.getQuantity()*o.getPrice();
                        tempEntryQty+=o.getQuantity();
                        entryTime=o.getOnlyTime();
                        entryDate= o.getOnlyDate();
                    }
                    else if (o.getQuantity()<0){
                        tempExitPrice+= o.getPrice()*o.getQuantity();
                        tempExitQty+=o.getQuantity();
                    }

                    if(tempEntryQty!=0.0 && tempEntryQty+tempExitQty==0.0){
                        trades.add(Trade.builder()
                            .entryPrice(tempAvgEntryPrice/tempEntryQty)
                            .exitPrice(tempExitPrice/tempExitQty)
                            .symbol(k)
                            .quantity(tempEntryQty)
                            .Date(entryDate)
                            .time(entryTime)
                            .build()
                            );
                        tempAvgEntryPrice=0.0;
                        tempEntryQty=0.0;
                        tempExitQty=0.0;
                        tempExitPrice=0.0;
                    }
                    
                }
            }
        }


    public void printTrades(){
        for(Trade t: trades){
            System.out.println(t.toString());
        }
    }

    public void printOrders(){
        for (String k: map.keySet()){
            ArrayList<Order> orl= map.get(k);
            Collections.sort(orl,Comparator.comparing(Order::getExecTimeAsDate)); 

            for(Order o: orl){
                System.out.println(o.toString());
            }
        }

    }






}
