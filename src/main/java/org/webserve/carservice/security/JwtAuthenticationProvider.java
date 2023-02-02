package org.webserve.carservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtHelper jwtHelper;

    @Autowired
    public JwtAuthenticationProvider(JwtHelper jwtHelper){
        this.jwtHelper=jwtHelper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        BearerTokenAuthentication bearerTokenAuthentication = (BearerTokenAuthentication) authentication;

        String token = bearerTokenAuthentication.getToken().getTokenValue();

        if(jwtHelper.validateAccessToken(token)){
            bearerTokenAuthentication.setAuthenticated(true);
        }
        return bearerTokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(BearerTokenAuthentication.class);
    }
}
