# JDBC
* 응용프로그램이 데이터 베이스에 연결하여 데이터 베이스를 이용할 수 있도록 하는 자바 클래스들의 모임
* 실제로는 인터페이스들이 많이 있고 각 DB 기업들이 클래스를 완성하였다

## 과정
1. DriverManager에게 사용할 Driver 등록
2. Connection 생성(Network 연결)
3. Statement 생성(Query 하나를 담는 그릇)
4. DB로 전송
    * `stat.executeQuery("select ...");`
        * ResultSet 생성
    * `stat.executeUpdate("~");` - select 이외
5. DB에서 온 결과 처리
6. 연결 해제 (stat, connection close())

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

    public static void main(String[] args) {
        //url(jdbc url)
        //jdbc:protocol 이름. mysql: db이름, 127.0.0.1: db서버, 3306: port, scott: db이름
        String url = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC";
        String user = "scott";
        String pass = "tiger";
        
        Connection con;
        Statement stat;
        String q = "select * from customer order by num";
        
        try {
            //1. DriverManager에 driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2. Connection 연결
            con = DriverManager.getConnection(url, user, pass);
            
            //3. Statement 생성
            stat = con.createStatement();
            
            //4. Query 전송
            ResultSet rs = stat.executeQuery(q);
            
            //5. db에서 온 결과 처리
            while(rs.next()) {
                String num = rs.getString(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                
                System.out.println(num + "--" + name + "--" + address);
            }
            
            //6. 마무리
            stat.close();
            con.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

}
```


### PrepareStatement
```java
package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCPre {

    public static void main(String[] args) {
        //url(jdbc url)
        //jdbc:protocol 이름. mysql: db이름, 127.0.0.1: db서버, 3306: port, scott: db이름
        String url = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC";
        String user = "scott";
        String pass = "tiger";
        
        Connection con;
        PreparedStatement pstat;
        String q = "insert into customer values(?,?,?)";
        //System.out.println(q);
        try {
            //1. DriverManager에 driver 등록
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2. Connection 연결
            con = DriverManager.getConnection(url, user, pass);
            
            //3. Statement 생성
            pstat = con.prepareStatement(q);
            
            //3-1. ?에 값 setting
            pstat.setString(1, args[0]);
            pstat.setString(2, args[1]);
            pstat.setString(3, args[2]);
            
            //4. Query 전송
            int cnt = pstat.executeUpdate();
            
            //5. db에서 온 결과 처리
            System.out.println(cnt + "개의 레코드 추가!");
            
            //6. 마무리
            pstat.close();
            con.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

}

```

## ResultSet에서 레코드 포인터 변경하는 함수
1. rs.next()
2. rs.previous()
3. rs.first()
4. rs.last()
4. rs.absolute(idx)



## Connection Pool
* 작업중에서 가장 오래 걸리는 Connection 작업을 빠르게 하기 위해 생성
* Connection이 필요할 때 Connection Pool에서 빌려와서 사용

* JNDI (Java Naming & Directing Interface)
