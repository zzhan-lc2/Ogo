package com.ogomonkey.eatery.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ogomonkey.common.media.MediaEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = { "dishMenu", "drinkMenu" })
public class MenuMedia extends MediaEntity {
    private static final long serialVersionUID = 1L;

    private DishMenu dishMenu;
    private DrinkMenu drinkMenu;
}
