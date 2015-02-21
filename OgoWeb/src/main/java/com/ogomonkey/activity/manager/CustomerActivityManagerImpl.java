package com.ogomonkey.activity.manager;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Preconditions;
import com.ogomonkey.activity.param.CreateCustomerRequest;
import com.ogomonkey.activity.param.SystemConstants;
import com.ogomonkey.common.RecordAlreadyExistException;
import com.ogomonkey.common.dao.SecureSaltDao;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.manager.CustomerManager;
import com.ogomonkey.security.dao.CrypterDao;
import com.ogomonkey.security.dao.PBEncryptStorage;
import com.ogomonkey.security.entity.SaltInfo;

public class CustomerActivityManagerImpl implements CustomerActivityManager {

    static final String CREATION_STAGE_TYPE = "login";

    @Autowired
    CustomerManager customerManager;

    @Autowired
    AddressActivityManager addressManager;

    @Autowired
    CrypterDao crypterDao;

    @Autowired
    SecureSaltDao saltDao;

    public Customer createCustomer(CreateCustomerRequest request, UserAction userAction) {
        Preconditions.checkNotNull(request, "request cannot be null");
        Preconditions.checkNotNull(userAction, "userAction cannot be null");

        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getUserLogin()), "userLogin cannot be empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getPassword()), "password cannot be empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(request.getCommNumber()), "commNumber cannot be empty");

        Customer customer = customerManager.findByLogin(request.getUserLogin());
        if (null != customer) {
            throw new RecordAlreadyExistException("The user login is already taken: " + request.getUserLogin());
        }

        Communication comm = new Communication();
        comm.setCommType(request.getCommType());
        comm.setCommNumber(request.getCommNumber());
        comm.setCategory(CREATION_STAGE_TYPE);
        List<Customer> customers = customerManager.findByCommunication(comm);
        if (!CollectionUtils.isEmpty(customers)) {
            throw new RecordAlreadyExistException("The communication info is already taken: " + request.getCommNumber());
        }

        // generate different salt for each customer.
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[8];
        random.nextBytes(salt);
        String passwordForAlg = DigestUtils.md5Hex(request.getCommNumber());

        PBEncryptStorage storage = crypterDao.encrypt(/* inputString= */request.getPassword(), passwordForAlg, salt);

        customer = new Customer();
        customer.setCreatedBy(userAction.getUser());
        customer.setLastUpdatedBy(userAction.getUser());
        customer.setSignupStatus(EntityStatus.INIT);
        customer.setSignupDate(new Date());
        customer.setLogin(request.getUserLogin());
        customer.setEncryptedPassword(storage.toString());
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
        customerManager.save(customer);

        // set the communication to associated with customer
        customerManager.addCommunicationToCustomer(customer, comm, userAction);

        addressManager.addAddressToCustomer(customer, request.getAddress(), CREATION_STAGE_TYPE, userAction);

        // save the encryption info into DB
        SaltInfo saltInfo = new SaltInfo();
        saltInfo.setRelatedEntityId(customer.getId());
        saltInfo.setRelatedEntityType(Customer.class.getSimpleName());
        saltInfo.setPasswordForAlg(passwordForAlg);
        saltInfo.setSalt(salt);
        saltInfo.setCreatedBy(SystemConstants.SYSTEM_USER);
        saltInfo.setLastUpdatedBy(SystemConstants.SYSTEM_USER);
        this.saltDao.save(saltInfo);

        return customer;
    }

    public boolean validateCustomer(String userLogin, String password) {
        // TODO Auto-generated method stub
        return false;
    }

}
