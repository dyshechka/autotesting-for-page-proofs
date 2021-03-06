package ru.martha.autotesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.martha.autotesting.enums.RoleCode;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.util.Iterator;

import static ru.martha.autotesting.enums.RoleCode.*;

@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String USER_BY_LOGIN_QUERY =
            "SELECT `login`,`password`,`enabled` FROM `users_in_roles` WHERE `login`=?";
    private static final String AUTHORITIES_BY_LOGIN_QUERY =
            "SELECT `login`,`role` FROM `users_in_roles` WHERE `login`=?";

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(USER_BY_LOGIN_QUERY)
            .authoritiesByUsernameQuery(AUTHORITIES_BY_LOGIN_QUERY)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/projects/**")
                .hasAnyAuthority(TESTER.role(), JUNIOR_TESTER.role(), SENIOR_TESTER.role())
            .antMatchers("/accounts/**")
                .hasAnyAuthority(ADMINISTRATOR.role())
            .and()
                .formLogin()
                    .loginPage("/login.xhtml")
                    .loginProcessingUrl("/login")
                    .successHandler((request, response, auth) -> handleLogin(response, auth))
            .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler((request, response, auth) ->
                                                  response.sendRedirect("/login.xhtml"))
            .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied.xhtml");
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    private void handleLogin(HttpServletResponse response, Authentication auth) throws IOException {
        Iterator<? extends GrantedAuthority> iterator = auth.getAuthorities().iterator();
        if (!iterator.hasNext()) {
            response.sendRedirect("/login.xhtml?error");
        }
        GrantedAuthority authority = iterator.next();
        RoleCode userRoleCode = RoleCode.getByCode(Integer.parseInt(authority.getAuthority()));
        if (userRoleCode == ADMINISTRATOR) {
            response.sendRedirect("/accounts/accountsList.xhtml");
        } else {
            response.sendRedirect("/projects/projectsList.xhtml");
        }
    }
}
