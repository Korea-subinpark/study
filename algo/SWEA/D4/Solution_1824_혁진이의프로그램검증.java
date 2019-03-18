import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1824_혁진이의프로그램검증 {
	static char[][] map;
	static boolean[][][][] visited;//좌표와 메모리와 이동방향에 따른 중복체크
	static int N, M;
	static boolean flag;//종료되었는지 여부
	
	static int[] dx = {-1, 1, 0, 0};//상하좌우
	static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, int memory, int dir) {
		if(flag) return;//이미 종료될 수 있는 경로를 찾은 경우
		if(map[x][y] == '@') {
			flag = true;
			return;
		}
		if(!visited[x][y][memory][dir]) {
			visited[x][y][memory][dir] = true;
			if(map[x][y] == '?') {
				visited[x][y][memory][0] = true;//물음표는 4방향 모두 중복체크가 필요
				visited[x][y][memory][1] = true;
				visited[x][y][memory][2] = true;
				visited[x][y][memory][3] = true;
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					//범위를 벗어나는지 확인
					if(nx == -1)
						nx = N - 1;
					else if(nx == N)
						nx = 0;
					else if(ny == -1)
						ny = M - 1;
					else if(ny == M)
						ny = 0;
					dfs(nx, ny, memory, i);
				}
			} else {
				switch(map[x][y]) {
				case '<':
					dir = 2;
					break;
				case '>':
					dir = 3;
					break;
				case '^':
					dir = 0;
					break;
				case 'v':
					dir = 1;
					break;
				case '_':
					if(memory == 0)
						dir = 3;
					else
						dir = 2;
					break;
				case '|':
					if(memory == 0)
						dir = 1;
					else
						dir = 0;
					break;
				case '+':
					if(memory == 15)
						memory = 0;
					else
						memory++;
					break;
				case '-':
					if(memory == 0)
						memory = 15;
					else
						memory--;
					break;
				case '.':
					break;
				default://숫자인 경우
					memory = map[x][y] - '0';
					break;
				}
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				//범위를 벗어나는지 확인
				if(nx == -1)
					nx = N - 1;
				else if(nx == N)
					nx = 0;
				else if(ny == -1)
					ny = M - 1;
				else if(ny == M)
					ny = 0;
				dfs(nx, ny, memory, dir);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			flag = false;
			
			map = new char[N][M];
			visited = new boolean[N][M][16][4];
			
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			
			dfs(0, 0, 0, 3);
			
			if(flag)//종료가 된 경우
				System.out.println("#" + tc + " " + "YES");
			else//무한루프가 도는 경우
				System.out.println("#" + tc + " " + "NO");
		}
	}

}