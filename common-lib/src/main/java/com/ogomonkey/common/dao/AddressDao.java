package com.ogomonkey.common.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.entity.AddressEntity;

public interface AddressDao {

    void save(AddressEntity entity);

    AddressEntity findById(String addressId);

    List<AddressEntity> findByRelatedEntity(String relatedEntityType, String relatedEntityId,
        @Nullable Set<EntityStatus> statusFilter);

    List<AddressEntity> findByLatLonRange(double latDD, double lonDD, double delta, int maxReturns);
}
