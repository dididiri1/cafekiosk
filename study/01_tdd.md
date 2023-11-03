# Practical Testing: 실용적인 테스트

## 섹션 1 테스트는 왜 필요할까?

### 요구사항

- 주문 목록에 음료 추가/삭제 기능
- 주문 목록 전체 지우기
- 주문 목록 총 금액 계산하기
- 주문 생성하기

### 올바른 테스트 코드는
- 자동화 테스트로 비교적 빠른 시간 안에 버그를 발견할 수 있고,  
  수동 테스트에 드는 비용을 크게 절약할 수 있다.
- 소프트웨어의 빠른 변화를 지원한다.
- 팀원들의 집단 지성을 팀 차원의 이익으로 승격시킨다.
- 가까이 보면 느리지만, 멀리 보면 가장 빠르다.

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

### 키워드 정리
- Layered Architecture
- Hexagonal Architecture
- 단위 테스트 VS. 통합 테스트
- IoC, DI, AOP
- ORM, 패러다임의 불일치, Hibernate
- Spring Data JPA
- QueryDSL - 타입체크, 동적쿼리
  - 장점 : 빌더 패턴, 컴파일 단계에서 에러가 나면 잡을수 있고, 동적쿼리(where 조건)

### 키워드 정리
- @SpringBootTest VS. @DataJpaTest
- @SpringBootTest VS. @WebMvcTest
- @Transactional (readOnly = true)
- Optimistic Lock(낙관적 락), Pessimistic Lock(비관적 락)
- CQRS

### 키워드 정리
- @WebMvcTest
- ObjectMapper
- @RestControllerAdvice, @ExceptionHandler
- Spring bean validation
- @NotNull, @NotEmpty, @NotBlank, …
- Mock, Mockito, @MockBean

## 섹션 6 Mock을 마주하는 자세

![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/06_01.png?raw=true)

### JPQL
``` java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.registeredDateTime >= :startDateTime" +
            " and o.registeredDateTime < :endDateTime" +
            " and o.orderStatus = :orderStatus")
    List<Order> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime, OrderStatus orderStatus);

}
``` 

### Native Query
``` java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o where o.registeredDateTime >= :startDateTime" +
            " and o.registeredDateTime < :endDateTime" +
            " and o.orderStatus = :orderStatus", nativeQuery = true)
    List<Order> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime, OrderStatus orderStatus);
    
}
``` 

### Stubbing(스터빙)
- Mock객체의 when 메소드를 이용한 stubbing 사용법
``` java
Mockito.when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
```
|        메소드         |                    기능                     |
|:------------------:|:-----------------------------------------:|
|     thenReturn     |      스터빙한 메소드 호출 후 어떤 객체를 리턴할 건지 정의       |
|     thenThrow      | 스터빙한 메소드 호출 후 어떤 Exception을 Throw할 건지 정의  |
| thenCallRealMethod |                 실제 메소드 호출                 |

> 참고 : OrderStatistService.java 메일 전송 로직에는 @Transactional 안하는게 좋음   
>       이메일 전송 같이 긴 네트워크 작업이 있는 로직은 불필요함.

## Test Double
### Dummy 
- 아무 것도 하지 않는 깡통 객체

### Fake
- 단순한 형태로 동일한 기능은 수행하나, 프로덕션에서 쓰기에는 부족한 객체

### Spy
- Stub이면서 호출된 내용을 기록하여 보여줄 수 있는 객체
- 일부는 실제 객체처럼 동작시키고 일부만 Stubbing할 수 있다.

### Stub 행위 검증 (Behavior Verification)
- 테스트에서 요청한 것에 대해 미리 준비한 결과를 제공하는 객체 그 외에는 응답하지 않는다.

### Mock 상태 검증 (State Verification)
- 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만들어진 객체

## 순수 Mockito로 검증해보기

### Mockito 검증 방법
|       메소드       |        기능         |
|:---------------:|:-----------------:|
|  times(int n)   |   몇번 호출 했는지 검증    |
|     never()     | 한 번도 호출 되지 않는지 검증 |
|  atLeastOne()   | 최소 한번은 호츨 됬는지 검증  |
| atLeast(int n)  |  최소 n번 호출 됬는지 검증  |
|  atMostOnce()   | 최대 한번은 호출 됬는지 검증  |
|  atMost(int n)  |  최대 n번 호출 됬는지 검증  |
|  calls(int n)   |   n번이 호출 됬는지 검증   |
|     only()      | 해당 메소드만 실행 됬는지 검증 |
| timeout(long n) |      타임아웃 검증      |
|  after(long n)  |     걸리는 시간 검증     |
| description() |     실패 문구 검증      |
| InOrder |       순서 검증       |

