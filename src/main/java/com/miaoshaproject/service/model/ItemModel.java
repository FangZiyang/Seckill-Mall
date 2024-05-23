package com.miaoshaproject.service.model;


import lombok.Data;


import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
public class ItemModel {

    private Integer id;

    //The name of the product
    @NotBlank(message = "Product name cannot be empty")
    private String title;

    //price of the product
    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Item price must be greater than 0")
    private BigDecimal price;

    // the inventory of the item
    @NotNull(message = "Stock cannot be left out")
    private Integer stock;

    // The description of the item
    @NotBlank(message = "Item description information can't be empty")
    private String description;

    //The sales of the item
    private Integer sales;

    // The url of the product description image
    @NotBlank(message = "Product image information cannot be null")
    private String imgUrl;

    //Use aggregation model, if promoModel is not null, then it means it has not yet ended the spike activity
    private PromoModel promoModel;
}
