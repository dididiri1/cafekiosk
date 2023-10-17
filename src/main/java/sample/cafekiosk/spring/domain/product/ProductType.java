package sample.cafekiosk.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    HANDADE("제조 음료"), BTTLE("병음료"), BAKERY("베이커리");

    private final String text;
}
