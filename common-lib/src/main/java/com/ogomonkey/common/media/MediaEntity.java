package com.ogomonkey.common.media;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.datatype.FileRepositoryPath;
import com.ogomonkey.common.entity.AuditableEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class MediaEntity extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private MediaType mediaType;
    private String detailFormat; // such as: PNG/JPG/GIF for image;
    private FileRepositoryPath fileLocation;
    private Quality quality;
    private Long widthPixel;
    private Long heightPixel;
    private Long videoSeconds;
    private String description;
    private Boolean expired;
    private Date expirationDate;
}
