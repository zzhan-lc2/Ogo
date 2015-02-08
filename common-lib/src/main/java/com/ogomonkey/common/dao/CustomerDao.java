package com.ogomonkey.common.dao;

import java.util.List;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.entity.customer.Customer;

public interface CustomerDao {

    void save(Customer customer);

    Customer findById(String customerId);

    Customer findByLogin(String uniqLogin);

    List<Customer> findByCommunication(Communication comm);

    List<Customer> findByAddressName(Address address, String firstName, String lastName);
}
