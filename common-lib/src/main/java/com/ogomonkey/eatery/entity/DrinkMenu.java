package com.ogomonkey.eatery.entity;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.DateRange;
import com.ogomonkey.common.entity.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "restaurant")
public class DrinkMenu extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Restaurant restaurant;
    private String name;
    private DrinkType drinkType;
    private String category;
    private String subCategory;
    private String shortDescription;
    private String longDescription;
    private Double listPrice; // currency code is controlled by Restaurant entity
    private Double salePrice;
    private DateRange saleDateRange;
    private Double alcoholLevel;
    private String origin;
    private String serveInstructions;
    private String container;
    private Double volume;
    private String volumeUnit;
    private Double calories;
    private Double availability;
    private MenuStatus status;
    private Date statusDate;
    private Set<DrinkIngredient> ingredients;
    private Set<MenuMedia> medias;

    public void addIngredient(DrinkIngredient ingredient) {
        Preconditions.checkNotNull(ingredient, "ingredient cannot be null");

        if (null == ingredients) {
            ingredients = Sets.newHashSet();
        }
        ingredient.setDrinkMenu(this);
        ingredients.add(ingredient);
    }

    public void addMedia(MenuMedia media) {
        Preconditions.checkNotNull(media, "media cannot be null");

        if (null == medias) {
            medias = Sets.newHashSet();
        }
        media.setDrinkMenu(this);
        medias.add(media);
    }
}
