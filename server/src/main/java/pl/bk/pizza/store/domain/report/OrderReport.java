package pl.bk.pizza.store.domain.report;

import com.google.common.io.Files;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.service.NowProvider;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;

@Service
@Slf4j
@AllArgsConstructor
public class OrderReport
{
    private final OrderRepository orderRepository;
    
    //TODO change findAll to use time
    //TODO change Files.append implementation
    public void generateLastOrdersReport(Duration time) throws IOException
    {
        final Flux<Order> orders = orderRepository.findAll();
        final String homeDir = System.getProperty("user.home");
        final String now = NowProvider.now().toString();
        final File file = new File(homeDir + "/Download/last-order-report-" + now + ".txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        
        Flowable
            .fromIterable(orders.toIterable())
            .zipWith(
                Flowable.range(1, Integer.MAX_VALUE),
                (order, counter) -> counter + "." + "\n"
                                    + ("Id: " + order.getId() + "\n")
                                    + ("ProductStatus: " + order.getOrderStatus() + "\n")
                                    + ("User email: " + order.getUserEmail() + "\n")
                                    + ("Total price: " + order.getTotalPrice() + "\n" + "\n")
                    )
            .subscribe(
                str -> Files.append(str, file, Charset.defaultCharset()),
                exception -> log.info("Error while generating order report: " + exception.getMessage()),
                () -> Files.append("-----------END--------------", file, Charset.defaultCharset())
                      );
    }
}
