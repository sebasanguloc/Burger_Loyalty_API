package org.api.burger_loyalty_api.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvent {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent){
        log.info("Success authentication for user: " +
                successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent){
        log.info("Fail authentication for user: " +
                failureEvent.getAuthentication().getName());
    }
}
