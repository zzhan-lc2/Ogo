package com.ogomonkey.activity.manager;

import javax.annotation.Nullable;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.AddressEntity;
import com.ogomonkey.common.entity.customer.Customer;

public interface AddressActivityManager {

    Address encryptAddressForEntity(Address address, String entityId, @Nullable String entityType);

    Address decryptAddressForEntity(Address address, String entityId, @Nullable String entityType);

    AddressEntity addAddressToCustomer(Customer customer, Address address, String addressType, UserAction userAction);
}
