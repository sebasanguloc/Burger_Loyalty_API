package org.api.burger_loyalty_api.config.dev;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.config.AngusBurgerLoyaltyService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class AngusBurgerUsernameAuthenticationDevProvider implements AuthenticationProvider {

    private final AngusBurgerLoyaltyService angusBurgerLoyaltyService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = angusBurgerLoyaltyService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
