# GUI
## 1. AWT
* JDK 초기 UI API
* 컴포넌트가 C와 같은 NATIVE CODE를 포함하고 있다
* 실행 환경에 따라 UI가 달라질 수 있다<br>
ex) Button
## 2. SWING
* 다양한 종류의 컴포넌트
* 컴포넌트가 100% JAVA로 작성되었다
* 어떤 실행 환경에서도 동일한 UI<br>
ex) JButton
---

## JFrame(창) 구성
* Frame
* Menu Bar
* Content Pane

## Layout Managers
* FlowLayout
    * 화면의 상단에서부터 중앙 정렬되어 오른쪽 방향으로 배치
    * 화면 크기가 변하면 배치가 달라진다
* BorderLayout
    * 화면이 동서남북, 중앙으로 나누어져 있다
    * 화면 크기가 변해도 배치가 달라지지 않는다
* GridLayout
    * 행렬 방식

---
## UI Test

```java
public class ChatTest {
	//field
	JFrame f;//창
	JButton ok, cancel;//버튼
	JTextField tf;//한줄 입력
	JTextArea ta;//여러줄 입력. 스크롤바가 나타나도록 JScrollPane을 사용해야 한다
	JLabel label;//화면 상에서의 글자
	
	//gui(화면) 작성
	ChatTest() {
		f = new JFrame("Char Test");//title bar에 글자 넣으면서 창 생성
		f.setLayout(new BorderLayout());//layout manager 지정. FlowLayout -> add 하는 순서대로 화면에 배치
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창닫기 버튼 눌렀을 때 할일 지정. 닫기 버튼 누르면 프로그램 종료
		
		ok = new JButton("전송");
		cancel = new JButton("cancel");
		label = new JLabel("Chat Test", JLabel.CENTER);
		Font font = new Font("맑은 고딕", Font.BOLD, 30);
		label.setFont(font);
		tf = new JTextField(30);//30글자 들어갈 정도
		ta = new JTextArea(5, 30);//30글자 5줄
		ta.setEditable(false);//편집 안됨
		ta.setBackground(Color.GRAY);
		
		JPanel panel = new JPanel(new FlowLayout());//container. 내부에 다른 화면 구성요소를 담을 수 있다
		panel.add(tf);
		panel.add(ok);
		
		JScrollPane pane = new JScrollPane(ta);
		Container c = f.getContentPane();//화면상에서 ContentPane 영역을 알아낸다
		//c.add(ok, "East");
		//c.add(cancel, "West");
		c.add(label, "North");
		c.add(pane, "Center");
		c.add(panel, "South");
		f.setSize(450, 500);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		ChatTest u = new ChatTest();
	}

}
```

![UITest](/img/uitest.PNG)


---
## Event Handling
* 화면을 구성하고 있는 컴포넌트들에 대해 발생하는 사건(이벤트)에 대한 처리
* Event Handler
    * Event 감시/처리자

|Component|Event|Listener|
|---|---|---|
JButton,<br>JtextField|ActionEvent|ActionListener|
JList|ListSelectionEvent|ListSelectionEvent|
Window|WindowEvent|WindowListener|

### 처리 순서
1. 이벤트 처리 클래스 정의
    * Listener implements
2. Event 발생 컴포넌트에 Listener 등록
3. Listener 추상 메소드 구현
    * Event 발생시 원하는 처리작업 기술

