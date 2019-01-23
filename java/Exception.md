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
* throw -> 발생한 예외를 호출자로 위임해서 처리 (Declare Exception)

<hr>

### ArrayIndexOutOfBoundsException 발생하는 코드

```java
public static void main(String[] args) {
    int[] score = {90,45,60,80,100};
    
    for(int i = 0; i <= score.length; i++)
        System.out.println(score[i]);
    System.out.println("done...");
}
```
* 크기가 5인 배열에 5번 인덱스에 접근하여 Exception이 발생한다
<hr>

 ### try-catch 방법으로 처리한 코드

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
* Exception이 발생할 수 있는 코드를 try로 감싸고 Exception은 catch에서 처리하도록 한다
* 각 Exception마다 catch를 만들 수 있지만 너무 많아지므로 부모 클래스인 Exception을 catch하면 코드가 더 깔끔해질 수 있다 (다른 처리가 필요한 Exception들은 앞에서 catch하여 처리, 같은 처리를 하는 Exception은 마지막에 Exception으로 catch)
* finally는 예외발생 여부와 관계없이 모든 경우에 실행한다
<hr>

### Checked Exception
```java
try {
    Thread.sleep(3000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
* Thread의 sleep함수는 그냥 사용하면 에러가 발생한다
* InterruptedException, FileNotFoundException 등은 Checked Exception으로 반드시 try-catch로 처리해야 한다

<hr>

### throw 방법으로 처리한 코드

```java
private void check(int i) throws Exception {
    if(i > 0)
        System.out.println("result:" + ++i);
    else //예외상황 --> 예외 발생
        throw new Exception();
}
```

* 자신을 호출한 곳으로 Exception을 throw
* 함수 옆에 throws, 예외로 정의하고 싶은 곳에는 throw

<hr>

### Customize Exception

```java
class MyException extends Exception {
    @Override
    public String toString() {
        return "MyException 발생!";
    }
}
```
* Exception 클래스를 상속받아 사용자 정의 Exception을 만들 수 있다

