import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2462_키순서 {
	static int N;
	static int[][] comp;
	
	public static void dfs(int start, int idx) {
		for(int i = 1; i <= N; i++) {
			if(i != idx && comp[idx][i] == 2 && comp[start][i] == 0) {
				if(comp[start][idx] == 2) {
					comp[start][i] = 2;
					comp[i][start] = 1;
					dfs(start, i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		comp = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			comp[a][b] = 2;//b가 a보다 클 때 comp[a][b] = 2
			comp[b][a] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j && comp[i][j] == 2) {
					dfs(i, j);
				}
			}
		}
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			boolean flag = false;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(comp[i][j] == 0) {//i와 j의 비교가 불가능한 경우
					flag = true;
					break;
				}
			}
			if(!flag)//모든 정점과 비교가 가능한 경우
				ans++;
		}
		
		System.out.println(ans);
	}

}
