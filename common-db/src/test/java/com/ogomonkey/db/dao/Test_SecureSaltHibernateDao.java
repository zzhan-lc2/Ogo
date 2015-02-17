package com.ogomonkey.db.dao;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.ogomonkey.db.support.CreateDao;
import com.ogomonkey.db.support.HibernateDaoUnitTestCase;
import com.ogomonkey.security.entity.SaltInfo;

@Test(groups = { "unit", "dao" })
public class Test_SecureSaltHibernateDao extends HibernateDaoUnitTestCase {

    @CreateDao(SecureSaltHibernateDao.class)
    private SecureSaltHibernateDao dao;

    List<SaltInfo> saltInfoList;

    @BeforeClass
    public void init() {
        saltInfoList = Lists.newArrayList();

        saltInfoList.add(buildSalt("id-1", "type-1", "password-1"));
        saltInfoList.add(buildSalt("id-2", "type-1", "password-2"));
        saltInfoList.add(buildSalt("id-3", "type-2", "password-3"));
        saltInfoList.add(buildSalt("id-4", "type-2", "password-4"));

        for (SaltInfo entity : saltInfoList) {
            dao.save(entity);
        }
    }

    public void test_find() {
        for (SaltInfo entity : saltInfoList) {
            SaltInfo actual = dao.find(entity.getRelatedEntityId(), null);
            Assert.assertEquals(actual, entity);
        }

        for (SaltInfo entity : saltInfoList) {
            SaltInfo actual = dao.find(entity.getRelatedEntityId(), entity.getRelatedEntityType());
            Assert.assertEquals(actual, entity);
        }

        for (SaltInfo entity : saltInfoList) {
            SaltInfo actual = dao.find(entity.getRelatedEntityId(), entity.getRelatedEntityType() + "wrong");
            Assert.assertNull(actual);
        }
    }

    SaltInfo buildSalt(String id, String type, String password) {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[8];
        random.nextBytes(salt);

        SaltInfo entity = new SaltInfo();
        entity.setRelatedEntityId(id);
        entity.setRelatedEntityType(type);
        entity.setPasswordForAlg(password);
        entity.setSalt(salt);
        entity.setCreationDate(new Date());

        return entity;
    }
}
