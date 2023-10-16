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
