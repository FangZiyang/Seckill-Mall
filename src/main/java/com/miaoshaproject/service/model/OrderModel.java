package com.miaoshaproject.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Transaction Modeling for User Orders
 */

@Data
public class OrderModel {


    private String id;

    // user id of the purchasing user
    private Integer userId;

    //Id of the purchased item
    private Integer itemId;

    //If it is not null, it means the order is placed as a second sale item.
    private Integer promoId;

    // the unit price of the purchased item, if promoId is not empty, then it means the price of the item in seconds.
    private BigDecimal itemPrice;

    //Purchase quantity
    private Integer amount;


    //Purchase amount, if promoId is not empty, then the price of the item.
    private BigDecimal orderPrice;


}
