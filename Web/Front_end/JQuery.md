# JQuery

* JavaScript의 라이브러리
* 다운받아 사용하거나 CDN 사용


## Syntax
```$(selector).action()```
* $: jquery sign
* selector: html elements, class, id 등의 선택자
* action(): element에 대해 실행할 동작

## Document ready event
* HTML 문서의 로딩이 끝난 후 jquery가 실행되도록 해주는 이벤트

```javascript
$document.ready(function() {
    //jQuery methods go here...
});
```

---

## JQuery Event

|Mouse Events|Keyboard Events|Form Events|Document/Window Events|
|---|---|---|---|
|click|keypress|submit|load|
|dblclick|keydown|change|resize|
|mouseenter|keyup|focus|scroll|
|mouseleave||blur|unload|

* 이벤트 예제
```javascript
$(document).ready(function(){
    //1. 액션 자리에 이벤트 직접 입력
    $('h1').click(function(){//클릭했을 때 alert창
        alert($(this).text());
    });
    //2. 해당 선택자에 이벤트 등록시키는 액션(상황에 따라서 on/off 가능)
    $('h1').on('click', function(){
        alert($(this).text());
    });

    $('h1').off('click');//이벤트 제거


    $('h1').mouseenter(function(){//마우스가 들어오면 reverse class 추가
        $(this).addClass('reverse');
    });
    $('h1').mouseleave(function(){//마우스가 떠나면 reverse class 제거
        $(this).removeClass('reverse');
    });
});
```

---

## html(), text(), val()
### parameter를 입력하면 setter, 비워두면 getter로 동작한다
1. html
    * 태그가 적용된다
2. text
    * 태그를 인식하지 않고 텍스트로 처리
3. val
    * 입력 칸에서 값을 가져오거나 setting할 때 사용

---
## effect

|fade|slide|animate|
|---|---|---|
|.fadeIn()|.slideDown()|.animate()|
|.fadeOut('slow')|.slideUp()||
|.fadeToggle(3000)|.slideToggle()||

---

## ajax
* $.ajax는 get과 post 구분이 필요없다 ($.get(), $.post())
* 기본은 get 방식
```javascript
$(document).ready(function(){
    $(":button").first().click(function(){
        //ajax요청 보내기
        $.ajax({
            //json형식으로 ajax통신에 필요한 정보 언급해 줘야 됨
            url: 'first.jsp',//서버
            success: function(result, status, xhr){//서버로부터 데이터 전송이 성공적이면
                $('#display').val = result;
            },
            error: {}//에러발생시
        });
    });
});
```

* 서버로 전송할 데이터가 있을 경우
```javascript
$(document).ready(function(){
    $(':button').click(function(){
        //ajax요청 보내기
        $.ajax({
            url: 'paramTest.jsp',//서버
            success: function(result){//서버로부터 전송된 값이 성공적이면
                $('#display').html("<h1>" + result + "</h1>");
            },
            data: { //서버로 보낼 데이터
                //name: value
                id: $('#id').val(),
                pass: $('#pass').val()
            }
        });
    });
});
```
