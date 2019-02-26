import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플로이드 워샬 알고리즘으로 최솟값을 구한다
 * */

public class Solution_1263_사람네트워크2_박수빈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 0)
						arr[i][j] = 1000000;
				}
			}
			
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) continue;
					for(int j = 0; j < N; j++) {
						if(j == k || j == i) continue;
						arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
					}
				}
			}
			
			int ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				int total = 0;
				for(int j = 0; j < N; j++) {
					if(i != j) {
						total += arr[i][j];
					}
				}
				if(ans > total)
					ans = total;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

}
