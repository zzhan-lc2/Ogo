package com.ogomonkey.common.dao;

import java.util.List;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.entity.business.Business;

public interface BusinessDao<T extends Business> {

    void save(T entity);

    T findById(String businessId);

    List<T> findByAddress(Address address);

    List<T> findByNameZipcode(String name, String zipcode);
}
