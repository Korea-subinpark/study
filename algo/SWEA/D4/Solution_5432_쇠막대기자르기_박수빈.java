import java.util.Scanner;
import java.util.Stack;

public class Solution_5432_쇠막대기자르기_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int pole = 0;//막대 개수
			int result = 0;//잘린 조각 수
			String str = sc.next();
			Stack st = new Stack();
			for(int i=0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c == '(') {
					if(str.charAt(i + 1) == ')') {//레이저
						result += pole;
						i++;
					} else {//새로운 막대
						pole++;
						result++;
						st.push(c);
					}
				} else {//막대의 끝
					st.pop();
					pole--;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
		sc.close();
	}
}
