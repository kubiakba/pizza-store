package pl.bk.pizza.store.infrastructure;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchlyConfig
{
    @Bean
    public JestClient jestClient()
    {
        JestClientFactory factory = new JestClientFactory();
        
        factory.setHttpClientConfig(new HttpClientConfig
            .Builder("https://site:96a05fde20818523ce03d9e6ac85a49b@gloin-eu-west-1.searchly.com")
                                        .multiThreaded(true)
                                        .defaultMaxTotalConnectionPerRoute(5)
                                        .maxTotalConnection(10)
                                        .build());
        return factory.getObject();
    }
    
}
