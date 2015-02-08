package com.ogomonkey.eatery.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "dishMenu")
public class DishIngredient extends IngredientEntity {
    private static final long serialVersionUID = 1L;

    private DishMenu dishMenu;

}
