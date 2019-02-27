import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int[][] map = new int[V][V];
		for(int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			map[s][e] = 1;
			map[e][s] = 1;
		}
		
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				if(map[i][j] != 1 && i != j)
					map[i][j] = 100000;
			}
		}
		
		for(int k = 0; k < V; k++) {
			for(int i = 0; i < V; i++) {
				if(k == i) continue;
				for(int j = 0; j < V; j++) {
					if(k == j || i == j) continue;
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
		
		int cnt = 0;
		for(int i = 1; i < V; i++) {
			if(map[0][i] != 100000)
				cnt++;
		}
		
		System.out.println(cnt);
	}

}
