import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 깎을 수 있는 K값을 입력받고
 * 0부터 K값 까지 모든 경우에 대해서 dfs로 탐색한다.
 * 
 * */
public class Solution_1949_등산로조성 {
	static int N, K, maxCnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<int[]> list;//가장 높은 봉우리들의 좌표
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static void dfs(int x, int y, int cnt, int k, boolean flag) {
		if(maxCnt < cnt)
			maxCnt = cnt;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny) && !visited[nx][ny]) {
				if(map[x][y] > map[nx][ny]) {//더 작은 봉우리인 경우
					visited[nx][ny] = true;
					dfs(nx, ny, cnt + 1, k, flag);
					visited[nx][ny] = false;
				} else if(!flag && (map[x][y] > map[nx][ny] - k)) {//더 큰 봉우리지만 k만큼 깎았을 때 더 작아지는 경우
					map[nx][ny] -= k;
					visited[nx][ny] = true;
					dfs(nx, ny, cnt + 1, k, true);
					visited[nx][ny] = false;
					map[nx][ny] += k;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			list = new ArrayList<>();
			maxCnt = 0;
			
			int max = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max <= map[i][j]) {
						int[] temp = {i, j};
						if(max < map[i][j])//가장 큰 봉우리 저장
							list.clear();
						max = map[i][j];
						list.add(temp);
					}
				}
			}
			
			for(int k = 0; k <= K; k++) {
				for(int i = 0; i < list.size(); i++) {
					visited = new boolean[N][N];
					int[] cur = list.get(i);
					visited[cur[0]][cur[1]] = true;
					dfs(cur[0], cur[1], 1, k, false);
				}
			}
			
			System.out.println("#" + tc + " " + maxCnt);
		}
	}

}
