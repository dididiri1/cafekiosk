

## contains()

- contains() 메소드는 List 값에 검색할 값이 있으면 true, 없으면 false를 리턴함

``` java
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> list = List.of("제조 음료", "병음료");

        boolean isValid1 = list.contains("제조 음료");
        boolean isValid2 = list.contains("베이커리");

        System.out.println(isValid1); // true
        System.out.println(isValid2); // false
    }
}
```

### 결과 
``` log
true
false
```

## indexOf()
- indexOf() 메소드는 List 값에 검색할 값이 있으면 index 값 반환

``` java
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> str = List.of("A", "B", "C");

        System.out.println(str.indexOf("A")); 
        System.out.println(str.indexOf("B")); 
        System.out.println(str.indexOf("F")); // 없으면 -1 
    }
}

``` 

### 결과
``` log
0
1
-1
```