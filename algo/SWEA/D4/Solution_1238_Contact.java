import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_Contact {
	static int[][] map;
	static boolean[] visited;
	static Queue<P> q;
	static int N, ans, maxCnt;
	
	public static class P {
		int num;//��ȣ
		int cnt;//�� ��°�� ���� �������� ����
		public P(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		P cur = q.poll();
		boolean flag = false;
		for(int i = 1; i <= 100; i++) {//���� ������ ��� ã��
			if(map[cur.num][i] == 1 && !visited[i]) {
				visited[i] = true;
				flag = true;
				q.add(new P(i, cur.cnt + 1));
			}
		}
		
		if(!flag) {//���� ������ ����� ���� ���
			if(maxCnt < cur.cnt) {//������ ��� ������Ʈ
				maxCnt = cur.cnt;
				ans = cur.num;
			} else if(maxCnt == cur.cnt && ans < cur.num)
				ans = cur.num;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map = new int[101][101];
			visited = new boolean[101];
			ans = 0;
			maxCnt = 0;
			q = new LinkedList<>();
			
			N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N/2; i++) {//�����迭
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			q.add(new P(start, 0));//���� ���� ����
			visited[start] = true;
			
			while(!q.isEmpty()) {
				bfs();
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}
