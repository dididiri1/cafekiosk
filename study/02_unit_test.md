# Practical Testing: 실용적인 테스트



## 섹션 1 테스트는 왜 필요할까?

### 요구사항

- 주문 목록에 음료 추가/삭제 기능
- 주문 목록 전체 지우기
- 주문 목록 총 금액 계산하기
- 주문 생성하기

## 섹션 2 단위 테스트


### 요구사항

- 가게 운영 시간(10:00 ~ 22:00) 외에는 주문을 생성할 수 없다.

### 테스트 하기 어려운 영역

- 관측할 떄마다 다른 값에 의존하는 코드
  - 현재 날짜/시간, 랜덤 값, 전연 변수/함수, 사용자 입력 등
  
- 외부 세계에 영향을 주는 코드
  - 표준 출력, 메세지 발송, 데이터베이스에 기록하기 등

### 순수함수(pure functions)

- 같은 입력에는 항상 같은 결과
- 외부 세상과 단절된 형태
- 테스트하기 쉬운 코드

### 키워드 정리

- 단위 테스트

- 수동 테스트, 자동화 테스트

- Junit5, AssertJ

- 해피 케이스, 예외 케이스

- 경계값 테스트 - 범위(이상, 이하, 초과, 미만), 구간, 날짜 등

- 테스트하기 쉬운/어려운 영역(순수함수)

## 섹션 3 TDD: Test Driven Development

### TDD: Test Driven Development
- 프로덕션 코드보다 테스트 코드를 먼저 작성하여 테스트가 구현 과정을 주도하도록 하는 방법론

- RED: 실패하는 테스트 작성
- GREEN: 테스트 통과 최소한의 코딩
- REFACTOR: 구현 코드 개선 테스트 통과 유지

![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/02_01.gif?raw=true)

### 선 기능 구현, 후 테스트 작성

- 테스트 자체의 누락 가능성
- 특정 테스트(해피 케이스) 케이스만 검증할 가능성
- 잘못된 구현을 다소 늦게 발견할 가능성
- 과감한 리펙토링이 가능해진다.

### 선 테스트 작성, 후 기능 구현

- 복잡도가 낮은, 테스트 가능한 코드로 구현할 수 있게 한다.
- 쉽게 발견하기 어려운 엣지(Edge) 케이스를 놓치지 않게 해준다.
- 구현에 대한 빠른 피드백을 받을 수 있다.
- 과감한 리팩토링이 가능해진다.


### TDD: 관점의 변화

![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/03_01.png?raw=true)

**클라이언트** 관점에서의 **피드백**을 주는 Test Driven

## 섹션 4 테스트는 문서다.

### 문서?

- 프로덕션 기능을 설명하는 테스트 코드 문서
- 다양한 테스트 케이스를 통해 프로덕션 코드를 이해하는 시각과 관점을 보완
- 어느 한 사람이 과거에 경험했던 고민의 결과물을 팀 차원으로 승격시켜서, 모두의 자산으로 공유할 수 있다.
- 

### DisplayName 설정

``` java
class CafeKioskTest {

    @DisplayName("음료 1개 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages()).size().isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");

    }
}
```

![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/04_01.png?raw=true)

### DisplayName을 섬세하게

- 명사의 나열보다 문장으로 - A이면 B이다. A이면 B가 아니고 C다
> → 음료 1개 추가 테스트  
> → **음료를 1개 추가할 수 있다.**

- 테스트 행위에 대한 결과까지 기술하기
> → 음료를 1개 추가할 수 있다.  
> → **음료를 1개 추가하면 주문 목록에 담긴다.**

- 도메인 용어를 사용하여 한층 추상화된 내용을 담기 (메서드 자체의 관점보다 도메인 정책 관점으로)
- 테스트의 현상을 중점으로 기술하지 말 것
> → 특정 시간 이전에 주문을 생성하면 실패한다.  
> → **영업 시작 시간 이전에는 주문을 생성할 수 없다.**

### BDD (Behavior Driven Development)

