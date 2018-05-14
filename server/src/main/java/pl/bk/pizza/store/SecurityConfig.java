package pl.bk.pizza.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*@EnableWebSecurity
@Configuration*/
@Profile("dev")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/
{
    
    //TODO to implement with webflux
  /*  @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/products")
            .hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/products/*")
            .hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/orders/{\\d+}/delivered")
            .hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/users/{\\d+}/bonus")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/users/*")
            .hasRole("ADMIN")
            .antMatchers("/*")
            .permitAll()
            .and()
            .formLogin()
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }*/
    
}
