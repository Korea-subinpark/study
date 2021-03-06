import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_N��M1 {
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	static int depth;
	
	public static void dfs() {
		if(depth == M) {
			for(int i = 0; i < arr.length; i++)
				sb.append(arr[i]).append(" ");
			sb.append('\n');
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i + 1;
				depth++;
				dfs();
				depth--;
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		sb = new StringBuilder();
		arr = new int[M];
		dfs();
		System.out.println(sb);
	}

}
