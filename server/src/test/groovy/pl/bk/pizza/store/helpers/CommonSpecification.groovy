package pl.bk.pizza.store.helpers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf

@AutoConfigureWebTestClient(timeout = "300000")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@WithMockUser(roles = "ADMIN")
class CommonSpecification extends Specification implements OrderHelper, ProductHelper, UserHelper
{
    @Autowired
    protected WebTestClient client

    @Autowired
    ReactiveMongoOperations operations

    def cleanup()
    {
        operations.getCollectionNames()
                  .flatMap { name -> operations.getCollection(name).drop() }
                  .then()
                  .block()
    }

    @Override
    WebTestClient getClient()
    {
        return client.mutateWith(csrf())
    }
}
