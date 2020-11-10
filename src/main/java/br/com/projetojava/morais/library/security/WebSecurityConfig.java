package br.com.projetojava.morais.library.security;


import br.com.projetojava.morais.library.util.Constantes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
/*@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private DataSource dataSource;*/

  /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER");

      //auth.jdbcAuthentication().dataSource(dataSource)
               //.passwordEncoder(passwordEncoder())
               //.usersByUsernameQuery("select `user_name` as matricula from login where `user_name` = ? and `pass_word` = ?")
               //.authoritiesByUsernameQuery("select `authority` as autoridade from login where `user_name` = ?");

       //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }*/


    /*@Autowired
    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
         builder.inMemoryAuthentication()
    }*/

    /*@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    /*@Bean
    public UserDetailsService userDetailsService() {
        return new LoginDetailsService(); // (1)
    }*/

    /*@Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Constantes.METODOS_PERMITIDOS);
        configuration.setAllowedHeaders(Constantes.HEADERS_PERMITIDOS);
        configuration.setExposedHeaders(Constantes.HEADERS_EXPOSTOS);
        configuration.setMaxAge(Constantes.MAX_AGE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        http.httpBasic()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}


   /*http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();*/

        /*
        .httpBasic()
                .and().cors()
                .and().csrf().disable();
         */


//http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();