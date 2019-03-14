import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_�Ʊ��� {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int exp, level = 2;
	static int ans;
	static Queue<P> q;
	static ArrayList<P> list;//���� �� �ִ� ����� ����Ʈ
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static class P implements Comparable<P> {
		int r, c, cnt;
		public P(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(P o) {
			if(cnt != o.cnt)//�Ÿ��� ����� ��
				return cnt - o.cnt;
			if(r != o.r)//���ʿ� �ִ� ��
				return r - o.r;
			return c - o.c;//���ʿ� �ִ� ��
		}
	}
	
	public static void bfs() {
		P cur = q.poll();
		for(int i = 0; i < 4; i++) {
			int nr = cur.r + dr[i];
			int nc = cur.c + dc[i];
			if(inRange(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] <= level)) {
				visited[nr][nc] = true;//����ְų� �ڽ��� ũ�⺸�� �۰ų� ���� ��� �̵�
				q.add(new P(nr, nc, cur.cnt + 1));
				if(map[nr][nc] < level && map[nr][nc] != 0)//�ڽ��� ũ�⺸�� �� ���� ��� ���� �� �ִ� ����� ����Ʈ�� ����
					list.add(new P(nr, nc, cur.cnt + 1));
			}
		}
	}
	
	public static boolean inRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		q = new LinkedList<>();
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					q.add(new P(i, j, 0));
					visited[i][j] = true;
					map[i][j] = 0;
				}
			}
		}
		while(true) {
			while(!q.isEmpty()) {//���� �� �ִ� ����� ã��
				bfs();
			}
			if(list.size() == 0)//�� �̻� ���� �� �ִ� ����Ⱑ ���� ���
				break;
			Collections.sort(list);//�Ա� ���� ����� ������ ����
			P fish = list.get(0);//���� ù ��° ����� �Ա�
			map[fish.r][fish.c] = 0;
			exp++;//����ġ ����
			if(exp == level) {//����ġ�� ������ ������ ������
				level++;
				exp = 0;
			}
			ans += fish.cnt;//�̵��Ÿ� ����
			visited = new boolean[N][N];//���� ����� ��ġ���� �ٽ� ����
			visited[fish.r][fish.c] = true;
			q.add(new P(fish.r, fish.c, 0));
			list.clear();
		}
		System.out.println(ans);
	}
}
