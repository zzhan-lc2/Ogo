package com.ogomonkey.db.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ogomonkey.common.dao.CustomerDao;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.CommEntity;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.manager.CustomerManager;

public class CustomerManagerImpl implements CustomerManager {

    @Autowired
    CustomerDao customerDao;

    @Override
    public void addCommunicationToCustomer(Customer customer, Communication comm, UserAction userAction) {
        CommEntity commEntity = new CommEntity();
        commEntity.setCommunication(comm);
        commEntity.setCreatedBy(userAction.getUser());

        customer.addCommunication(commEntity);

        customerDao.save(customer);
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        Preconditions.checkNotNull(customer, "customer cannot be null");

        customerDao.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveInNewTx(Customer customer) {
        Preconditions.checkNotNull(customer, "customer cannot be null");

        customerDao.save(customer);
    }

    @Override
    public Customer findById(String customerId) {
        Preconditions.checkNotNull(customerId, "customerId cannot be null");

        return customerDao.findById(customerId);
    }

    @Override
    public Customer findByLogin(String uniqLogin) {
        Preconditions.checkNotNull(uniqLogin, "uniqLogin cannot be null");

        return customerDao.findByLogin(uniqLogin);
    }

    @Override
    public List<Customer> findByCommunication(Communication comm) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> findByAddressName(Address address, String firstName, String lastName) {
        // TODO Auto-generated method stub
        return null;
    }

}
