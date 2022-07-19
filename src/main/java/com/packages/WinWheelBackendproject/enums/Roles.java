package com.packages.WinWheelBackendproject.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_ADMIN("ADMIN"),
    ROLE_PLAYER("PLAYER");

    private String permission;

    Roles(String p) {
        this.permission = p;
    }
}
