# Exception
## 1. Checked
* RuntimeException을 제외한 모든 Exception
* SW적으로 복구 가능
* 컴파일러가 체크

## 2. Unchecked
* Error, RunTimeException
* SW적으로 복구 불가능


## Throwable
- Exception들의 조상
- 크게 Error와 Exception으로 나누어 진다

## Exception handling 종류
* try-catch -> 예외 발생 위치에서 바로 처리
* throw -> 발생한 예외를 호출자로 위임해서 처리

<hr>

* ArrayIndexOutOfBoundsException 발생하는 코드

```java
public static void main(String[] args) {
    int[] score = {90,45,60,80,100};
    
    for(int i = 0; i <= score.length; i++)
        System.out.println(score[i]);
    System.out.println("done...");
}
```
<hr>

 * try-catch 방법으로 처리한 코드

```java
public static void main(String[] args) {
    int[] score = {90,45,60,80,100};
    
    for(int i = 0; i <= score.length; i++) {
        try {
            System.out.println(score[i]);
        } catch(ArrayIndexOutOfBoundsException a) {
            System.out.println("Index out of Bounds:" + a.getMessage());
        }
    }
    System.out.println("done...");
}
```
<hr>

