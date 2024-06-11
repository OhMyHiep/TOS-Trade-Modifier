package com.tos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tos.Domain.Trade;

public class FileManager {
     

        // Provide the path to your Excel file
        private String filePath = "src/main/resources/Trades.xlsx";
    
        private FileInputStream fis;
        private Workbook workbook;


        public Sheet open(){
            try {
                 // Create a FileInputStream to read the Excel file
                fis = new FileInputStream(new File(filePath));
                // Create a Workbook object
                workbook = new XSSFWorkbook(fis);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
             // Get the first sheet
             return workbook.getSheetAt(0);
        }

        public void writeTrades(ArrayList<Trade> tradeList){
            Sheet sheet = workbook.getSheet("TradeData");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Type");
            headerRow.createCell(1).setCellValue("Time");
            headerRow.createCell(5).setCellValue("Date");
            headerRow.createCell(6).setCellValue("Symbol");
            headerRow.createCell(7).setCellValue("Quantity");
            headerRow.createCell(8).setCellValue("Entry Price");
            headerRow.createCell(10).setCellValue("Quantity2");
            headerRow.createCell(11).setCellValue("Exit Price");

            // Create data rows for each trade in the list
            int rowIndex = 1;
            for (Trade trade : tradeList) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(trade.getType());
                dataRow.createCell(1).setCellValue(trade.getTime());
                dataRow.createCell(5).setCellValue(trade.getDate());
                dataRow.createCell(6).setCellValue(trade.getSymbol());
                dataRow.createCell(7).setCellValue(trade.getQuantity());
                dataRow.createCell(8).setCellValue(trade.getEntryPrice());
                dataRow.createCell(10).setCellValue(trade.getQuantity());
                dataRow.createCell(11).setCellValue(trade.getExitPrice());
            }

            // Adjust column widths for better visibility
            for (int i = 0; i <= 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/Trades.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void close(){
               // Close the FileInputStream and Workbook
               try {
                fis.close();
                workbook.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
               
        }

    }
