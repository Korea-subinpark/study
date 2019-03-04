import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
2차원 메모이제이션으로 각 행의 max값을 저장한다
max값은 한 열 이전의 max값에서 자신을 더한 것과 두 열 이전의 max 값에서 자신을 더한 것 중 더 큰 값을 
*/
public class Main_9465_스티커 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				arr[0][i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				arr[1][i] = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[2][N];
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			dp[0][1] = arr[1][0] + arr[0][1];
			dp[1][1] = arr[0][0] + arr[1][1];
			
			for(int i = 2; i < N; i++) {
				dp[0][i] = Math.max(dp[1][i - 1] + arr[0][i], Math.max(dp[0][i - 2], dp[1][i - 2]) + arr[0][i]);
				dp[1][i] = Math.max(dp[0][i - 1] + arr[1][i], Math.max(dp[0][i - 2], dp[1][i - 2]) + arr[1][i]);
			}
			
			sb.append(Math.max(dp[0][N - 1], dp[1][N - 1]) + "\n");
		}
		System.out.print(sb);
	}

}
