# I/O
* 자바의 I/O는 Stream을 이용해서 데이터를 주고 받는 구조로 되어있다.
<hr>

## Stream 종류
* 입출력
    * 입력용
        * InputStream, Reader (데이터를 읽어오는 Stream)
    * 출력용
        * OutputStream, Writer (데이터를 출력하는 Stream)
* 데이터 전송 단위
    * byte Stream (1 byte에 코드 값을 담아 전송하는 Stream)
        * InputStream / OutputStream
    * character Stream (2 byte에 코드 값을 담아 전송하는 Stream)
        * Reader / Writer
* 데이터와의 연결 여부
    * Node Stream (데이터와 직접 연결되어 있는 Stream)
    * Process Stream(Filter Stream) - Node Stream으로부터 데이터를 받아 가공하는 Stream

<hr>

### Node Stream만 사용하여 파일을 복사하는 코드
```java
public static void main(String[] args) throws Exception {
    FileInputStream fis;
    FileOutputStream fos;
    
    fis = new FileInputStream(args[0]);//원본파일
    fos = new FileOutputStream(args[1]);//복사
    
    for(int i; (i = fis.read()) != -1;) {//파일의 끝에서 -1이 리턴된다
        fos.write(i);//해당 코드에 일치하는 글자가 쓰여진다
    }
    fis.close();
    fos.close();
    System.out.println("file copied...");
}
```
* args로 입력된 파일 이름을 이용하여 파일을 읽고 쓴다
* 프로젝트 바로 밑에 있는 파일은 파일 이름만 써도 되지만 다른 곳의 파일은 경로를 입력해 주어야 한다
* Node Stream으로부터 그대로 읽고 쓰는 코드

<hr>

### Filter Stream 이용한 파일 입출력 코드

```java
public static void main(String[] args) throws Exception {
    File f = File.createTempFile("sample", ".java");

    FileWriter fw = new FileWriter(f);//node Stream
    BufferedWriter bw = new BufferedWriter(fw);//filter Stream
    
    bw.write("안녕하세요");
    bw.newLine();
    bw.write("파일입출력");
    bw.newLine();
    
    bw.close();
    fw.close();
    
    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    
    for(String str; (str = br.readLine()) != null;)
        System.out.println(str);
    
    br.close();
    fr.close();
}
```
* FileReader와 FileWriter(Node Stream)와 BufferedReader, BufferedWriter(Filter Stream)을 연결하여 파일을 읽고 쓰는 코드이다

<hr>

## 보조 스트림의 종류 (Filter)
기능|byte 기반|char 기반
---|---|---|
byte 스트림을 char 스트림으로 변환||InputStreamReader<br>OutputStreamWriter|
버퍼링을 통한 속도 향상|BufferedInputStream<br>BufferedOutputStream|BufferedReader<br>BufferedWriter|
기본 데이터형 전송|DataInputStream<br>DataOutputStream||
객체 전송|ObjectInputStream<br>ObjectOutputStream||
문자열 표현으로 출력|PrintStream|PrintWriter|



<hr>

## 객체 전송 스트림
* 객체를 Stream을 통해 이동시키려면 직렬화 조건을 만족해야 한다
* 직렬화 - 객체가 Stream을 통해 이동될 때 객체 안의 데이터가 byte 배열로 바뀌어 전송되는 작업
* 직렬화 조건 - Serializable 구현

```java
class Person implements Serializable { //직렬화 조건
    private String name;
    private int age;

    private transient String ssn;//직렬화 제외
    private LoginInfo lInfo;//직렬화 필요 
}
```

<hr>

