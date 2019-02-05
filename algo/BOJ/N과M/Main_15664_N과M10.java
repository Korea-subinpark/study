import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main_15664_N°úM10 {
	static boolean[] visited;
	static int N, M;
	static int[] arr;
	static int depth;
	static int[] input;
	
	static LinkedHashSet<String> set;
	
	public static void dfs() {
		if(depth == M) {
			for(int i = 0; i < arr.length - 1; i++)
				if(arr[i] > arr[i + 1]) return;
			String str = "";
			for(int i = 0; i < arr.length; i++)
				str += arr[i] + " ";
			set.add(str);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = input[i];
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
		visited = new boolean[N];
		input = new int[N];
		set = new LinkedHashSet<>();
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < input.length; i++)
			input[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(input);
		arr = new int[M];
		
		
		dfs();
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
			sb.append(it.next()).append('\n');
		System.out.print(sb);
	}

}
