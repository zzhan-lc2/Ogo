package com.ogomonkey.web.dao;

import java.util.List;

import javax.annotation.Nullable;

import com.ogomonkey.common.datatype.DateRange;
import com.ogomonkey.web.entity.Session;

public interface SessionDao {

    void save(Session entity);

    Session findById(String sessionId);

    List<Session> findByUser(String userId, @Nullable DateRange lastAccessDateRange);

    List<Session> findByIpAgent(String ip, @Nullable String userAgent, @Nullable DateRange lastAccessDateRange);
}
