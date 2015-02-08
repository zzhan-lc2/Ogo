package com.ogomonkey.eatery.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.entity.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class IngredientEntity extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String origin; // where is this ingredient coming from?
    private String description;
    private Boolean optional;
    private Double extraPrice; // does it cause extra price for the menu?
    private Double calories;
    private MenuStatus status;
    private Date statusDate;
}
