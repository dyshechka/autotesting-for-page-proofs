package ru.martha.autotesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/login.xhtml");

        http.authorizeRequests()
            .antMatchers("/index.xhtml")
            .hasRole("TESTER")
            .and()
            .formLogin()
            .loginPage("/login.xhtml")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/index.xhtml")
            .and().exceptionHandling().accessDeniedPage("/denied.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("tester")
            .password("tester")
            .roles("TESTER");
    }
}
