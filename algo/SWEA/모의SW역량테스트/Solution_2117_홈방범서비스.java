import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스 {
	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;
	static Queue<P> q;
	
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
		int currentCnt = 0;
		int houseCnt = 0;
		Queue<P> q2 = new LinkedList<>();
		
		while(!q.isEmpty()) {
			while(!q.isEmpty()) {
				P cur = q.poll();
				if(map[cur.x][cur.y] == 1)//집 개수 카운트
					houseCnt++;
				currentCnt = cur.cnt;//현재 K값 저장
				for(int i = 0; i < 4; i++) {//한 칸씩 전진
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(inRange(nx ,ny)) {
						if(!visited[nx][ny]) {
							visited[nx][ny] = true;
							q2.add(new P(nx, ny, cur.cnt + 1));
						}
					}
				}
			}
			int cost = (currentCnt * currentCnt) + ((currentCnt - 1) * (currentCnt - 1));//비용 계산
			if(cost <= (houseCnt * M) && max < houseCnt)//이득을 볼 수 있는 경우 최대 집 수 업데이트
				max = houseCnt;
			while(!q2.isEmpty()) {//큐 옮겨담기
				q.add(q2.poll());
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = 0;
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {//맵의 모든 곳을 출발점으로 bfs 탐색
					visited = new boolean[N][N];
					q = new LinkedList<>();
					visited[i][j] = true;
					q.add(new P(i, j, 1));
					bfs();
				}
			}
			
			System.out.println("#" + tc +  " " + max);
		}
	}

}
