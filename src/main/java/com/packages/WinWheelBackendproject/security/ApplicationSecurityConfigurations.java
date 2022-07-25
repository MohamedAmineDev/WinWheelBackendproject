package com.packages.WinWheelBackendproject.security;

import com.packages.WinWheelBackendproject.enums.Roles;
import com.packages.WinWheelBackendproject.services.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/app/api/manage_user/user/check_user").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_user/user/username/{email}").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                //user/username/{email}
                .antMatchers(HttpMethod.POST, "/app/api/manage_admin/add_admin", "/app/api/manage_player/add_player").permitAll()
                .antMatchers(HttpMethod.GET, "/app/api/manage_admin/admins", "/app/api/manage_player/").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_user/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.PUT, "/app/api/manage_user/update_user/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                //Gift
                .antMatchers(HttpMethod.GET, "/app/api/manage_gift/gift/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.POST, "/app/api/manage_gift/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.PUT, "/app/api/manage_gift/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_gift/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                //Game
                .antMatchers(HttpMethod.GET, "/app/api/manage_game/game/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.POST, "/app/api/manage_game/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.PUT, "/app/api/manage_game/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_game/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_game/games").permitAll()
                //Selection
                //.antMatchers(HttpMethod.GET, "/app/api/manage_selection/game/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.POST, "/app/api/manage_selection/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.PUT, "/app/api/manage_selection/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_selection/admin/**").hasRole(Roles.ROLE_ADMIN.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_selection/selection/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                //Play the game
                .antMatchers(HttpMethod.POST, "/app/api/manage_playing/player/**").hasRole(Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.PUT, "/app/api/manage_playing/player/**").hasRole(Roles.ROLE_PLAYER.getPermission())
                .antMatchers(HttpMethod.GET, "/app/api/manage_playing/player/**").hasRole(Roles.ROLE_PLAYER.getPermission())
                //.antMatchers(HttpMethod.GET, "/app/api/manage_playing/player/**").hasAnyRole(Roles.ROLE_ADMIN.getPermission(), Roles.ROLE_PLAYER.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
