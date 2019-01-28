import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
bfs로 자신 주변에 0이 있을 경우 자신의 값에 +1 을 하면 depth를 알 수 있다
depth를 계속 변수에 저장하여 마지막에 출력한다
*/

public class Main_7576_토마토 {
	static int[][] arr;
	static boolean[][] visited;//방문 여부
	static Queue<int[]> q;//queue에 방문할 곳의 xy좌표 저장
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int ans;//토마토가 모두 익을 때까지의 최소 날짜
	static int cnt;//안 익은 토마토 개수
	
	public static void bfs() {
		int[] temp = q.poll();
		int x = temp[0];
		int y = temp[1];
		if(!visited[x][y]) {//방문하지 않았을 경우
			
			visited[x][y] = true;
			for(int i = 0; i < 4; i++) {//주변 순회
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(arr[nx][ny] == 0) {//토마토가 있는 경우
					arr[nx][ny] = arr[x][y] + 1;
					ans = arr[nx][ny] - 1;
					int[] nxy = {nx, ny};
					q.add(nxy);
					cnt--;//안 익은 토마토 개수 감소
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[M + 2][N + 2];
		visited = new boolean[M + 1][N + 1];
		q = new LinkedList<>();
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0)
					cnt++;//안 익은 토마토 개수 증가
			}
		}
		
		for(int i = 0; i < M + 2; i++) { //padding
			arr[i][0] = -1;
			arr[i][N + 1] = -1;
		}
		
		for(int i = 0; i < N + 2; i++) { //padding
			arr[0][i] = -1;
			arr[M + 1][i] = -1;
		}
		
		
		for(int i = 1; i <= M; i++) {
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] == 1) {//초기 다 익은 토마토 위치 enqueue
					int[] temp = {i,j};
					q.add(temp);
				}
			}
		}
		
		while(!q.isEmpty()) {
			bfs();
		}
		
		if(cnt > 0)//토마토가 모두 익지 못하는 상황
			System.out.println(-1);
		else//모두 익어있는 상태
			System.out.println(ans);
		
	}

}
