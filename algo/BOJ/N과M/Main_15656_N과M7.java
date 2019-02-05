import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15656_N��M7 {
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	static int depth;
	static int[] input;
	
	public static void dfs() {
		if(depth == M) {
			for(int i = 0; i < arr.length; i++)
				sb.append(arr[i]).append(" ");
			sb.append('\n');
			return;
		}
		for(int i = 0; i < N; i++) {
			arr[depth] = input[i];
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
		input = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < input.length; i++)
			input[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(input);
		sb = new StringBuilder();
		arr = new int[M];
		dfs();
		System.out.println(sb);
	}

}
