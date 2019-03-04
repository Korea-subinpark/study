import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
주변 지뢰 수를 표시하도록 map을 수정한 뒤에 dfs로 탐색하며 최소 클릭 수를 계
*/
public class Solution_1868_파핑파핑지뢰찾기 {
	static int N;
	static char[][] map;
	static int min;
	static boolean[][] visited;
	
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
	static int[] dy = {0, 0, 1, -1, 1, 1, -1, -1};
	
	public static class Position {
		int x, y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void count(int x, int y) {//주변의 지뢰 수 만큼 값 바꾸기
		int cnt = 0;
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(map[nx][ny] == '*')
				cnt++;
		}
		map[x][y] = (char) (cnt + '0');
	}
	
	public static void dfs(int x, int y) {
		boolean flag = false;
		if(map[x][y] == '0')
			flag = true;
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!visited[nx][ny] && map[nx][ny] != '*') {
				if(flag) {//주변에 지뢰가 없는 경우 주변을 전부 다 방문한 것으로 체크
					visited[nx][ny] = true;
					if(map[nx][ny] == '0')
						dfs(nx, ny);
				} else {
					if(map[nx][ny] == '0') {//주변에 지뢰가 있는 경우 주변에 0인 것만 방문 체크
						visited[nx][ny] = true;
						dfs(nx, ny);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N + 2][N + 2];
			min = Integer.MAX_VALUE;
			
			//padding
			for(int i = 0; i < N + 2; i++) {
				map[0][i] = '1';
				map[N + 1][i] = '1';
				map[i][0] = '1';
				map[i][N + 1] = '1';
			}
			
			for(int i = 1; i <= N; i++) {
				String str = br.readLine();
				for(int j = 1; j <= N; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}
			
			for(int i = 1; i <= N; i++) {//주변 지뢰 수 세기
				for(int j = 1; j <= N; j++) {
					if(map[i][j] == '.') {
						count(i, j);
					}
				}
			}
			
			int ans = 0;
			visited = new boolean[N + 2][N + 2];
			for(int i = 1; i <= N; i++) {//최소 클릭 수 세기
				for(int j = 1; j <= N; j++) {
					if(!visited[i][j] && map[i][j] != '*') {
						visited[i][j] = true;
						dfs(i, j);
						ans++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}
