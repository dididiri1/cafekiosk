package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.mail.MailService;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

//@Transactional
@RequiredArgsConstructor
@Service
public class OrderStatistService {

    private final OrderRepository orderRepository;

    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {

        System.out.println("=====");
        System.out.println(orderDate.atStartOfDay());
        System.out.println(orderDate.plusDays(1).atStartOfDay());
        System.out.println(OrderStatus.PAYMENT_COMPLETED);


        // 해당 일자에 결제완료된 주문을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED
        );

        System.out.println("orders.size() = " + orders.size());

        for (Order order : orders) {
            System.out.println("order.toString() = " + order.toString());
        }

        // 총 매출 합계를 계산하고
        int totalAmount = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        System.out.println("totalAmount = " + totalAmount);

        // 메일 전송
        boolean result = mailService.sendMail(
                "no-reply@cafekiosk.com",
                email,
                String.format("[매출통계] %s", orderDate),
                String.format("총 매출 합계는 %s원 입니다.", totalAmount)
        );

        if (!result) {
            throw new IllegalArgumentException("메출 통계 메일 전송에 실패했습니다.");
        }

        return true;
    }

}