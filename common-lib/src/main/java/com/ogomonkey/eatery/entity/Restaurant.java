package com.ogomonkey.eatery.entity;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.entity.business.Business;
import com.ogomonkey.common.entity.business.BusinessType;

@Data
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends Business {
    private static final long serialVersionUID = 1L;

    private boolean hasBar;
    private String foodStyle;
    private String foodSubStyle;
    private Set<Table> tables;
    private Set<DishMenu> dishMenus;
    private Set<DrinkMenu> drinkMenus;

    public Restaurant() {
        this(BusinessType.RESTAURANT);
    }

    public Restaurant(BusinessType type) {
        this.setBusinessType(type);
        if (Objects.equal(type, BusinessType.RESTAURANT_WITH_BAR) ||
            Objects.equal(type, BusinessType.BAR)) {
            this.hasBar = true;
        } else {
            this.hasBar = false;
        }
    }

    public void addTable(Table table) {
        Preconditions.checkNotNull(table, "table cannot be null");

        if (tables == null) {
            tables = Sets.newHashSet();
        }
        table.setRestaurant(this);
        this.tables.add(table);
    }

    public void addDishMenu(DishMenu dishMenu) {
        Preconditions.checkNotNull(dishMenu, "dishMenu cannot be null");

        if (dishMenus == null) {
            dishMenus = Sets.newHashSet();
        }
        dishMenu.setRestaurant(this);
        this.dishMenus.add(dishMenu);
    }

    public void addDrinkMenu(DrinkMenu drinkMenu) {
        Preconditions.checkNotNull(drinkMenu, "drinkMenu cannot be null");

        if (drinkMenus == null) {
            drinkMenus = Sets.newHashSet();
        }
        drinkMenu.setRestaurant(this);
        this.drinkMenus.add(drinkMenu);
    }
}
