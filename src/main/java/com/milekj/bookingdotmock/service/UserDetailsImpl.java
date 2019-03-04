package com.milekj.bookingdotmock.service;

import com.milekj.bookingdotmock.entity.User;
import com.milekj.bookingdotmock.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {
    private User user;
    private List<GrantedAuthorityImpl> authorities;

    public UserDetailsImpl(User user, List<UserRole> roles) {
        this.user = user;
        authorities = convertToAuthorities(roles);

    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class GrantedAuthorityImpl implements GrantedAuthority {
        private String authority;

        public GrantedAuthorityImpl(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }

    private static List<GrantedAuthorityImpl> convertToAuthorities(List<UserRole> roles){
        return roles
                .stream()
                .map(a -> a.getId().getAuthority())
                .map(GrantedAuthorityImpl::new)
                .collect(Collectors.toList());
    }
}
