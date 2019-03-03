package com.milekj.dbtest.entity;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@Table(name = "Authorities")
public class Authority {
    @EmbeddedId
    private AuthorityPK id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username", columnDefinition = "varchar(50)") //columnDefinition because of Hibernate bug
    private User user;                                               // https://hibernate.atlassian.net/browse/HHH-8536

    public Authority() {
    }

    public Authority(AuthorityPK id) {
        this.id = id;
    }

    public AuthorityPK getId() {
        return id;
    }

    public void setId(AuthorityPK id) {
        this.id = id;
    }

    @Embeddable
    public static class AuthorityPK implements Serializable {
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
    }
}
