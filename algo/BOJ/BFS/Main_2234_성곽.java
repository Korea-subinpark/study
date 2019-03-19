import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2234_���� {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<P> q;
	static int ans1, ans2, ans3;
	
	public static class P {
		int x, y;
		public P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
	
	public static void bfs(boolean flag) {
		int cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			P cur = q.poll();
			int val = map[cur.x][cur.y];
			if((val & 1) == 0) {//����
				if(inRange(cur.x, cur.y - 1) && !visited[cur.x][cur.y - 1]) {
					visited[cur.x][cur.y - 1] = true;
					q.add(new P(cur.x, cur.y - 1));
				}
			}
			val = val >> 1;
			if((val & 1) == 0) {//����
				if(inRange(cur.x - 1, cur.y) && !visited[cur.x - 1][cur.y]) {
					visited[cur.x - 1][cur.y] = true;
					q.add(new P(cur.x - 1, cur.y));
				}
			}
			val = val >> 1;
			if((val & 1) == 0) {//����
				if(inRange(cur.x, cur.y + 1) && !visited[cur.x][cur.y + 1]) {
					visited[cur.x][cur.y + 1] = true;
					q.add(new P(cur.x, cur.y + 1));
				}
			}
			val = val >> 1;
			if((val & 1) == 0) {//����
				if(inRange(cur.x + 1, cur.y) && !visited[cur.x + 1][cur.y]) {
					visited[cur.x + 1][cur.y] = true;
					q.add(new P(cur.x + 1, cur.y));
				}
			}
			
		}
		if(!flag && ans2 < cnt)//���� ������ ���� ���
			ans2 = cnt;
		if(flag && ans3 < cnt)//�� �ϳ��� ���� ���
			ans3 = cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {//���� ������ �ʴ� ��� �ִ� ��ũ�⸦ ���Ѵ�
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					ans1++;
					visited[i][j] = true;
					q.add(new P(i, j));
					bfs(false);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {//���� �ϳ� ���� ��� �ִ� ��ũ�⸦ ���Ѵ�
			for(int j = 0; j < M; j++) {
				int cur = map[i][j];
				for(int k = 0; k < 4; k++) {
					if((cur & 1) == 1) {
						visited = new boolean[N][M];
						map[i][j] -= Math.pow(2, k);//�� ���ֱ�
						visited[i][j] = true;
						q.add(new P(i, j));
						bfs(true);
						map[i][j] += Math.pow(2, k);//�� ���󺹱�
					}
					cur = cur >> 1;
				}
			}
		}
		
		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
	}

}
