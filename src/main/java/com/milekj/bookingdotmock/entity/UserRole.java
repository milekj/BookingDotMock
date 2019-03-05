package com.milekj.bookingdotmock.entity;

import javax.persistence.*;

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

    public void addUser(User user) {
        this.user = user;
        user.addToRoles(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRolePK getId() {
        return id;
    }

    public void setId(UserRolePK id) {
        this.id = id;
    }

}
