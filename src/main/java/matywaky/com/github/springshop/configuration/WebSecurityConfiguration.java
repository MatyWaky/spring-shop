package matywaky.com.github.springshop.configuration;

import matywaky.com.github.springshop.service.custom.CustomFailureHandler;
import matywaky.com.github.springshop.service.custom.CustomSuccessHandler;
import matywaky.com.github.springshop.service.custom.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomSuccessHandler customSuccessHandler;

    private final CustomFailureHandler customFailureHandler;

    public WebSecurityConfiguration(CustomUserDetailsService customUserDetailsService,
                                    CustomSuccessHandler customSuccessHandler,
                                    CustomFailureHandler customFailureHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customSuccessHandler = customSuccessHandler;
        this.customFailureHandler = customFailureHandler;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/admin")
                        .hasAuthority("ADMIN").requestMatchers("/settings").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("", "/", "/css/**", "/images/**", "/signup", "/sign-in").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form.loginPage("/sign-in").loginProcessingUrl("/sign-in")
                        .successHandler(customSuccessHandler).failureHandler(customFailureHandler).permitAll())

                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .deleteCookies("user", "status", "role").invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/sign-out"))
                        .logoutSuccessUrl("/sign-in?logout").permitAll());

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}