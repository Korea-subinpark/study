import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs 탐색으로 안전하지 않은 구역을 모두 찾고 체크 되지 않은 구역을 P로 바꾼 뒤에 출력한다
 * 늑대가 빙판을 밟았을 경우 여러 방향으로 밟을 수 있기 때문에 빙판의 중간은 방문 체크를 하지 않는다
 * 빙판을 밟아서 산에 막힌 경우에만 빙판의 끝을 방문 체크 한다
 * */

public class Main_16441_아기돼지와늑대 {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static Queue<P> q;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static class P {
		int x, y;
		public P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs() {
		P cur = q.poll();
		
		for(int i = 0; i < 4; i++) {
			int nx = cur.x + dx[i];
			int ny = cur.y + dy[i];
			if(!visited[nx][ny] && map[nx][ny] == '.') {//방문하지 않은 초원인 경우
				visited[nx][ny] = true;
				q.add(new P(nx, ny));
			} else if(map[nx][ny] == '+') {//빙판을 밟은 경우
                while(map[nx][ny] == '+') {//빙판이 아닐 때까지 전진
                    nx += dx[i];
                    ny += dy[i];
                }
                if(map[nx][ny] == '#') {//산을 만난 경우 한 칸 뒤로 후진
                    nx -= dx[i];
                    ny -= dy[i];
                }
                if(!visited[nx][ny]) {//방문하지 않은 경우 큐에 추가 (산을 만난 빙판의 끝 또는 초원이 올 수 있다)
                    visited[nx][ny] = true;
                    q.add(new P(nx, ny));
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
		visited = new boolean[N][M];
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'W') {//늑대 위치 저장
					visited[i][j] = true;
					q.add(new P(i, j));
				} else if(map[i][j] == '#')//산은 방문 체크
                    visited[i][j] = true;
			}
		}
		
		while(!q.isEmpty())
			bfs();
		
		//결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == '.')
					map[i][j] = 'P';
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
