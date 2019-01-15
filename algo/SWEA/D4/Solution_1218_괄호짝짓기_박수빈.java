import java.util.Scanner;
import java.util.Stack;

public class Solution_1218_°ýÈ£Â¦Áþ±â_¹Ú¼öºó {

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
