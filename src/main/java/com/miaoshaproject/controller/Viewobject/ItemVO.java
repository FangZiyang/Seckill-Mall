package com.miaoshaproject.controller.Viewobject;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVO {
    private Integer id;

    //The name of the product
    private String title;

    //price of the product
    private BigDecimal price;

    //Inventory of the product
    private Integer stock;

    //Description of the product
    private String description;

    //Sales of the product
    private Integer sales;

    //url of the product description image
    private String imgUrl;

    //Record whether the product is in the promo campaign, and the corresponding status
    // 0: no promo campaign,
    // 1: promo campaign to be started
    // 2: promo campaign in progress
    private Integer promoStatus;

    // price of the promote event
    private BigDecimal promoPrice;

    //Seconds event ID
    private Integer promoId;

    //Spike event start time
    private String startDate;
}