package pl.bk.pizza.store.domain.report;

import com.google.common.io.CharSink;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.domain.exception.AppException;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;

import static com.google.common.io.FileWriteMode.APPEND;
import static com.google.common.io.Files.asCharSink;
import static com.google.common.io.Files.createParentDirs;
import static com.google.common.io.Files.touch;
import static io.reactivex.Flowable.fromIterable;
import static io.reactivex.Flowable.range;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.getProperty;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static pl.bk.pizza.store.domain.exception.ErrorCode.INTERNAL_ERROR;
import static pl.bk.pizza.store.domain.service.NowProvider.now;
import static reactor.core.publisher.Mono.from;

@Service
@Slf4j
@AllArgsConstructor
public class OrderReport
{
    private final ReactiveMongoOperations operations;
    
    public Mono<String> generateLastOrdersReport(Duration time)
    {
        final Flux<Order> orders = getAllLastOrders(time);
        
        final String homeDir = getProperty("user.home");
        final String now = now().toLocalDateTime().toString();
        
        final File file = createFile(homeDir, now);
        
        CharSink sink = asCharSink(file, defaultCharset(), APPEND);
        
        return from(fromIterable(orders.toIterable())
                        .zipWith(
                            range(1, MAX_VALUE),
                            (order, counter) -> counter + "." + "\n"
                                                + appendId(order.getId())
                                                + appendStatus(order.getOrderStatus())
                                                + appendUserEmail(order.getUserEmail())
                                                + appendTotalPrice(order.getTotalPrice())
                                )
                        .doOnNext(str -> writeToFile(str, sink))
                        .doOnComplete(() -> writeToFile("-----------END--------------", sink))
                        .doOnError(exception -> log.info("Error while generating order report: ", exception)));
    }
    
    private Flux<Order> getAllLastOrders(Duration time)
    {
        final long reportStartTime = now()
            .minus(Duration.ofMinutes(time.toMinutes()))
            .toEpochSecond();
        
        Query query = new Query().addCriteria(Criteria.where("orderDateTime").gt(reportStartTime));
        
        return operations.find(query, Order.class, "order");
    }
    
    private File createFile(String homeDir, String now)
    {
        final File file = new File(homeDir + "/Download/last-order-report-" + now + ".txt");
        
        try
        {
            createParentDirs(file);
            touch(file);
        }
        catch(IOException e)
        {
            log.info("Error while generating order report: ", e);
            throw new AppException("Error while generating order report.", INTERNAL_SERVER_ERROR, INTERNAL_ERROR);
        }
        return file;
    }
    
    private String appendUserEmail(String email)
    {
        return "User email: " + email + "\n";
    }
    
    private String appendStatus(OrderStatus status)
    {
        return "ProductStatus: " + status + "\n";
    }
    
    private String appendId(String id)
    {
        return "Id: " + id + "\n";
    }
    
    private String appendTotalPrice(BigDecimal price)
    {
        return "Total price: " + price + "\n\n";
    }
    
    private void writeToFile(String str, CharSink sink)
    {
        try
        {
            sink.write(str);
        }
        catch(IOException e)
        {
            log.debug("Error occurred during creating of report", e);
            
            throw new AppException(
                "Error occurred during creating of report",
                INTERNAL_SERVER_ERROR,
                INTERNAL_ERROR
            );
        }
    }
}
