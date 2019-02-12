import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Position {
	int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_1247_최적경로 {
	
	static int N;
	static Position home;
	static Position corp;
	static ArrayList<Position> customer;
	static boolean[] visited;
	static int min;
	
	public static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static void solve(int d, int x, int y, int depth) {
		if(d > min) return;
		if(depth == N) {
			d += dist(x, y, home.x, home.y);
			if(d < min)
				min = d;
			return; 
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				Position p = customer.get(i);
				solve(d + dist(x, y, p.x, p.y), p.x, p.y, depth + 1);
				visited[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			corp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			customer = new ArrayList<>();
			visited = new boolean[N];
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				Position temp = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				customer.add(temp);
			}
			
			solve(0, corp.x, corp.y, 0);
			System.out.println("#" + tc + " " + min);
		}
	}

}
