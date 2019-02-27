import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4408_자기방으로돌아가기 {
	
	public static void main(String[] agrs) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][2];
			int[] aisle = new int[201];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				a = (a % 2 == 1) ? (a / 2) : (a / 2 - 1);
				b = (b % 2 == 1) ? (b / 2) : (b / 2 - 1);
				arr[i][0] = Math.min(a, b);
				arr[i][1] = Math.max(a, b);
			}
			
			for(int i = 0; i < N; i++)//지나간 복도 값 증가
				for(int j = arr[i][0]; j <= arr[i][1]; j++)
					aisle[j]++;
			
			int ans = 0;//최댓값 찾기
			for(int i = 1; i < aisle.length; i++)
				if(ans < aisle[i])
					ans = aisle[i];
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
