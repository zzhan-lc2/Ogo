package com.ogomonkey.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.ogomonkey.common.datatype.DateRange;
import com.ogomonkey.db.support.CreateDao;
import com.ogomonkey.db.support.HibernateDaoUnitTestCase;
import com.ogomonkey.db.util.DateUtil;
import com.ogomonkey.web.entity.Session;

@Test(groups = { "unit", "dao" })
public class Test_SessionHibernateDao extends HibernateDaoUnitTestCase {

    @CreateDao(SessionHibernateDao.class)
    private SessionHibernateDao dao;

    List<Session> sessionList;

    @Override
    protected void setupClass() {
        sessionList = Lists.newArrayList();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            Session session = new Session();
            session.setId(UUID.randomUUID().toString());
            session.setUserId("userId-" + random.nextInt(3));
            session.setIp("ip-" + random.nextInt(6));
            session.setUserAgent("userAgent-" + i);
            session.setRobot(false);
            session.setType("type");
            session.setCreationDate(DateUtil.toDate("2015-01-0" + i));
            session.setLastAccessDate(DateUtil.toDate("2015-01-0" + i));
            session.setLastAccessDateTime(DateUtil.toDate("2015-01-0" + i));

            sessionList.add(session);
        }

        for (Session entity : sessionList) {
            dao.save(entity);
        }
    }

    public void test_findById() {
        for (Session entity : sessionList) {
            Session actual = dao.findById(entity.getId());
            Assert.assertEquals(actual, entity);
        }
    }

    public void test_findByUser() {
        String userId = "userId-2";
        List<Session> subList = Lists.newArrayList();
        Date startDate = null;
        Date endDate = null;
        for (Session entity : sessionList) {
            if (!Objects.equals(userId, entity.getUserId())) {
                continue;
            }
            subList.add(entity);
            Date date = entity.getLastAccessDate();
            if (startDate == null) {
                startDate = date;
                endDate = date;
            } else {
                if (startDate.after(date)) {
                    startDate = date;
                }
                if (endDate.before(date)) {
                    endDate = date;
                }
            }
        }

        List<Session> actual = dao.findByUser(userId, null);
        Assert.assertEquals(actual.size(), subList.size());

        DateRange dateRange = new DateRange();
        dateRange.setStart(DateUtils.addHours(endDate, 1));
        actual = dao.findByUser(userId, dateRange);
        Assert.assertEquals(actual.size(), 0);

        dateRange = new DateRange();
        dateRange.setStart(startDate);
        dateRange.setEnd(endDate);

        actual = dao.findByUser(userId, dateRange);
        Assert.assertEquals(actual.size(), subList.size());
    }
}
