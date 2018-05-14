package pl.bk.pizza.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*
@EnableWebSecurity
@Configuration*/
@Profile("no-security")
public class TestSecurity /*extends WebSecurityConfigurerAdapter*/
{
    
    //TODO to implement with webflux
    /*@Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
            .authorizeRequests().antMatchers("/").permitAll().and().csrf().disable();
    }*/
}
