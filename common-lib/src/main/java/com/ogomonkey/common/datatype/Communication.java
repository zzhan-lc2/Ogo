package com.ogomonkey.common.datatype;

import java.util.Date;
import java.util.Set;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

@Data
public class Communication {
    private CommType commType;
    private String category;
    private String commNumber;
    private boolean encrypted; // apply for the field "commNumber"
    private Set<String> tags;
    private EntityStatus status;
    private Date statusDate;
    private String notes;

    static final String TAG_SEPARATOR = ";";

    public void setTagsStr(String tagsStr) {
        if (StringUtils.isNotEmpty(tagsStr)) {
            tags = Sets.newHashSet(StringUtils.split(tagsStr, TAG_SEPARATOR));
        } else {
            tags = Sets.newHashSet();
        }
    }

    public String getTagsStr() {
        if (tags != null) {
            return StringUtils.join(tags, TAG_SEPARATOR);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public void addTag(String tag) {
        if (null == tags) {
            tags = Sets.newHashSet();
        }
        tags.add(tag);
    }
}
