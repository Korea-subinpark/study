import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140_이차원배열과연산 {
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
		if(flag) {//R연산
			for(int i = 1; i <= maxR; i++) {
				PriorityQueue<P> q = new PriorityQueue<>();
				int[] cnt = new int[101];
				for(int j = 1; j <= maxC; j++) {//맵을 0으로 초기화하며 숫자카운트
					cnt[map[i][j]]++;
					map[i][j] = 0;
				}
				for(int j = 1; j < cnt.length; j++) {//우선순위 큐에 페어로 저장
					if(cnt[j] != 0)
						q.add(new P(j, cnt[j]));
				}
				int m = 0;
				while(!q.isEmpty()) {//맵 수정
					P cur = q.poll();
					map[i][++m] = cur.num;
					map[i][++m] = cur.cnt;
					if(m == 100)
						break;
				}
				if(update < m)//최대 열 업데이트
					update = m;
			}
			maxC = update;//최대 열 업데이트
			
		} else {//C연산
			for(int i = 1; i <= maxC; i++) {
				PriorityQueue<P> q = new PriorityQueue<>();
				int[] cnt = new int[101];
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
			if(map[r][c] == k) {//값이 나왔는지 확인
				ans = time;
				break;
			}
			if(maxR >= maxC) {//최대 행과 열에 따라 연산 수행
				change(true);
			} else {
				change(false);
			}
			
		}
		
		System.out.println(ans);
	}
}
