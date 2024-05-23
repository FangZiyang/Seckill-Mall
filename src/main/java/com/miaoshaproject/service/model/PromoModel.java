package com.miaoshaproject.service.model;

import org.joda.time.DateTime;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PromoModel {

    private Integer id;

    //Seconds Activity Status:
    // 1 means not started yet,
    // 2 means in progress,
    // 3 means finished
    private Integer status;

    //promotion name
    private String promoName;

    //Starting time of the skill
    private DateTime startDate;

    //End time of the skill
    private DateTime endDate;

    //Applicable products id for the skill campaign
    private Integer itemId;

    //promotion price
    private BigDecimal promoItemPrice;
}
