import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873_배틀필드_박수빈 {
	static int H, W, L;//맵의 Height, Width, 명령문의 길이
	static char[][] map;
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	public static class Tank {//전차의 현재 위치와 방향 저장
		int x, y;
		char dir;
		public Tank(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static boolean inRange(int x, int y) {//범위 안에 있는 지 확인
		if(x >= 0 && x < H && y >= 0 && y < W)
			return true;
		return false;
	}
	
	public static void go(Tank t) {//탱크를 한 칸 이동시키는 함수
		map[t.x][t.y] = t.dir;
		int dir = 0;
		switch(t.dir) {
			case '^':
				dir = 0;
				break;
			case 'v':
				dir = 1;
				break;
			case '<':
				dir = 2;
				break;
			case '>':
				dir = 3;
				break;
			default:
				break;
		}
		int nx = t.x + dx[dir];
		int ny = t.y + dy[dir];
		if(inRange(nx, ny) && map[nx][ny] == '.') {
			map[t.x][t.y] = '.';
			map[nx][ny] = t.dir;
			t.x = nx;
			t.y = ny;
		}
	}
	
	public static void shoot(Tank t, int shootDir) {//포탄을 쐈을 때
		int nx = t.x + dx[shootDir];
		int ny = t.y + dy[shootDir];
		while(inRange(nx, ny)) {//범위를 벗어날 때까지 바라보고 있는 방향에 벽이 있는지 확인
			if(map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			} else if(map[nx][ny] == '#')
				break;
			nx += dx[shootDir];//벽이 아닐 경우 계속 전진
			ny += dy[shootDir];
		}
	}
	
	public static void simulation(String s, Tank t) {
		for(int i = 0; i < L; i++) {//명령문 길이만큼 반복
			switch(s.charAt(i)) {
				case 'U'://위로 이동
					t.dir = '^';
					go(t);
					break;
				case 'D'://아래로 이동
					t.dir = 'v';
					go(t);
					break;
				case 'L'://왼쪽으로 이동
					t.dir = '<';
					go(t);
					break;
				case 'R'://오른쪽으로 이동
					t.dir = '>';
					go(t);
					break;
					
				case 'S'://포탄 발사
					int shootDir = 0;
					switch(t.dir) {
						case '^':
							shootDir = 0;
							shoot(t, shootDir);
							break;
						case 'v':
							shootDir = 1;
							shoot(t, shootDir);
							break;
						case '<':
							shootDir = 2;
							shoot(t, shootDir);
							break;
						case '>':
							shootDir = 3;
							shoot(t, shootDir);
							break;
						default:
							break;
					}
				default:
					break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			Tank t = null;
			
			for(int i = 0; i < H; i++) {
				String row = br.readLine();
				for(int j = 0; j < W; j++) {
					map[i][j] = row.charAt(j);
					if(map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v')
						t = new Tank(i, j, map[i][j]);//탱크의 현재 위치와 방향 저장
				}
			}
			
			L = Integer.parseInt(br.readLine());
			String s = br.readLine();
			//시뮬레이션 진행
			simulation(s, t);
			
			//결과 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
	}
}
