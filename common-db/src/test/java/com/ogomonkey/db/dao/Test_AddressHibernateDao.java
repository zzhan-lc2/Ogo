package com.ogomonkey.db.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ogomonkey.common.entity.AddressEntity;
import com.ogomonkey.db.support.CreateDao;
import com.ogomonkey.db.support.HibernateDaoUnitTestCase;

@Test(groups = { "unit", "dao" })
public class Test_AddressHibernateDao extends HibernateDaoUnitTestCase {

    @CreateDao(AddressHibernateDao.class)
    private AddressHibernateDao dao;

    List<AddressEntity> addressEntities;

    @BeforeClass
    public void init() {
        TestDataBuilder builder = new TestDataBuilder();
        addressEntities = builder.getAddressEntities();

        for (AddressEntity entity : addressEntities) {
            dao.save(entity);
        }

    }

    public void test_findById() {
        for (AddressEntity entity : addressEntities) {
            AddressEntity actual = dao.findById(entity.getId());
            Assert.assertEquals(actual, entity);
        }
    }

    public void test_findByRelatedEntity() {
        List<AddressEntity> actual = dao.findByRelatedEntity("eType1", "eID1", null);
        Assert.assertEquals(actual.size(), 2);
    }

    public void test_findByLatLonRange() {
        List<AddressEntity> actual = dao.findByLatLonRange(31.0, -114.005, 0.1, -1);
        Assert.assertEquals(actual.size(), 2);
    }

}
