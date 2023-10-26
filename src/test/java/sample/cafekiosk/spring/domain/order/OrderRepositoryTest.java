package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @DisplayName("해당 일자에 결제완료된 주문들을 조회한다.")
    @Test
    void findOrdersBy() throws Exception {
        //given
        LocalDateTime registeredDateTime = LocalDateTime.now();

        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        Order order = Order.create(products, registeredDateTime);

        //when


        //then
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }
}