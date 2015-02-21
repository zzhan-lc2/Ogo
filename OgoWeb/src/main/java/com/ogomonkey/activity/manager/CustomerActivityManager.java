package com.ogomonkey.activity.manager;

import com.ogomonkey.activity.param.CreateCustomerRequest;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.customer.Customer;

public interface CustomerActivityManager {

    boolean validateCustomer(String userLogin, String password);

    /**
     * Create a new customer based on input creation request.
     * 
     * @param request
     * @param userAction
     * @return
     */
    Customer createCustomer(CreateCustomerRequest request,
        UserAction userAction);

}
