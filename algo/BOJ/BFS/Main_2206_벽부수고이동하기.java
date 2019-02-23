import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * ó������ ���� �ϳ��� ���ְ� ��� ��쿡 ���� bfs Ž���� �Ͽ��µ� �ð��� �ʰ��Ǿ���
 * �� �������� ť�� ���� �ν����� Ȯ���ϴ� ������ ���� �ְ� �Ⱥν��� ��� ���� ������ �����ϴ� ������� �����Ͽ���
 * �� �ٸ� ������ ���� �μ��� ���� ���� ���� �μ��� �ʰ� ���� �� �ߺ� ó���� �޶�� �Ѵٴ� ���̴�
 * ���� �μ��� ������ ���� ���� �μ��� ���� ���·� ���� ���Ѵٸ� 
3 6
010000
010111
000110
 * ������ ���� ������ �κ��� ������ �������� �� ���ܰ� �߻��ϰ� �ȴ�
 * 
 */

public class Main_2206_���μ����̵��ϱ� {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<P> q;
	static int min = Integer.MAX_VALUE;
	
	public static boolean inRange(int x, int y) {//���� �ȿ� �ִ��� Ȯ��
		if(x >= 0 && x < N && y >= 0 && y < M)
			return true;
		return false;
	}
	
	public static class P {//��ǥ�� ������� �Ÿ�, ���� �ν����� ���� ����
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
		
		for(int i = 0; i < 4; i++) {//�ֺ� ��ȸ
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
		visited = new boolean[N][M][2]; //��ǥ�� ���� �ν����� ���ο� ���� �湮 üũ
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		if(N == 1 && M == 1) {//���� 1x1�� �� ����ó��
			System.out.println(1);
			return;
		}
		
		q.add(new P(0, 0, 1, 0));
		visited[0][0][0] = true;//��ǥ�� ���� �ν����� ���ο� ���� �湮 üũ
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
