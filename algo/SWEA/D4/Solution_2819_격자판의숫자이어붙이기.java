import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution_2819 {
	static int[][] arr;
	static HashSet<String> set;
	static int ans;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static boolean inRange(int x, int y) {
		if(x >= 0 && x < 4 && y >= 0 && y < 4)
			return true;
		return false;
	}
	
	public static void dfs(int x, int y, String str, int depth) {
		if(depth == 6) {
			set.add(str);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny)) {
				dfs(nx, ny, str + arr[nx][ny], depth + 1);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			arr = new int[4][4];
			set = new HashSet<>();
			for(int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < 4; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					String str = arr[i][j] + "";
					dfs(i, j, str, 0);
				}
			}
			ans = set.size();
			System.out.println("#" + tc + " " + ans);
		}
		
	}

}
