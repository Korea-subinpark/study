import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_백조의호수 {
	static int N, M;
	static char[][] map;
	static int [][] timeMap;//얼음이 녹는 시간 마킹
	static Queue<P> q;
	static boolean finish;//백조가 만날 수 있는 경우 true
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static class P {
		int x, y, day;
		public P (int x, int y) {
			this.x = x;
			this.y = y;
		}
		public P (int x, int y, int day) {
			this(x, y);
			this.day = day;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
	
	public static int meltBfs() {//물이 있는 곳부터 bfs 탐색으로 얼음이 녹는 시간을 timeMap에 저장
		int maxDay = 0;//가장 늦게 녹는 얼음이 녹는 시간
		while(!q.isEmpty()) {
			P cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(inRange(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					timeMap[nx][ny] = cur.day + 1;
					maxDay = cur.day + 1;
					q.add(new P(nx, ny, cur.day + 1));
				}
			}
		}
		return maxDay;//가장 늦게 녹는 얼음이 녹는 시간
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			P cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(inRange(nx, ny)) {
					if(map[nx][ny] == 'L') {
						finish = true;//다른 백조와 만난 경우
						return;
					} else if(!visited[nx][ny] && timeMap[nx][ny] <= cur.day) {
						visited[nx][ny] = true;
						q.add(new P(nx, ny, cur.day));
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
		map = new char[N][M];
		timeMap = new int[N][M];
		q = new LinkedList<>();
		P[] L = new P[2];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'L') {
					if(L[0] == null) L[0] = new P(i, j);
					else L[1] = new P(i, j);
					map[i][j] = '.';
				}
			}
		}
		
		//물이 있는 곳으로부터 bfs탐색하기 위해 모든 물의 위치를 큐에 저장
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '.') {
					visited[i][j] = true;
					q.add(new P(i, j, 0));
				}
			}
		}
		int maxDay = meltBfs();
		
		map[L[1].x][L[1].y] = 'L';
		int curDay = 0;
		while(curDay <= maxDay) {//백조가 만날 수 있는 시간을 이분 탐색
			int mid = (curDay + maxDay) >> 1;
			visited = new boolean[N][M];
			finish = false;
			q.clear();
			visited[L[0].x][L[0].y] = true;
			q.add(new P(L[0].x, L[0].y, mid));
			bfs();
			if(finish)
				maxDay = mid - 1;
			else
				curDay = mid + 1;
			
		}
		System.out.println(curDay);
	}

}
