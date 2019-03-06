import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1082_ȭ������Ż�� {
	static int R, C;
	static char[][] map;
	static Obj start;//��� ����
	static boolean[][] visited;//����� ���� ��
	static Queue<Obj> q;
	static int min = Integer.MAX_VALUE;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static class Obj {
		int x, y, cnt;
		boolean fire;//���̸� true
		public Obj(int x, int y, int cnt, boolean fire) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.fire = fire;
		}
	}
	
	public static boolean inRange(int x, int y) {
		if(x >= 0 && x < R && y >= 0 && y < C)
			return true;
		return false;
	}
	
	public static void bfs() {
		Obj cur = q.poll();
		if(cur.fire) {//���� ���
			for(int i = 0; i < 4; i++) {//�ֺ� ��ȸ
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(inRange(nx, ny) && map[nx][ny] == '.') {//������� �� �Űܺ��̱�
					map[nx][ny] = '*';
					q.add(new Obj(nx, ny, 0, true));
				}
			}
		} else {//����� ���
			if(cur.cnt + 1 > min)
				return;
			for(int i = 0; i < 4; i++) {//�ֺ� ��ȸ
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
                if(inRange(nx, ny)) {
				    if(!visited[nx][ny] && map[nx][ny] == '.') {//���� �湮���� ���� ������ �̵�
					    visited[nx][ny] = true;
					    q.add(new Obj(nx, ny, cur.cnt + 1, false));
				    } else if(map[nx][ny] == 'D') {//���� ������ ��� �ּڰ� ����
				    	if(cur.cnt < min)
					    	min = cur.cnt;
					    return;
				    }
                }
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				switch(map[i][j]) {
					case '*':
						q.add(new Obj(i, j, 0, true));
						break;
					case 'S':
						start = new Obj(i, j, 1, false);
						visited[i][j] = true;
						map[i][j] = '.';
						break;
					default:
						break;
				}
			}
		}
		q.add(start);
		
		while(!q.isEmpty()) {
			bfs();
		}
		
		if(min == Integer.MAX_VALUE)
			System.out.println("impossible");
		else
			System.out.println(min);
		
	}

}
