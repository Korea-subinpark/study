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

