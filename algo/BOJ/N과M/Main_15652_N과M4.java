import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15652_N°úM4 {
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	static int depth;
	
	public static void dfs() {
		if(depth == M) {
			for(int i = 0; i < arr.length - 1; i++)
				if(arr[i] > arr[i + 1])
					return;
			for(int i = 0; i < arr.length; i++)
				sb.append(arr[i]).append(" ");
			sb.append('\n');
			return;
		}
		for(int i = 0; i < N; i++) {
			arr[depth] = i + 1;
			depth++;
			dfs();
			depth--;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		arr = new int[M];
		dfs();
		System.out.println(sb);
	}

}
