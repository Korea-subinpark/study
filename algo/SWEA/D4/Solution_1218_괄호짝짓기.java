import java.util.Scanner;
import java.util.Stack;
/*
괄호를 여는 경우 Stack에 push한다
괄호를 닫는 경우 종류를 구분하여 pop을 했을 때 같은 종류 괄호가 아니라면 짝이 맞지 않는 것이다
*/
public class Solution_1218 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();
			String str = sc.next();
			Stack st = new Stack();
			int result = 1;

			for (int i = 0; i < N; i++) {
				char c = str.charAt(i);
				if(c == '(' || c == '[' || c == '{' || c == '<')
					st.push(c);
				else if(c == ')') {
					if((char)st.pop() != '(') {
						result = 0;
						break;
					}
				} else if(c == ']') {
					if((char)st.pop() != '[') {
						result = 0;
						break;
					}
				} else if(c == '}') {
					if((char)st.pop() != '{') {
						result = 0;
						break;
					}
				} else if(c == '>') {
					if((char)st.pop() != '<') {
						result = 0;
						break;
					}
				}
			}
			
			System.out.println("#" + tc + " " + result);
		}

		sc.close();
		
	}

}
