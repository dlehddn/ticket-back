package ticketing.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import ticketing.ticket.common.slack.RequestStorage;

@Configuration
public class RequestCachingConfig {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestStorage requestStorage() {
        return new RequestStorage();
    }

}
