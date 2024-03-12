package com.tos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Order {
    private String execTime;
    private String spread;
    private String side;
    private double quantity;
    private String posEffect;
    private String symbol;
    private String exp;
    private String strike;
    private String type;
    private double price;
    private double netPrice;
    private String orderType;


    public Date getExecTimeAsDate() {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                return dateFormat.parse(execTime);
            } catch (ParseException e) {
                // Handle parsing exception
                e.printStackTrace();
                return null;
            }
        }


    public String getOnlyDate(){
        SimpleDateFormat justDate = new SimpleDateFormat("MM/dd/yy");
        return justDate.format(getExecTimeAsDate());
    }

    public String getOnlyTime(){
        SimpleDateFormat justDate = new SimpleDateFormat("HH:mm:ss");
        return justDate.format(getExecTimeAsDate());
    }
}