- TDD에서 파생된 개발 방법
- 함수 단위의 테스트에 집중하기보다, 시나리오에 기반한 테스트케이스(TC) 자체에 집중하여 테스트한다.
- 개발자가 아닌 사람이 봐도 이해할 수 있을 정도의 추상화 수준(레벨)을 권장

### Given / When / Then

- Given : 시나리오 진행에 필요한 모든 준비 과정(객체, 값, 상태 등)
  - 어떤 환경에서
- When : 시나리오 행동 진행
  - 어떤 행동을 진행했을 때
- Then : 시나리오 진행에 대한 결과 명시, 검증
  - 어떤 상태 변화가 일어난다.

## 섹션 5 Spring & JPA 기반 테스트

### 통합 테스트
- 여러 모듈이 헙력하는 기능을 통합적으로 검증하는 테스트
- 일반적으로 작은 범위의 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.
- 풍부한 단위 테스트 & 큰 기능 단위를 검증하는 통합 테스트

### ORM (Object-Relational Mapping)

- 객체 지향 패러다임과 관계형 DB 패러다임의 불일치
- 이전에는 개발자가 객체의 데이터를 한딴한땀 매핑하여 DB에 저장 및 조회
- ORM을 사용함으로써 개발자는 단순 작업을 줄이고, 비즈니스 로직에 집중할 수 있다.

### JPA (Java Persitence API)
- Java 진영의 ORM 기술 표준
- 인터페이스이고, 여러 구현체가 있지만 보통 HIberante를 많이 사용한다.
- 반복정인 CRUD SQL을 생성 및 실행해주고, 여러 부가 기능들을 제공한다.
- 편리하지만 쿼리를 직접 작성하지 않기 때문에, 어떤 식으로 쿼리가 만들어지고 실행되는지 명확하게 이해하고 있어야 한다.

- Spring 진여에서는 JPA를 한번 더 추상화한 Spring Data JPA 제공
- QueryDSL과 조합하여 많이 사용한다.(타입체크, 동적쿼리)
- @Entity, @Id, @Column
- @ManyToOne, @OneToMany, @OneToOne, @ManyToMany

### 요구사항
- 키오스크 주문을 위한 상품 후보 리스트 조회하기
- 상품의 판매 상태: 판매중, 판매보류, 판매중지 ->  판매중, 판매보류인 상태의 상품을 화면에 보여준다.
- id, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격


#### application.yml
``` yml
spring:
  profiles:
    default: local

  datasource:
    url: jdbc:h2:mem:~/cafeKioskApplication
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: none

---
spring:
  config:
    activate:
      on-profile: local

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    defer-datasource-initialization: true # (2.5~) Hibernate 초기화 이후 data.sql 실행

  h2:
    console:
      enabled: true

---
spring:
  config:
    activate:
      on-profile: test

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        format_sql: true

  sql:
    init:
      mode: never
``` 

### defer-datasource-initialization 옵션
- data.sql 쿼리를 Hibernate가 시작할때 자동으로 생성해줌.

#### data.sql
``` sql
insert into product(product_number, type, selling_status, name, price)
values ('001', 'HANDMADE', 'SELLING', '아메리카노', 4000),
       ('002', 'HANDMADE', 'HOLD', '카페라떼', 4500),
       ('003', 'BAKERY', 'STOP_SELLING', '크루아상', 3500);
``` 

``` java
package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest // JPA 전용
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() throws Exception {
        //given
        Product product1 = Product.builder()
                .productNumber("001")
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        Product product2 = Product.builder()
                .productNumber("002")
                .type(HANDMADE)
                .sellingStatus(HOLD)
                .name("카페라떼")
                .price(4500)
                .build();

        Product product3 = Product.builder()
                .productNumber("003")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .name("팥빙수")
                .price(7000)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));


        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );

    }
}
``` 

### 리스트 테스트 방법
- 리스트 사이즈 검증 - hasSize()
- 필드 이름 검증 - extracting()
- 필드 값 검증 - containsExactlyInAnyOrder(tuple(), tuple(), ..)


![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/05_01.png?raw=true)

### Persistence Layer(퍼시스턴스)
- Data Access의 역활
- 비즈니스 가공 로직이 포함되어서는 안 된다.
- Data에 대한 CRUD에만 집중한 레이어

