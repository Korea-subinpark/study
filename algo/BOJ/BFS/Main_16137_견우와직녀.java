import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16137_견우와직녀 {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Obj> q;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static boolean isCross(int x, int y) {//교차되는 지점인지 확인
		if(inRange(x + 1, y) && inRange(x - 1, y) && inRange(x, y + 1) && inRange(x, y - 1)) {
			if((map[x + 1][y] != 1 || map[x - 1][y] != 1) && (map[x][y + 1] != 1 || map[x][y - 1] != 1))
				return true;
		}
		return false;
	}
	
	public static class Obj {
		int x, y, cnt;
		public Obj(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			Obj cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx == N - 1 && ny == N - 1) {//도착했을 경우
					if(min > cur.cnt + 1)
						min = cur.cnt + 1;
					q.clear();
					return;
				}
				if(inRange(nx, ny) && !visited[nx][ny]) {
					if(map[nx][ny] == 1) {//일반적인 땅인 경우
						visited[nx][ny] = true;
						q.add(new Obj(nx, ny, cur.cnt + 1));
					} else if(map[nx][ny] != 0) {
						if((cur.cnt + 1) % map[nx][ny] == 0 && map[cur.x][cur.y] == 1) {//주기가 맞는 다리인 경우
							visited[nx][ny] = true;
							q.add(new Obj(nx, ny, cur.cnt + 1));
						} else {//건널 수 있을 때까지 기다리기
							q.add(new Obj(cur.x, cur.y, cur.cnt + 1));
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0 && !isCross(i, j)) {//교차되지 않은 절벽에 새로운 다리 놓아보기
					visited = new boolean[N][N];
					visited[0][0] = true;
					q.add(new Obj(0, 0, 0));
					map[i][j] = M;//다리 놓기
					bfs();
					map[i][j] = 0;//원상 복구
				}
			}
		}
		
		System.out.println(min);
	}

}
