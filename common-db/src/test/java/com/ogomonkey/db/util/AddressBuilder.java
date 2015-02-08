package com.ogomonkey.db.util;

import java.util.Date;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Location;
import com.ogomonkey.common.entity.AddressEntity;

public final class AddressBuilder {
    String addressLine1;
    String addressLine2;
    boolean isLineEncrypted;
    String city;
    String state;
    String country;
    String zipcode;
    String relatedEntityType;
    String relatedEntityId; // could be customer id, instrument id, business id
    String addressType;
    Location location;
    String createdBy = "tester";
    Date creationDate = new Date();

    public AddressBuilder withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public AddressBuilder withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public AddressBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public AddressBuilder withRelatedEntity(String relatedEntityType, String relatedEntityId) {
        this.relatedEntityType = relatedEntityType;
        this.relatedEntityId = relatedEntityId;
        return this;
    }

    public AddressBuilder withAddressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public AddressBuilder withCreation(Date creationDate, String createdBy) {
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        return this;
    }

    public Address build() {
        Address address = new Address();
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipcode(zipcode);
        return address;
    }

    public AddressEntity buildEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setAddress(build());
        entity.setAddressType(addressType);
        entity.setRelatedEntity(relatedEntityType, relatedEntityId);
        entity.setLocation(location);
        entity.setCreationDate(new Date());

        return entity;
    }
}