### Business Layer(비즈니스)
- 비즈니스 로직을 구현하는 역할
- Persistence Layer와의 상호작용(Data를 읽고 쓰는 행위)을 통해 비즈니스 로직을 전개시킨다.
- **트랜잭션**을 보장해야 한다.

### 요구사항 1
- 상품 번호 리스트를 받아 주문 생성하기
- 주문은 주문 상태, 주문 등록 시간을 가진다.
- 주문의 총 금액을 계산할 수 있어야 한다.

### 요구사항 2
- 주문 생성 시 재고 확인 및 개수 차감 후 생성하기
- 재고는 상품번호를 가진다.
- 재고와 관련 있는 상품 타입은 병 음료, 베이커리이다.

### Presentation Layer(프레젠테이션)

- 외부 세계의 요청을 가장 먼저 받는 계층
- 파라미터에 대한 최소한의 검증을 수행한다.

### MockMvc
- Mock(가짜, 대여) 객체를 사용해
  - 스프링 MVC 동작을 재현할 수 있는 테스트 프레임워크
  - 실제 객체를 만들어서 테스트하기 어려운 경우 가짜 객체를 만들어서 테스트하는 기술

### 요구사항 3

![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/05_02.png?raw=true)

- 관리자 페이지에서 신규 상품을 등록할 수 있다.
- 상품명, 상품 타입, 판매 상태, 가격 등을 입력받는다.


### @Transactional(readOnly = true)

- readOnly = ture : 읽기전용
- CRUD 에서 CUD 동작 x / only Read
- JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
- CQRS - Command / Query 용으로 서비스로 분리를 하자
- Master DB(쓰기용), Slave DB(읽기용) readOnly 값으로 구분해서 나눌수 있음
- createProduct(command, CUD) @Transactional(readOnly = false)
- getSellingProducts(Query, R) @Transactional(readOnly = true)
- Service에 전체적으로 @Transactional(readOnly = true) 걸고 CUD 메소드에 따로 설정


``` java
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product saveProduct = productRepository.save(product);

        return ProductResponse.of(saveProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }
}
``` 

### @WebMvcTest
- Controller 빈들만 주입 받아서 사용하는 가벼운 테스트
``` java
package sample.cafekiosk.spring.api.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 주입 O
    
    @Autowired
    private ProductController productController; // 주입 O
    
    @Autowired
    private ProductService productService; // 주입 X
    
    @Autowired
    private ProductRepository productRepository; // 주입 X
    
}
``` 

### @MockBean 
- @MockBean 사용하면 service, repository 주입 가능 
``` java
package sample.cafekiosk.spring.api.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 주입 O
    
    @Autowired
    private ProductController productController; // 주입 O
    
    @MockBean
    private ProductService productService; // 주입 O
    
    @MockBean
    private ProductRepository productRepository; // 주입 O
    
}
``` 

### JpaAuditing 
- 생성일, 수정일 자동 시간을 매핑해서 넣어줌.

- 1. @EntityListeners(AuditingEntityListener.class) 어노테이션 추가
``` java
package sample.cafekiosk.spring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

}

``` 

- 2. @EnableJpaAuditing 어노테이션 추가(자주 까먹을수도 있음)
``` java
package sample.cafekiosk.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CafekioskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafekioskApplication.class, args);
    }

}

``` 

### 참고
> MockMvc 테스트시 @WebMvcTest JPA 빈들을 주입받지 않기 때문에 문제가 발생한다.  
> 따라서 config로 분리해서 문제를 해결해야된다.
``` java
package sample.cafekiosk.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JapAuditingConfig {

}
``` 

### @NotNull, @NotEmpty, @NotBlank
- @NotNull는 null만 허용하지 않고 ""이나 " "은 허용됨
- @NotEmpty는 null, "", 허용하지 않지만 " "은 허용됨
- @NotBlank는 null, "", " " 모두 허용하지 않습니다.

> 참고: String 타입은 @NotBlank, enum 타입은 @NotNull, List 타입은 @NotEmpty 사용하자.
> 
