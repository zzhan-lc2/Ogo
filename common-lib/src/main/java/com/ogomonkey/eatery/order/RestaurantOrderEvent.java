package com.ogomonkey.eatery.order;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;

import com.ogomonkey.common.entity.AuditableEntity;

@Setter
@Getter
@ToString(exclude = "order")
public class RestaurantOrderEvent extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id; // unique order event id
    private RestaurantOrder order;
    private OrderStatus previousStatus;
    private OrderStatus newStatus;
    private DateTime eventTime;
    private String customerId;
    private String serverId;
    private String notes;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof RestaurantOrderEvent)) {
            return false;
        }

        RestaurantOrderEvent other = (RestaurantOrderEvent) obj;
        if (id != null && other.id != null) {
            return Objects.equals(id, other.id);
        }

        return Objects.equals(order, other.order) &&
            Objects.equals(this.eventTime, other.eventTime);
    }
}
