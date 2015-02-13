package com.ogomonkey.manager;

import com.ogomonkey.security.entity.CryptInfoType;
import com.ogomonkey.security.entity.SaltInfo;

public interface CryptoManager {

    SaltInfo findSalt(String relatedEntityId, CryptInfoType infoType);
}
