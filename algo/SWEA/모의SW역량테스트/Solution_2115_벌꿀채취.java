import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {
	static int N, M, C, max;
	static int[][] map;
	static boolean[] visited;
	
	public static boolean valid(int x, int y, int x2, int y2) {
		return ((y + M) <= N && (y2 + M) <= N && (x != x2 || (x == x2 && (y + M) <= y2)));
	}
	
	public static void dfs(int[] arr, int depth, int sum, int sqrSum) {
		if(depth == M) {//모든 벌통을 순회한 경우
			if(max < sqrSum)
				max = sqrSum;
			return;
		}

		if(sum + arr[depth] <= C)//채취할 수 있는 최대량을 넘지 않는 경우
		    dfs(arr, depth + 1, sum + arr[depth], sqrSum + arr[depth] * arr[depth]);//현재 벌통에서 채취했을 때
		dfs(arr, depth + 1, sum, sqrSum);//현재 벌통에서 채취하지 않았을 때
	}
	
	public static int chk(int x, int y, int x2, int y2) {
		int[] a = new int[M];
		int[] b = new int[M];
		for(int i = 0; i < M; i++) {//채취할 벌통 복사
			a[i] = map[x][y + i];
			b[i] = map[x2][y2 + i];
		}
		
		int ret = 0;
		max = 0;
		dfs(a, 0, 0, 0);//첫 번째 일꾼이 최대로 얻을 수 있는 수익
		ret += max;
		max = 0;
		dfs(b, 0, 0, 0);//두 번째 일꾼이 최대로 얻을 수 있는 수익
		ret += max;
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < N; k++) {
						for(int m = 0; m < N; m++) {
							if(valid(i, j, k, m)) {//벌통이 서로 겹치지 않는지 체크
								int ret = chk(i, j, k, m);
								if(ans < ret)//최댓값 업데이트
									ans = ret;
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}
