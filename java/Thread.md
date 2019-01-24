# Thread
* 프로세스 - 현재 실행중인 프로그램
* thread - 프로세스를 구성하는 실행 단위

## multi thread 장/단점
* 장점
    * CPU 사용률 향상
    * 응답성 향상
    * 자원 공유를 통한 효율성 증대
* 단점
    * 컨텍스트 스위칭 비용 발생
    * thread 제어의 어려움
* thread의 순서는 보장되지 않는다

<hr>

## thread의 상태
|enum 상수|설명|
|---|---|
NEW|thread 객체가 생성된 후 아직 start()가 호출되지 않은 상태|
RUNNABLE|JVM 선택에 의해 실행 가능한 상태|
BLOCKED|사용하려는 객체의 모니터 락이 풀릴 때까지 기다리는 상태|
WAITING|sleep(), wait(), join() 등에 의해 정해진 시간 없이 대기중인 상태|
TIMED_WAITING|sleep(), wait(), join() 등에 의해 정해진 시간 없이 대기중인 상태|
TERMINATE|run() 메서드의 종료로 소멸된 상태|

<hr>

## thread 생애
![thread](/img/thread.png)

* NEW에서 RUNNABLE로 상태로 가기 위해서 start() 사용

<hr>

## thread 사용
### 1. thread 클래스 상속
```java
//Thread 클래스 상속
class Tiger extends Thread {
    //callback method:사용자에 의해 직접 호출되지는 않지만 특정 조건이 되면 자동 실행되는 메소드
    //thread가 수행해야할 작업 내용을 갖는 메소드
    public void run() {
        System.out.println("thread is running..." + getName());
    }
}
public class TigerTest {
    public static void main(String[] args) {
        Tiger t1 = new Tiger();
        Tiger t2 = new Tiger();
        Tiger t3 = new Tiger();
        t1.start();//thread에게 작업 시작을 알리는 메소드
        t2.start();
        t3.start();
    }
}
```

### 2. Runnable interface 상속
```java
class Lion implements Runnable {
    //thread가 수행해야할 작업 내용을 갖는 메소드
    @Override
    public void run() {
        System.out.println("thread is running..." + Thread.currentThread().getName());
    }
}
public class LionTest {
    public static void main(String[] args) {
        Lion l1 = new Lion();//thread는 아님. Runnable 객체. thread가 수행해야 하는 작업 메소드 run()은 가지고 있음
        Thread t1 = new Thread(l1);//l1의 run을 실행
        Thread t2 = new Thread(l1);
        Thread t3 = new Thread(l1);
        t1.start();
        t2.start();
        t3.start();
    }
}
```

<hr>

