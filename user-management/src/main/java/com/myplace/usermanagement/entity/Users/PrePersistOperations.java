package com.myplace.usermanagement.entity.Users;

import jakarta.persistence.PrePersist;

import java.sql.Timestamp;
import java.time.Instant;

public class PrePersistOperations {
    @PrePersist
    public void addDate(Object entity){
        if(entity instanceof  CreationTimeStamp x){
            if(x.getCreatedAt() == null){
                x.setCreatedAt(Timestamp.from(Instant.now()));
            }
        }
    }

}
