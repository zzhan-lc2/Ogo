package com.ogomonkey.eatery.order;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.ogomonkey.common.entity.AuditableEntity;

@Setter
@Getter
@ToString(exclude = "order")
public class RestaurantOrderItem extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private RestaurantOrder order;
    private Set<RestaurantOrderItemOption> options;
}
