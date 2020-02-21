package com.livraria.livraria.util;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
*/
public enum CustomerRole {
    ADMIN("ROLE_ADMIN"),
    CLIENT("ROLE_USER");

    /*
    public static final GrantedAuthority ADM = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static final GrantedAuthority USER = new SimpleGrantedAuthority("ROLE_USER");
*/
    private String customerRole;
    CustomerRole(String role) {
        this.customerRole = role;
    }

    public String getUserRole() {
        return this.customerRole;
    }

    /*
    public GrantedAuthority toAuthotity(String role){
        switch(role){
            case "ROLE_ADMIN": return ADM;
            case "ROLE_USER": return USER;
        }
        return null;
    }
    */

}
