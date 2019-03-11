import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
	static boolean[][] map;//갈 수 있는 곳 true
	static int N, ans;
	
	//dir -> 0: 오른/ 1: 대각/ 2: 아래
	public static void dfs(int i, int j, int dir) {
		if(i == N && j == N) {
			ans++;
			return;
		}
		switch(dir) {
			case 0:
				if(map[i][j + 1])
					dfs(i, j + 1, 0);
				if(map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					dfs(i + 1, j + 1, 1);
				break;
			case 1:
				if(map[i][j + 1])
					dfs(i, j + 1, 0);
				if(map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					dfs(i + 1, j + 1, 1);
				if(map[i + 1][j])
					dfs(i + 1, j, 2);
				break;
			case 2:
				if(map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					dfs(i + 1, j + 1, 1);
				if(map[i + 1][j])
					dfs(i + 1, j, 2);
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[N + 2][N + 2];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 0)
					map[i][j] = true;
			}
		}
		
		dfs(1, 2, 0);
		
		System.out.println(ans);
	}

}
