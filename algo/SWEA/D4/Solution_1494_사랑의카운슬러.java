import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 벡터의 합을 구한 다음 크기를 구하는 것이기 때문에 순열이 아닌 조합으로 풀어야 제한 시간안에 풀 수 있다
 * visited 배열을 이용하여 절반만 선택한 후에 선택이 완료되면
 * 선택된 배열의 x값의 합, y값의 합을 구하고 선택되지 않은 배열의 x값의 합, y값의 합을 구하여 벡터의 크기를 계산한다
 * */
public class Solution_1494_사랑의카운슬러 {
	static int[][] arr;
	static boolean[] visited;
	static int N;
	static long min;
	
	public static void dfs(int idx, int depth) {
		if(depth == N / 2) {
			long x1 = 0, x2 = 0, y1 = 0, y2 = 0;
			for(int i = 0; i < N; i++) {
				if(visited[i]) {
					x1 += arr[i][0];
					y1 += arr[i][1];
				} else {
					x2 += arr[i][0];
					y2 += arr[i][1];
				}
			}
			long tempX = x1 - x2;
			long tempY = y1 - y2;
			long v = tempX * tempX + tempY * tempY;
			if(min > v)
				min = v;
			return;
		}
		
		for(int i = idx; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][2];
			visited = new boolean[N];
			min = Long.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.print(sb);
	}

}
