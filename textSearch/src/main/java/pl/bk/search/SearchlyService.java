package pl.bk.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.OrderDTO;

import java.util.List;
import java.util.StringJoiner;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Component
@AllArgsConstructor
public class SearchlyService
{
    private final ObjectMapper mapper;
    
    @SneakyThrows
    public void addOrder(JestClient client, OrderDTO source)
    {
        final Index doc = new Index.Builder(mapper.writeValueAsString(source))
            .index("order")
            .type("realized")
            .build();
        
        client.execute(doc);
    }
    
    @SneakyThrows
    public List<OrderDTO> findOrdersByCity(JestClient client, String city)
    {
        final SearchSourceBuilder query = new SearchSourceBuilder()
            .query(matchQuery("deliveryInfoDTO.addressDTO.city", city));
        
        final Search search = new Search
            .Builder(query.toString())
            .addIndex("order")
            .addType("realized")
            .build();
        
        final SearchResult result = client.execute(search);
        
        final String ordersListInJson = parseJsonToList(result.getSourceAsString());
        
        return mapper.readValue(ordersListInJson, mapper.getTypeFactory().constructCollectionType(List.class, OrderDTO.class));
    }
    
    private String parseJsonToList(String sourceAsString)
    {
        final StringJoiner stringJoiner = new StringJoiner("", "[", "]");
        return stringJoiner.add(sourceAsString).toString();
    }
}
