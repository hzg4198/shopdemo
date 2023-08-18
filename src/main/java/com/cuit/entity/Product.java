package com.cuit.entity;

import lombok.Data;

@Data
public class Product {
    private int pid;
    private String pname;
    private double market_price;
    private double shop_price;
    private String pimage;
    private String pdate;
    private int is_hot;
    private String pdesc;
    private int pflag;
    private String cid;
}
