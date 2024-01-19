package com.emerson.propertyservice.Repositories;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
@Component
public class PrePostJpaOperations {
    /*
    @PrePersist: Invoked before an entity is saved (persisted) for the first time.
    @PostPersist: Invoked after an entity has been saved (persisted) for the first time.
    @PreUpdate: Invoked before an entity is updated.
    @PostUpdate: Invoked after an entity has been updated.
    @PreRemove: Invoked before an entity is removed (deleted).
    @PostRemove: Invoked after an entity has been removed (deleted).
    @PostLoad: Invoked after an entity has been loaded from the database.
     */
    @PrePersist
    public void setDataForEntity(Object obj){
        if(obj instanceof CreationStamp x){
            if(x.getCreationStamp() == null){
                x.setCreationStamp(Timestamp.from(Instant.now()));
            }
        }
    }
    @PreUpdate
    public void setLastUpdateForListing(Object obj){
        if(obj instanceof LastUpdate x){
            if(x.getLastUpdate() == null){
                x.setLastUpdate(Timestamp.from(Instant.now()));
            }
        }
    }
}
