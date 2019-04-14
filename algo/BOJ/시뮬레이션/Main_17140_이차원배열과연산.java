import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140_�������迭������ {
	static int r, c, k;
	static int maxR, maxC;
	static int[][] map;
	
	static class P implements Comparable<P> {
		int num, cnt;
		public P(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(P o) {
			if(cnt == o.cnt) {
				return num - o.num;
			} else
				return cnt - o.cnt;
		}
	}
	
	public static void change(boolean flag) {
		int update = 0;
		if(flag) {//R����
			for(int i = 1; i <= maxR; i++) {
				PriorityQueue<P> q = new PriorityQueue<>();
				int[] cnt = new int[200];
				for(int j = 1; j <= maxC; j++) {//���� 0���� �ʱ�ȭ�ϸ� ����ī��Ʈ
					cnt[map[i][j]]++;
					map[i][j] = 0;
				}
				for(int j = 1; j < cnt.length; j++) {//�켱���� ť�� ���� ����
					if(cnt[j] != 0)
						q.add(new P(j, cnt[j]));
				}
				int m = 0;
				while(!q.isEmpty()) {//�� ����
					P cur = q.poll();
					map[i][++m] = cur.num;
					map[i][++m] = cur.cnt;
					if(m == 100)
						break;
				}
				if(update < m)//�ִ� �� ������Ʈ
					update = m;
			}
			maxC = update;//�ִ� �� ������Ʈ
			
		} else {//C����
			for(int i = 1; i <= maxC; i++) {
				PriorityQueue<P> q = new PriorityQueue<>();
				int[] cnt = new int[200];
				for(int j = 1; j <= maxR; j++) {
					cnt[map[j][i]]++;
					map[j][i] = 0;
				}
				for(int j = 1; j < cnt.length; j++) {
					if(cnt[j] != 0)
						q.add(new P(j, cnt[j]));
				}
				int m = 0;
				while(!q.isEmpty()) {
					P cur = q.poll();
					map[++m][i] = cur.num;
					map[++m][i] = cur.cnt;
					if(m == 100)
						break;
				}
				if(update < m)
					update = m;
			}
			maxR = update;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[101][101];
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		maxR = 3;
		maxC = 3;
		int ans = -1;
		
		for(int time = 0; time <= 100; time++) {
			if(map[r][c] == k) {//���� ���Դ��� Ȯ��
				ans = time;
				break;
			}
			if(maxR >= maxC) {//�ִ� ��� ���� ���� ���� ����
				change(true);
			} else {
				change(false);
			}
			
		}
		
		System.out.println(ans);
	}
}