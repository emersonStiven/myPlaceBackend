package com.myplace.usermanagement.entity.EnumClasses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    HOST_READ("host:read"),
    HOST_UPDATE("host:update"),
    HOST_CREATE("host:create"),
    HOST_DELETE("host:delete");
    @Getter
    private final String permission;
}
