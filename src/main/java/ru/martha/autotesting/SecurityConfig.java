package ru.martha.autotesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/**")
                .denyAll()
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/processLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/webdriver-sample.xhtml")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tester").password("123456").roles("TESTER");
    }
}
