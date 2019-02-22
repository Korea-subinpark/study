import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2468_안전영역 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int min, max;//최소 강수량과 최대 강수량
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static boolean inRange(int x, int y) {//현재 좌표가 범위 안에 있는지 확인
		if(x >= 0 && x < N && y >= 0 && y < N)
			return true;
		return false;
	}
	
	public static void dfs(int x, int y, int rain) {
		if(!visited[x][y] && map[x][y] > rain) {//방문여부와 강수량보다 높은지 확인
			visited[x][y] = true;
			
			for(int i = 0; i < 4; i++) {//주변 순회
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] > rain) {
					dfs(nx, ny, rain);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		min = 200;
		max = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(min > map[i][j])//map의 최솟값과 최댓값 저장
					min = map[i][j];
				else if(max < map[i][j])
					max = map[i][j];
			}
		}
		
		int ans = 0;
		for(int rain = min - 1; rain < max; rain++) {//강수량을 최소부터 최대까지 반복
			int cnt = 0;
			for(int i = 0; i < N; i++) {//모든 map을 돌면서 dfs로 확인
				for(int j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] > rain) {
						dfs(i, j, rain);
						cnt++;//영역 수 증가
					}
				}
			}
			if(ans < cnt) {//영역 수 업데이트
				ans = cnt;
			}
			visited = new boolean[N][N];
		}
		
		System.out.println(ans);
	}

}
