import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2146_�ٸ������ {

	static int[][] arr;
	static int islandNum = 2;//�� ���� ��ȣ
	static Queue<Position> q;//�ٴٿ� �´��� ���� ��ǥ�� ���� �� bfs
	static Stack<Position> s;//dfs�� ���� stack
	static boolean[][] visited;//bfs �ߺ� üũ
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int min = Integer.MAX_VALUE;
	
	static public class Position {
		int x, y, len;//��ǥ�� ������� �ٸ� ����
		public Position(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	public static void dfs() {
		Position temp = s.pop();
		for(int i = 0; i < 4; i++) {
			int nx = temp.x + dx[i];
			int ny = temp.y + dy[i];
			arr[temp.x][temp.y] = islandNum;
			if(arr[nx][ny] == 1) {
				s.push(new Position(nx, ny, 0));
			} else if(!visited[temp.x][temp.y] && arr[nx][ny] == 0) {
				visited[temp.x][temp.y] = true;
				q.add(new Position(temp.x, temp.y, 0));
			}
		}
	}
	
	public static void bfs(int x, int y, int len) {
		if(min < len)
			return;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(arr[nx][ny] == 1) {
				if(min > len) 
					min = len;
				return;
			} else if(!visited[nx][ny] && arr[nx][ny] == 0) {
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
					while(!s.isEmpty()) {
						dfs();
					}
					
					while(!q.isEmpty()) {
						Position temp = q.poll();
						bfs(temp.x, temp.y, temp.len);
					}
					islandNum++;
				}
			}
		}
		
		System.out.println(min);
	}


}
