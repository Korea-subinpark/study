import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 주변을 순회하며 자신보다 1 작은 방을 찾으면 dfs로 탐색한다
 * */
public class Solution_1861_정사각형방 {
	static int N, max, maxNum;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void dfs(int i, int j, int cnt, int start) {
		for(int dir = 0; dir < 4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if(map[i][j] + 1 == map[nx][ny])//1 작은 방인 경우
				dfs(nx, ny, cnt + 1, start);
		}
		if(cnt > max) {
			max = cnt;
			maxNum = start;
		} else if (cnt == max) {
			maxNum = (start < maxNum) ? start : maxNum;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 2][N + 2];
			maxNum = Integer.MAX_VALUE;
			max = 0;
			
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 1; j <= N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++)
				for(int j = 1; j <= N; j++)
					dfs(i, j, 1, map[i][j]);
			
			sb.append("#" + tc + " " + maxNum + " " + max + "\n");
		}
		System.out.print(sb);
	}

}
