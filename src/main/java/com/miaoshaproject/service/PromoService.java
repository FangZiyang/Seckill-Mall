package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

public interface PromoService {

    //    Get upcoming or ongoing seckilling promotion by itemid
    PromoModel getPromoByItemId(Integer itemId);
}
