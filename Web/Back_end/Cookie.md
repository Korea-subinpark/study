# Cookie, Session
### Cookie
* 웹 서버에서 적은 양의 정보를 웹 브라우저에 보내고, 웹 브라우저는 그 정보를 저장하고 있다가, 나중에 다시 웹 페이지를 방문할 때 서버에게 제출하도록 하는 것

### Session

`HttpSession session = request.getSession();`
parameter
* 이전에 만들어 놓은 session 있는지 확인
* 있을 경우 이전 session return
1. true
    * 없을 경우 새로운 session 생성하여 return
2. false
    * 없을 경우 null return
