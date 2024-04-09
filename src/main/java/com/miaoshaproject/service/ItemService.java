package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    // Create an item
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //Browse the list of items
//    List<ItemModel> listItem();

    //Browse item details
    ItemModel getItemById(Integer id);


    // Deduction of stock
//    boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException;

    //Increase sales of the item
//    void increaseSales(Integer itemId, Integer amount) throws BusinessException;
}
