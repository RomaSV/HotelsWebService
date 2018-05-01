package web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private ConfigAuthenticationProvider configAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hotels/**").fullyAuthenticated()
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(configAuthenticationProvider);
        auth.inMemoryAuthentication()
                .withUser("ADMIN").password("SUPER_SECRET_PASSWORD").roles("MANAGER");
    }
}
