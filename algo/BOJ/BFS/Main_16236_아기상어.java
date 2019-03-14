import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int exp, level = 2;
	static int ans;
	static Queue<P> q;
	static ArrayList<P> list;//먹을 수 있는 물고기 리스트
	
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
			if(cnt != o.cnt)//거리가 가까운 순
				return cnt - o.cnt;
			if(r != o.r)//위쪽에 있는 순
				return r - o.r;
			return c - o.c;//왼쪽에 있는 순
		}
	}
	
	public static void bfs() {
		P cur = q.poll();
		for(int i = 0; i < 4; i++) {
			int nr = cur.r + dr[i];
			int nc = cur.c + dc[i];
			if(inRange(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] <= level)) {
				visited[nr][nc] = true;//비어있거나 자신의 크기보다 작거나 같은 경우 이동
				q.add(new P(nr, nc, cur.cnt + 1));
				if(map[nr][nc] < level && map[nr][nc] != 0)//자신의 크기보다 더 작은 경우 먹을 수 있는 물고기 리스트에 저장
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
			while(!q.isEmpty()) {//먹을 수 있는 물고기 찾기
				bfs();
			}
			if(list.size() == 0)//더 이상 먹을 수 있는 물고기가 없는 경우
				break;
			Collections.sort(list);//먹기 쉬운 물고기 순으로 정렬
			P fish = list.get(0);//가장 첫 번째 물고기 먹기
			map[fish.r][fish.c] = 0;
			exp++;//경험치 증가
			if(exp == level) {//경험치가 레벨과 같으면 레벨업
				level++;
				exp = 0;
			}
			ans += fish.cnt;//이동거리 증가
			visited = new boolean[N][N];//먹은 물고기 위치부터 다시 시작
			visited[fish.r][fish.c] = true;
			q.add(new P(fish.r, fish.c, 0));
			list.clear();
		}
		System.out.println(ans);
	}
}
