package pl.bk.pizza.store.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

class BasicSpecification extends Specification{

    //TODO change to reactive mongo
    @Autowired
    private MongoTemplate mongo

    def setup(){
        def collections = mongo.getCollectionNames()
        for(String collection :collections){
            mongo.dropCollection(collection)
        }
    }

}
