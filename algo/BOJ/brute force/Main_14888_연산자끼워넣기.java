import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main_14888_연산자끼워넣기 {
	static int N, min, max;
	static int[] operator;
	static int[] num;
	static boolean[] visited;
	
	public static void init(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		num = new int[N];
		operator = new int[4];
		visited = new boolean[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++)//숫자
			num[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++)//연산자 개수
			operator[i] = Integer.parseInt(st.nextToken());
	}
	
	public static void dfs(int depth, int result) {
		if(depth == N - 1) {//계산이 끝났을 때
			if(min > result)
				min = result;
			if(max < result)
				max = result;
			return;
		}
		
		
		for(int i = 0; i < 4; i++) {//depth마다 연산자를 각각 한번씩만 써본다
			if(operator[i] != 0) {
				operator[i]--;
				if(i == 0)
					dfs(depth + 1, result + num[depth + 1]);
				else if(i == 1)
					dfs(depth + 1, result - num[depth + 1]);
				else if(i == 2)
					dfs(depth + 1, result * num[depth + 1]);
				else if(i == 3)
					dfs(depth + 1, result / num[depth + 1]);
				operator[i]++;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        init(br);
        dfs(0, num[0]);
        System.out.println(max);
        System.out.println(min);
	}
}
