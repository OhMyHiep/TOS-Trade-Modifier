package com.tos;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            // Provide the path to your Excel file
            String filePath = "src/main/resources/Trades 3-8.xlsx";

            // Create a FileInputStream to read the Excel file
            FileInputStream fis = new FileInputStream(new File(filePath));

            // Create a Workbook object
            Workbook workbook = new XSSFWorkbook(fis);

            // Create a DataFormatter to format cell values
            DataFormatter dataFormatter = new DataFormatter();

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // Use DataFormatter to get formatted cell values
                String execTime = dataFormatter.formatCellValue(row.getCell(0));
                String spread = dataFormatter.formatCellValue(row.getCell(1));
                String side = dataFormatter.formatCellValue(row.getCell(2));
                int quantity = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(3)));
                String posEffect = dataFormatter.formatCellValue(row.getCell(4));
                String symbol = dataFormatter.formatCellValue(row.getCell(5));
                String exp = dataFormatter.formatCellValue(row.getCell(6));
                String strike = dataFormatter.formatCellValue(row.getCell(7));
                String type = dataFormatter.formatCellValue(row.getCell(8));
                double price = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(9)));
                double netPrice = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(10)));
                String orderType = dataFormatter.formatCellValue(row.getCell(11));

                // Print the parsed data
                System.out.println("Exec Time: " + execTime);
                System.out.println("Spread: " + spread);
                System.out.println("Side: " + side);
                System.out.println("Quantity: " + quantity);
                System.out.println("Pos Effect: " + posEffect);
                System.out.println("Symbol: " + symbol);
                System.out.println("Exp: " + exp);
                System.out.println("Strike: " + strike);
                System.out.println("Type: " + type);
                System.out.println("Price: " + price);
                System.out.println("Net Price: " + netPrice);
                System.out.println("Order Type: " + orderType);
                System.out.println();
            }

            // Close the FileInputStream and Workbook
            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}