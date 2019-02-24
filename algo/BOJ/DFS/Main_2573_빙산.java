import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * dfs로 빙산의 영역 수를 세면서 각 빙산의 높이와 인접한 바닷물의 수를 저장한다
 * 영역의 수가 두 덩어리 이상일 때 시간을 출력 후 종료하고
 * 한 덩어리 이하일 때 저장했던 각 빙산의 정보를 통해 빙산을 녹인 다음 다시 dfs 탐색을 반복한다
 * 
 * */


public class Main_2573_빙산 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Ice> list;//각 빙산의 좌표와 높이, 인접한 바닷물의 수를 저장하는 큐
	static int num, area, year;//빙산의 수, 빙산 덩어리의 수, 지나간 시간
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static class Ice {
		int x, y, h, num;//좌표와 자신의 높이, 주변 바닷물의 수
		public Ice(int x, int y, int h, int num) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.num = num;
		}
	}
	
	public static boolean inRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M)
			return true;
		return false;
	}
	
	public static void dfs(int x, int y) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny)) {
				if(map[nx][ny] == 0) {
					cnt++;
				} else if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny);
				}
			}
		}
		list.add(new Ice(x, y, map[x][y], cnt));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		list = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0)
					num++;
			}
		}
		
		while(true) {
			for(int i = 0; i < N; i++) {//영역 수 세기
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						visited[i][j] = true;
						dfs(i, j);
						area++;
					}
				}
			}
			
			if(area > 1) {// 두 덩어리 이상일 경우
				System.out.print(year);//출력 후 종료
				return;
			} else {
				year++;
			}
			
			//빙산 녹이기
			while(!list.isEmpty()) {
				Ice temp = list.poll();
				if(temp.h <= temp.num) {//녹아서 사라질 경우
					map[temp.x][temp.y] = 0;
					num--;
				} else {
					map[temp.x][temp.y] = temp.h - temp.num;
				}
			}
			
			if(num <= 1) {//빙산이 다 녹을 때까지 두 덩어리가 될 수 없는 경우
				System.out.print(0);
				return;
			}
			
			area = 0;
			visited = new boolean[N][M];
		}
		
	}

}
