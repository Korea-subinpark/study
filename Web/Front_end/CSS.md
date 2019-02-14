# CSS

## HTML 문서에서 CSS 연결 방법
* `<link>` 사용하여 외부 스타일시트 연결
    * 여러 html 파일에 공통적으로 들어갈 때 재사용성이 좋다
    * `<link rel=stylesheet type="text/css" href="samp.css">`
    * `<head>` 안에 선언
* `<sytle>`과 @import 사용하여 외부 스타일시트 연결
* `<sytle>` 사용하여 직접 스타일 지정(내부 스타일 시트)
    * 하나의 파일에서 html 코드와 css 코드를 한번에 볼 수 있다는 장점
    * `<style> h3 { color: blue } </style>`
    * `<head>` 안에 선언
* `<body>`의 하위 요소에 'style' 속성을 사용하여 지정(lnline)
    * 많이 사용하지는 않는다
* 내부에 선언한 것이 우선순위가 더 높다

---

## CSS 규칙
* 선택자 {속성: 값}<br>
    ```css
    h1 { 
        color: blue;
    } 
    ```
    * 선택자와 선언부(속성, 값)로 구성
    * 모든 HTML tag가 선택자가 될 수 있다<br>

    ```css
    h1, h2, h3 { font-family: 굴림 } 
    ```
    * ,를 사용하여 그룹화
* 주석 `/* */`

* class 속성
    ```html
    <head><style>
    h1.italic {font-style: italic}
    </style></head>
    <body>
    <h1 class="italic">이탤릭체 머리글</h1>
    </body>
    ```
    <br>

    * 모든 요소에서 class 사용시
    ```css
    .italic {font-style: italic}
    ```

* id 속성
    ```html
    <head><style>
    #space1 {letter-spacing: 0.3em}
    h1#space {letter-spacing: 0.2em}
    </style></head>
    <body>
    <h1 id="space">id가 적용된 머리글</h1>
    <p id="space1">id가 적용된 문장</p>
    </body>
    ```
    * 중복이 불가능하여 재활용성이 떨어진다
---
## CSS의 포맷팅 모델(box model)
![box-model](/img/box-model.png)

* width는 브라우저마다 다르다
    * chrome은 content까지
    * explore는 padding까지

<br>

```css
    p {
        padding: 30px;
        margin: 20px;
        border: 3px;
    }
```
* 여백 규칙
* 하나만 썼을 경우 -> 4방향 모두 여백 주기
* 두 개를 썼을 경우 -> 상하, 좌우로 묶어서 여백 주기
* 네 개를 썼을 경우 -> 위부터 시계방향으로 여백 주기

---

## Position
* position을 선언하지 않으면 기본 값이 static 이기 때문에 left, top 등을 조정해도 변화가 없다
```css
.box2{
    position: relative;
    float: left;
    left: -50px;
    top: 50px;
    background-color: #0094ff;
    border: 2px solid blue;
}
```

---

## Reponsive Web
* 해상도에 따라서 웹 페이지 배치가 달라진다
* PC, mobile, tablet 등

```html
<head>
    <title>Responsive Web Basic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- 반응형 웹: 미디어 쿼리를 사용해 장치를 구분하고 장치늬 크기나 비율 등도 고려해서 CSS를 적용하는 웹 사이트 -->
  	<style type="text/css">
  	@media screen and (max-width: 767px) { /*0-767 pixel*/
            html {
                background: red;
                color: white; 
                font-weight: bold;
            }
        }
        
 		/*768-979 pixel, ipad가 수직일 때 768*/
        @media screen and (min-width: 768px) and (max-width: 959px) {
            html {
                background-color: green;
                color: white; 
                font-weight: bold;
            }
        }
        
  		/*980- pixel, 일반 브라우저의 경우*/
        @media screen and (min-width: 980px) {
            html {
            	background-color: blue;
            	color: white;
            	font-weight: bold;
            }
        }
  	</style>
</head>
```