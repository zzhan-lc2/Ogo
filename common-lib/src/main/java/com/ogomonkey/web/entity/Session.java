package com.ogomonkey.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Session {
    private String id;
    private String previousId;
    private String userId;
    private String type; //
    private boolean robot;
    private boolean expired;
    private String ip;
    private String userAgent;
    private String deviceId;
    private Date creationDate;
    private Date lastAccessDateTime; // date + time (to second)
    private Date lastAccessDate; // year/month/date only -- will be used as partition
}
