import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1824_�����������α׷����� {
	static char[][] map;
	static boolean[][][][] visited;//��ǥ�� �޸𸮿� �̵����⿡ ���� �ߺ�üũ
	static int N, M;
	static boolean flag;//����Ǿ����� ����
	
	static int[] dx = {-1, 1, 0, 0};//�����¿�
	static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, int memory, int dir) {
		if(flag) return;//�̹� ����� �� �ִ� ��θ� ã�� ���
		if(map[x][y] == '@') {
			flag = true;
			return;
		}
		if(!visited[x][y][memory][dir]) {
			visited[x][y][memory][dir] = true;
			if(map[x][y] == '?') {
				visited[x][y][memory][0] = true;//����ǥ�� 4���� ��� �ߺ�üũ�� �ʿ�
				visited[x][y][memory][1] = true;
				visited[x][y][memory][2] = true;
				visited[x][y][memory][3] = true;
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					//������ ������� Ȯ��
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
				default://������ ���
					memory = map[x][y] - '0';
					break;
				}
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				//������ ������� Ȯ��
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
			
			if(flag)//���ᰡ �� ���
				System.out.println("#" + tc + " " + "YES");
			else//���ѷ����� ���� ���
				System.out.println("#" + tc + " " + "NO");
		}
	}

}