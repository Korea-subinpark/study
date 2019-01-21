import java.util.Scanner;
import java.util.Stack;
/*
Stack을 이용한다
(가 나온 경우 다음이 )라면 레이저이다
레이저인 경우 현재 막대수를 조각 수에 더해준다
(가 나온 다음 (이 나왔다면 막대가 추가된 것이므로 막대 수와 조각 수를 증가시키고 Stack에 push한다
)가 나왔다면 막대의 끝이므로 Stack에서 pop을 해서 막대 하나를 제거하고 막대 수를 감소시킨다
*/
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
