package com.milekj.bookingdotmock.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @EmbeddedId
    private UserRolePK id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username", columnDefinition = "varchar(50)") //columnDefinition because of Hibernate bug
    private User user;                                               //https://hibernate.atlassian.net/browse/HHH-8536

    public UserRole() {
    }

    public UserRole(UserRolePK id) {
        this.id = id;
    }

    public UserRolePK getId() {
        return id;
    }

    public void setId(UserRolePK id) {
        this.id = id;
    }

    @Embeddable
    public static class UserRolePK implements Serializable {
        @Column(name = "username", length = 50)
        private String username;

        @Column(length = 50)
        private String authority;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other)
                return true;
            if (!(other instanceof UserRolePK))
                return false;
            UserRolePK that = (UserRolePK) other;
            return Objects.equals(username, that.username) &&
                    Objects.equals(authority, that.authority);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, authority);
        }
    }
}
