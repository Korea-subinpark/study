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

## CSS의 포맷팅 모델(box model)
![box_model](/img/box_model.png)

* width는 브라우저마다 다르다
    * chrome은 content
    * explore는 padding까지