package matywaky.com.github.springshop.service.custom;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(GrantedAuthority::getAuthority).findFirst();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        if (roles.isPresent()) {
            response.addCookie(
                    new Cookie("user", customUserDetails.getUsername()));
            response.addCookie(
                    new Cookie("status", "logged"));
            response.addCookie(
                    new Cookie("role", roles.get()));
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/sign-in?error=unknownRole");
        }
    }

}
