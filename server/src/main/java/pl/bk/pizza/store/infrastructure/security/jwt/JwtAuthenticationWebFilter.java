package pl.bk.pizza.store.infrastructure.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter
{
    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager,
        UnauthorizedEntryPoint entryPoint, JwtServerExchangeConverter jwtServerExchangeConverter)
    {
        super(authenticationManager);
        
        setAuthenticationConverter(jwtServerExchangeConverter);
        setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        setRequiresAuthenticationMatcher(new JWTHeadersExchangeMatcher());
    }
    
    private static class JWTHeadersExchangeMatcher implements ServerWebExchangeMatcher
    {
        @Value("${jwt.header}")
        private String tokenHeader;
        
        @Override
        public Mono<MatchResult> matches(final ServerWebExchange exchange)
        {
            Mono<ServerHttpRequest> request = Mono.just(exchange).map(ServerWebExchange::getRequest);
            
            return request.map(ServerHttpRequest::getHeaders)
                          .filter(h -> h.containsKey("Authorization"))
                          .flatMap(header -> MatchResult.match())
                          .switchIfEmpty(MatchResult.notMatch());
        }
    }
    
}
