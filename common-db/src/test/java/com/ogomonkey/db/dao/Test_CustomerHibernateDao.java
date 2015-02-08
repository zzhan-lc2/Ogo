package com.ogomonkey.db.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Iterables;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.db.support.CreateDao;
import com.ogomonkey.db.support.HibernateDaoUnitTestCase;

@Test(groups = { "unit", "dao" })
public class Test_CustomerHibernateDao extends HibernateDaoUnitTestCase {

    @CreateDao(CustomerHibernateDao.class)
    private CustomerHibernateDao dao;

    TestDataBuilder builder = new TestDataBuilder();
    List<Customer> customers;

    @BeforeClass
    public void init() {
        customers = builder.getCustomers();

        for (Customer entity : customers) {
            dao.save(entity);
        }
    }

    public void test_findById() {
        for (Customer entity : customers) {
            Customer actual = dao.findById(entity.getId());
            Assert.assertEquals(actual, entity);
        }
    }

    public void test_findByLogin() {
        for (Customer entity : customers) {
            Customer actual = dao.findByLogin(entity.getLogin());
            Assert.assertEquals(actual, entity);
        }
    }

    public void test_findByCommunication() {
        List<Customer> actual = dao.findByCommunication(Iterables.getFirst(
            builder.getCustomerHasEmailInit().getCommunications(), null).getCommunication());
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), builder.getCustomerHasEmailInit());

        actual = dao.findByCommunication(Iterables.getFirst(
            builder.getCustomerHasPhoneAddrVerified().getCommunications(), null).getCommunication());
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), builder.getCustomerHasPhoneAddrVerified());
    }

    public void test_findByAddressName() {
        Customer expected = builder.getCustomerHasPhoneAddrVerified();
        Address address = new Address();
        List<Customer> actual = dao.findByAddressName(address, expected.getFirstName(), expected.getLastName());
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), expected);

        address = expected.getAddress();
        actual = dao.findByAddressName(address, null, expected.getLastName());
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), expected);
    }
}
