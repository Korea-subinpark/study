import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_�����������ٰ��� {
	static int N, M;
	static char[][] map;
	static boolean[][][][][][][][] visited;//������ ���տ� ���� �ߺ�üũ
	static Queue<Obj> q;
	static int min = -1;
	
	public static class Obj {
		int x, y, cnt;
		int[] key;//���踦 ������ �ִ��� ����
		public Obj(int x, int y, int cnt) {
			key = new int[6];
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
	
	public static void bfs() {
		final int[] dx = {-1, 1, 0, 0};
		final int[] dy = {0, 0, -1, 1};
		
		while(!q.isEmpty()) {
			Obj o = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = o.x + dx[i];
				int ny = o.y + dy[i];
				if(inRange(nx, ny)) {
					if(map[nx][ny] == '1') {//������ ����
						min = o.cnt + 1;
						q.clear();
						break;
					}
					if(map[nx][ny] == '#') continue;//���� ��� continue
					
					Obj n = new Obj(nx, ny, o.cnt + 1);
					System.arraycopy(o.key, 0, n.key, 0, 6);
					
					if('a' <= map[nx][ny] && map[nx][ny] <= 'f')//���谡 ������ ��
						n.key[map[nx][ny] - 'a'] = 1;
					else if('A' <= map[nx][ny] && map[nx][ny] <= 'F')//���� �ִ� ��
						if(o.key[map[nx][ny] - 'A'] == 0) continue;//���� �´� ���谡 ���� ��� continue
					
					if(!visited[nx][ny][o.key[0]][o.key[1]][o.key[2]][o.key[3]][o.key[4]][o.key[5]]) {//���� �湮���� ���� ��
						visited[nx][ny][o.key[0]][o.key[1]][o.key[2]][o.key[3]][o.key[4]][o.key[5]] = true;
						q.add(n);
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
		visited = new boolean[N][M][2][2][2][2][2][2];
		q = new LinkedList<>();
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '0') {
					Obj start = new Obj(i, j, 0);
					visited[i][j][0][0][0][0][0][0] = true;
					q.add(start);
					map[i][j] = '.';
				}
			}
		}
		
		bfs();
		System.out.println(min);
	}

}
