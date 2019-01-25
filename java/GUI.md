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
public class UITest {
	//field
	JFrame f;//창
	JButton ok, cancel;//버튼
	JTextField tf;//한줄 입력
	JTextArea ta;//여러줄 입력. 스크롤바가 나타나도록 JScrollPane을 사용해야 한다
	JLabel label;//화면 상에서의 글자
	
	//gui(화면) 작성
	UITest() {
		f = new JFrame("swing test");//title bar에 글자 넣으면서 창 생성
		f.setLayout(new BorderLayout());//layout manager 지정. FlowLayout -> add 하는 순서대로 화면에 배치
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창닫기 버튼 눌렀을 때 할일 지정. 닫기 버튼 누르면 프로그램 종료
		
		ok = new JButton("ok");
		cancel = new JButton("cancel");
		label = new JLabel("hello swing");
		tf = new JTextField(30);//30글자 들어갈 정도
		ta = new JTextArea(5, 30);//30글자 5줄
		JScrollPane pane = new JScrollPane(ta);
		Container c = f.getContentPane();//화면상에서 ContentPane 영역을 알아낸다
		c.add(ok, "East");
		c.add(cancel, "West");
		c.add(label, "North");
		c.add(pane, "Center");
		c.add(tf, "South");
		f.setSize(300, 300);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		UITest u = new UITest();
	}

}
```

![UITest](/img/uitest.PNG)
