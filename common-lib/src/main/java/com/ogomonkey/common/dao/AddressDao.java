package com.ogomonkey.common.dao;

import java.util.List;

import com.ogomonkey.common.entity.AddressEntity;

public interface AddressDao {

    void save(AddressEntity entity);

    AddressEntity findById(String addressId);

    List<AddressEntity> findByRelatedEntity(String relatedEntityType, String relatedEntityId);

    List<AddressEntity> findByLatLonRange(double latDD, double lonDD, double delta, int maxReturns);
}
