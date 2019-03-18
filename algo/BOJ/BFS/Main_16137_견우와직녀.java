import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16137_�߿������ {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Obj> q;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static boolean isCross(int x, int y) {//�����Ǵ� �������� Ȯ��
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
				if(nx == N - 1 && ny == N - 1) {//�������� ���
					if(min > cur.cnt + 1)
						min = cur.cnt + 1;
					q.clear();
					return;
				}
				if(inRange(nx, ny) && !visited[nx][ny]) {
					if(map[nx][ny] == 1) {//�Ϲ����� ���� ���
						visited[nx][ny] = true;
						q.add(new Obj(nx, ny, cur.cnt + 1));
					} else if(map[nx][ny] != 0) {
						if((cur.cnt + 1) % map[nx][ny] == 0 && map[cur.x][cur.y] == 1) {//�ֱⰡ �´� �ٸ��� ���
							visited[nx][ny] = true;
							q.add(new Obj(nx, ny, cur.cnt + 1));
						} else {//�ǳ� �� ���� ������ ��ٸ���
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
				if(map[i][j] == 0 && !isCross(i, j)) {//�������� ���� ������ ���ο� �ٸ� ���ƺ���
					visited = new boolean[N][N];
					visited[0][0] = true;
					q.add(new Obj(0, 0, 0));
					map[i][j] = M;//�ٸ� ����
					bfs();
					map[i][j] = 0;//���� ����
				}
			}
		}
		
		System.out.println(min);
	}

}