### @Mock
``` java
package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.cliemt.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() throws Exception {
        //given
        when(mailService.sendMail(any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}
``` 

### @Spy
``` java
package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.cliemt.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() throws Exception {
        //given
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(any(String.class), any(String.class), any(String.class), any(String.class));


        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}
``` 

### BDDMockito
- Mockito랑 기능은 같은 하지만 given 있어서 직관적임
``` java
package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.cliemt.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() throws Exception {
        //given
        BDDMockito.given(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .willReturn(true);

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}
``` 

### Classicist VS. Mockist

#### Classicist
- Classicist는 전통적인 테스트 방법이다. 코드가 동작하는지 확인하기 위해 실제 코드와 상호 작용하고 테스트하는 것에 중점을 줌
- 꼭 필요할때만 Mocking를 사용하자!

#### Mockist
- Mockist 테스트는 종종 테스트 더블(test doubles)라고 알려진 가짜 객체를 사용하여 외부 의존성을 대하며, 의존성이 예상대로 상호 작용하는지 검증

#### 언제 Mocking을 써야 되나~?
- 외부 시스템일 경우 Mocking 처리 하는게 좋타!
![](https://github.com/dididiri1/cafekiosk/blob/main/study/images/06_02.png?raw=true)

### 키워드 정리
- Test Double, Stubbing
  - dummmy, fake, stub, spy, mock
- @Mock, @MockBean, @Spy, @SpyBean, @InjectMocks
- BDDMockito
- Classicist VS. Mockist

# 섹션 7 더 나은 테스트를 작성하기 위한 구체적 조언

## 한 문단에 한 주제
- 한 가지 테스트에서는 한 가지 목적의 검증만 수행을 한다.

## 완벽하게 제어하기

## 테스트 환경의 독립성을 보장하자
- 다른 API들을 끌어다가 사용할 경우 테스트 간 결합도가 생기는 케이스가 있을수 있으며, 그런 부분에서 독립성을 보장해야 된다.
- 독립적으로 테스트 환경을 구성하고 최대한 독립성을 보장해서 구성하는게 제일 좋음
- 밑에 코드를보며 deductQuantity todo 문제

``` java
    @DisplayName("재고가 부족한 상품으로 주문을 생성하려는 경우 예외가 발생한다.")
    @Test
    void createOrderWithNoStock() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();

        Product product1 = createProduct(BOTTLE, "001", 1000);
        Product product2 = createProduct(BAKERY, "002", 3000);
        Product product3 = createProduct(HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001", 2);
        Stock stock2 = Stock.create("002", 2);
        stock1.deductQuantity(1); // todo
        stockRepository.saveAll(List.of(stock1, stock2));

        OrderCreateServiceRequest request = OrderCreateServiceRequest.builder()
                .productNumbers(List.of("001", "001", "002", "003"))
                .build();

        // when // then
        assertThatThrownBy(() -> orderService.createOrder(request, registeredDateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족한 상품이 있습니다.");
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }
```


### 생성자와 정적 팩토리 메소드
- 정적 펙토르 메소드
  - 장점1 : 생성자에 넘기는 메게변수 이름만으로는 반환될 객체의 특성을 설명하기 어렵지만, 정적 펙토리 메소드는  
    이름 지을수 있어 객체 특성을 쉽게 표현할 수 있다.
  - 장점2 : 인스턴스를 새로 생성하지 않아도 된다.
``` java
package sample.cafekiosk.spring.domain.stock;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    private int quantity;

    
    // 생성자
    public Stock(String productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    // 정적 펙토리 메소드
    public static Stock create(String productNumber, int quantity) {
        return Stock.builder()
                .productNumber(productNumber)
                .quantity(quantity)
                .build();
    }
}

``` 

## 테스트 간 독립성을 보장하자

### 두 가지 이상의 테스트가 하나의 자원을 공유할때 잘못된 얘
- 테스트간에 순서는 무관해야 됨. A테스트가 수행된 이후 B테스트가 수행되어야 성공을 한다는 개념 자체가 없어야됨.
- 각각 독립적으로 언제 수행이 되도 항상 같은 결과를 내야됨.
- 공유 자원을 사용하는 경우에는 테스트 간 순서가 생길 수 있기 때문이다.
``` java
class StockTest {

    private static final Stock stock = Stock.create("100", 1);

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인하다.")
    @Test
    void isQuantityLessThan() throws Exception {
        //given
        int quantity = 2;

        //when
        boolean result = stock.isQuantityLessThan(quantity);

        //then
        assertThat(result).isTrue();

    }

    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() throws Exception {
        //given
        int quantity = 1;

        //when
        stock.deductQuantity(quantity);

        //then
        assertThat(stock.getQuantity()).isEqualTo(0);
    }
}
``` 

``` java
class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인하다.")
    @Test
    void isQuantityLessThan() throws Exception {
        //given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        //when
        boolean result = stock.isQuantityLessThan(quantity);

        //then
        assertThat(result).isTrue();

    }

    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() throws Exception {
        //given
        Stock stock = Stock.create("100", 1);
        int quantity = 1;

        //when
        stock.deductQuantity(quantity);

        //then
        assertThat(stock.getQuantity()).isEqualTo(0);
    }
}
```

## 한 눈에 들어오는 Test Fixture 구성하기
### Test Fixture

- Fixture : 고정물, 고정되어 있는 물체
- 테스트를 위해 원하는 상태로 고정시킨 일련의 객체

## Test Fixture 클렌징 (3가지)

### @Transactional

### deleteAllInBatch
- deleteAllInBatch는 테이블 전체를 벌크성으로 날릴 수 있는 메소드인데 지우는 순서를 잘 고려야해야 한다.
- 외래키 조건이 걸려 있거나 조건들이 순서에 따라 영향을 받는다. 해서 어떤걸 먼저 순서를 고민해야되는게 단점이지만 deleteAll() 보다 성능이 좋다.

``` java
@AfterEach
void tearDown() {
    orderProductRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    orderRepository.deleteAllInBatch();
}
``` 

### deleteAll
- deleteAll의 장점은 Order를 지우고 Product를 지웠을때 Order를 지우면서 OrderProduct까지 같이 지워준다.
- foreign key 맺고 있는 애들은 전부 찾아와서 select하고 하나씩 껀껀히 반복문을 돌면서 지워서 성능이 저하되는 단점이 있음.

``` java
@AfterEach
void tearDown() {
    orderRepository.deleteAll();
    productRepository.deleteAll();
}
``` 

#### 정리
> @Transactional 편하니깐 써야지 보다는 상황의 맞쳐 잘 알고 써야된다.

## @ParameterizedTest(파리미터라이즈 테스트)
- 하나의 테스트 케이스데 값을 여러 개로 바꿔보면서 테스트를 하고 싶을때 사용

### @ValueSource 방식
- 파라미터 하나일 때 간단한 형태
``` java
class ProductTypeTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) throws Exception {
        //then
        assertThat(argument > 0 && argument < 4);
    }
    
}
```


### @CsvSource 방식
``` java
class ProductTypeTest {
     
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockType4(ProductType productType, boolean expected) throws Exception {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
``` 

### @MethodSource 방식
- 이경우 프로덕션 코드에서는 private method를 보통 아래쪽에 명시하는 편인데. given의 역활이기 떄문에 테스트 바로 위에다가 명시할수도 있다.
``` java
class ProductTypeTest {
     
     private static Stream<Arguments> provideProductTypesForCheckingStockType() {
        return Stream.of(
                Arguments.of(ProductType.HANDMADE, false),
                Arguments.of(ProductType.BOTTLE, true),
                Arguments.of(ProductType.BAKERY, true)
        );
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockType5(ProductType productType, boolean expected) throws Exception {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        assertThat(result).isEqualTo(expected);
    }
    
}
``` 
### 참고
- [Junit5 Parameterized Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests)
- [Spock Data Tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables)

## @DynamicTest
- Runtime 중에 생성되는 동적 테스트이며, given 안에서 when이 연속적으로 이루어지는 형태를 가진다.
- 일련의 시나리오를 테스트 하고 싶을때 단계별로 어떤 행위와 검증을 수행하고 싶을때 사용
- @TestFactory 사용

``` java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StockTest {
    
    @DisplayName("재고 차감 시나리오")
    @TestFactory
    Collection<DynamicTest> sockDeductionDynamicTest() throws Exception {
        //given
        Stock stock = Stock.create("001", 1);

        return List.of(
                DynamicTest.dynamicTest("재고를 주어진 개수만큼 차감할 수 있다.", () -> {
                    //given
                    int quantity = 1;

                    //when
                    stock.deductQuantity(quantity);

                    //then
                    assertThat(stock.getQuantity()).isZero();
                }),
                DynamicTest.dynamicTest("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.", () -> {
                    //given
                    int quantity = 1;

                    //when then
                    assertThatThrownBy(() -> stock.deductQuantity(quantity))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage("차감할 재고 수량이 없습니다."); // 메세지 검증
                })
        );

    }

}
``` 

## 테스트 수행도 비용이다. 환경 통합하기
- 테스트를 작성하는 이유 중 하나는 사람이 수동하는 검증하는 비용보다 기계의 도움을 받아서 수시로 우리가 피드백을  
  받고 우리의 프로덕션 코드를 발전시켜 나갈 수 있는 도구 
- 해서 테스트 자체를 자주 수행하려면 테스트가 수행되는 시간이 다 비용기 떄문에 관리가 필요하다.

``` java
package sample.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.cliemt.mail.MailSendClient;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    @MockBean
    protected MailSendClient mailSendClient;
}
``` 

``` java
package sample.cafekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.spring.api.controller.order.OrderController;
import sample.cafekiosk.spring.api.controller.product.ProductController;
import sample.cafekiosk.spring.api.service.order.OrderService;
import sample.cafekiosk.spring.api.service.product.ProductService;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;
    
}
``` 

## Q. private 메서드의 테스트는 어떻게 하나요?
- 객체가 공개한 API들을 테스트하다 보면 자연스럽게 검증이 되기 떄문이다.  
  만약에 욕망이 강하게 든다 객체 분리의 신호롸 봐야함.

``` java
package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Component
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }
        int latestProductNumberInt = Integer.valueOf(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
``` 

## Q. 테스트에서만 필요한 메서드가 생겼는데 프로덕션 코드에서는 필요 없다면?
- Test에서만 사용하는 ① ProductCreateRequest 생성자
- 있어도 상관없지만 **보수적**으로 접근하기!
``` java
package sample.cafekiosk.spring.api.controller.product.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;

    @Builder // ①
    private ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public ProductCreateServiceRequest toServiceRequest() {
        return ProductCreateServiceRequest.builder()
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
``` 

## 키워드 정리
- 테스트 하나 당 목적은 하나!
- 완벽한 제어
- 테스트 환경의 독립성, 테스트 간 독립성
- Test Fixture
- deleteAll(), deleteAllInBatch()
- @ParameterizedTest, @DynamicTest
- 수행 환경 통합하기
- private method test
- 테스트에서만 필요한 코드

## 섹션 8 Appendix

- [Guava](https://github.com/google/guava)


## 학습 테스트
- 잘 모르는 기능, 라이브러리, 프레임워크를 학습하기 위해 작성하는 테스트
- 여러 테스트 케이스를 스스로 정의하고 검증하는 과정을 통해 보다 구체적인 동작과 기능을 학습할 수 있다.
- 관련 문서만 읽는 것보다 훨씬 재미있게 학습할 수 있다.

## Spring REST Docs
- 테스트 코드를 통한 API 문서 자동화 도구
- API 명세를 문서로 만들고 외부에 제공함으로써
- 협업을 원활하게 한다.
- 기본적으로 AsciiDoc을 사용하여 문서를 작성한다.

## REST Docs VS. Swagger
### REST Docs
#### 장점
- 테스트를 통과해야 문서가 만들어진다. (신뢰도가 높다.)
- 프로덕션 코드에 비침투적이다.
#### 단점
- 코드 양이 많다.
- 설정이 어렵다.

### Swagger
#### 장점
- 적용이 쉽다.
- 문서에서 바로 API 호출을 수행해볼 수 있다.
#### 단점
- 프로덕션 코드에 침투적이다.
- 테스트와 무관하기 때문에 신뢰도가 떨어질 수 있다.

### REST Docs 설치 방법
- build.gradle 추가
- Settings -> Plugins -> asciidoc 설치

#### 설정 1
``` java
package sample.cafekiosk.spring.docs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(provider))
                .build();
    }
}
``` 

#### 설정 2
``` java
package sample.cafekiosk.spring.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();
}
``` 

### 참고
- [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
- [Asciidoctor](https://asciidoctor.org/)

## 섹션 9 Outro

### 타엽하지 않는 마음
#### 가까이 보면 느리지만, 멀리보면 가장 빠르다.

### Reference

* [Practical Testing: 실용적인 테스트 가이드](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C/dashboard)