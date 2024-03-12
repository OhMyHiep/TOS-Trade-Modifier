package com.tos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Trade {
    private String Date;
    private String time;
    private Double quantity;
    private String symbol;
    private double entryPrice;
    private double exitPrice;

}
