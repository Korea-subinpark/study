import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * map의 크기가 [4001][4001]이기 때문에
 * 테스트 케이스마다 map을 재생성하면 런타임 에러가 발생하였다
 * map을 static 변수로 만든 뒤에 한번만 생성하고 모든 테스트케이스에서 재사용하여
 * 런타임 에러를 해결하였다
 * */
public class Solution_5648_원자소멸시뮬레이션 {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] map;
	
	static class Atom {
		int x, y, dir, k;
		boolean crush;
		public Atom(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < 4001 && y >= 0 && y < 4001);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		map = new int[4001][4001];//원자들 번호를 저장
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Atom[] atoms = new Atom[N + 1];//원자들의 정보를 저장
			
			int atomCnt = N;//원자 개수
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken()) * 2 + 2000;
				int y = Integer.parseInt(st.nextToken()) * 2 + 2000;
				map[y][x] = i;
				atoms[i] = new Atom(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			int ans = 0;
			while(atomCnt > 0) {//남은 원자가 없을 때까지
				ArrayList<Atom> crushList = new ArrayList<>();//충돌한 원자들 저장
				
				for(int i = 1; i <= N; i++) {
					if(atoms[i].crush) continue;
					int nx = atoms[i].x + dx[atoms[i].dir];//이동
					int ny = atoms[i].y + dy[atoms[i].dir];
					
					if(!inRange(nx, ny)) {//바깥으로 나간 경우
						if(map[atoms[i].y][atoms[i].x] == i)
							map[atoms[i].y][atoms[i].x] = 0;
						atoms[i].crush = true;
						atomCnt--;
						continue;
					}
					
					if(map[ny][nx] == 0) {//이동하는 곳에 아무 원자도 없는 경우
						if(map[atoms[i].y][atoms[i].x] == i)
							map[atoms[i].y][atoms[i].x] = 0;
						map[ny][nx] = i;
						atoms[i].x = nx;
						atoms[i].y = ny;
 					} else {
 						if(map[ny][nx] > i) {//아직 이동하지 않은 원자인 경우
 							if(map[atoms[i].y][atoms[i].x] == i)
 								map[atoms[i].y][atoms[i].x] = 0;
 							map[ny][nx] = i;
 							atoms[i].x = nx;
 							atoms[i].y = ny;
 						} else {//충돌
 							if(map[atoms[i].y][atoms[i].x] == i)
 								map[atoms[i].y][atoms[i].x] = 0;
 							crushList.add(atoms[i]);
 							crushList.add(atoms[map[ny][nx]]);
 							atoms[i].x = nx;
 							atoms[i].y = ny;
 						}
 					}
				}
				for(int i = 0; i < crushList.size(); i++) {//충돌한 원자들 처리
					Atom temp = crushList.get(i);
					if(!temp.crush) {
						map[temp.y][temp.x] = 0;
						temp.crush = true;
						ans += temp.k;
						atomCnt--;
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

}
