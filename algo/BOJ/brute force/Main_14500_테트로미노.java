import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_��Ʈ�ι̳� {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
	
	public static void dfs(int x, int y, int cnt, int sum) {
		if(cnt == 3) {//�� 4���� ������ ��
			if(max < sum)//�ִ� ����
				max = sum;
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
		if(cnt != 0)//ù ��° ���� ��쿡�� ��� ����
			return;
		int temp = sum;//�Ǹ���� Ž��
		if(inRange(x + dx[0], y + dy[0]) && inRange(x + dx[2], y + dy[2]) && inRange(x + dx[3], y + dy[3])) {
			temp += map[x + dx[0]][y + dy[0]];
			temp += map[x + dx[2]][y + dy[2]];
			temp += map[x + dx[3]][y + dy[3]];
			if(max < temp)
				max = temp;
		}
		if(inRange(x + dx[1], y + dy[1]) && inRange(x + dx[2], y + dy[2]) && inRange(x + dx[3], y + dy[3])) {
			temp = sum;
			temp += map[x + dx[1]][y + dy[1]];
			temp += map[x + dx[2]][y + dy[2]];
			temp += map[x + dx[3]][y + dy[3]];
			if(max < temp)
				max = temp;
		}
		if(inRange(x + dx[2], y + dy[2]) && inRange(x + dx[0], y + dy[0]) && inRange(x + dx[1], y + dy[1])) {
			temp = sum;
			temp += map[x + dx[2]][y + dy[2]];
			temp += map[x + dx[0]][y + dy[0]];
			temp += map[x + dx[1]][y + dy[1]];
			if(max < temp)
				max = temp;
		}
		if(inRange(x + dx[0], y + dy[0]) && inRange(x + dx[1], y + dy[1]) && inRange(x + dx[3], y + dy[3])) {
			temp = sum;
			temp += map[x + dx[3]][y + dy[3]];
			temp += map[x + dx[0]][y + dy[0]];
			temp += map[x + dx[1]][y + dy[1]];
			if(max < temp)
				max = temp;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {//��� �������� ���Ͽ� �� 4���� ���Ѵ�
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}

}
