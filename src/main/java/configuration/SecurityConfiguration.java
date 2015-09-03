package configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by Pikachu on 8/23/15.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    Environment env;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("client").password("client").roles("USER").and()
//                .withUser("admin").password("admin").roles("USER", "ADMIN");
        auth.jdbcAuthentication() // We override the default queries to custom match the login query with our tables
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name, password, true from springtest.identity where user_name = ?")
                .authoritiesByUsernameQuery("select user_name, role from springtest.identity where user_name=?") // Role stored in database must be "ROLE_USER" and "ROLE_ADMIN"
                .passwordEncoder(new StandardPasswordEncoder(env.getProperty("encoder.key")));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Note csrf() must be disabled if we don't provide it in logout page or any methods that post
        httpSecurity
                .formLogin()
                    .loginPage("/login")
                .and()
                .authorizeRequests()
                    .antMatchers("/greeting").authenticated()
                    .antMatchers("/addNewUser").hasRole("ADMIN") // Need to specify redirection after failure, otherwise the user is still logged in
                    .antMatchers("/adminLogin").hasRole("ADMIN")
                    .antMatchers("/userLogin").authenticated()
                    .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/fuck").requiresSecure()
                .and()
                .httpBasic().disable() // To login http://userName:password@localhost:8080/greeting. Note it's impossible for the client to logout.
                .csrf().disable();
    }


}
