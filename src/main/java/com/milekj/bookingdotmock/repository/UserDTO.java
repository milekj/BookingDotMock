package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.validation.EmailUnique;
import com.milekj.bookingdotmock.validation.UsernameUnique;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDTO {
    @UsernameUnique
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Password confirmation is required")
    private String passwordConfirmation;

    @Email
    @EmailUnique
    @NotEmpty(message = "Email is required")
    private String email;

    @AssertTrue(message = "Passwords don\'t match")
    private boolean passwordsMatch;

    public void setPassword(String password) {
        this.password = password;
        synchPasswordsMatch();
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
        synchPasswordsMatch();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPasswordsMatch() {
        return passwordsMatch;
    }

    private void synchPasswordsMatch() {
        passwordsMatch = password.equals(passwordConfirmation);
    }
}
