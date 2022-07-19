package com.packages.WinWheelBackendproject.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PLAYER("ROLE_ADMIN");

    private String permission;

    Roles(String p) {
        this.permission = p;
    }
}
