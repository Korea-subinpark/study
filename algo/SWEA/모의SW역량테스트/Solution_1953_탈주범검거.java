import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * ���� ���������� �� �� �ִ� ����� ���� �������� ���� �� �� �ִ��� ���θ� Ȯ���Ͽ� bfsŽ���� �Ѵ�
 * */
public class Solution_1953_Ż�ֹ��˰� {
	static int N, M, L, ans;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pipe> q;
	
	static int[] dx = {-1, 1, 0, 0};//�����¿�
	static int[] dy = {0, 0, -1, 1};
	
	public static class Pipe {
		int x, y, cnt;
		public Pipe(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		Pipe cur = q.poll();
		if(cur.cnt == L)
			return;
		switch(map[cur.x][cur.y]) {
			case 1://�����¿� �ͳ�
				for(int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(!visited[nx][ny] && map[nx][ny] != 0) {
						if(i == 0 && map[nx][ny] != 3 && map[nx][ny] != 4 && map[nx][ny] != 7) {
							visited[nx][ny] = true;//���� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						} else if(i == 1 && map[nx][ny] != 3 && map[nx][ny] != 5 && map[nx][ny] != 6) {
							visited[nx][ny] = true;//�Ʒ��� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						} else if(i == 2 && map[nx][ny] != 2 && map[nx][ny] != 6 && map[nx][ny] != 7) {
							visited[nx][ny] = true;//�·� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						} else if(i == 3 && map[nx][ny] != 2 && map[nx][ny] != 4 && map[nx][ny] != 5) {
							visited[nx][ny] = true;//��� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						}
					}
				}
				break;
			case 2://���� �ͳ�
				for(int i = 0; i < 2; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(!visited[nx][ny] && map[nx][ny] != 0) {
						if(i == 0 && map[nx][ny] != 3 && map[nx][ny] != 4 && map[nx][ny] != 7) {
							visited[nx][ny] = true;//���� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						} else if(i == 1 && map[nx][ny] != 3 && map[nx][ny] != 5 && map[nx][ny] != 6) {
							visited[nx][ny] = true;//�Ʒ��� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						}
					}
				}
				break;
			case 3://�¿� �ͳ�
				for(int i = 2; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(!visited[nx][ny] && map[nx][ny] != 0) {
						if(i == 2 && map[nx][ny] != 2 && map[nx][ny] != 6 && map[nx][ny] != 7) {
							visited[nx][ny] = true;//�·� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						} else if(i == 3 && map[nx][ny] != 2 && map[nx][ny] != 4 && map[nx][ny] != 5) {
							visited[nx][ny] = true;//��� ���� ������ ��
							ans++;
							q.add(new Pipe(nx, ny, cur.cnt + 1));
						}
					}
				}
				break;
			case 4://��� �ͳ�
				int nx = cur.x + dx[0];
				int ny = cur.y + dy[0];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 3 && map[nx][ny] != 4 && map[nx][ny] != 7) {
					visited[nx][ny] = true;//���� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				nx = cur.x + dx[3];
				ny = cur.y + dy[3];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 2 && map[nx][ny] != 4 && map[nx][ny] != 5) {
					visited[nx][ny] = true;//��� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				break;
			case 5://�Ͽ� �ͳ�
				nx = cur.x + dx[1];
				ny = cur.y + dy[1];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 3 && map[nx][ny] != 5 && map[nx][ny] != 6) {
					visited[nx][ny] = true;//�Ʒ��� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				nx = cur.x + dx[3];
				ny = cur.y + dy[3];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 2 && map[nx][ny] != 4 && map[nx][ny] != 5) {
					visited[nx][ny] = true;//��� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				break;
			case 6://���� �ͳ�
				nx = cur.x + dx[1];
				ny = cur.y + dy[1];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 3 && map[nx][ny] != 5 && map[nx][ny] != 6) {
					visited[nx][ny] = true;//�Ʒ��� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				nx = cur.x + dx[2];
				ny = cur.y + dy[2];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 2 && map[nx][ny] != 6 && map[nx][ny] != 7) {
					visited[nx][ny] = true;//�·� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				break;
			case 7://���� �ͳ�
				nx = cur.x + dx[0];
				ny = cur.y + dy[0];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 3 && map[nx][ny] != 4 && map[nx][ny] != 7) {
					visited[nx][ny] = true;//���� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				nx = cur.x + dx[2];
				ny = cur.y + dy[2];
				if(!visited[nx][ny] && map[nx][ny] != 0 && map[nx][ny] != 2 && map[nx][ny] != 6 && map[nx][ny] != 7) {
					visited[nx][ny] = true;//�·� ���� ������ ��
					ans++;
					q.add(new Pipe(nx, ny, cur.cnt + 1));
				}
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 1;
			map = new int[N + 2][M + 2];
			visited = new boolean[N + 2][M + 2];
			q = new LinkedList<>();
			
			int[] start = new int[2];//��Ȧ ��ġ
			start[0] = Integer.parseInt(st.nextToken()) + 1;
			start[1] = Integer.parseInt(st.nextToken()) + 1;
			L = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 1; j <= M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//0: ��, 1: ��, 2: ��, 3: ��
			visited[start[0]][start[1]] = true;
			q.add(new Pipe(start[0], start[1], 1));
			
			while(!q.isEmpty()) {
				bfs();
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
