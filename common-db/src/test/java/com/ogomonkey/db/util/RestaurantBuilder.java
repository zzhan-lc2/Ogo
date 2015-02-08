package com.ogomonkey.db.util;

import java.util.Date;
import java.util.Set;

import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.RiskStatus;
import com.ogomonkey.common.entity.CommEntity;
import com.ogomonkey.common.entity.business.BusinessHour;
import com.ogomonkey.common.entity.business.BusinessLicense;
import com.ogomonkey.common.entity.business.BusinessPersonal;
import com.ogomonkey.common.entity.business.BusinessPolicy;
import com.ogomonkey.common.entity.business.BusinessType;
import com.ogomonkey.common.entity.finance.Instrument;
import com.ogomonkey.eatery.entity.DishMenu;
import com.ogomonkey.eatery.entity.DrinkMenu;
import com.ogomonkey.eatery.entity.Restaurant;
import com.ogomonkey.eatery.entity.Table;

public final class RestaurantBuilder {

    private String name;
    private EntityStatus signupStatus;
    private Date signupDate;
    private RiskStatus riskStatus;
    private Date riskStatusDate;
    private Address address;
    private Set<CommEntity> communications = Sets.newHashSet();
    private String businessCategory;
    private String shortDescription;
    private String fullDescription;
    private String ein; // federal Employer Identification Number
    private Long capacity;
    private String mainPhone;
    private Set<BusinessLicense> licenses = Sets.newHashSet();
    private Set<BusinessHour> businessHours = Sets.newHashSet();
    private Set<BusinessPolicy> businessPolicies = Sets.newHashSet();
    private Set<BusinessPersonal> businessPersonals = Sets.newHashSet();
    private Set<Instrument> financialInstruments = Sets.newHashSet();
    private boolean hasBar;
    private String foodStyle;
    private String foodSubStyle;
    private Set<Table> tables = Sets.newHashSet();
    private Set<DishMenu> dishMenus = Sets.newHashSet();
    private Set<DrinkMenu> drinkMenus = Sets.newHashSet();
    String createdBy = "tester";
    Date creationDate = new Date();

    public RestaurantBuilder addComm(CommEntity comm) {
        this.communications.add(comm);
        return this;
    }

    public RestaurantBuilder addLicense(BusinessLicense license) {
        this.licenses.add(license);
        return this;
    }

    public RestaurantBuilder addBusinessHour(BusinessHour hour) {
        this.businessHours.add(hour);
        return this;
    }

    public RestaurantBuilder withBusinessHours(Set<BusinessHour> hours) {
        this.businessHours = hours;
        return this;
    }

    public RestaurantBuilder addBusinessPolicy(BusinessPolicy policy) {
        this.businessPolicies.add(policy);
        return this;
    }

    public RestaurantBuilder addBusinessPersonal(BusinessPersonal personal) {
        this.businessPersonals.add(personal);
        return this;
    }

    public RestaurantBuilder addInstrument(Instrument instrument) {
        this.financialInstruments.add(instrument);
        return this;
    }

    public RestaurantBuilder addTable(Table table) {
        this.tables.add(table);
        return this;
    }

    public RestaurantBuilder withTables(Set<Table> tables) {
        this.tables = tables;
        return this;
    }

    public RestaurantBuilder addDishMenu(DishMenu menu) {
        this.dishMenus.add(menu);
        return this;
    }

    public RestaurantBuilder addDrinkMenu(DrinkMenu menu) {
        this.drinkMenus.add(menu);
        return this;
    }

    public RestaurantBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantBuilder withSignupInfo(EntityStatus signupStatus, Date signupDate) {
        this.signupStatus = signupStatus;
        this.signupDate = signupDate;
        return this;
    }

    public RestaurantBuilder withRiskInfo(RiskStatus riskStatus, Date riskDate) {
        this.riskStatus = riskStatus;
        this.riskStatusDate = riskDate;
        return this;
    }

    public RestaurantBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public RestaurantBuilder withCategory(String category) {
        this.businessCategory = category;
        return this;
    }

    public RestaurantBuilder withDescription(String shortDescription, String fullDescription) {
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        return this;
    }

    public RestaurantBuilder withEIN(String ein) {
        this.ein = ein;
        return this;
    }

    public RestaurantBuilder withCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public RestaurantBuilder withMainPhone(String phone) {
        this.mainPhone = phone;
        return this;
    }

    public RestaurantBuilder withHasBar(boolean hasBar) {
        this.hasBar = hasBar;
        return this;
    }

    public RestaurantBuilder withFoodStyle(String foodStyle, String subStyle) {
        this.foodStyle = foodStyle;
        this.foodSubStyle = subStyle;
        return this;
    }

    public RestaurantBuilder withCreation(Date creationDate, String createdBy) {
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        return this;
    }

    public Restaurant build(BusinessType type) {
        Restaurant entity = new Restaurant(type);
        entity.setName(name);
        entity.setAddress(address);
        entity.setBusinessCategory(businessCategory);
        entity.setBusinessHours(businessHours);
        entity.setBusinessPersonals(businessPersonals);
        entity.setBusinessPolicies(businessPolicies);
        entity.setCapacity(capacity);
        entity.setCommunications(communications);
        entity.setDishMenus(dishMenus);
        entity.setDrinkMenus(drinkMenus);
        entity.setEin(ein);
        entity.setFinancialInstruments(financialInstruments);
        entity.setFoodStyle(foodStyle);
        entity.setFoodSubStyle(foodSubStyle);
        entity.setFullDescription(fullDescription);
        entity.setShortDescription(shortDescription);
        entity.setHasBar(hasBar);
        entity.setLicenses(licenses);
        entity.setMainPhone(mainPhone);
        entity.setRiskStatus(riskStatus);
        entity.setRiskStatusDate(riskStatusDate);
        entity.setSignupStatus(signupStatus);
        entity.setSignupDate(signupDate);
        entity.setTables(tables);
        entity.setCreationDate(creationDate);
        entity.setCreatedBy(createdBy);

        return entity;
    }
}
