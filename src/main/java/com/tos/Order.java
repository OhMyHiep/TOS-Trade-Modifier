package com.tos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String execTime;
    private String spread;
    private String side;
    private int quantity;
    private String posEffect;
    private String symbol;
    private String exp;
    private String strike;
    private String type;
    private double price;
    private double netPrice;
    private String orderType;
}