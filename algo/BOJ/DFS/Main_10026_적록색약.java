import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_10026_적록색약_박수빈 {
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int ans;//구역 수
	
	public static void dfs(char[][] arr, int x, int y) {
		if(!visited[x][y]) {
			visited[x][y] = true;
			char c = arr[x][y];
			for(int i = 0; i < 4; i++) {//주변 순회
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(arr[nx][ny] == c) {
					dfs(arr, nx, ny);
				}
			}
			if(c == 'G')//적록색약용 arr를 만들기 위해 G를 R로 변경
				arr[x][y] = 'R';
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		char[][] arr = new char[N + 2][N + 2];
		visited = new boolean[N + 1][N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j + 1] = str.charAt(j);
			}
		}
		
		for(int i = 1; i < N + 1; i++) {//일반인 dfs
			for(int j = 1; j < N + 1; j++) {
				if(!visited[i][j]) {
					dfs(arr, i, j);
					ans++;//구역 수 증가
				}
			}
		}
		
		System.out.print(ans + " ");
		ans = 0;
		visited = new boolean[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {//적록색약 dfs
			for(int j = 1; j < N + 1; j++) {
				if(!visited[i][j]) {
					dfs(arr, i, j);
					ans++;
				}
			}
		}
		
		System.out.print(ans);
	}

}
