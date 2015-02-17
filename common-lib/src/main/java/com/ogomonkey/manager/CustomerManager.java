package com.ogomonkey.manager;

import java.util.List;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.customer.Customer;

public interface CustomerManager {

    void addCommunicationToCustomer(Customer customer, Communication comm, UserAction userAction);

    void save(Customer customer);

    void saveInNewTx(Customer customer);

    Customer findById(String customerId);

    Customer findByLogin(String uniqLogin);

    List<Customer> findByCommunication(Communication comm);

    List<Customer> findByAddressName(Address address, String firstName, String lastName);

}
