import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
	int rx, ry, bx, by;// 빨간 구슬의 좌표와 파란 구슬의 좌표
	int cnt;// 기울이기 방향이 바뀐 횟수
}

class Main_13460_구슬탈출2 {
	static int[][] arr;
	static Queue<Position> q;
	static int N, M;
	static boolean[][][][] visited;

	public static int bfs() {
		int ans = -1;// 기울기 방향이 바뀐 횟수를 저장할 변수, 실패할 경우 -1
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		while (!q.isEmpty()) {// 큐에 남은 데이터가 없을 때까지 반복
			Position temp = q.poll();
			if (temp.cnt > 10)// 기울이기 방향을 바꾼 횟수가 10을 초과하면 실패
				break;
			if (arr[temp.rx][temp.ry] == 'O' && arr[temp.bx][temp.by] != 'O') {// 빨간 구슬만 구멍으로 빠진 경우
				ans = temp.cnt;
				break;
			} 

			for (int i = 0; i < 4; i++) {// 4방향으로 이동
				int nrx = temp.rx;
				int nry = temp.ry;
				int nbx = temp.bx;
				int nby = temp.by;
				while (true) {// 빨간 구슬 이동 시작
					if (arr[nrx][nry] != '#' && arr[nrx][nry] != 'O') {// 벽이나 구멍이 아닌 경우 계속 이동
						nrx += dx[i];
						nry += dy[i];
					} else {// 벽이나 구멍을 만난 경우
						if (arr[nrx][nry] == '#') {// 현재 위치가 벽인 경우 한 칸 이전으로 이동
							nrx -= dx[i];
							nry -= dy[i];
						}
						break;
					}
				} // 빨간 구슬 이동 끝
				while (true) {// 파란 구슬 이동 시작
					if (arr[nbx][nby] != '#' && arr[nbx][nby] != 'O') {// 벽이나 구멍이 아닌 경우 계속 이동
						nbx += dx[i];
						nby += dy[i];
					} else {// 벽이나 구멍을 만난 경우
						if (arr[nbx][nby] == '#') {// 현재 위치가 벽인 경우 한 칸 이전으로 이동
							nbx -= dx[i];
							nby -= dy[i];
						}
						break;
					}
				} // 파란 구슬 이동 끝
				
				if(arr[nbx][nby] == 'O') continue;//파란 구슬이 구멍에 빠진 경우 넘어간다

				if (arr[nrx][nry] != 'O' && nrx == nbx && nry == nby) { // 두 구슬이 같은 위치에 있는 경우 (동시에 구멍에 빠진 경우 제외)
					int red_len = Math.abs(nrx - temp.rx) + Math.abs(nry - temp.ry);// 각 구슬의 이동 거리를 저장
					int blue_len = Math.abs(nbx - temp.bx) + Math.abs(nby - temp.by);
					if (red_len < blue_len) {// 더 많이 이동한 구슬이 더 늦게 도착한 것이므로 한 칸 이전으로 이동
						nbx -= dx[i];
						nby -= dy[i];
					} else {
						nrx -= dx[i];
						nry -= dy[i];
					}
				}

				if (!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby] = true;
					Position next = new Position();// 현재 방향으로 이동이 끝난 경우 다른 방향으로 가기위해 큐에 add
					next.rx = nrx;
					next.ry = nry;
					next.bx = nbx;
					next.by = nby;
					next.cnt = temp.cnt + 1;
					q.add(next);
				}

			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		Position begin = new Position();
		visited = new boolean[N][M][N][M];// 빨간 구슬과 파란 구슬의 방문 여부
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'R') {// 빨간 구슬의 초기 위치 저장
					begin.rx = i;
					begin.ry = j;
				} else if (arr[i][j] == 'B') {// 파란 구슬의 초기 위치 저장
					begin.bx = i;
					begin.by = j;
				}
			}
		}
		visited[begin.rx][begin.ry][begin.bx][begin.by] = true;// 초기 위치 방문 표시
		q.add(begin);
		System.out.print(bfs());
	}
}
