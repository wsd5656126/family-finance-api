package com.wusd.familyfinanceapi.security;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
public class MyGrantedAuthority implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
