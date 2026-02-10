package com.cnbc.quotegraph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String id;
    private String symbol;
    private double price;
    private double change;
    private double changePercent;
    private int volume;
    private String timestamp;
}
