# Inner class
## 1. static class
```java
//static class
public class Outer {//top-level class, outer class
    static int num = 90;

    static class Inner{//inner class, static class
        public void go() {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        Outer.Inner i = new Outer.Inner();
        i.go();
        System.out.println(i.getClass().getName());
    }

}

//실행 결과
//90
//com.inner.Outer$Inner
```
## 2. member class
```java
//member class
public class Car {//top-level
    int num = 1234;

    //member class를 사용하려면 그 전에 바깥 class가 먼저 생성되어 있어야 한다
    public class Engine{//inner, member
        public void go() {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        Car c = new Car();
        System.out.println(c.num);
        
        Engine e = c.new Engine();
        e.go();
        System.out.println(e.getClass().getName());
    }

}
//실행 결과
//1234
//1234
//com.inner.Car$Engine
```
## 3. local class
```java
//local class
public class Mountain {//top-level
    int height = 1950;

    public void work() {
        class Gold {//local class. 메소드 안에 선언되어 있는 클래스
            void dig() {
                System.out.println(height);
                System.out.println(this.getClass().getName());
            }
        }
        Gold g = new Gold();
        g.dig();
    }

    public static void main(String[] args) {
            Mountain m = new Mountain();
            m.work();
    }

}

//실행 결과
//1950
//com.inner.Mountain$1Gold
```
## 4. anonymous class
* event 처리에 주로 사용
```java
//anonymous class
abstract class Movie {
    abstract void play();
}

interface Video {
    void play();
}

public class MP3Player {

    public void start(Video m) {
        m.play();
    }

    public void start(Movie m) {
        m.play();
    }
	
    public static void main(String[] args) {
        MP3Player player = new MP3Player();
        //class 선언과 객체 생성이 동시에 이루어진다. 1회용 객체

        player.start(new Video() {
            @Override
            public void play() {
                System.out.println("spider man");
            }
        });

        player.start(new Movie() {
            @Override
            void play() {
                System.out.println("super man");
            }
        });
        
        player.start(new Movie() {
            @Override
            void play() {
                System.out.println("aqua man");
            }
        });
    }

}
//실행 결과
//spider man
//super man
//aqua man

//클래스 이름(이름이 없다)
//MP3Player$1.class
//MP3Player$2.class
//MP3Player$3.class
```

