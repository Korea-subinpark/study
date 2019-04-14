import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17141_연구소2 {
	static int N, M, min, blank;
	static int[][] map;
	static P[] comb;//놓을 곳 조합
	static ArrayList<P> list;//놓을 수 있는 위치
	static Queue<P> q;
	static boolean[][] visited;
	static boolean complete;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class P {
		int x, y, cnt;
		public P(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static void bfs() {
		int remain = blank;//남은 칸 수 초기화
		for(int i = 0; i < M; i++) {//시작 위치 큐에 삽입
			P cur = comb[i];
			visited[cur.x][cur.y] = true;
			remain--;
			q.add(cur);
		}
		
		while(!q.isEmpty()) {
			P cur = q.poll();
			
			for(int i = 0; i < 4; i++) {//4방향 순회
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(inRange(nx, ny)) {
					if(!visited[nx][ny]) {
						if(map[nx][ny] != 1) {//벽이 아닌 경우 진행
							visited[nx][ny] = true;
							remain--;
							q.add(new P(nx, ny, cur.cnt + 1));
						}
					}
				}
			}
			if(remain == 0) {//최솟값 업데이트
				if(min > cur.cnt + 1)
					min = cur.cnt + 1;
				return;
			}
		}
		
	}
	
	public static void dfs(int depth, int idx) {
		if(depth == M) {//시작 위치 조합이 완성되었을 때
			visited = new boolean[N][N];
			q.clear();
			bfs();
			return;
		}
		
		for(int i = idx; i < list.size() - M + 1 + depth; i++) {//시작 위치 조합
			comb[depth] = list.get(i);
			dfs(depth + 1, i + 1);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		list = new ArrayList<>();
		map = new int[N][N];
		comb = new P[M];
		min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					list.add(new P(i, j, 0));
				}
				if(map[i][j] != 1) {//벽이 아닌 칸 수 카운트
					blank++;
				}
			}
		}
		
		dfs(0, 0);
		
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

}
