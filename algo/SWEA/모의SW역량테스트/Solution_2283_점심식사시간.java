import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2283_���ɽĻ�ð� {
	static int N, min;
	static ArrayList<P> list;
	static ArrayList<S> stair;
	static int[] stairSelect;
	
	static class S {
		int x, y;//��ǥ
		int n;//�������� �� �ɸ��� �ð�
		public S(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	static class P implements Comparable<P> {
		int x, y; //��ǥ
		int stair;//� ������� ���ϴ���
		int dist; //���� ������ �Ÿ�
		int cnt; //���� �̵��� �Ÿ�
		int down; //��ܿ� ������ �� ���� �ð�
		public P (int x, int y, int stair, int dist, int cnt, int down) {
			this.x = x;
			this.y = y;
			this.stair = stair;
			this.dist = dist;
			this.cnt = cnt;
			this.down = down;
		}
		@Override
		public int compareTo(P o) {
			if(cnt == o.cnt)
				return dist - o.dist;
			else
				return cnt - o.cnt;
		}
	}
	
	public static void dfs(int depth) {
		if(depth == list.size()) {//��� ������ �Ϸ�� ���
			solve();
			return;
		}
		
		stairSelect[depth] = 1;//��� ����
		dfs(depth + 1);
		stairSelect[depth] = 0;
		dfs(depth + 1);
	}

	public static void solve() {
		PriorityQueue<P> q = new PriorityQueue<>();
		for(int i = 0; i < stairSelect.length; i++)
			q.add(new P(list.get(i).x, list.get(i).y, stairSelect[i], Math.abs(stair.get(stairSelect[i]).x - list.get(i).x) + Math.abs(stair.get(stairSelect[i]).y - list.get(i).y), 0, 0));
		
		int time = Integer.MAX_VALUE;
		int[] waitPeople = new int[2];//�� ��ܿ��� ��ٸ��� �ִ� ��� ��
		while(!q.isEmpty()) {
			P cur = q.poll();
			time = cur.cnt;
			if(cur.dist > cur.cnt) {//���� ��ܿ� �������� ���� ���
				q.add(new P(cur.x, cur.y, cur.stair, cur.dist, cur.cnt + 1, cur.down));
				continue;
			}
			
			if(cur.down == 0) {//���� �� ��ܿ� ������ ���
				q.add(new P(cur.x, cur.y, cur.stair, cur.dist, cur.cnt + 1, cur.down + 1));
				continue;
			}
			
			if(cur.down == stair.get(cur.stair).n + 1) {//����� �� ������ ���
				waitPeople[cur.stair]--;
				continue;
			}
			
			if(cur.down != 1) {//����� �������� ���� �ܿ�
				q.add(new P(cur.x, cur.y, cur.stair, cur.dist, cur.cnt + 1, cur.down + 1));
				continue;
			}
			
			if(waitPeople[cur.stair] != 3) {//��� �Ա����� ������̾��� ���
				waitPeople[cur.stair]++;
				q.add(new P(cur.x, cur.y, cur.stair, cur.dist, cur.cnt + 1, cur.down + 1));
			} else {//����� �������� ���� ����� �� �� ���
				q.add(new P(cur.x, cur.y, cur.stair, cur.dist, cur.cnt + 1, cur.down));
			}
		}
		if(min > time)
			min = time;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			list = new ArrayList<>();
			stair = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a == 1) //��� ��ġ
						list.add(new P(i, j, 0, 0, 0, 0));
					else if(a != 0)//��� ��ġ
						stair.add(new S(i, j, a));
				}
			}
			
			stairSelect = new int[list.size()];
			dfs(0);
			
			System.out.println("#" + tc + " " + min);
		}
	}

}
