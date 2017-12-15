package pl.bk.pizza.store.domain.report;

import com.google.common.io.Files;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.service.NowProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

import io.reactivex.Flowable;

@Service
@Slf4j
public class OrderReport {

    private final OrderRepository orderRepository;

    public OrderReport(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void generateLastOrdersReport(Duration time) throws IOException {
        List<Order> orders = orderRepository.findLastOrders(time);
        final String homeDir = System.getProperty("user.home");
        final String now = NowProvider.now().toString();
        final File file = new File(homeDir + "/Download/last-order-report-" + now + ".txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Flowable
            .fromIterable(orders)
            .zipWith(Flowable.range(1, Integer.MAX_VALUE),
                (order, counter) -> counter + "." + "\n"
                                        + ("Id: " + order.getId() + "\n")
                                        + ("Status: " + order.getStatus() + "\n")
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
