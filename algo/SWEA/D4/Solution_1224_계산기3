import java.util.Scanner;
/*
중위계산식을 후위계산식으로 바꾼 뒤 계산한다
이 문제에서 연산기호는 *, + 만 사용한다

1. 중위계산식을 후위계산식으로 변환
'('는 스택에 push한다
연산기호는 자신보다 우선순위가 낮은 연산기호가 나올 때까지 pop을 하고 후위계산식 String에 추가한 뒤에 push한다
'*'일 때에는 자신과 같은 '*'가 나올 때까지 pop
'+'일 때에는 '*' 또는 '+'가 나올 때까지 pop
')'는 '('가 나올 때까지 pop을 하고 '('는 버린다
숫자일 경우에는 그대로 후위계산식 String에 추가한다

2. 후위계산식 계산
숫자일 경우에는 스택에 push한다
연산기호가 나오면 pop을 두 번하여 계산을 한 뒤에 결과를 다시 push한다
마지막에 남는 숫자가 최종 연산 결과이다

*/
public class Solution_1224_SW문제해결기본6일차_계산기3_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();
			String pre = sc.next();//중위계산식
			String post = "";//후위계산식
			char[] stack = new char[N / 2 + 1];
			int[] stack2 = new int[N / 2 + 1];
			int top = -1;
			for(int i = 0; i < N; i++) {
				char c = pre.charAt(i);
				switch(c) {
				case '(':
					stack[++top] = c;
					break;
				case '*':
					while(top > -1 && stack[top] == '*') {
						post += stack[top];
						top--;
					}
					stack[++top] = c;
					break;
				case '+':
					while(top > -1 && (stack[top] == '*' || stack[top] == '+')) {
						post += stack[top];
						top--;
					}
					stack[++top] = c;
					break;
				case ')':
					while(top > -1 && stack[top] != '(') {
						post += stack[top];
						top--;
					}
					if(stack[top] == '(')
						top--;
					break;
				default:
					post += c;
					break;
				}
			}
			while(top > -1) {
				if(stack[top] == '(')
					top--;
				else {
					post += stack[top];
					top--;
				}
			}
			
			int a, b;
			for(int i = 0; i < post.length(); i++) {
				char c = post.charAt(i);
				switch(c) {
				case '*':
					a = stack2[top--];
					b = stack2[top--];
					stack2[++top] = b * a;
					break;
				case '+':
					a = stack2[top--];
					b = stack2[top--];
					stack2[++top] = b + a;
					break;
				default:
					stack2[++top] = c - '0';
					break;
				}
			}
			System.out.println("#" + tc + " " + stack2[top--]);
			
		}
		
		sc.close();
	}

}
