import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3184_¾ç {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int sheep, wolf;
	static int totalSheep, totalWolf;
	
	public static boolean inRange(int x, int y) {
		if(x >= 0 && x < R && y >= 0 && y < C)
			return true;
		return false;
	}
	
	public static void bfs() {
		final int[] dx = {1, -1, 0, 0};
		final int[] dy = {0, 0, 1, -1};
		
		int[] cur = q.poll();
		if(map[cur[0]][cur[1]] == 'o')
			sheep++;
		else if(map[cur[0]][cur[1]] == 'v')
			wolf++;
		
		for(int i = 0; i < 4; i++) {
			int nx = cur[0] + dx[i];
			int ny = cur[1] + dy[i];
			
			if(inRange(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				if(map[nx][ny] != '#') {
					int[] next = {nx, ny};
					q.add(next);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		q = new LinkedList<>();
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(!visited[i][j] && map[i][j] != '#') {
					visited[i][j] = true;
					int[] start = {i, j};
					q.add(start);
					
					while(!q.isEmpty()) {
						bfs();
					}
					
					if(sheep > wolf) {
						totalSheep += sheep;
					} else {
						totalWolf += wolf;
					}
					sheep = 0;
					wolf = 0;
				}
			}
		}
		
		System.out.print(totalSheep + " " + totalWolf);
	}

}
