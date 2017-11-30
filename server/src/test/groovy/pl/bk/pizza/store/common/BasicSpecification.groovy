package pl.bk.pizza.store.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.PATCH
import static org.springframework.http.HttpMethod.PATCH
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT


class BasicSpecification extends Specification{

    @Autowired
    private MongoTemplate mongo

    def setup(){
        def collections = mongo.getCollectionNames()
        for(String collection :collections){
            mongo.dropCollection(collection)
        }
    }

    @Autowired
    private TestRestTemplate restTemplate

    protected <T> ResponseEntity<T> get(String uri, Class<T> responseBodyType) {
        return sendRequest(uri, GET, null, responseBodyType)
    }

    protected <T> ResponseEntity<T> get(String uri, ParameterizedTypeReference<T> responseBodyType) {
        return sendRequest(uri, GET, null, responseBodyType)
    }

    protected ResponseEntity post(String uri, Object requestBody) {
        return sendRequest(uri, POST, requestBody, Object)
    }

    protected ResponseEntity put(String uri, Object requestBody) {
        return sendRequest(uri, PUT, requestBody, Object)
    }

    protected ResponseEntity patch(String uri) {
        return sendRequest(uri, PATCH, null, Object)
    }

    protected ResponseEntity patch(String uri, Object requestBody) {
        return sendRequest(uri, PATCH, requestBody, Object)
    }

    private <T> ResponseEntity<T> sendRequest(String uri, HttpMethod method, Object requestBody, Class<T> responseBodyType) {
        def entity = new HttpEntity<>(requestBody)
        return restTemplate.exchange(uri, method, entity, responseBodyType)
    }

    private <T> ResponseEntity<T> sendRequest(String uri, HttpMethod method, Object requestBody, ParameterizedTypeReference<T> responseBodyType) {
        def entity = new HttpEntity<>(requestBody)
        return restTemplate.exchange(uri, method, entity, responseBodyType)
    }
}
