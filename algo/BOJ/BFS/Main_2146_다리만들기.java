import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
/*
DFS로 섬에서 바다와 맞닿는 부분의 좌표를 Queue에 저장하고
다시 BFS로 다른 섬까지의 거리를 구하였다
처음엔 DFS를 재귀함수로 구현하고 visited배열도 각각 만들었지만
메모리 낭비가 심해서 섬에 번호를 붙여서 섬 전체를 그 번호로 바꾸는 방법으로 구현하였다.
*/
public class Main_2146_다리만들기 {

	static int[][] arr;
	static int islandNum = 2;//각 섬의 번호
	static Queue<Position> q;//바다와 맞닿은 육지 좌표를 저장 후 bfs
	static Stack<Position> s;//dfs를 위한 stack
	static boolean[][] visited;//bfs 중복 체크
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int min = Integer.MAX_VALUE;
	
	static public class Position {
		int x, y, len;//좌표와 현재까지 다리 길이
		public Position(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	public static void dfs() {
		Position temp = s.pop();//stack에서 좌표 하나를 pop
		for(int i = 0; i < 4; i++) {//주변 순회
			int nx = temp.x + dx[i];
			int ny = temp.y + dy[i];
			arr[temp.x][temp.y] = islandNum;//섬에 번호 부여
			if(arr[nx][ny] == 1) {//같은 섬일 경우 좌표를 stack에 push
				s.push(new Position(nx, ny, 0));
			} else if(!visited[temp.x][temp.y] && arr[nx][ny] == 0) {//바다를 만났을 경우 Queue에 add
				visited[temp.x][temp.y] = true;
				q.add(new Position(temp.x, temp.y, 0));
			}
		}
	}
	
	public static void bfs(int x, int y, int len) {
		if(min < len)//현재까지 구한 최소값보다 클 경우 백트래킹
			return;
		for(int i = 0; i < 4; i++) {//주변 순회
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(arr[nx][ny] == 1) {//육지를 만났을 경우
				if(min > len) //최소값 업데이트
					min = len;
				return;
			} else if(!visited[nx][ny] && arr[nx][ny] == 0) {//계속 바다일 경우 
				visited[nx][ny] = true;
				q.add(new Position(nx, ny, len + 1));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];
		q = new LinkedList<>();
		s = new Stack<>();
		
		//input
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//padding
		for(int i = 0; i < N + 2; i++) {
			arr[i][0] = -1;
			arr[i][N + 1] = -1;
			arr[0][i] = -1;
			arr[N + 1][i] = -1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] == 1) {
					s.push(new Position(i, j, 0));
					while(!s.isEmpty()) {//dfs로 하나의 섬에 대하여 바다와 맞닿은 곳의 좌표를 구한다.
						dfs();
					}

					while(!q.isEmpty()) {//바다와 맞닿은 곳의 좌표부터 다른 섬을 만날 때까지 bfs
						Position temp = q.poll();
						bfs(temp.x, temp.y, temp.len);
					}
					islandNum++;//섬 번호 증가
				}
			}
		}
		System.out.println(min);//결과 출력
	}


}
