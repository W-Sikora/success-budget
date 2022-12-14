package pl.wsikora.successbudget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import pl.wsikora.successbudget.common.currentuser.application.CurrentUserDetailsService;

import static pl.wsikora.successbudget.preference.interfaces.PreferenceConstants.AFTER_LOGIN_URL;


@Configuration
@EnableWebSecurity
class SecurityConfig {

    private static final String EMPLOYEE = "employee";
    private static final String MANAGER = "manager";
    private static final String OWNER = "owner";

    private static final String CSS = "/css/style.css";

    private final PasswordEncoder passwordEncoder;
    private final CurrentUserDetailsService currentUserDetailsService;

    SecurityConfig(PasswordEncoder passwordEncoder,
                   CurrentUserDetailsService currentUserDetailsService) {

        this.passwordEncoder = passwordEncoder;
        this.currentUserDetailsService = currentUserDetailsService;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(currentUserDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    HttpFirewall defaultHttpFirewall() {

        return new DefaultHttpFirewall();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .failureUrl("/login?invalid=true")
                .defaultSuccessUrl(AFTER_LOGIN_URL, true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/**").permitAll();
                    auth.requestMatchers("/static/css/style.css").permitAll();
                })
                .build();
    }
}
