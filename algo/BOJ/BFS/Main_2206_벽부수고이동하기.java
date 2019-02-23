import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음에는 벽을 하나씩 없애고 모든 경우에 대해 bfs 탐색을 하였는데 시간이 초과되었다
 * 그 다음으로 큐에 벽을 부쉈는지 확인하는 변수를 같이 넣고 안부쉈을 경우 벽을 만나도 진행하는 방법으로 구현하였다
 * 또 다른 문제는 벽을 부수고 갔을 때와 벽을 부수지 않고 갔을 때 중복 처리가 달라야 한다는 점이다
 * 벽을 부수고 진행한 곳을 벽을 부수지 않은 상태로 가지 못한다면 
3 6
010000
010111
000110
 * 다음과 같이 도착지 부분이 벽으로 막혀있을 때 예외가 발생하게 된다
 * 
 */

public class Main_2206_벽부수고이동하기 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<P> q;
	static int min = Integer.MAX_VALUE;
	
	public static boolean inRange(int x, int y) {//범위 안에 있는지 확인
		if(x >= 0 && x < N && y >= 0 && y < M)
			return true;
		return false;
	}
	
	public static class P {//좌표와 현재까지 거리, 벽을 부쉈는지 여부 저장
		int x, y, len, crash;
		public P(int x, int y, int len, int crash) {
			this.x = x;
			this.y = y;
			this.len = len;
			this.crash = crash;
		}
	}
	
	public static void bfs() {
		final int[] dx = {1, -1, 0, 0};
		final int[] dy = {0, 0, 1, -1};
		
		P temp = q.poll();
		if(min <= (temp.len + 1))
			return;
		
		for(int i = 0; i < 4; i++) {//주변 순회
			int nx = temp.x + dx[i];
			int ny = temp.y + dy[i];
			if(inRange(nx, ny)) {
				if(nx == N - 1 && ny == M - 1) {
					min = temp.len + 1;
					return;
				} else if(temp.crash == 0) {
					if(!visited[nx][ny][0] && map[nx][ny] == 0) {
						visited[nx][ny][0] = true;
						q.add(new P(nx, ny, (temp.len + 1), 0));
					} else if(!visited[nx][ny][0] && map[nx][ny] == 1) {
						visited[nx][ny][0] = true;
						q.add(new P(nx, ny, (temp.len + 1), 1));
					}
				} else if(temp.crash == 1) {
					if(!visited[nx][ny][1] && map[nx][ny] == 0) {
						visited[nx][ny][1] = true;
						q.add(new P(nx, ny, (temp.len + 1), 1));
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
		
		map = new int[N][M];
		visited = new boolean[N][M][2]; //좌표와 벽을 부쉈는지 여부에 따른 방문 체크
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		if(N == 1 && M == 1) {//맵이 1x1일 때 예외처리
			System.out.println(1);
			return;
		}
		
		q.add(new P(0, 0, 1, 0));
		visited[0][0][0] = true;//좌표와 벽을 부쉈는지 여부에 따른 방문 체크
		visited[0][0][1] = true;
		while(!q.isEmpty()) {
			bfs();
		}
		
		if(min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println(-1);
		
	}

}
