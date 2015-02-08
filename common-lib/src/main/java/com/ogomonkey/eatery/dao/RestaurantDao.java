package com.ogomonkey.eatery.dao;

import java.util.List;

import javax.annotation.Nullable;

import com.ogomonkey.common.dao.BusinessDao;
import com.ogomonkey.eatery.entity.Restaurant;

public interface RestaurantDao extends BusinessDao<Restaurant> {

    List<Restaurant> findByZipcodeStyle(String zipcode, String foodStyle, @Nullable String foodSubStyle);
}
