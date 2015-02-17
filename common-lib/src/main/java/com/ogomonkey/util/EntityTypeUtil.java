package com.ogomonkey.util;

public final class EntityTypeUtil {

    public static String getEntityType(Object entity) {
        if (entity == null)
            return null;

        return (entity.getClass().getSimpleName());
    }
}
