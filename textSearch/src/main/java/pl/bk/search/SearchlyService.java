package pl.bk.search;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SearchlyService
{
    @SneakyThrows
    public void addOrderDocument(JestClient client, Object source)
    {
        client.execute(new Index.Builder(source).index("order").type("realized").build());
    }
}
