import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_지희의고장난계산기 {

	static boolean[] flag;
	static int[] cnt;
	static int X, len;
	
	public static void cntNum(int num, int d) {
		if(num > X || d > len)
			return;
		cnt[num] = d;
		
		for(int i = 0; i < 10; i++) {
			if(flag[i]) {
				cntNum(num * 10 + i, d + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s = br.readLine();
			len = s.length();
			X = Integer.parseInt(s);
			cnt = new int[X + 1];
			
			flag = new boolean[10];
			for(int i = 0; i < 10; i++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					flag[i] = true;
				else
					flag[i] = false;
			}
			
			cntNum(0, 0);//누를 수 있는 수의 버튼 수 갱신
			if(X == 1 && flag[1]) {
				System.out.println("#" + tc + " " + 2);
				continue;
			}
			
			int ans = 0;
			int min = Integer.MAX_VALUE;
			int n = X;
			for(int i = n; i > 1; i--) {
				if(cnt[i] != 0 && n % i == 0) {
					ans = calc(n, i);//나누어 지는 수로 끝까지 나누어 본다
					if(min > ans)//최솟값 갱신
						min = ans;
				}
			}
			if(min != Integer.MAX_VALUE)//최솟값이 갱신되었을 경우
				System.out.println("#" + tc + " " + min);
			else
				System.out.println("#" + tc + " " + "-1");
		}
		
	}
	
	public static int calc(int n, int i) {
		int ans = 0;
		for(int j = i; j > 1; j--) {//몫을 하나씩 감소한다
			while(cnt[j] > 0) {//버튼으로 누를 수 있는 경우
				if(n % j == 0) {//나누어 떨어지는 경우
					ans += cnt[j] + 1;//버튼 수 더하기
					n /= j;
				} else
					break;
			}
			if(n == 1)//다 나누어진 경우
				break;
		}
		if(n == 1)//지금까지 누른 버튼 수 리턴
			return ans;
		else
			return Integer.MAX_VALUE;
	}

}
