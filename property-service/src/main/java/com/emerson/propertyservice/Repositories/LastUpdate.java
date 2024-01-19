package com.emerson.propertyservice.Repositories;

import java.util.Date;

public interface LastUpdate {
    void setLastUpdate(Date lastUpdate);
    Date getLastUpdate();
}
