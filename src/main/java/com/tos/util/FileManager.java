package com.tos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import com.tos.Domain.Trade;
import org.apache.poi.util.IOUtils;
import lombok.Getter;
@Getter
public class FileManager {
     

        // Provide the path to your Excel file
        private String filePath = "src/main/resources/Trades.xlsx";
    
        private FileInputStream fis;
        private Workbook workbook;

        public Workbook getWorkbook(){
            return workbook;
        }
        public void open(){
            try {
                 // Create a FileInputStream to read the Excel file
                fis = new FileInputStream(new File(filePath));
                // Create a Workbook object
                workbook = new XSSFWorkbook(fis);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void writeTrades(ArrayList<Trade> tradeList){
               Sheet sheet = workbook.getSheet("TradeData");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Time");
            headerRow.createCell(1).setCellValue("Date");
            headerRow.createCell(2).setCellValue("Symbol");
            headerRow.createCell(3).setCellValue("Quantity");
            headerRow.createCell(4).setCellValue("Entry Price");
            headerRow.createCell(5).setCellValue("Exit Price");

            // Create data rows for each trade in the list
            int rowIndex = 1;
            for (Trade trade : tradeList) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(trade.getTime());
                dataRow.createCell(1).setCellValue(trade.getDate());
                dataRow.createCell(2).setCellValue(trade.getSymbol());
                dataRow.createCell(3).setCellValue(trade.getQuantity());
                dataRow.createCell(4).setCellValue(trade.getEntryPrice());
                dataRow.createCell(5).setCellValue(trade.getExitPrice());
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

        public void readImage(String path){
            path= "src/main/resources/8:14:24 GNLN.png";
            File file = new File(path);
            if (file.exists()){
                System.out.println("exist");
                Sheet sheet = workbook.getSheet("Images");
                Row row=sheet.getRow(1);
                row.createCell(2).setCellValue(path);
                try {
                    FileOutputStream fileOut = new FileOutputStream("src/main/resources/Trades.xlsx");
                    FileInputStream imageInput = new FileInputStream(file);
                    byte[] imageBytes = IOUtils.toByteArray(imageInput);

                    int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
                    Drawing<?> drawing = sheet.createDrawingPatriarch();

                    // Create an anchor that is attached to a specific cell
                    ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                    anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
                    
                    // Set top-left corner of the image to cell (row 1, column 2)
                    anchor.setCol1(2);  // Column C (0-based index, so 2 is column C)
                    anchor.setRow1(1);  // Row 2 (0-based index, so 1 is row 2)

                    // Insert the image
                    Picture picture = drawing.createPicture(anchor, pictureIdx);

                    // Optionally resize the image to fit in the cell
                    // picture.resize();
                    
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
            else{
                System.out.println("not");
            }
        }

    }
