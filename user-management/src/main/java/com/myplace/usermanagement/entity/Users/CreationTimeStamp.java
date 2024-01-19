package com.myplace.usermanagement.entity.Users;

import java.sql.Timestamp;
import java.util.Date;

public interface CreationTimeStamp {
    void setCreatedAt(Timestamp d);
    Date getCreatedAt();
}
