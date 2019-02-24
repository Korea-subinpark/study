import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs Ž������ �������� ���� ������ ��� ã�� üũ ���� ���� ������ P�� �ٲ� �ڿ� ����Ѵ�
 * ���밡 ������ ����� ��� ���� �������� ���� �� �ֱ� ������ ������ �߰��� �湮 üũ�� ���� �ʴ´�
 * ������ ��Ƽ� �꿡 ���� ��쿡�� ������ ���� �湮 üũ �Ѵ�
 * */

public class Main_16441_�Ʊ�����ʹ��� {
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
			if(!visited[nx][ny] && map[nx][ny] == '.') {//�湮���� ���� �ʿ��� ���
				visited[nx][ny] = true;
				q.add(new P(nx, ny));
			} else if(map[nx][ny] == '+') {//������ ���� ���
                while(map[nx][ny] == '+') {//������ �ƴ� ������ ����
                    nx += dx[i];
                    ny += dy[i];
                }
                if(map[nx][ny] == '#') {//���� ���� ��� �� ĭ �ڷ� ����
                    nx -= dx[i];
                    ny -= dy[i];
                }
                if(!visited[nx][ny]) {//�湮���� ���� ��� ť�� �߰� (���� ���� ������ �� �Ǵ� �ʿ��� �� �� �ִ�)
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
				if(map[i][j] == 'W') {//���� ��ġ ����
					visited[i][j] = true;
					q.add(new P(i, j));
				} else if(map[i][j] == '#')//���� �湮 üũ
                    visited[i][j] = true;
			}
		}
		
		while(!q.isEmpty())
			bfs();
		
		//��� ���
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
