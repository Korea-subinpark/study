import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main_15665_N°úM11 {
	static int N, M;
	static int[] arr;
	static int depth;
	static int[] input;
	
	static LinkedHashSet<String> set;
	
	public static void dfs() {
		if(depth == M) {
			String str = "";
			for(int i = 0; i < arr.length; i++)
				str += arr[i] + " ";
			set.add(str);
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
