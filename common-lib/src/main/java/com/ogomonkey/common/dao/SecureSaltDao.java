package com.ogomonkey.common.dao;

import javax.annotation.Nullable;

import com.ogomonkey.security.entity.SaltInfo;

public interface SecureSaltDao {

    void save(SaltInfo entity);

    SaltInfo find(String relatedEntityId, @Nullable String relatedEntityType);
}
