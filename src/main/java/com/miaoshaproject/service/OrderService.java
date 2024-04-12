package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

public interface OrderService {

    //Use 1, upload the id of the spike activity through the front-end url,
    // and then check whether the corresponding id belongs to the corresponding product
    // and the activity has begun in the ordering interface.

    //2. directly in the order interface to determine whether the corresponding product spike activity,
    // if there is in progress, then the price of the spike order
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
