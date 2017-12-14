package maxb.pro.simpleblogger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // using in memory authentication
                .inMemoryAuthentication()
                // adding Administrator user
                .withUser("admin").password("admin").authorities("ADMIN", "USER")
                // using add() method for adding new user
                .and()
                // adding a user without Administrator authority
                .withUser("user").password("user").authorities("USER");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("/", "/vendor/**", "/css/**", "/js/**", "/img/**", "/static/**", "/index.html", "/app/**", "/register", "/authenticate", "/favicon.ico");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // starts authorizing configurations
//                .authorizeRequests()
//                // authenticate all remaining URLS
//                .anyRequest().fullyAuthenticated().and()
//                // adding JWT filter
//                //.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
//                // enabling the basic authentication
//                .httpBasic().and()
//                // configuring the session as state less. Which means there is
//                // no session in the server
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // disabling the CSRF - Cross Site Request Forgery
//                .csrf().disable();
    }


}
