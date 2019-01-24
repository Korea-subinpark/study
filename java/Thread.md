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

## thread 우선 순위
```java
public class MainThreadTest implements Runnable {
	
    MainThreadTest() {
        //thread 생성하고 start
        Thread t = new Thread(this);
        t.start();
    }

    public static void main(String[] args) {
        MainThreadTest m = new MainThreadTest();
        m.go();
    }

    private void go() {
        System.out.println("gogogo~~~");
    }

    @Override
    public void run() {//thread가 실행하는 메소드
        System.out.println("run by thread...");
    }

}
```
* 위 코드를 실행하면 Main thread의 우선 순위가 높기 때문에 go함수가 run함수보다 먼저 실행된다

```java
MainThreadTest() {
    //thread 생성하고 start
    Thread t = new Thread(this);
    t.start();
    try {
        t.join();//t를 기다린다
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```
* join함수를 사용하면 Main thread가 t thread의 작업이 끝날 때까지 기다리게 된다

<hr>

## Multi Thread 데이터 공유와 동기화 문제
* 공유 데이터에 여러 thread가 동시에 접근해서 수정하면 문제가 발생하게 된다
* lock pool
synchronized 블록을 실행하면서 lock을 획득하지 못한 thread는 lock이 객체에 돌아오기 전까지 실행할 수 없는 BLOCKED 상태가 된다<br>
BLOCKED 객체는 lock pool에서 대기하고 있다가 객체의 lock이 반납되면 다시 RUNNABLE 상태로 변경 후 실행될 수 있다

|메서드 명|선언부와 설명|
|:-:|:-:|
wait()|<pre>public final void wait() InterruptedException<code><br>다른 thread가 notify(), notifyAll()을 호출하기 전까지 현재 thread를 WAITING 상태로 유지한다|
notify()|`public final native void notify()`<br>이 객체의 lock이 필요한 thread 하나를 WAITING 상태에서 RUNNABLE 상태로 변경한다|
notifyAll()|`public final native void notifyAll()`<br>이 객체의 lock이 필요한 모든 thread를 WAITING 상태에서 RUNNABLE 상태로 변경한다|

