import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2차원 dp 배열에 최댓값을 저장한다
 * 0일차 상담만 고려했을 때
 * 1일차 상담만 고려했을 때
 * 1, 2일차 상담만 고려했을 때 ...
 * ... 1 ~ 7일차 상담을 고려했을 때 순서로 최댓값을 계속 업데이트 한다
 * 상담을 하다가 퇴사일이 넘어가는 경우에는 이전 최댓값을 그대로 복사하여 예외처리한다
 * */

public class Main_14501_퇴사 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] plan = new int[N + 1][2];//상담 일정표
		int[][] dp = new int[N + 1][N + 1];
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			plan[i][0] = Integer.parseInt(st.nextToken());//상담하는데 필요한 기간
			plan[i][1] = Integer.parseInt(st.nextToken());//받을 수 있는 금액
		}
		
		for(int i = 1; i <= N; i++) { //1일차부터 상담을 하나씩 고려한다
			int idx = plan[i][0] + i - 1;
			if(idx > N) {//상담을 하다가 퇴사일이 넘어가는 경우
				if(i != N)//마지막 날이 아니라면 이전 최댓값을 그대로 복사한다
					System.arraycopy(dp[i - 1], 0, dp[i], 0, N + 1);
				continue;
			}
			System.arraycopy(dp[i - 1], 0, dp[i], 0, idx);
			for(int j = idx; j <= N; j++) {//최댓값 갱신
				dp[i][j] = Math.max(dp[i - 1][j], plan[i][1] + dp[i - 1][idx - plan[i][0]]);
			}
			if(ans < dp[i][N])
				ans = dp[i][N];
		}
		
		System.out.println(ans);
	}

}
