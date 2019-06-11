package com.milekj.bookingdotmock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity()
@PropertySource("authorities.properties")
@EnableSpringDataWebSupport
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private UserDetailsService userDetailsService;
    @Value("${authorities.owner}")
    private String OWNER_AUTHORITY;

    @Value("${authorities.customer}")
    private String CUSTOMER_AUTHORITY;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .and()
            .logout()
                .and()
            .authorizeRequests()
                .antMatchers("/hotels/*", "/rooms/*").hasAuthority(OWNER_AUTHORITY)
                .antMatchers("/bookings/add", "/search").hasAuthority(CUSTOMER_AUTHORITY)
                .anyRequest().permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    @Qualifier("dbUserDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
