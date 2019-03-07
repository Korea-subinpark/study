import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 바깥 벽을 -1로 패딩한 뒤에
 * 빈 공간에 대해서 dfs 탐색을 한다
 * 1을 만나면 좌표를 저장하고
 * -1을 만나면 flag 값을 true로 바꾸어 바깥 공기임을 표시한다
 * dfs를 다 돌고 flag 값이 true라면 저장되어 있던 1의 좌표를 모두 0으로 바꾼다
 * 다시 처음부터 치즈가 모두 녹을 때까지 반복한다
 * */
public class Main_1840_치즈 {
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> list;//녹아야 할 치즈 좌표
	static boolean flag;//치즈 안에 있는 빈 공간이면 false
	static int cheese;//치즈 총 개수
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(map[nx][ny] == 0 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			} else if(map[nx][ny] == -1) {//바깥 벽을 만난 경우
				flag = true;
			} else if(map[nx][ny] == 1 && !visited[nx][ny]) {
				int[] temp = {nx, ny};
				visited[nx][ny] = true;
				list.add(temp);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R + 2][C + 2];
		for(int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					cheese++;
			}
		}
		
		//padding
		for(int i = 0; i < R + 2; i++) {
			map[i][0] = -1;
			map[i][C + 1] = -1;
			if(i == 0 || i == R + 1)
				for(int j = 0; j < C + 2; j++)
					map[i][j] = -1;
		}
		
		int cnt = 1;
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				visited = new boolean[R + 2][C + 2];
				if(map[i][j] == 0 && !visited[i][j]) {
					visited[i][j] = true;
					int pre = cheese;//녹이기 이전 치즈 수
					flag = false;
					list = new ArrayList<>();
					dfs(i, j);
					if(!flag) {//빈 공간이 치즈 속에 있다면 list 초기화
						list.clear();
					} else {//바깥 공기라면 치즈 녹이기
						for(int k = 0; k < list.size(); k++) {
							int[] temp = list.get(k);
							map[temp[0]][temp[1]] = 0;//치즈 녹이기
							cheese--;
						}
						list.clear();
					}
					if(cheese <= 0) {
						System.out.print(cnt + "\n" + pre + "\n");
						return;
					} 
					cnt++;
					i = 0;
					j = 0;
				}
			}
		}
	}

}
