package com.motospec.entity;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;
    
    public String getValue() {
        return this.name();
    }
    
    public static Role fromString(String role) {
        if (role == null || role.isBlank()) {
            return ROLE_USER;
        }
        
        // Adicionar ROLE_ se não tiver
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ROLE_USER;
        }
    }
}
