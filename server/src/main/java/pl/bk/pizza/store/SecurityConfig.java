package pl.bk.pizza.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.bk.pizza.store.domain.service.UserDetailsService;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/products")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/products/*")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/orders/{\\d+}/delivered")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/{\\d+}/bonus")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/users/*")
                .hasRole("ADMIN")
                .antMatchers("/*")
                .permitAll()
            .and()
            .formLogin()
            .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
