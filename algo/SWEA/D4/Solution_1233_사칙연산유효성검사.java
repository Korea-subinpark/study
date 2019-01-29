import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
루트가 숫자일 경우와 잎이 연산자인 경우 실패로 출력
실패인 경우 더 이상 연산을 수행하지 않고 데이터를 읽기만 한다
*/
public class Solution_1233 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;
			char[] tree = new char[256];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				if(ans == 1) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1) {//루트 검사
						char c = st.nextToken().charAt(0);
						if(48 <= c && c >= 57) {//루트가 숫자일 경우 실패
							ans = 0;
						}
					}
					if(st.countTokens() == 1) {
						char c = st.nextToken().charAt(0);
						if(c < 48 || c > 57) {//잎 노드가 연산자일 경우 실패
							ans = 0;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

}
