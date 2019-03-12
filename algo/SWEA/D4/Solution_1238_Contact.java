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
		int num;//번호
		int cnt;//몇 번째로 받은 연락인지 저장
		public P(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		P cur = q.poll();
		boolean flag = false;
		for(int i = 1; i <= 100; i++) {//연락 가능한 사람 찾기
			if(map[cur.num][i] == 1 && !visited[i]) {
				visited[i] = true;
				flag = true;
				q.add(new P(i, cur.cnt + 1));
			}
		}
		
		if(!flag) {//다음 연락할 사람이 없는 경우
			if(maxCnt < cur.cnt) {//마지막 사람 업데이트
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
			for(int i = 0; i < N/2; i++) {//인접배열
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			q.add(new P(start, 0));//시작 지점 저장
			visited[start] = true;
			
			while(!q.isEmpty()) {
				bfs();
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}
