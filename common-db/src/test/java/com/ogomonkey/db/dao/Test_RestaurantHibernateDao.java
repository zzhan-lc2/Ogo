package com.ogomonkey.db.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ogomonkey.db.support.CreateDao;
import com.ogomonkey.db.support.HibernateDaoUnitTestCase;
import com.ogomonkey.eatery.entity.Restaurant;

@Test(groups = { "unit", "dao" })
public class Test_RestaurantHibernateDao extends HibernateDaoUnitTestCase {

    @CreateDao(RestaurantHibernateDao.class)
    private RestaurantHibernateDao dao;

    List<Restaurant> restaurants;
    TestDataBuilder builder;

    @BeforeClass
    public void init() {
        builder = new TestDataBuilder();
        restaurants = builder.getRestaurants();

        for (Restaurant entity : restaurants) {
            dao.save(entity);
        }
    }

    public void test_findById() {
        for (Restaurant entity : restaurants) {
            Restaurant actual = dao.findById(entity.getId());
            Assert.assertEquals(actual, entity);
        }
    }

    public void test_findByNameZipcode() {
        Restaurant expected = builder.getRestaurantNoBar();
        String name = expected.getName();
        String zipcode = expected.getAddress().getZipcode();

        List<Restaurant> actual = dao.findByNameZipcode(name, zipcode);
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), expected);
    }
}
