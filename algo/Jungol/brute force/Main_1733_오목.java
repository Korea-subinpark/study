import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1733_오목 {
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < 20 && y >= 0 && y < 20);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[20][20];
		int[] dx = {0, -1, 1, 1};
		int[] dy = {1, 1, 0, 1};
		
		for(int i = 1; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j < 20; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < 20; i++) {
			for(int j = 1; j < 20; j++) {
				if(map[i][j] == 0) continue;
				for(int k = 0; k < 4; k++) {
					int win = map[i][j];
					if(!inRange(i - dx[k], j - dy[k]) || map[i - dx[k]][j - dy[k]] == win)//6목 체크
						continue;
					int nx = i + dx[k];
					int ny = j + dy[k];
					boolean flag = false;
					for(int m = 0; m < 4; m++) {//오목 체크
						if(!inRange(nx, ny) || win != map[nx][ny]) {
							flag = true;
							break;
						}
						nx += dx[k];
						ny += dy[k];
					}
					if(!flag && (!inRange(nx, ny) || map[nx][ny] != win)) {//6목 체크
						System.out.println(win);
						System.out.println(i + " " + j);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

}
