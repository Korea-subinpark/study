# XML
* potable data
---
## Well-formed Document
### 규칙
* Case Sensitive
* Closing Tag
* No Overlapping Tag
    * ex) `<a>` `<b>` `</a>` `</b>` 허용X
* Root Element
    * element
        * 시작 태그 + 내용 + 닫는 태그
* Attribute: name='value' or name="value"
    * ex) `<first type = "1">`

## Valid Document
* DTD 또는 Schema에 정의된 형식에 맞는 문서

---
* CDATA 세션
    * 모든 텍스트가 마크업이 아닌 문제 데이터로 해석되기 원할 때 사용
    * `<![CDATA[내용]]>`

* Namespace
    * tag이름을 사용자가 임의로 정할 수 있기 때문에 이름이 중복될 수 있다<br>
    따라서 Namespace가 필요하다

* Prefix
    * 사용규칙
        * 문자나 '_'로 시작되며 문자, 숫자, '.', '-', '_'를 포함하는 문자열이 올 수 있다

