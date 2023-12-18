package edu.festu.ivankuznetsov.securitydemo.security;

import edu.festu.ivankuznetsov.securitydemo.entity.Role;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private final Role role;

    public Authority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}

