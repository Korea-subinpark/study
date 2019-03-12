import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7250_Ż�� {
	static int N, M, K;
	static int min;
	static char[][] map;
	static boolean[][] visitedAnt;
	static boolean[][] visitedBad;
	static boolean badFirst;
	static Queue<Obj> q;
	
	public static class Obj {
		int r, c, k, cnt;
		char type;
		public Obj(int r, int c, int k, int cnt, char type) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
			this.type = type;
		}
	}
	
	public static boolean inRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
	
	public static void bfs() {
		final int[] dr = {1, -1, 0, 0};
		final int[] dc = {0, 0, 1, -1};
		Obj current = q.poll();
		for(int i = 0; i < 4; i++) {
			int nr = current.r + dr[i];
			int nc = current.c + dc[i];
			if(inRange(nr, nc)) {
				switch(current.type) {
				case 'F'://��
					if(map[nr][nc] == 'A') {//������ �Ű� ���̱�
						map[nr][nc] = 'F';
						q.add(new Obj(nr, nc, 0, 0, 'F'));
					}
					break;
				case 'S'://��Ʈ��
					if(map[nr][nc] != 'F' && map[nr][nc] !='X' && !visitedAnt[nr][nc]) {
						if(map[nr][nc] == 'E') {//�� ����
							if(current.cnt + 1 < min) 
								min = current.cnt + 1;
							return;
						} else if(map[nr][nc] == 'A') {//������ �� ���� ���� �� �ִ� �ð� �ʱ�ȭ
							visitedAnt[nr][nc] = true;
							q.add(new Obj(nr, nc, K, current.cnt + 1, 'S'));
						} else if(map[nr][nc] == 'W' && current.k != 0) {//���̰� ���� ���� �� �ִ� ���
							q.add(new Obj(nr, nc, current.k - 1, current.cnt + 1, 'S'));
						}
					}
					break;
				case 'V'://�Ǵ�
					if(map[nr][nc] != 'X' && map[nr][nc] != 'W' && !visitedBad[nr][nc]) {
						if(map[nr][nc] == 'E') {//�Ǵ��� ���� ������ ���
							badFirst = true;
							return;
						} else if(map[nr][nc] == 'A' || map[nr][nc] == 'F') {//���̳� ������ ��� �̵�
							visitedBad[nr][nc] = true;
							q.add(new Obj(nr, nc, 0, 0, 'V'));
						}
					}
					break;
				}
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
			K = Integer.parseInt(st.nextToken());
			min  = Integer.MAX_VALUE;
			q = new LinkedList<>();
			
			map = new char[N][M];
			visitedAnt = new boolean[N][M];
			visitedBad = new boolean[N][M];
			badFirst = false;//�Ǵ��� ���� ����
			
			Obj antMan = null;
			Obj badMan = null;
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					switch(map[i][j]) {
						case 'S'://��Ʈ�� ����� ����
							antMan = new Obj(i, j, K, 0, 'S');
							map[i][j] = 'A';
							visitedAnt[i][j] = true;
							break;
						case 'V'://�Ǵ� ����� ����
							badMan = new Obj(i, j, 0, 0, 'V');
							map[i][j] = 'A';
							visitedBad[i][j] = true;
							break;
						case 'F'://�� ��ġ ����
							q.add(new Obj(i, j, 0, 0, 'F'));
							break;
						default:
							break;
					}
				}
			}
			if(badMan != null)
				q.add(badMan);
			q.add(antMan);
			
			boolean print = false;
			while(!q.isEmpty()) {
				bfs();
				if(min != Integer.MAX_VALUE) {//��Ʈ�� ����
					System.out.println("#" + tc + " " + min);
					print = true;
					break;
				}
				if(badFirst) {//�Ǵ��� ���� ����
					System.out.println("#" + tc + " " + "-1");
					print = true;
					break;
				}
			}
			if(!print)//�������� �� �� ���� ���
				System.out.println("#" + tc + " " + "-1");
		}
	}

}
