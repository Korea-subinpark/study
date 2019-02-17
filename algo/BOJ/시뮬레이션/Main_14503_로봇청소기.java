import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503 {
	
	static int N, M;
	static int x, y, dir;//로봇 청소기의 위치와 방향
	static boolean flag;//4방향을 회전하며 청소할 곳을 찾았는지 확인하는 flag
	
	static int[] dx = {-1, 0, 1, 0};//북, 서, 남, 동
	static int[] dy = {0, -1, 0, 1};

	public static boolean inRange(int i, int j) {//로봇 청소기가 범위 안에 있는지 확인
		if(i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
	}
	
	public static int solve(int[][] arr) {
		int ans = 1;//청소한 구역 수
		//1. 현재 위치를 청소한다.
		arr[x][y] = 2;
		while(true) {
			flag = false;
			
			//2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
			for(int i = 1; i <= 4; i++) {
				int nx = x + dx[(dir + i) % 4];
				int ny = y + dy[(dir + i) % 4];
				
				if(inRange(nx, ny)) {
					if(arr[nx][ny] != 0)//2-1. 왼쪽에 청소할 공간이 없다면 그 다시 회전
						continue;
					if(arr[nx][ny] == 0) {//2-2. 청소할 공간이 있다면 flag값 변경 후 회전하고 청소
						flag = true;
						x = nx;
						y = ny;
						dir = (dir + i) % 4;
						arr[x][y] = 2;
						ans++;
						break;
					}
				}
			}
			
			if(flag == false) {//2-3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에, 후진
				int nx = x + dx[(dir + 2) % 4];
				int ny = y + dy[(dir + 2) % 4];
				if(inRange(nx, ny) && arr[nx][ny] != 1) {
					x = nx;
					y = ny;
					continue;
				}
				if(!inRange(nx, ny) || arr[nx][ny] == 1)//2-4. 후진을 못하는 경우 종료
					break;
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		if(dir == 1)//왼쪽으로 회전하기 쉽게 동쪽과 서쪽 index교환
			dir = 3;
		else if(dir == 3)
			dir = 1;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve(arr));
	}

}
