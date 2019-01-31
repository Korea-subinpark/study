# JSON (JavaScript Object Notation)
### - 텍스트 기반의 경량 데이터 변환 포멧
### - 데이터 처리가 쉽다
### - JavaScript 기반으로 만들어졌으나, 프로그래밍 언어에 독립적인 텍스트 형식
### - 이기종간의 데이터 교환에 적합

---
## 컬렉션데이터 구조
* 이름/값 쌍으로 이루어진 데이터 집합
* 중괄호 내에 key와 value로 구성
* key와 value 사이에는 :로 구분
* key는 문자열 타입, value는 JSON데이터
* 다수의 데이터가 존재할 경우 ,로 구분
* ex) { "name1" : value1,
        "name2" : value2
        ...
      }


## 배열 구조
* 순서가 있는 데이터들의 목록
* [로 시작해서 ]로 끝
* 각 데이터는 ,로 구분
* ex) [value1, value2, ...]

---
## Gson

```java
public class JsonTest {

    public static void main(String[] args) {
        //1. java object --> json string
        Member m = new Member();
        m.setNum("123");
        m.setName("tommy");
        m.setAddress("la");
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String str = gson.toJson(m);
        System.out.println(str);

        //2. json string --> java object
        String json = "{'num':'678', 'name':'william', 'address' : 'seattle'}";
        Gson gson2 = new Gson();
        Member m2 = gson2.fromJson(json, Member.class);
        System.out.println(m2);

        //3. JsonObject --> json string
        JsonObject obj = new JsonObject();
        obj.addProperty("num", "890");
        obj.addProperty("name", "kim");
        obj.addProperty("address", "seoul");
        str = gson.toJson(obj);
        System.out.println(str);

        //4/ parsing
        JsonParser parser = new JsonParser();
        JsonObject obj2 = parser.parse(json).getAsJsonObject();
        String num = obj2.get("num").getAsString();
        String name = obj2.get("name").getAsString();
        String address = obj2.get("address").getAsString();
        
        System.out.println(num + "--" + name + "--" + address);
    }

}
////////////////////////////////
출력 결과
{
  "num": "123",
  "name": "tommy",
  "address": "la"
}
Member [num=678, name=william, address=seattle]
{
  "num": "890",
  "name": "kim",
  "address": "seoul"
}
678--william--seattle
```
