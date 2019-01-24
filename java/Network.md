# Network
* 서버와 클라이언트간 Stream을 통해서 데이터를 교환하는 프로그래밍

---
* port
    * 0 ~ 65535번까지 사용할 수 있다
    * http 80
    * ftp 21
    * smtp 25
    * 1024~65535번 사이의 포트를 써야 하며 다른 애플리케이션이 사용하는 포트와 겹치면 안된다

---

## 소켓 프로그래밍

![network](/img/network.PNG)

1. 서버에서는 특정 포트에서 ServerSocket이 클라이언트의 접속을 기다린다. 서버 소켓은 n명의 사용자로부터 접속 요청을 받을 수 있다.
2. 클라이언트는 서버의 아이피와 포트를 이용해 Socket 객체를 생성하면 서버로 접속을 요청하게 된다
3. 클라이언트의 접속 요청을 받은 서버는 accept() 메서드를 통해 클라이언트의 접속을 허용한다
4. accept()의 리턴 값으로 Socket 객체가 생성되는데 이 소켓이 앞으로 클라이언트와 1:1 통신을 담당한다
5. 클라이언트의 소켓과 서버의 소켓을 노드로 하는 InputStream과 OutputStream이 각각 생성돼서 통신한다

---
## 서버, 클라이언트
### 1. 서버
```java
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//server
/* 1. Serverclient(port) 생성
 * 2. Serverclient.accept(): 클라이언트가 접속해오길 기다렸다가 받는 메소드 --> client이 들어옴(client)
 * 3. client으로부터 입출력 스트림 얻어냄
 * 4. 스트림으로 메세지 전송
 * 5. client 닫기(close())
 * 6. 스트림 닫기
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server;//서버 역할
        Socket client;//서버로 접속해 들어온 클라이언트를 받을 객체
        OutputStream out;//node stream
        DataOutputStream dos;//filter stream
        String message = "hello, world 입니다";//클라이언트에 보낼 메세지
        
        server = new ServerSocket(3000);//3000번 포트에서 실행
        System.out.println("클라이언트를 기다리는 중...");
        client = server.accept();//클라이언트가 접속해 올 때까지 기다렸다가 클라이언트를 받음. block 되는 특징
        System.out.println("클라이언트 접속!");
        
        out = client.getOutputStream();
        dos = new DataOutputStream(out);
        
        dos.writeUTF(message);
        dos.close();
        out.close();
        client.close();
        server.close();
    }

}
```

### 2. 클라이언트
```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//client
/* 1. Socket(ip, port) 생성
 * 2. Socket으로부터 입출력 스트림 얻어냄
 * 3. 스트림으로 메세지 전송
 * 4. Socket 닫기(close())
 * 5. 스트림 닫기
 */
public class SimpleClient {

	public static void main(String[] args) throws Exception {
		Socket client;
		InputStream in;
		DataInputStream dis;
		
		//127.0.0.1: loopback address
		client = new Socket("localhost", 3000);//소켓 객체가 만들어 지는 순간 서버로 접속해 들어간다
		in = client.getInputStream();
		dis = new DataInputStream(in);
		
		String message = dis.readUTF();
		System.out.println(message);
		
		dos.writeUTF(message);
		
		dis.close();
		in.close();
		client.close();
	}

}
```

---
